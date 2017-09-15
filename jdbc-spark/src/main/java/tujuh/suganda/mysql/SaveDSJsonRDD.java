package tujuh.suganda.mysql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class SaveDSJsonRDD implements Serializable {
	private static final Logger LOGGER = Logger.getLogger(SaveDSJsonRDD.class);
	private static final String MYSQL_USERNAME = "root";
	private static final String MYSQL_PWD = "aing";
	private static final String MYSQL_CONNECTION_URL = "jdbc:mysql://localhost:3306/mydb?user=" + MYSQL_USERNAME
			+ "&password=" + MYSQL_PWD;

	private static final JavaSparkContext sc = new JavaSparkContext(
			new SparkConf().setAppName("SparkSaveToDb").setMaster("local[*]"));

	private static final SQLContext sqlContext = new SQLContext(sc);

	public static void main(String[] args) {
		LOGGER.setLevel(Level.OFF);

		final Properties connectionProperties = new Properties();

		StructType schema = DataTypes
				.createStructType(new StructField[] { DataTypes.createStructField("id", DataTypes.StringType, true),
						DataTypes.createStructField("name", DataTypes.StringType, true),
						DataTypes.createStructField("email", DataTypes.StringType, true),
						DataTypes.createStructField("city", DataTypes.StringType, true),
						DataTypes.createStructField("country", DataTypes.StringType, true),
						DataTypes.createStructField("ip", DataTypes.StringType, true) });

		List<String> JsonList = new ArrayList<String>();
		JsonList.add(
				"{\"id\":1,\"name\":\"Suganda7\",\"email\":\"suganda7@google.co.uk\",\"city\":\"Évry\",\"country\":\"France\",\"ip\":\"199.63.123.157\"}");
		JsonList.add(
				"{\"id\":2,\"name\":\"Cinis\",\"email\":\"cinsis@google.co.uk\",\"city\":\"Évry\",\"country\":\"France\",\"ip\":\"199.63.123.157\"}");
		JsonList.add(
				"{\"id\":3,\"name\":\"Bat\",\"email\":\"batman0@google.co.uk\",\"city\":\"Évry\",\"country\":\"France\",\"ip\":\"199.63.123.157\"}");
		JsonList.add(
				"{\"id\":5,\"name\":\"Oke\",\"email\":\"okemans0@google.co.uk\",\"city\":\"Évry\",\"country\":\"France\",\"ip\":\"199.63.123.157\"}");

		// Add list Json to JavaRDD
		JavaRDD<String> json = sc.parallelize(JsonList);

		Dataset<Row> RowDs = sqlContext.jsonRDD(json, schema);

		RowDs.show(20);
		// insert to existing table
		RowDs.write().mode("append").jdbc(MYSQL_CONNECTION_URL, "person", connectionProperties);

		// insert With Creating new Table
		RowDs.write().jdbc(MYSQL_CONNECTION_URL, "person", connectionProperties);

		System.out.println("---------FINISH---------");
	}
}