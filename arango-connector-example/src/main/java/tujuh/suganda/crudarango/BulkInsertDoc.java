package tujuh.suganda.crudarango;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.arangodb.ArangoDB;

import tujuh.suganda.model.vertices.CarModel;
import tujuh.suganda.model.vertices.PersonModel;

public class BulkInsertDoc {
	static Date date = new Date();
	final static String SERVER = "localhost";
	final static int PORT = 8529;
	final static String USER = "root";
	final static String PASS = "aing";
	final static String DB = "dbperson";
	final static String COLL = "car";

	public static void main(String[] args) {
		ArangoDB arango = new ArangoDB.Builder().host(SERVER).port(PORT).user(USER).password(PASS).build();
		try {
//			Collection<PersonModel> docs = getExampleData();
//			System.out.println(docs);
//			arango.db(DB).collection(COLL).insertDocuments(docs);
			Collection<CarModel> docs = exampleCar();
			System.out.println(docs);
			arango.db(DB).collection(COLL).insertDocuments(docs);
			System.out.println("BULK INSERT FINISH!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Collection<CarModel> exampleCar() {
		Collection<CarModel> car = new ArrayList<CarModel>();
		CarModel modelCar1 = new CarModel("B07SG", "BMW", "B07SG", "RED", "EUROPE", "Sport");
		CarModel modelCar2 = new CarModel("B6822MLS", "CARRY BUEUK", "B6822MLS", "HEJO DAUN CAU", "CICALENGKA",
				"PICK-UP");
		car.add(modelCar1);
		car.add(modelCar2);
		return car;
	}
	
	public static Collection<PersonModel> getExampleData() {
		Collection<PersonModel> AllData = new ArrayList<PersonModel>();
		String gender = "";
		for (int i = 1; i < 10; i++) {
			Map<String, String> address = new HashMap<String, String>();
			address.put("zip code", "46523" + 1);
			address.put("district", "TheCity " + 1);
			address.put("province", "The Province " + 1);
			address.put("village", "The Vilage " + 1);
			List<String> hobies = new LinkedList<String>();
			hobies.add("example hobi " + 1);
			hobies.add("Football");
			hobies.add("Swim");

			if (i % 2 == 1)
				gender = "Male";
			else
				gender = "Famale";
			
			PersonModel person = new PersonModel("bulkperson" + i, "Rider" + i, "Galau" + i, address, hobies,
					BigInteger.valueOf(date.getTime()), gender, "Rgalau_" + i + "@abcd.com");
			AllData.add(person);
		}
		return AllData;
	}
}
