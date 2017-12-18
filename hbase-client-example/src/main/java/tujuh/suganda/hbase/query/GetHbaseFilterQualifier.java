package tujuh.suganda.hbase.query;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter;
import org.apache.hadoop.hbase.mapred.TableInputFormat;
import org.apache.hadoop.hbase.util.Bytes;

public class GetHbaseFilterQualifier {
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

			// Define Table Name
			HTable table = new HTable(conf, "test");

			// ====================================================================================================================================//
			// Query One RowKey
			Get g = new Get(Bytes.toBytes("testcf0"));

			// Find all columns Qualifier in a row and family that start with "examp" or "coba"
			byte[][] prefixes = new byte[][] { Bytes.toBytes("ebde"), Bytes.toBytes("c")};
			Filter filterSet = new MultipleColumnPrefixFilter(prefixes);
			g.setFilter(filterSet);

			// Column Family Filter
			g.addFamily("0".getBytes());

			Result result = table.get(g);
			for (KeyValue keyValue : result.list()) {
				String key = Bytes.toString(keyValue.getRow());
				System.out.println("Qualifier : " + Bytes.toString(keyValue.getQualifier()) + " : Value : "
						+ Bytes.toString(keyValue.getValue()));
			}
		}
		
		catch (Exception e) {
			// e.printStackTrace();
		}
	}
}
