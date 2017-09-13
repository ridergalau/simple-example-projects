package tujuh.suganda.mysql;

import java.io.Serializable;

import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class SaveDSJsonFile implements Serializable {
	private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(LoadData.class);

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

		// LOAD DATA FROM FILE
		Dataset<Row> usersDf = sqlContext.jsonFile("src/main/resources/users.json", schema);

		// insert With Creating new Table
		usersDf.write().jdbc(MYSQL_CONNECTION_URL, "data", connectionProperties);

		// insert to existing table
		// overwrite or append
		usersDf.write().mode("append").jdbc(MYSQL_CONNECTION_URL, "person", connectionProperties);

		usersDf.show(20);
		System.out.println("---------FINISH---------");
	}
}