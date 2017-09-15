package tujuh.suganda.postgree;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class loadDataPostgree implements Serializable {

	private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(loadDataPostgree.class);

	private static final String POSTGRE_USERNAME = "postgres";
	private static final String POSTGRE_PWD = "aing";
	private static final String POSTGRE_CONNECTION_URL = "jdbc:postgresql://127.0.0.1:5432/mydb?user="
			+ POSTGRE_USERNAME + "&password=" + POSTGRE_PWD;

	private static final JavaSparkContext sc = new JavaSparkContext(
			new SparkConf().setAppName("SparkJdbcDs").setMaster("local[*]"));

	private static final SQLContext sqlContext = new SQLContext(sc);

	public static void main(String[] args) {
		StructType schema = DataTypes
				.createStructType(new StructField[] { DataTypes.createStructField("id", DataTypes.StringType, true),
						DataTypes.createStructField("name", DataTypes.StringType, true),
						DataTypes.createStructField("email", DataTypes.StringType, true),
						DataTypes.createStructField("city", DataTypes.StringType, true),
						DataTypes.createStructField("country", DataTypes.StringType, true),
						DataTypes.createStructField("ip", DataTypes.StringType, true) });

		// Data source options
		Map<String, String> options = new HashMap<>();
		options.put("url", POSTGRE_CONNECTION_URL);
		options.put("dbtable", "(SELECT * FROM data) as data");
		options.put("lowerBound", "10001");
		options.put("upperBound", "499999");
		options.put("numPartitions", "10");

		Dataset<Row> DsJdbc = sqlContext.load("jdbc", options);

		DsJdbc.show(10);

		// List<Row> employeeFullNameRows = jdbcDF.collectAsList();
		//
		// for (Row employeeFullNameRow : employeeFullNameRows) {
		// System.out.println(employeeFullNameRow);
		// }

		// for (Row employeeFullNameRow : employeeFullNameRows) {
		// LOGGER.info(employeeFullNameRow);
		// }

	}
}
