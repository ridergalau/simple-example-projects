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

public class SaveDSJsonRDDSnappy {

	private static final String SNAPPY_CONNECTION_URL = "jdbc:snappydata://localhost:1527/";

	private static final JavaSparkContext sc = new JavaSparkContext(
			new SparkConf().setAppName("SparkSaveToSnappy").setMaster("local[*]"));
	// .set("snappydata.store.locators","localhost:1527"));

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

		List<String> schemaList = new ArrayList<String>();
		schemaList.add(
				"{\"id\":\"2fd\",\"name\":\"Juddssaxith\",\"email\":\"jknighsst0@google.co.uk\",\"city\":\"Évry\",\"country\":\"France\",\"ip\":\"199.63.123.157\"}");
		schemaList.add(
				"{\"id\":\"53f3\",\"name\":\"Judi3423fth\",\"email\":\"jkniasaght0@google.co.uk\",\"city\":\"Évry\",\"country\":\"France\",\"ip\":\"199.63.123.157\"}");
		schemaList.add(
				"{\"id\":\"4fddff\",\"name\":\"Judxzx54343ith\",\"email\":\"jknisasght0@google.co.uk\",\"city\":\"Évry\",\"country\":\"France\",\"ip\":\"199.63.123.157\"}");
		// where xyz is your schema line
		// menambhkan JSONlist ke RDD
		JavaRDD<String> json = sc.parallelize(schemaList);
		Dataset<Row> usersDf = sqlContext.jsonRDD(json, schema);

		usersDf.show();
		// insert ke tabel yg ada
		usersDf.write().mode("append").jdbc(SNAPPY_CONNECTION_URL, "MENCOBA7", connectionProperties);

		// Membuat table baru

		// usersDf.write().jdbc(MYSQL_CONNECTION_URL, "sulung", connectionProperties);

		// usersDf.createJDBCTable(MYSQL_CONNECTION_URL, "DATAAING", true);

		System.out.println("--------------- OKE ----------------------");
	}
}
