package tujuh.suganda;

import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.json.simple.JSONObject;
import scala.Tuple2;

public class BulkLoadHbaseSpark {
	final static String HMASTER = "namenode01.cluster4.ph";
	final static String ZOOKEEPER = "datanode01.cluster4.ph,namenode02.cluster4.ph,namenode01.cluster4.ph";

	public static void main(String[] args) {
		SparkConf sparkConf = new SparkConf().setAppName("RequestProfil").setMaster("local[*]");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);
		SQLContext sqlContext = new SQLContext(jsc);

		Configuration config = HBaseConfiguration.create();
		config.set("hbase.master", HMASTER);
		config.set("zookeeper.znode.parent", "/hbase-unsecure");
		config.setInt("timeout", 5000);
		config.set("hbase.zookeeper.quorum", ZOOKEEPER);
		config.set(TableInputFormat.INPUT_TABLE, "sna-counter");
		config.set(TableInputFormat.SCAN_COLUMN_FAMILY, "0");

		// config.set(TableInputFormat.SCAN_COLUMNS, "0:tweet");

		// Range RowKey
//		 config.set(TableInputFormat.SCAN_ROW_START, "20171101");
//		 config.set(TableInputFormat.SCAN_ROW_STOP, "20171107");

		// Time Range
		 config.setLong(TableInputFormat.SCAN_TIMERANGE_START, new Date().getTime() - (60*60*1000) );
		 config.setLong(TableInputFormat.SCAN_TIMERANGE_END, new Date().getTime());

		System.out.println("LOADING  . . . . . . ");
		JavaPairRDD<ImmutableBytesWritable, Result> hBaseRDD = jsc.newAPIHadoopRDD(config, TableInputFormat.class,
				ImmutableBytesWritable.class, Result.class);

		JavaPairRDD<String, String> rowPairRDD = hBaseRDD
				.mapToPair(new PairFunction<Tuple2<ImmutableBytesWritable, Result>, String, String>() {
					public Tuple2<String, String> call(Tuple2<ImmutableBytesWritable, Result> entry) throws Exception {
						Result rs = entry._2;
						String keyRow = Bytes.toString(rs.getRow());
						JSONObject json = new JSONObject();

						for (KeyValue keyValue : rs.list()) {
							json.put(Bytes.toString(keyValue.getKey()), Bytes.toString(keyValue.getValue()));
						}
						
						return new Tuple2<String, String>(keyRow, json.toJSONString());
					}

				});

		Dataset<Row> schemaRDD = sqlContext
				.createDataset(JavaPairRDD.toRDD(rowPairRDD), Encoders.tuple(Encoders.STRING(), Encoders.STRING()))
				.toDF();
		schemaRDD.show();
		
//		schemaRDD.createOrReplaceTempView("data");
//		Dataset<Row> tempTable = sqlContext.sql("select * from data where _1 LIKE '20171102%'");
//
//		tempTable.show();

		// schemaRDD.foreach((ForeachFunction<Row>) row -> System.out.println(row));
		System.out.println("----FINISH----");
		jsc.stop();
	}
}