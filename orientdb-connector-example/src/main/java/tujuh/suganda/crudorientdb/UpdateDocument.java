package tujuh.suganda.crudorientdb;

import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

import tujuh.orientconfig.Config;

public class UpdateDocument {
	static Config orientConf;

	public static void main(String[] args) {
		OrientGraph graph = orientConf.connectGraph();
		String key = "Gaudi";
		try (OObjectDatabaseTx database = orientConf.connectDatabase()) {
			database.command(new OCommandSQL("update `Person` set location='TASIK' where name='" + key + "'"))
					.execute();
			database.commit();
			System.out.println("OKE");
		}

	}
}
