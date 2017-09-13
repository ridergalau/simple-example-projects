package tujuh.suganda.mysql;

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

	private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private static final String MYSQL_USERNAME = "root";
	private static final String MYSQL_PWD = "aing";
	private static final String MYSQL_CONNECTION_URL = "jdbc:mysql://localhost:3306/mydb?user=" + MYSQL_USERNAME
			+ "&password=" + MYSQL_PWD;

	private static final JavaSparkContext sc = new JavaSparkContext(
			new SparkConf().setAppName("SparkJdbcDS").setMaster("local[*]"));

	private static final SQLContext sqlContext = new SQLContext(sc);

	public static void main(String[] args) {
		Map<String, String> options = new HashMap<>();
		options.put("driver", MYSQL_DRIVER);
		options.put("url", MYSQL_CONNECTION_URL);
		options.put("dbtable", "(SELECT * FROM `data`) as data");
		options.put("partitionColumn", "id");
		options.put("lowerBound", "10001");
		options.put("upperBound", "499999");
		options.put("numPartitions", "10");

		Dataset<Row> jdbcDF = sqlContext.load("jdbc", options);
		jdbcDF.show();

		List<Row> employeeFullNameRows = jdbcDF.collectAsList();
		for (Row employeeFullNameRow : employeeFullNameRows) {
			System.out.println("-> " + employeeFullNameRow);
		}

	}
}
