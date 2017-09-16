package tujuh.suganda;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrameWriter;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

import tujuh.suganda.model.MyModel;

public class SparkStoredToOrient {
	final static String ORIENT_URL = "remote:127.0.0.1:2424/dbaing";
	final static String USER = "root";
	final static String PASSWORD = "aing";

	public static void main(String[] args) {

		SparkSession spark = SparkSession.builder().master("local").appName("OrientDBSparkConnector").getOrCreate();

		JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());

		final SQLContext sqlContext = new SQLContext(sc);

		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.ERROR);

		String formatDoc = "org.apache.spark.orientdb.documents";
		String formatGraph = "org.apache.spark.orientdb.graphs";

		Map<String, String> orientConfig = new HashMap<>();
		orientConfig.put("dburl", ORIENT_URL);
		orientConfig.put("user", USER);
		orientConfig.put("password", PASSWORD);
		orientConfig.put("spark", "true");
		orientConfig.put("class", "Person");

		JavaRDD<MyModel> dataRdd = sc.textFile("src/main/resources/exampledata.csv")
				.map(new Function<String, MyModel>() {
					public MyModel call(String value) throws Exception {
						String json = "";
						MyModel model = null;
						try {
							String[] data = value.split(",");
							model = new MyModel(data[0], data[0], data[1], Integer.valueOf(data[2]), data[3],
									Integer.valueOf(data[4]), Integer.valueOf(data[5]), Integer.valueOf(data[6]),
									data[7], data[8], Integer.valueOf(data[9]), data[10], data[11]);
						} catch (Exception e) {
						}
						return model;
					}
				});

		// SCALA PROJECT EXAMPLE
		// https://dzone.com/articles/apache-spark-datasource-for-orientdb

		try {
			// Stored to Orient
			DataFrameWriter<Row> usersDfs = sqlContext.createDataFrame(dataRdd, MyModel.class).write().format(formatDoc)
					.options(orientConfig).mode(SaveMode.Append);
			usersDfs.save();
			
			System.out.println("---FINISH---");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
