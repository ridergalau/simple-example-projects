package tujuh.suganda.graph;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientEdge;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

import tujuh.orientconfig.Config;

public class CreateGraph {
	static Config orientConf;

	public static void main(String[] args) {
		OrientGraph GRAPH = orientConf.connectGraph();
		try {
			friendship(GRAPH);
			parentship(GRAPH); 
			carOwnerShip(GRAPH);
			System.out.println("Finish...");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static void friendship(OrientGraph GRAPH) {
		Vertex source = null;
		Vertex target = null;
		List<String> friend = new ArrayList<>();
		friend.add("keySadewa_keyJuno");
		friend.add("keyLaura_keyJuno");
		friend.add("keyRamon_keyJuno");
		friend.add("keyRider_keyIsabela");
		friend.add("keyRaisa_keyLaura");

		for (Iterator iterator = friend.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String[] part = key.split("_");
			// Query
			for (Vertex v : GRAPH.getVertices("Person._key", part[0])) {
				source = v;
				System.out.println("Found vertex: " + v);
			}

			for (Vertex v : GRAPH.getVertices("Person._key", part[1])) {
				target = v;
			}
			OrientEdge edge = GRAPH.addEdge("class:FriendEdge", source, target, null);
			edge.setProperty("timestamp", new Date().getTime());
			edge.setProperty("edge_id", key);
			GRAPH.commit();
			System.out.println("Created edge by #Rid: " + edge.getId());
		}
	}

	private static void parentship(OrientGraph GRAPH) {
		Vertex source = null;
		Vertex target = null;
		List<String> childRelation = new ArrayList<>();
		childRelation.add("keyRider_keyThomas");
		childRelation.add("keyRider_keyIsyana");
		childRelation.add("keyRider_keySadewa");
		childRelation.add("keyIsabela_keyJuno");
		childRelation.add("keyIsabela_keyIsco");

		for (Iterator iterator = childRelation.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String[] part = key.split("_");
			// Query
			for (Vertex v : GRAPH.getVertices("Person._key", part[0])) {
				source = v;
				System.out.println("Found vertex: " + v);
			}

			for (Vertex v : GRAPH.getVertices("Person._key", part[1])) {
				target = v;
			}
			OrientEdge edge = GRAPH.addEdge("class:ChildEdge", source, target, null);
			edge.setProperty("timestamp", new Date().getTime());
			edge.setProperty("edge_id", key);
			GRAPH.commit();
			System.out.println("Created edge by #Rid: " + edge.getId());
		}
	}

	private static void carOwnerShip(OrientGraph GRAPH) {
		Vertex source = null;
		Vertex target = null;
		List<String> carOwner = new ArrayList<>();
		carOwner.add("keyRider_B07SG");
		carOwner.add("keyLaura_B6822MLS");

		for (Iterator iterator = carOwner.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String[] part = key.split("_");
			// Query
			for (Vertex v : GRAPH.getVertices("Person._key", part[0])) {
				source = v;
				System.out.println("Found vertex: " + v);
			}

			for (Vertex v : GRAPH.getVertices("Car._key", part[1])) {
				target = v;
			}
			OrientEdge edge = GRAPH.addEdge("class:CarEdge", source, target, null);
			edge.setProperty("timestamp", new Date().getTime());
			edge.setProperty("edge_id", key);
			GRAPH.commit();
			System.out.println("Created edge by #Rid: " + edge.getId());
		}
	}

}