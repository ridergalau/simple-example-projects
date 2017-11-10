package tujuh.suganda.hbase.exampe;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class GetListTables {
	public static void main(String[] args) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {

		Configuration conf = HBaseConfiguration.create();
		// conf.set("hbase.master", "localhost:60000");
		// conf.setInt("timeout", 5000);
		// conf.set("hbase.zookeeper.quorum", "localhost:2181");
		// conf.set("zookeeper.znode.parent", "/hbase-unsecure");
		
		HBaseAdmin admin = new HBaseAdmin(conf);
		System.out.println("-----");
		// Getting all the list of tables using HBaseAdmin object
		HTableDescriptor[] tableDescriptor = admin.listTables();
		System.out.println("***");
		// printing all the table names.
		for (int i = 0; i < tableDescriptor.length; i++) {
			System.out.println(tableDescriptor[i].getNameAsString());
		}
	}
}
