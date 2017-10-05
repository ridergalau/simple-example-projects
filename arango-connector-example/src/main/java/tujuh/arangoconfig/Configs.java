package tujuh.arangoconfig;

import com.arangodb.ArangoDB;

public class Configs {
	final static String SERVER = "localhost";
	final static int PORT = 8529;
	final static String USER = "root";
	final static String PASS = "aing";
	final static String DB = "dbperson";
	final static String COLL = "person";

	public static ArangoDB connect() {
		ArangoDB arango = new ArangoDB.Builder().host(SERVER).port(PORT).user(USER).password(PASS).build();
		return arango;
	}
}
