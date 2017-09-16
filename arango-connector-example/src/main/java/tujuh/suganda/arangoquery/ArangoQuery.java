package tujuh.suganda.arangoquery;

/*Example Query 
 * 
 * 2017/09/16
 * 
 * By RiderGalau
 * */

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.BaseDocument;

public class ArangoQuery {
	public static void main(String[] args) {
		ArangoDB arango = new ArangoDB.Builder().host("127.0.0.1").port(8529).user("root").password("aing").build();
		List<String> list = new ArrayList<>();

		try {
			// #1
			// -------------------------------------------------------------//
			/*
			 * String query = "FOR t IN person FILTER t.email==@email RETURN t"; Map<String,
			 * Object> bindVars = new MapBuilder().get(); bindVars.put("email",
			 * "Rgalau_6@abcd.com"); ArangoCursor<BaseDocument> cursor =
			 * arango.db("dbperson").query(query, bindVars, null, BaseDocument.class);
			 */
			// -------------------------------------------------------------//

			// #2
			String query1 = "FOR t IN person FILTER t.email=='Rgalau_6@abcd.com' RETURN t";
			ArangoCursor<BaseDocument> cursor = arango.db("dbperson").query(query1, null, null, BaseDocument.class);

			// #1 is equivalent with #2
			// Example AQL for graph(Graql) : src/main/resources/example-graph-ql.txt

			cursor.forEachRemaining(aDocument -> {
				JSONObject obj = new JSONObject(aDocument.getProperties());
				list.add(obj.toString());
			});

			BaseDocument myDocument = arango.db("dbperson").collection("person").getDocument("45893",
					BaseDocument.class);
			System.out.println(" - " + myDocument.toString());

			System.out.println(list);
		} catch (ArangoDBException e) {
			System.err.println("Failed to execute query. " + e.getMessage());
		}

	}
}
