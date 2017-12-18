
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapred.TableInputFormat;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.NullOutputFormat;

public class HbaseCounter {

	public static class CounterMapper extends TableMapper<Text, Text> {
		public static enum Counters {
			ROWS
		}

		public void map(ImmutableBytesWritable row, Result value, Context context)
				throws InterruptedException, IOException {
			context.getCounter(Counters.ROWS).increment(1);
		}
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			System.out.println("Usage: HbaseCounter <zookeeper> <master> <tablename> <cacheSize[optional]>");
			System.exit(0);
		}

		final String hMaster = args[0];
		final String quorum = args[1];
		final String table = args[2];

		String cacheSize = "";
		if (args[3].equals(null))
			cacheSize = "1000";
		else
			cacheSize = args[3];

		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.master", hMaster);
		conf.setInt("timeout", 150000);
		conf.set("hbase.zookeeper.quorum", quorum);
		conf.set("zookeeper.znode.parent", "/hbase-unsecure");
		conf.set(TableInputFormat.COLUMN_LIST, "50000");

		Job job = new Job(conf, "Row Counter - hbase:" + hMaster + " table : " + table);
		job.setJarByClass(HbaseCounter.class);
		Scan scan = new Scan();
		scan.setCaching(Integer.valueOf(cacheSize));

		TableMapReduceUtil.initTableMapperJob(table, // input table
				scan, // Scan instance
				CounterMapper.class, // mapper class
				Text.class, // mapper output key
				Text.class, // mapper output value
				job);

		job.setOutputFormatClass(NullOutputFormat.class);
		job.setNumReduceTasks(0); // at least one, adjust as required

		boolean b = job.waitForCompletion(true);
		if (!b) {
			throw new IOException("error with job!");
		}
	}
}
