package tujuh.suganda.mongo.spark;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.bson.Document;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.spark.MongoSpark;
import com.mongodb.spark.config.WriteConfig;
import com.mongodb.util.JSON;
import tujuh.suganda.mongo.model.MyModel;

public class SparkMongoCsvDoc implements Serializable {
	public static void main(String[] args) throws IOException {
		SparkSession spark = SparkSession.builder().master("local").appName("MongoSparkSample")
							.config("spark.mongodb.output.uri", "mongodb://127.0.0.1/mydb.data").getOrCreate();

		JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.ERROR);


		
		JavaRDD<Document> dataRdd = jsc.textFile("src/main/resources/exampledata.csv")
				.map(new Function<String, Document>() {
					@Override
					public Document call(String value) throws Exception {
						String json = "";
						MyModel model = null;
						String[] data = value.split(",");
						model = new MyModel(data[0], data[0], data[1], Integer.valueOf(data[2]), data[3],
								Integer.valueOf(data[4]), Integer.valueOf(data[5]), Integer.valueOf(data[6]), data[7],
								data[8], Integer.valueOf(data[9]), data[10], data[11]);
						json = SparkMongoCsvDoc.jsonParse(model);
						return new Document((BasicDBObject) JSON.parse(json));
					}
				});

		
		System.out.println("==================");
		Map<String, String> writeOverrides = new HashMap<String, String>();
		writeOverrides.put("database", "mydb");
		writeOverrides.put("collection", "data");
		writeOverrides.put("replaceDocument", "true");
		writeOverrides.put("writeConcern.w", "1");
		WriteConfig writeConfig = WriteConfig.create(jsc).withOptions(writeOverrides);

		MongoSpark.save(dataRdd, writeConfig);
		// dataRdd.first();
		jsc.close();
	}

	public static String jsonParse(Object value) throws JsonProcessingException {
		ObjectMapper oMap = new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		oMap.setSerializationInclusion(Include.NON_NULL);
		String newJson = oMap.writeValueAsString(value);
		return newJson;
	}
}
