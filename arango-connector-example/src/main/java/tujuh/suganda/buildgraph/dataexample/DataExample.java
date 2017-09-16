package tujuh.suganda.buildgraph.dataexample;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import tujuh.suganda.model.edges.HasCarEdge;
import tujuh.suganda.model.edges.HasChildEdge;
import tujuh.suganda.model.edges.HasFriendEdge;
import tujuh.suganda.model.vertices.CarModel;
import tujuh.suganda.model.vertices.PersonModel;

public class DataExample {
	static Date date = new Date();

	public static Collection<HasCarEdge> carRelations() {
		Collection<HasCarEdge> data = new ArrayList<HasCarEdge>();
		List<String> friend = new ArrayList<>();
		friend.add("keyRider_B07SG");
		friend.add("keyLaura_B6822MLS");

		for (Iterator iterator = friend.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String[] part = key.split("_");
			HasCarEdge child = new HasCarEdge(key, "person/" + part[0], "car/" + part[1],
					BigInteger.valueOf(date.getTime()));
			data.add(child);
		}
		return data;
	}

	public static Collection<HasFriendEdge> friendRelations() {
		Collection<HasFriendEdge> data = new ArrayList<HasFriendEdge>();
		List<String> friend = new ArrayList<>();
		friend.add("keySadewa_keyJuno");
		friend.add("keyLaura_keyJuno");
		friend.add("keyRamon_keyJuno");
		friend.add("keyRider_keyIsabela");
		friend.add("keyRaisa_keyLaura");

		for (Iterator iterator = friend.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String[] part = key.split("_");
			HasFriendEdge child = new HasFriendEdge(key, "person/" + part[0], "person/" + part[1],
					BigInteger.valueOf(date.getTime()));
			data.add(child);
		}
		return data;
	}

	public static Collection<HasChildEdge> childRelations() {
		Collection<HasChildEdge> data = new ArrayList<HasChildEdge>();
		List<String> childRelation = new ArrayList<>();
		childRelation.add("keyRider_keyThomas");
		childRelation.add("keyRider_keyIsyana");
		childRelation.add("keyRider_keySadewa");
		childRelation.add("keyIsabela_keyJuno");
		childRelation.add("keyIsabela_keyIsco");

		for (Iterator iterator = childRelation.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String[] part = key.split("_");
			HasChildEdge child = new HasChildEdge(key, "person/" + part[0], "person/" + part[1],
					BigInteger.valueOf(date.getTime()));
			data.add(child);
		}
		return data;
	}

	public static Collection<PersonModel> examplePerson() {
		Collection<PersonModel> AllDataPerson = new ArrayList<PersonModel>();
		String[] arr = { "Male", "Famela", "Other" };
		Random random = new Random();
		int select = random.nextInt(arr.length);
		List<String> persons = Arrays.asList("Rider", "Isabela", "Thomas", "Juno", "Sadewa", "Isyana", "Raisa", "Ramon",
				"Isco", "Laura");

		for (Iterator iterator = persons.iterator(); iterator.hasNext();) {

			String name = (String) iterator.next();
			Map<String, String> address = new HashMap<String, String>();
			address.put("zip code", "46523");
			address.put("district", "TheCity ");
			address.put("province", "The Province ");
			address.put("village", "The Vilage ");
			List<String> hobies = new LinkedList<String>();
			hobies.add("example hobi");
			hobies.add("Football");
			hobies.add("Swim");

			PersonModel person = new PersonModel("key" + name, name, "last" + name, address, hobies,
					BigInteger.valueOf(date.getTime()), arr[select], name + "@Example.oke");
			AllDataPerson.add(person);
		}
		return AllDataPerson;
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
}
