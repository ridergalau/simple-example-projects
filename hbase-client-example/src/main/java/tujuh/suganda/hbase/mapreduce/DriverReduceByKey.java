package tujuh.suganda.hbase.mapreduce;

//http://rishavrohitblog.blogspot.co.id/2013/10/mapreduce-on-hbase-table.html

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapred.TableInputFormat;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

public class DriverReduceByKey {
	public static void main(String[] args) throws Exception {
//		final String hMaster = "namenode02.cluster4.ph,namenode01.cluster4.ph";
//		final String quorum = "datanode01.cluster4.ph,namenode02.cluster4.ph,namenode01.cluster4.ph";

		final String hMaster = "localhost";
		final String quorum = "localhost:2181";
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.master", hMaster);
		conf.setInt("timeout", 150000);
		conf.set("hbase.zookeeper.quorum", quorum);
		conf.set("zookeeper.znode.parent", "/hbase");
		conf.set(TableInputFormat.COLUMN_LIST, "50000");

		// define scan and define column families to scan
		Scan scan = new Scan();
		scan.addFamily(Bytes.toBytes("0"));
		Job job = new Job(conf, "ExampleReduceByKey");
		job.setJarByClass(DriverReduceByKey.class);
		// define input hbase table
		TableMapReduceUtil.initTableMapperJob("aing", scan, KeyMapper.class, Text.class, Text.class, job);
		// define output table
		TableMapReduceUtil.initTableReducerJob("test", KeyReducer.class, job);
		job.waitForCompletion(true);
		System.out.println("OKE");
	}
}
