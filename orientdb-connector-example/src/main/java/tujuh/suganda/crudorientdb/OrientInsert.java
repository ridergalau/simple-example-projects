package tujuh.suganda.crudorientdb;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

import tujuh.orientconfig.Config;

public class OrientInsert {
	static Config orientConf;

	public static void main(String[] args) {
		OrientGraph graph = orientConf.connectGraph();

		Vertex vertex = graph.addVertex("class:Person");
		// _key has been indexed
		vertex.setProperty("_key", "suganda7");
		vertex.setProperty("name", "Sulung Suganda");
		graph.commit();
		System.out.println("Vertex Created By Id : " + vertex.getId());
		
		
		// Insert By Document API
		/*
		ODatabaseDocumentTx db = orientConf.connectDatabaseDocumentTx();

		ODocument person = new ODocument("Person");
		person.field( "name", "Gaudi" );
		person.field( "location", "Madrid" );
		person.save();
		db.commit();
		System.out.println("Vertex Created  =>  " + person.toJSON());*/
		
	}
}
