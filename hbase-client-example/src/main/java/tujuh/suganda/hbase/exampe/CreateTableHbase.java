package tujuh.suganda.hbase.exampe;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class CreateTableHbase {
public static void main(String[] args) throws IOException {
	try {
		Configuration conf = HBaseConfiguration.create();
	    conf.set("hbase.master", "namenode02.cluster3.ph");
	    conf.setInt("timeout", 5000);
	    conf.set("hbase.zookeeper.quorum", "namenode02.cluster3.ph,namenode03.cluster3.ph,master03.cluster3.ph");
	    conf.set("zookeeper.znode.parent", "/hbase-unsecure");

	    // Instantiating HbaseAdmin class
	    HBaseAdmin admin = new HBaseAdmin(conf);

	    // Instantiating table descriptor class
	    HTableDescriptor tableDescriptor = new
	    HTableDescriptor(TableName.valueOf("tw_pilkada"));

	    // Adding column families to table descriptor
	    tableDescriptor.addFamily(new HColumnDescriptor("data_tw"));

	    // Execute the table through admin
	    admin.createTable(tableDescriptor);
	    
	    System.out.println(" Table created ");
		
	} catch (Exception e) {
e.printStackTrace();
	}
}
}
