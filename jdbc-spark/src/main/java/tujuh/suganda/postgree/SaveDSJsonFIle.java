package tujuh.suganda.postgree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class SaveDSJsonFIle implements Serializable {
	private static final String POSTGRE_USERNAME = "postgres";
	private static final String POSTGRE_PWD = "aing";
	private static final String POSTGRE_CONNECTION_URL = "jdbc:postgresql://127.0.0.1:5432/mydb?user="
			+ POSTGRE_USERNAME + "&password=" + POSTGRE_PWD;

	private static final JavaSparkContext sc = new JavaSparkContext(
			new SparkConf().setAppName("SparkSaveToPostgre").setMaster("local[*]"));

	private static final SQLContext sqlContext = new SQLContext(sc);

	public static void main(String[] args) {
		final Properties connectionProperties = new Properties();

		StructType schema = DataTypes
				.createStructType(new StructField[] { DataTypes.createStructField("id", DataTypes.StringType, true),
						DataTypes.createStructField("name", DataTypes.StringType, true),
						DataTypes.createStructField("email", DataTypes.StringType, true),
						DataTypes.createStructField("city", DataTypes.StringType, true),
						DataTypes.createStructField("country", DataTypes.StringType, true),
						DataTypes.createStructField("ip", DataTypes.StringType, true) });

		// LOAD DATA FROM FILE
		Dataset<Row> DsJdbc = sqlContext.jsonFile("src/main/resources/users.json", schema);

		DsJdbc.show();

		// insert to existing table
		// overwrite or append
		DsJdbc.write().mode("append").jdbc(POSTGRE_CONNECTION_URL, "newdata", connectionProperties);
		// insert With Creating new Table
		DsJdbc.write().jdbc(POSTGRE_CONNECTION_URL, "data", connectionProperties);

		// save to csv
		DsJdbc.write().mode("append").csv("/home/Documents/TEST.csv");

		System.out.println("OKE");
	}
}