package tujuh.suganda.hbase.query;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.mapred.TableInputFormat;
import org.apache.hadoop.hbase.util.Bytes;

public class GetHbase {
	public static void main(String[] args) {
		final String hMaster = "namenode02.cluster4.ph,namenode01.cluster4.ph";
		final String quorum = "datanode01.cluster4.ph,namenode02.cluster4.ph,namenode01.cluster4.ph";
		try {
			Configuration conf = HBaseConfiguration.create();
			conf.set("hbase.master", hMaster);
			conf.setInt("timeout", 150000);
			conf.set("hbase.zookeeper.quorum", quorum);
			conf.set("zookeeper.znode.parent", "/hbase-unsecure");
			conf.set(TableInputFormat.COLUMN_LIST, "50000");

			HTable table = new HTable(conf, "profiling-network");
			
			//====================================================================================================================================//
			 //Query One Row
			 Get g = new Get(Bytes.toBytes("1502271877908137047384"));

			 Result result = table.get(g);
			 byte[] data1 = result.getValue(Bytes.toBytes("cf"),
			 Bytes.toBytes("q1"));
			 byte[] data2 = result.getValue(Bytes.toBytes("cf"),
			 Bytes.toBytes("q2"));
			 
			 String vData1 = Bytes.toString(data1);
			 String vData2 = Bytes.toString(data2);
			 System.out.println("q1: " + vData1 + " \n q2: " + vData2);

			//====================================================================================================================================//
		}

		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
