package tujuh.suganda;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.Job;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;
import tujuh.suganda.model.SimpleModel;

public class InsertHbaseSpark {
	final static String HMASTER = "your-master";
	final static String ZOOKEEPER = "your-zookeeper";

	public static void main(String[] args) throws IOException {
		SparkSession spark = SparkSession.builder().master("local").appName("OrientDBSparkConnector").getOrCreate();
		JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());
		final SQLContext sqlContext = new SQLContext(sc);
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.ERROR);
		JavaRDD<SimpleModel> dataRdd = sc.textFile("src/main/resources/simple.csv")
				.map(new Function<String, SimpleModel>() {
					public SimpleModel call(String value) throws Exception {
						SimpleModel model = null;
						try {
							String[] data = value.split(";");
							model = new SimpleModel(data[0], data[1], data[2], data[3]);
						} catch (Exception e) {
						}
						return model;
					}
				});
		saveHbase(dataRdd);
	}

	public static void saveHbase(JavaRDD<SimpleModel> rdd) throws IOException {
		JavaPairRDD<ImmutableBytesWritable, Put> putData = rdd
				.mapToPair(new PairFunction<SimpleModel, ImmutableBytesWritable, Put>() {
					public Tuple2<ImmutableBytesWritable, Put> call(SimpleModel value) throws Exception {
						Put p = new Put(Bytes.toBytes(value.getId()));
						p.addColumn(Bytes.toBytes("0"), Bytes.toBytes("firstname"),
								Bytes.toBytes(value.getFirstname()));
						p.addColumn(Bytes.toBytes("0"), Bytes.toBytes("lastname"),
								Bytes.toBytes(value.getLastname()));
						p.addColumn(Bytes.toBytes("0"), Bytes.toBytes("address"), Bytes.toBytes(value.getAddress()));
						return new Tuple2<ImmutableBytesWritable, Put>(new ImmutableBytesWritable(), p);
					}
				});
		Job job = setInit();
		putData.saveAsNewAPIHadoopDataset(job.getConfiguration());
		System.out.println("Success..");
	}

	public static Job setInit() throws IOException {
		Configuration config = HBaseConfiguration.create();
		config.set("hbase.master", HMASTER);
		config.set("zookeeper.znode.parent", "/hbase-unsecure");
		config.setInt("timeout", 5000);
		config.set("hbase.zookeeper.quorum", ZOOKEEPER);
		config.set(TableOutputFormat.OUTPUT_TABLE, "test1");

		Job newAPIJobConfiguration1 = Job.getInstance(config);
		newAPIJobConfiguration1.getConfiguration().set(TableOutputFormat.OUTPUT_TABLE, "test1");
		newAPIJobConfiguration1.setOutputFormatClass(TableOutputFormat.class);
		return newAPIJobConfiguration1;
	}

}
