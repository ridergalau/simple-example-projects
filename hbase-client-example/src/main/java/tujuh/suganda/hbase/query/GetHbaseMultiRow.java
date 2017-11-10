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

public class GetHbaseMultiRow {
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

			List<Get> queryRowList = new ArrayList<Get>();
			queryRowList.add(new Get(Bytes.toBytes("1502271877908137047384")));
			queryRowList.add(new Get(Bytes.toBytes("1502271877933137052614")));
			queryRowList.add(new Get(Bytes.toBytes("15022718779271367539830")));
			queryRowList.add(new Get(Bytes.toBytes("1502271877903137045784")));
			
			Result[] results = table.get(queryRowList);
			byte[] value =null;
			byte[] value1 = null;
			for (Result r : results) {
				
				value = r.getValue(Bytes.toBytes("cf"),
						Bytes.toBytes("q1"));
				
				value1 = r.getValue(Bytes.toBytes("cf"),
							Bytes.toBytes("q2"));
				 
				String v1 = Bytes.toString(value);
				String v2 = Bytes.toString(value1);

				System.out.println("q1: " + v1 + " \n q2: " + v2);
			}
			
		}

		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
