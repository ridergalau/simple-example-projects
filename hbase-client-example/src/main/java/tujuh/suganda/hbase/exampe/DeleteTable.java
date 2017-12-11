package tujuh.suganda.hbase.exampe;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class DeleteTable {
public static void main(String[] args) throws IOException {
	final String hMaster = "yourHmaster";
	final String quorum = "yourZookeeper";
	
	Configuration conf = HBaseConfiguration.create();
    conf.set("hbase.master", hMaster);
    conf.setInt("timeout", 5000);
    conf.set("hbase.zookeeper.quorum", quorum);
    conf.set("zookeeper.znode.parent", "/hbase-unsecure");

    HBaseAdmin admin = new HBaseAdmin(conf);

    // disabling table named emp
    admin.disableTable("biodata2");

    // Deleting emp
    admin.deleteTable("biodata2");
    System.out.println("Table deleted");
    
    
    HTableDescriptor[] tableDescriptor = admin.listTables();

    // printing all the table names.
    for (int i=0; i<tableDescriptor.length;i++ ){
       System.out.println(tableDescriptor[i].getNameAsString());
    }
    
}
}
