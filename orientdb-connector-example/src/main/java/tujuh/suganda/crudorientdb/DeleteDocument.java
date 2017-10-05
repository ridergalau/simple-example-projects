package tujuh.suganda.crudorientdb;

import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

import tujuh.orientconfig.Config;

public class DeleteDocument {
	static Config orientConf;

	public static void main(String[] args) {
	
		
		
	OrientGraph graph = orientConf.connectGraph();
	String key = "Sulung Suganda";
	try (OObjectDatabaseTx database = orientConf.connectDatabase()) {
		database.command(new OCommandSQL("delete vertex `Person` where name='" + key + "'"))
				.execute();
		database.commit();
		System.out.println(key+" has been Deleted!");
	}
}}
