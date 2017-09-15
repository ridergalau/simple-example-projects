package tujuh.suganda.snappy;

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

public class SaveDSJsonFileSnappy {


	private static final String SNAPPY_CONNECTION_URL = "jdbc:snappydata://localhost:1527/";

	private static final JavaSparkContext sc = new JavaSparkContext(
			new SparkConf().setAppName("SparkSaveToSnappy").setMaster(
					"local[*]"));
//	.set("snappydata.store.locators","localhost:1527"));
	
	private static final SQLContext sqlContext = new SQLContext(sc);

	public static void main(String[] args) {
		final Properties connectionProperties = new Properties();		        

		StructType schema = DataTypes.createStructType(new StructField[] { 
			    DataTypes.createStructField("id", DataTypes.StringType,true),
			    DataTypes.createStructField("name", DataTypes.StringType, true),
			    DataTypes.createStructField("email", DataTypes.StringType, true),
			    DataTypes.createStructField("city", DataTypes.StringType, true),
			    DataTypes.createStructField("country", DataTypes.StringType, true),
			    DataTypes.createStructField("ip", DataTypes.StringType, true)});
		// LOAD DATA JSON DARI FILE
		Dataset<Row> usersDf = sqlContext
				.jsonFile("src/main/resources/users.json",schema);

		//insert ke tabel yg ada
//		usersDf.write().mode("append").jdbc(MYSQL_CONNECTION_URL, "sulung", connectionProperties);

		//Membuat table baru
		
		usersDf.write().jdbc(SNAPPY_CONNECTION_URL, "sulung", connectionProperties);
		
		usersDf.show();

//		 usersDf.createJDBCTable(MYSQL_CONNECTION_URL, "DATAAING", true);

		System.out.println("--------------- OKE ----------------------");
	}
}
