package tujuh.suganda.elastics;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class InsertByMap {
	static final Settings settings = Settings.builder().put("cluster.name", "my-elastics")
			.put("client.transport.sniff", true).put("client.transport.ignore_cluster_name", false)
			.put("client.transport.ping_timeout", "30s").put("client.transport.nodes_sampler_interval", "5s").build();

	public static void main(String[] args) {

		TransportClient client = null;
		try {
			client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		String id = "idRider";
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("user", "Rider Galau");
		jsonMap.put("datepost", "2017-19-09");
		jsonMap.put("address", "Jakarta");
		jsonMap.put("age", 34);
		jsonMap.put("status", "Maried");

		IndexResponse response = client.prepareIndex("myfirstindex", "myfirsttype", id)
				.setSource(jsonMap, XContentType.JSON).get();
		System.out.println("OKE");

	}
}
