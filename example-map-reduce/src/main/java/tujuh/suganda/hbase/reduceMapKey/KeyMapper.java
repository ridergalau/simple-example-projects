package tujuh.suganda.hbase.reduceMapKey;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;


public class KeyMapper extends TableMapper<Text, Text> {
	
@Override
public void map(ImmutableBytesWritable rowKey, Result columns, Context context)
			throws IOException, InterruptedException {
		try {
			// get rowKey and convert it to string
			String inKey = new String(rowKey.get());
			
			// set new key having only date
			String oKey = inKey.split("_")[0];
			
			// get sales column in byte format first and then convert it to string (as it is
			// stored as string from hbase shell)
			byte[] bSales = columns.getValue(Bytes.toBytes("0"), Bytes.toBytes("data"));
			String sSales = new String(bSales);
			
			// emit date and sales values
			context.write(new Text(oKey), new Text(sSales));
			
		} catch (RuntimeException e) {
//			e.printStackTrace();
		}
	}

}