package tujuh.suganda.crudorientdb.query;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientEdge;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

import tujuh.orientconfig.Config;

public class QueryAndBuildGraph {
	static Config orientConf;
	public static void main(String[] args) {
		OrientGraph graph = orientConf.connectGraph();
		Vertex source = null;
		Vertex target = null;

		for (Vertex v : graph.getVertices("Person._key", "keyRider")) {
			source = v;
			System.out.println("Found vertex: " + v);
		}

		for (Vertex v : graph.getVertices("Person._key", "keyIsabela")) {
			target = v;
		}
		
		//add Edge
		OrientEdge edge = graph.addEdge("class:FriendEdge", source, target, null);
		edge.setProperty("timestamp", 2321312);
		edge.setProperty("edge_id", "idtesh");
		graph.commit();
		System.out.println(edge.getId());
	}
}