package tujuh.suganda.hbase.query;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.mapred.TableInputFormat;
import org.apache.hadoop.hbase.util.Bytes;

public class GetHbaseMultiRow {
	private final static String HMASTER = "yourHmaster";
	private final static String ZOOKEEPER = "yourZookeeper";
	public static void main(String[] args) {
		try {
			Configuration conf = HBaseConfiguration.create();
			conf.set("hbase.master", HMASTER);
			conf.setInt("timeout", 150000);
			conf.set("hbase.zookeeper.quorum", ZOOKEEPER);
			conf.set("zookeeper.znode.parent", "/hbase-unsecure");
			conf.set(TableInputFormat.COLUMN_LIST, "50000");

			HTable table = new HTable(conf, "test");

			List<Get> queryRowList = new ArrayList<Get>();
			queryRowList.add(new Get(Bytes.toBytes("testcf1")));
			queryRowList.add(new Get(Bytes.toBytes("testcf0")));
			queryRowList.add(new Get(Bytes.toBytes("15022718779271367539830")));
			queryRowList.add(new Get(Bytes.toBytes("1502271877903137045784")));
			
			Result[] results = table.get(queryRowList);
			
			for (Result r : results) {
				 for(KeyValue keyValue : r.list()) {
		    		  String key = Bytes.toString(keyValue.getRow());
		    		  System.out.println(key);
		    		  System.out.println("Qualifier : " + Bytes.toString(keyValue.getQualifier()) + " : Value : " + Bytes.toString(keyValue.getValue()));
		    	    }
			}
			
		}

		
		catch (Exception e) {
//			e.printStackTrace();
		}
	}
}
