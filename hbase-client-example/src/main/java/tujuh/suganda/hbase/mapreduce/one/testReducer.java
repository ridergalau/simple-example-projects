package tujuh.suganda.hbase.mapreduce.one;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;


public class testReducer extends TableReducer<Text, IntWritable, ImmutableBytesWritable>{
	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		try {
			int count = 0;
			for (IntWritable value : values) {
				String data = value.toString();
				
				Integer intSales = new Integer(data.toString());
				count += intSales;
			}
			// create hbase put with rowkey as date
			Put insHBase = new Put(key.getBytes());
			
			// insert sum value to hbase
			insHBase.add(Bytes.toBytes("0"), Bytes.toBytes("hasil"), Bytes.toBytes(String.valueOf(count)));
			// write data to Hbase table
			context.write(null, insHBase);
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
}