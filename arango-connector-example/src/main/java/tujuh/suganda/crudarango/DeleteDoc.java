package tujuh.suganda.crudarango;

import java.util.Date;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;

public class DeleteDoc {
	static Date date = new Date();
	final static String SERVER = "localhost";
	final static int PORT = 8529;
	final static String USER = "root";
	final static String PASS = "aing";
	final static String DB = "dbperson";
	final static String COLL = "person";
	static ArangoDB arango = new ArangoDB.Builder().host(SERVER).port(PORT).user(USER).password(PASS).build();

	public static void main(String[] args) {

		try {
			ArangoDatabase db = arango.db(DB);
			ArangoCollection collection = db.collection(COLL);
			collection.deleteDocument("person1");
			System.out.println("Delete Successfull!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
