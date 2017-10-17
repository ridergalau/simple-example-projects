package tujuh.suganda.hbase.reduceMapKey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;


public class KeyReducer extends TableReducer<Text, Text, ImmutableBytesWritable>{
	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		try {
			
			List<String> inidata = new ArrayList<String>();
			
			for (Text sales : values) {
				String data = sales.toString();
				inidata.add(data);
			}

			// create hbase put with rowkey as date
			Put insHBase = new Put(key.getBytes());
			
			// insert sum value to hbase
			insHBase.add(Bytes.toBytes("0"), Bytes.toBytes("hasil"), Bytes.toBytes(String.valueOf(inidata)));
			
			// write data to Hbase table
			context.write(null, insHBase);

		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
}