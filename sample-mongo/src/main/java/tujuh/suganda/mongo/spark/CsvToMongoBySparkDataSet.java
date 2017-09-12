package tujuh.suganda.mongo.spark;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
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

public class CsvToMongoBySparkDataSet implements Serializable {
	public static void main(String[] args) throws IOException {

		SparkConf sparkConf = new SparkConf().setAppName("SparkCsvReader").setMaster("local[2]")
				.set("spark.mongodb.output.uri", "mongodb://127.0.0.1/mydb");

		JavaSparkContext jsc = new JavaSparkContext(sparkConf);
		final SQLContext sqlContext = new SQLContext(jsc);
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.ERROR);

		JavaRDD<String> dataRdd = jsc.textFile("src/main/resources/exampledata.csv")
				.map(new Function<String, String>() {
					@Override
					public String call(String value) throws Exception {
						String json = "";
						try {
							String[] data = value.split(",");
							MyModel model = new MyModel(data[0], data[0], data[1], Integer.valueOf(data[2]), data[3],
									Integer.valueOf(data[4]), Integer.valueOf(data[5]), Integer.valueOf(data[6]),
									data[7], data[8], Integer.valueOf(data[9]), data[10], data[11]);
							json = CsvToMongoBySparkDataSet.jsonParse(model);
						} catch (Exception e) {
						}
						return json;
					}
				});

		Dataset<Row> myDs = sqlContext.jsonRDD(dataRdd).distinct();
		// Upsert
		MongoSpark.write(myDs).option("collection", "data").mode("overwrite").save();
		jsc.close();
	}

	public static String jsonParse(Object value) throws JsonProcessingException {
		ObjectMapper oMap = new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		oMap.setSerializationInclusion(Include.NON_NULL);
		String newJson = oMap.writeValueAsString(value);
		return newJson;
	}
}
