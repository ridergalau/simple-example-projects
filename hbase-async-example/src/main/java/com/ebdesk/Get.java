package com.ebdesk;

import java.util.ArrayList;

import org.hbase.async.Bytes;
import org.hbase.async.Config;
import org.hbase.async.GetRequest;
import org.hbase.async.HBaseClient;
import org.hbase.async.KeyValue;

public class Get {
	public static void main(String[] args) throws InterruptedException, Exception {
		
		Config conf = new Config();
		conf.overrideConfig("hbase.increments.buffer_size", "65535");
		conf.overrideConfig("hbase.zookeeper.quorum",
				"datanode01.cluster4.ph,namenode02.cluster4.ph,namenode01.cluster4.ph");
		conf.overrideConfig("hbase.zookeeper.znode.parent", "/hbase-unsecure");
		conf.overrideConfig("hbase.zookeeper.session.timeout", "5000");
		conf.overrideConfig("hbase.client.retries.number", "10");

		final HBaseClient client = new HBaseClient(conf);
		final byte[] table = "test".getBytes();
		GetRequest get = new GetRequest(table, "asin".getBytes());
		ArrayList<KeyValue> kvs = client.get(get).join();
		for (int i = 0; i < kvs.size(); i++) {
			KeyValue data = kvs.get(i);
			System.out.println(Bytes.pretty(data.key()));
			System.out.println(Bytes.pretty(data.value()));
			System.out.println(data.timestamp());
		}
		
		 client.shutdown().join();
	}
}
