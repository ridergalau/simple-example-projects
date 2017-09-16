package tujuh.suganda.crudarango;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;

import tujuh.suganda.model.vertices.PersonModel;

public class UpdateDoc {
	static Date date = new Date();
	final static String SERVER = "localhost";
	final static int PORT = 8529;
	final static String USER = "root";
	final static String PASS = "aing";
	final static String DB = "dbperson";
	final static String COLL = "person";

	public static void main(String[] args) {
		Date date = new Date();
		Map<String, String> address = new HashMap<String, String>();
		address.put("zip code", "46523");
		address.put("district", "Ciamis");
		address.put("province", "Jaw Barat");
		address.put("village", "Sindang");
		List<String> hobies = new LinkedList<String>();
		hobies.add("sing");
		hobies.add("Football");
		hobies.add("Swim");
		try {
			ArangoDB arango = new ArangoDB.Builder().host(SERVER).port(PORT).user(USER).password(PASS).build();
			ArangoDatabase db = arango.db(DB);
			ArangoCollection collection = db.collection(COLL);
			PersonModel person = new PersonModel("person1", "RiderS", "GalauS", address, hobies,
					BigInteger.valueOf(date.getTime()), "Male", "Rgalau@abcd.com");
			collection.updateDocument("person1", person);
			System.out.println("Update Successfull!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
