package tujuh.suganda.hbase.query;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class ScanHbaseFIlter {
	private final static String HMASTER = "yourHmaster";
	private final static String ZOOKEEPER = "yourZookeeper";
	public static void main(String[] args) {
	
    try {
    	Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.master", HMASTER);
        conf.setInt("timeout", 5000);
        conf.set("hbase.zookeeper.quorum", ZOOKEEPER);
        conf.set("zookeeper.znode.parent", "/hbase-unsecure");	    
	    System.out.println("Konek");
	    
	    HTable table = new HTable(conf, "the-table");
	   
	    final byte[] CF = "0".getBytes();
	    final byte[] qualifier = "data".getBytes();
	  
	    
	    
	    Scan scan = new Scan();
	    scan.addFamily(CF);
	    scan.addColumn(CF,qualifier);
	    
	    //RowkeyPrefixFilter
	    scan.setRowPrefixFilter(Bytes.toBytes("2017"));

	    //Start and Stop Row Key 
	    scan.setStartRow(Bytes.toBytes("20170801"));
	    scan.setStopRow(Bytes.toBytes("20171106"));
	    
	    //Set Timerange(Timestamp Hbase)
	    scan.setTimeRange(Long.valueOf("1504876700236"), Long.valueOf("1504876730236"));
	    
	    //Reverese or Sort Desc 
	    scan.isReversed();
	    
	    ResultScanner rs = table.getScanner(scan);
	    
	    try {
	      for (Result r = rs.next(); r != null; r = rs.next()) {
	    	  for(KeyValue keyValue : r.list()) {
	    		  String key = Bytes.toString(keyValue.getRow());
	    		  System.out.println(key);
	    		  System.out.println("Qualifier : " + Bytes.toString(keyValue.getQualifier()) + " : Value : " + Bytes.toString(keyValue.getValue()));
	    	    }
	      }
	    } finally {
	      rs.close();
	    }
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    }

	
	
}
}
