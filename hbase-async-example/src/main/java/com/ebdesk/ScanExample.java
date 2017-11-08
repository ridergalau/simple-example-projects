package com.ebdesk;

import java.util.ArrayList;
import java.util.Date;

import org.hbase.async.Scanner;
import org.hbase.async.Bytes;
import org.hbase.async.Config;
import org.hbase.async.HBaseClient;
import org.hbase.async.KeyValue;

public class ScanExample {
	public static void main(String[] args) throws InterruptedException, Exception {
		Config conf = new Config();
		conf.overrideConfig("hbase.increments.buffer_size", "65535");
		conf.overrideConfig("hbase.zookeeper.quorum",
				"datanode01.cluster4.ph,namenode02.cluster4.ph,namenode01.cluster4.ph");
		conf.overrideConfig("hbase.zookeeper.znode.parent", "/hbase-unsecure");
		conf.overrideConfig("hbase.zookeeper.session.timeout", "5000");
		conf.overrideConfig("hbase.client.retries.number", "10");

		final HBaseClient client = new HBaseClient(conf);
		final byte[] table = "sna-network".getBytes();

		// List<ScanFilter> filters = new ArrayList<ScanFilter>();
		// filters.add(new RowFilter(CompareOp.EQUAL, new RegexStringComparator("")));
		// filters.add(new KeyOnlyFilter());
		// filters.add(new FirstKeyOnlyFilter());

		// FilterList fl = new FilterList(filters, FilterList.Operator.MUST_PASS_ALL);

		final Scanner scanner = client.newScanner(table);
		scanner.setFamily("0");
		scanner.setTimeRange(new Date().getTime() - (60*60*1000), new Date().getTime());
		// scanner.setQualifier("1");
		scanner.setMaxNumRows(1);
		
		// Scan By key regex (Contains)
		// scanner.setKeyRegexp("23");

//		Date start = new Date();
//		// Scan Timestamp Minimal
//
//		scanner.setMinTimestamp(start.getTime() - (60000 * 60));
//System.out.println(start.getTime() - (60000 * 60));
//		// // Scan Timestamp Miximal
//		scanner.setMaxTimestamp(start.getTime());

		// scanner.isReversed();
//		scanner.setReversed(true);

		// Scan By Start and Stop
		// scanner.setStartKey("key0");
		// scanner.setStopKey("key7");

		// Scan Range Timestamp
		// scanner.setTimeRange(end.getTime()-60000*60,end.getTime());

		final ArrayList<ArrayList<KeyValue>> rows = scanner.nextRows().join();
	
		for (int i = 0; i < rows.size(); i++) {
			ArrayList<KeyValue> kvs = rows.get(i);
			
			for (int j = 0; j < kvs.size(); j++) {
				KeyValue data = kvs.get(j);
				Date d = new Date(data.timestamp());

				System.out.println("Key : " + Bytes.pretty(data.key()) + ", " + Bytes.pretty(data.qualifier()) + " : "
						+ Bytes.pretty(data.value()) + "\n, Timestamp : " + d + " / " + data.timestamp());
				System.out.println();
			}
		}
		client.shutdown().join();
		System.out.println("size = " + rows.size());
	}
}
