package tujuh.suganda.hivespark;

import org.apache.spark.sql.Dataset;
//import org.apache.spark.sql.Row;
//import org.apache.spark.sql.SparkSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
public class cobaLgai {
//	private static final String SQL_STATEMENT = "select employeeid as idnya from employetext";
	
	private static final String HIVE_HOST = "namenode02.cluster4.ph";
	private static final String HIVE_JDBC_PORT = "10000";
	public static final String HIVE_CONNECTION_URL = "jdbc:hive2://"+ HIVE_HOST + ':' + HIVE_JDBC_PORT + "/default";
	
	
	private static final String JDBC_DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";

	public static void main(String[] args) {

		String warehouseLocation = "/user/hive/warehouse";
		SparkSession spark = SparkSession.builder()
				.appName("Java Spark Hive Example")
				.config("hive.metastore.warehouse.dir", "/home/spark/warehouse")
				.config("hive.metastore.uris", "thrift://namenode02.cluster4.ph:9083")
				.config("spark.sql.warehouse.dir", warehouseLocation)
				.master("local[2]")
				.enableHiveSupport().getOrCreate();

//		SparkSession spark = SparkSession.builder()
//				.appName("Java Spark Hive Example").master("local[2]")
//				.config("hive.metastore.uris", "thrift://192.168.20.86:9083")
//				.enableHiveSupport().getOrCreate();

		// HiveContext hc = new HiveContext(spark);
//		SQLContext sqlContext = new SQLContext(spark);

		
		 Map<String, String> options = new HashMap<String, String>();
		 options.put("url", HIVE_CONNECTION_URL);
		 options.put("driver", JDBC_DRIVER_NAME);
		 options.put("dbtable", "(SELECT key,value FROM coba_1) as data");
//		 options.put("user", "hive");
//		 options.put("password", "rahasia2016");
		
		 Dataset<Row> sqlDF = spark.read().format("jdbc").options(options).load();
		 sqlDF.show();
		 
		 Dataset<String> stringsDS = sqlDF.map(new MapFunction<Row, String>() {
			  @Override
			  public String call(Row row) throws Exception {
			    return "Key: " + row.get(0).toString() + ", Value: " + row.get(1).toString();
			  }
			}, Encoders.STRING());
			stringsDS.show();
		 
//		 
//		 JavaRDD<Row> rdd =
//		 spark.read().format("jdbc").options(options).load().toJavaRDD();
//		
//		 rdd.foreach(new VoidFunction<Row>() {
//		
//		 public void call(Row data) throws Exception {
//		 // TODO Auto-generated method stub
//		 System.out.println("=============================");
//		 System.out.println(data);
//		 }
//		 });
//		
		
//		try {
//			Dataset<Row> sqlDF = spark.sql("SELECT * FROM dosm_lazada limit 1000000");
//			
//			Dataset<String> stringsDS = sqlDF.map(new MapFunction<Row, String>() {
//				  @Override
//				  public String call(Row row) throws Exception {
//				    return "Key: " + row.get(0) + ", Value: " + row.get(1);
//				  }
//				}, Encoders.STRING());
//				stringsDS.show();
//				
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println();
//		}

		long start = System.currentTimeMillis();
	}
}
