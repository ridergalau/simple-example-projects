package tujuh.suganda;

import java.util.HashMap;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkLoadOrient {

	final static String ORIENT_URL = "remote:127.0.0.1:2424/dbaing";
	final static String USER = "root";
	final static String PASSWORD = "aing";

	public static void main(String[] args) {

		SparkConf conf = new SparkConf();
		conf.setMaster("local[2]");
		SparkSession spark = SparkSession.builder().appName("OrientDBSparkConnector").config(conf).getOrCreate();

		String formatDoc = "org.apache.spark.orientdb.documents";
		String formatGraph = "org.apache.spark.orientdb.graphs";

		Map<String, String> orientConfig = new HashMap<>();
		orientConfig.put("dburl", ORIENT_URL);
		orientConfig.put("user", USER);
		orientConfig.put("password", PASSWORD);
		orientConfig.put("spark", "true");
		orientConfig.put("class", "Person");
		// orientConfig.put("vertextype", "demovertex");
		// orientConfig.put("edgetype", "demoedge");
		// orientConfig.put("query", "select * from Person where name = 'John'");

		Dataset<Row> tableDataset = spark.read().format(formatDoc).options(orientConfig).load();
		tableDataset.show();

	}
}
