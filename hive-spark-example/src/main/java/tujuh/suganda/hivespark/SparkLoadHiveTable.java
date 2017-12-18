package tujuh.suganda.hivespark;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkLoadHiveTable {
	private static final String SQL_STATEMENT = "select key,value from coba_1";
	private static final String HIVE_HOST = "192.168.20.123";
	private static final String HIVE_JDBC_PORT = "10000";
	public static final String HIVE_CONNECTION_URL = "jdbc:hive2://"+ HIVE_HOST + ':' + HIVE_JDBC_PORT + "/default";
	private static final String JDBC_DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";

	public static void main(String[] args) {
		System.out.println(HIVE_CONNECTION_URL);
		SparkSession spark = SparkSession.builder()
		          .appName("interfacing spark sql to hive metastore without configuration file")
		          .config("hive.metastore.uris", "thrift://192.168.20.123:9083") // replace with your hivemetastore service's thrift url
		          .enableHiveSupport() // don't forget to enable hive support
		          .master("local[*]")
		          .getOrCreate();

		spark.sql(SQL_STATEMENT);

 
		
		Map<String, String> options = new HashMap<String, String>();
	    options.put("url", HIVE_CONNECTION_URL);
	    options.put("driver", JDBC_DRIVER_NAME);
	    options.put("dbtable", "coba_1");

		Dataset<Row> asd = spark.read().format("jdbc").options(options).load();
		asd.show(10000);
		JavaRDD<Row> rdd = asd.toJavaRDD(); 
		
//	    System.out.println("+++++++++++++++++++++++++++");
	    rdd.foreach(new VoidFunction<Row>() {

			public void call(Row data) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("=============================");
				System.out.println(data);
			}
		});
}
}
