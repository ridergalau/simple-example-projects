package tujuh.suganda.hbase.reduceSumValue;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;


public class testMapper extends TableMapper<Text, IntWritable> {
	
@Override
public void map(ImmutableBytesWritable rowKey, Result columns, Context context)
			throws IOException, InterruptedException {
		try {
			// get rowKey and convert it to string
			String keyHbase = new String(rowKey.get());
			
			// set new key
			String mapKey = keyHbase.split("_")[0];
			
			// get 0:data in hbase
			byte[] bData = columns.getValue(Bytes.toBytes("0"), Bytes.toBytes("data"));
			String sData = new String(bData);
			Integer data = new Integer(sData);
			
			// set ket and data(sum iterbale) 
			context.write(new Text(mapKey), new IntWritable(data));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

}