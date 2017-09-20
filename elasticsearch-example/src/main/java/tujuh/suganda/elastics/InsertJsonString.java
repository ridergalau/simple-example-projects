package tujuh.suganda.elastics;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class InsertJsonString {
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

		String json = "{" + "\"user\":\"Maman\"," + "\"datepost\":\"2013-01-30\"," + "\"address\":\"Cipaku\","
				+ "\"age\":32," + "\"status\":\"Test OKE ---------------!\"" + "}";

		IndexResponse response = client.prepareIndex("myfirstindex", "myfirsttype", "idPerson1")
				.setSource(json, XContentType.JSON).get();
		System.out.println("OKE");

	}
}
