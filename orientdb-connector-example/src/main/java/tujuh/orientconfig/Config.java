package tujuh.orientconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

import tujuh.suganda.model.Person;

public class Config {
	private static final Logger log = LoggerFactory.getLogger(Config.class);

	final static String SERVER = "127.0.0.1";
	final static String DB = "simplegraph";
	final static String USER = "root";
	final static String PASSWORD = "aing";
	final static String PORT = "2480";

	public static OObjectDatabaseTx connectDatabase() {
		OObjectDatabaseTx db = new OObjectDatabaseTx("remote:" + SERVER + "/" + DB).open(USER, PASSWORD);

		// Register All classes from package
		db.getEntityManager().registerEntityClasses("tujuh.suganda.model");

		// Register class
		// The Class name will be use for declare the name of collections/class
		// db.getEntityManager().registerEntityClass(Person.class);
		// db.getEntityManager().registerEntityClass(Car.class);
		return db;
	}

	public static OrientGraph connectGraph() {
		final OrientGraph GRAPH = new OrientGraph("remote:" + SERVER + "/" + DB, USER, PASSWORD);
		return GRAPH;
	}

	public static ODatabaseDocumentTx connectDatabaseDocumentTx() {
		ODatabaseDocumentTx db = new ODatabaseDocumentTx("remote:" + SERVER + "/" + DB);
		db.open(USER, PASSWORD);
		return db;
	}

	public static ODatabaseDocument connectDBdocTx() {
		ODatabaseDocument dbDoc = new ODatabaseDocumentTx("remote:" + SERVER + "/" + DB);
		dbDoc.open(USER, PASSWORD);
		return dbDoc;
	}

}
