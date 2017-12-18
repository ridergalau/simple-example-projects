package tujuh.suganda.hivespark;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

public class LoadData implements Serializable {

	private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(LoadData.class);
 
	private static final String HIVE_HOST = "192.168.20.123";
	private static final String HIVE_JDBC_PORT = "10000";
	public static final String HIVE_CONNECTION_URL = "jdbc:hive2://"+ HIVE_HOST + ':' + HIVE_JDBC_PORT + "/default";
	private static final String JDBC_DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";
	

	private static final JavaSparkContext sc = new JavaSparkContext(
			new SparkConf().setAppName("SparkJdbcDS").setMaster("local[*]"));

	private static final SQLContext sqlContext = new SQLContext(sc);

	public static void main(String[] args) {
		Map<String, String> options = new HashMap<>();
		options.put("driver", JDBC_DRIVER_NAME);
		options.put("url", HIVE_CONNECTION_URL);
		options.put("dbtable", "(SELECT key,value FROM coba_1) as data");
		options.put("partitionColumn", "key");
		options.put("lowerBound", "10001");
		options.put("upperBound", "499999");
		options.put("numPartitions", "10");

		Dataset<Row> RowDs = sqlContext.load("jdbc", options);
		RowDs.show();

		List<Row> employeeFullNameRows = RowDs.collectAsList();
		for (Row employeeFullNameRow : employeeFullNameRows) {
			System.out.println("-> " + employeeFullNameRow);
		}
		

	}
}
