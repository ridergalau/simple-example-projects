package tujuh.suganda.hbase.exampe;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapred.TableOutputFormat;
import org.apache.hadoop.hbase.util.Bytes;

public class InsertHbase {
	public static void main(String[] args) throws IOException {
		final String hMaster = "namenode01.cluster2.ph";
		final String quorum = "namenode01.cluster2.ph,namenode02.cluster2.ph";
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.master", hMaster);
		conf.setInt("timeout", 5000);
		conf.set("hbase.zookeeper.quorum", quorum);
		conf.set("zookeeper.znode.parent", "/hbase-unsecure");
		// System.out.println("Konek");
	    HTable hTable = new HTable(conf, "the-table");

		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("yyyyMMddhh").format(date);
		List<Put> puts = new ArrayList<Put>();
		System.out.println("Konek");
		for (int i = 1; i < 9; i++) {
			Put p = new Put(Bytes.toBytes("2017110" + String.valueOf(i) + "iououoiukkjkjiujhhiu"));
			p.add(Bytes.toBytes("0"), Bytes.toBytes("out1" + i), Bytes.toBytes("fb_mufaku.kurniawan" + i));
			p.add(Bytes.toBytes("0"), Bytes.toBytes("in" + i), Bytes.toBytes("isabela" + i));
			puts.add(p);
			System.out.println("LOOP " + i);
		}
		try {
			// Saving the put Instance to the HTable.
			hTable.put(puts);
			System.out.println("data inserted");
			hTable.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
