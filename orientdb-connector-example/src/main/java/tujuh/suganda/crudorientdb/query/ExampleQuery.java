package tujuh.suganda.crudorientdb.query;

import java.awt.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.query.OResultSet;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

import tujuh.orientconfig.Config;

public class ExampleQuery {
	static Config orientConf;

	public static void main(String[] args) {
		OrientGraph graph = orientConf.connectGraph();
		Map<String, String> myMp = new HashMap<String, String>();
		for (Vertex v : graph.getVertices("Person._key", "keyIsabela")) {
			Set<String> properties = v.getPropertyKeys();
			for (String s : properties) {
				myMp.put(s, v.getProperty(s).toString());
			}
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(myMp);
		System.out.println(json);
				
	}
}