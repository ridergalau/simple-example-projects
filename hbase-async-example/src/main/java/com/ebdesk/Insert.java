package com.ebdesk;
import org.hbase.async.*;

public class Insert {
	public static void main(String[] args) throws InterruptedException, Exception {
		Config conf = new Config();
		conf.overrideConfig("hbase.increments.buffer_size", "65535");
		conf.overrideConfig("hbase.zookeeper.quorum", "datanode01.cluster4.ph,namenode02.cluster4.ph,namenode01.cluster4.ph");
		conf.overrideConfig("hbase.zookeeper.znode.parent", "/hbase-unsecure");
		conf.overrideConfig("hbase.zookeeper.session.timeout", "5000");
		conf.overrideConfig("hbase.client.retries.number", "10");
		
		 final HBaseClient client = new HBaseClient(conf);
		 
		    final byte[] table = "test".getBytes();
		    final byte[] family = "0".getBytes();
		   
		    
		    client.ensureTableFamilyExists(table, family).join();
		    
		    for (int i = 26; i < 30; i++) {
		        String the = "iniKey"+i;
		        String v = "value "+i;
		        
		        final byte[] qualifier = "data".getBytes();
			    final byte[] value = v.getBytes();
		    	final byte[] key = the.getBytes();
		        final PutRequest put = new PutRequest(table, key, family, qualifier, value);
		        
		        put.setDurable(false);
		        client.put(put);
		        System.out.println("oke "+i);
		    }
		    
		    client.shutdown().join();
		    
	}
}
