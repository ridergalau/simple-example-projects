package tujuh.suganda.service;

import java.util.Collection;
import java.util.Date;
import com.arangodb.ArangoDB;
import tujuh.suganda.model.edges.HasCarEdge;
import tujuh.suganda.model.edges.HasChildEdge;
import tujuh.suganda.model.edges.HasFriendEdge;
import tujuh.suganda.model.vertices.CarModel;
import tujuh.suganda.model.vertices.PersonModel;

public class BuildGraph {
	static Date date = new Date();
	final static String SERVER = "localhost";
	final static int PORT = 8529;
	final static String USER = "root";
	final static String PASS = "aing";
	final static String DB = "dbperson";
	final static String COLL = "person";
	final static ArangoDB arango = new ArangoDB.Builder().host(SERVER).port(PORT).user(USER).password(PASS).build();

	public void insertPerson(Collection<PersonModel> person) {
		try {
			arango.db(DB).collection("person").insertDocuments(person);
			System.out.println("Add Person Succesful!");
		} catch (Exception e) {
			e.getMessage();
		}
		System.out.println("+------------------------+");
	}

	public void insertCar(Collection<CarModel> car) {
		try {
			arango.db(DB).collection("car").insertDocuments(car);
			System.out.println("Add Car Succesful!");
		} catch (Exception e) {
			e.getMessage();
		}
		System.out.println("+------------------------+");
	}

	public void HasChild(Collection<HasChildEdge> HasChild, String edge) {
		arango.db(DB).collection(edge).insertDocuments(HasChild);
		System.out.println("Add " + edge + " Succesful!");
		System.out.println("+------------------------+");

	}

	public void HasFriend(Collection<HasFriendEdge> hasFriend, String edge) {
		arango.db(DB).collection(edge).insertDocuments(hasFriend);
		System.out.println("Add " + edge + " Succesful! ");
		System.out.println("+------------------------+");

	}

	public void HasCar(Collection<HasCarEdge> hasCar, String edge) {
		arango.db(DB).collection(edge).insertDocuments(hasCar);
		System.out.println("Add " + edge + " Succesful! ");
		System.out.println("+------------------------+");

	}

}
