package tujuh.suganda.snappy;
import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.chainsaw.Main;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

public class LoadSnappyTable implements Serializable {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(LoadSnappyTable.class);

    private static final String MYSQL_DRIVER = "org.snappydata.Driver";
   private static final String MYSQL_CONNECTION_URL = "jdbc:snappydata://localhost:1527/";

    private static final JavaSparkContext sc =
            new JavaSparkContext(new SparkConf().setAppName("SparkJdbcDs").setMaster("local[*]"));

    private static final SQLContext sqlContext = new SQLContext(sc);

    public static void main(String[] args) {
    	LOGGER.setLevel(Level.OFF);
        //Data source options
        Map<String, String> options = new HashMap<>();
//        options.put("driver", MYSQL_DRIVER);
        options.put("url", MYSQL_CONNECTION_URL);
        options.put("dbtable",
                    "select NAME from APP.MENCOBA7;");
        options.put("partitionColumn", "ID");
        options.put("lowerBound", "10001");
        options.put("upperBound", "499999");
        options.put("numPartitions", "10");

        
//        pake 
//        <properties>
//        <spark.version>1.3.1</spark.version>
//    </properties>
        //Load MySQL query result as DataFrame
        

        Dataset<Row> jdbcDF = sqlContext.load("jdbc", options);
        jdbcDF.show();

//        List<Row> employeeFullNameRows = jdbcDF.collectAsList();
//        
//        for (Row employeeFullNameRow : employeeFullNameRows) {
//            System.out.println(employeeFullNameRow);
//        }
        
//        for (Row employeeFullNameRow : employeeFullNameRows) {
//            LOGGER.info(employeeFullNameRow);
//        }
        
        
    }
}
