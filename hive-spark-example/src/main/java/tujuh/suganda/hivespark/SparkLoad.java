package tujuh.suganda.hivespark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
//import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.hive.HiveContext;

public class SparkLoad {

public static void main(String[] args) {
    SparkConf sparkConf = new SparkConf().setAppName("SparkSQLHiveContextExample").setMaster("local[1]");
    JavaSparkContext ctx = new JavaSparkContext(sparkConf);
//    SQLContext sqlContext = new SQLContext(ctx);

    HiveContext hiveContext = new org.apache.spark.sql.hive.HiveContext(ctx.sc());
//    hiveContext.sql("CREATE TABLE IF NOT EXISTS src(id STRING, name STRING,lastname STRING, address STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'");
    hiveContext.sql("CREATE TABLE IF NOT EXISTS src (key INT, value STRING)");
    hiveContext.sql("LOAD DATA LOCAL INPATH '/home/tujuh/Documents/simple.csv' INTO TABLE src");
    
    JavaRDD<Row> results = hiveContext.sql("FROM src SELECT key, value").toJavaRDD();

	Dataset<Row> schemaRDD = hiveContext.createDataFrame(results, Row.class);
	
	schemaRDD.show();
    
    System.out.println(results.toString());
	
}
}
