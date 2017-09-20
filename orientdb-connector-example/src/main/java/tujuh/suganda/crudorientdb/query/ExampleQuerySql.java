package tujuh.suganda.crudorientdb.query;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

import tujuh.orientconfig.Config;

public class ExampleQuerySql {
	static Config orient;

	public static void main(String[] args) {
		JsonParser parser = new JsonParser();

		ODatabaseDocumentTx db = orient.connectDatabaseDocumentTx();
		List<String> allData = new ArrayList<String>();
		List<ODocument> result = db.query(new OSQLSynchQuery<ODocument>("select from person"));
		System.out.println(result.size());
		for (ODocument oDocument : result) {
			allData.add(oDocument.getRecord().toJSON());
		}
		
		JsonArray json = parser.parse(allData.toString()).getAsJsonArray();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = gson.toJson(json);
		System.out.println(prettyJson);
		System.out.println("FINISH....");
	}

}
