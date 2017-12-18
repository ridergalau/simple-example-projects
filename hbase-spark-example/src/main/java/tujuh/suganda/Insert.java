package tujuh.suganda;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;

import scala.Tuple2;
import tujuh.suganda.model.SimpleModel;

public class Insert {
	public static void main(String[] args) {
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

		
		
		JavaPairRDD<ImmutableBytesWritable, Put> putData = dataRdd
				.mapToPair(new PairFunction<SimpleModel, ImmutableBytesWritable, Put>() {
					public Tuple2<ImmutableBytesWritable, Put> call(SimpleModel value) throws Exception {
						Put p = new Put(Bytes.toBytes(value.getId()));
						p.addColumn(Bytes.toBytes("0"), Bytes.toBytes("firstname"),
								Bytes.toBytes(value.getFirstname()));
						p.addColumn(Bytes.toBytes("0"), Bytes.toBytes("lastname"), Bytes.toBytes(value.getLastname()));
						p.addColumn(Bytes.toBytes("0"), Bytes.toBytes("address"), Bytes.toBytes(value.getAddress()));
						return new Tuple2<ImmutableBytesWritable, Put>(new ImmutableBytesWritable(), p);
					}
				});

		dataRdd.foreach(new VoidFunction<SimpleModel>() {
			@Override
			public void call(SimpleModel arg0) throws Exception {
				System.out.println(arg0.getFirstname());

			}
		});
	}
}
