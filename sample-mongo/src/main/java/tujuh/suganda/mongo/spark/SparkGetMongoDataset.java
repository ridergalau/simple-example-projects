package tujuh.suganda.mongo.spark;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.mongodb.spark.MongoSpark;

import tujuh.suganda.mongo.model.MyModel;

public class SparkGetMongoDataset {
	public static void main(String[] args) {
		SparkSession spark = SparkSession.builder().master("local").appName("SimpleMongoSparkConnector")
				.config("spark.mongodb.input.uri", "mongodb://127.0.0.1/mydb.data")
				.config("spark.mongodb.output.uri", "mongodb://127.0.0.1/mydb.data").getOrCreate();

		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.ERROR);

		JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());

		/*
		 * Dataset<Row> myDs = MongoSpark.load(jsc).toDF(); myDs.printSchema();
		 * myDs.show();
		 */

		Dataset<MyModel> myDsModel = MongoSpark.load(jsc).toDS(MyModel.class);
		myDsModel.printSchema();
		myDsModel.show();

		System.out.println("===================================================================");
		myDsModel.createOrReplaceTempView("mydata");
		Dataset<Row> query = spark.sql("SELECT latitude, longitude ,sale_date FROM mydata");
		query.show(7);

		// loop Dataset
		query.foreach((ForeachFunction<Row>) row -> System.out.println("-> " + row));

		jsc.close();
	}
}
