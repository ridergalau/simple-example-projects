package tujuh.suganda.exampledata;

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

import tujuh.suganda.model.CarEdge;
import tujuh.suganda.model.ChildEdge;
import tujuh.suganda.model.FriendEdge;
import tujuh.suganda.model.Person;
import tujuh.suganda.model.Car;

public class DataExample {
	static Date date = new Date();

	public static Collection<CarEdge> carRelations() {
		Collection<CarEdge> data = new ArrayList<CarEdge>();
		List<String> carOwner = new ArrayList<>();
		carOwner.add("keyRider_B07SG");
		carOwner.add("keyLaura_B6822MLS");

		for (Iterator iterator = carOwner.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String[] part = key.split("_");
			CarEdge child = new CarEdge(key, "person/" + part[0], "car/" + part[1], BigInteger.valueOf(date.getTime()));
			data.add(child);
		}
		return data;
	}

	public static Collection<FriendEdge> friendRelations() {
		Collection<FriendEdge> data = new ArrayList<FriendEdge>();
		List<String> friend = new ArrayList<>();
		friend.add("keySadewa_keyJuno");
		friend.add("keyLaura_keyJuno");
		friend.add("keyRamon_keyJuno");
		friend.add("keyRider_keyIsabela");
		friend.add("keyRaisa_keyLaura");

		for (Iterator iterator = friend.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String[] part = key.split("_");
			FriendEdge child = new FriendEdge(key, "person/" + part[0], "person/" + part[1],
					BigInteger.valueOf(date.getTime()));
			data.add(child);
		}
		return data;
	}

	public static Collection<ChildEdge> childRelations() {
		Collection<ChildEdge> data = new ArrayList<ChildEdge>();
		List<String> childRelation = new ArrayList<>();
		childRelation.add("keyRider_keyThomas");
		childRelation.add("keyRider_keyIsyana");
		childRelation.add("keyRider_keySadewa");
		childRelation.add("keyIsabela_keyJuno");
		childRelation.add("keyIsabela_keyIsco");

		for (Iterator iterator = childRelation.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String[] part = key.split("_");
			ChildEdge child = new ChildEdge(key, "person/" + part[0], "person/" + part[1],
					BigInteger.valueOf(date.getTime()));
			data.add(child);
		}
		return data;
	}

	public static Collection<Person> examplePerson() {
		Collection<Person> AllDataPerson = new ArrayList<Person>();
		String[] arr = { "Male", "Female" };
		
		
		List<String> persons = Arrays.asList("Rider", "Isabela", "Thomas", "Juno", "Sadewa", "Isyana", "Raisa", "Ramon",
				"Isco", "Laura");

		for (Iterator iterator = persons.iterator(); iterator.hasNext();) {
			Random random = new Random();
			int select = random.nextInt(arr.length);
			
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
			Person person = new Person("key" + name, name, "last" + name, address, hobies,
					BigInteger.valueOf(date.getTime()), arr[select], name + "@Example.oke");
			AllDataPerson.add(person);
		}
		return AllDataPerson;
	}

	public static Collection<Car> exampleCar() {
		Collection<Car> car = new ArrayList<Car>();
		Car modelCar1 = new Car("B07SG", "BMW", "B07SG", "RED", "EUROPE", "Sport");
		Car modelCar2 = new Car("B6822MLS", "CARRY BUEUK", "B6822MLS", "HEJO DAUN CAU", "CICALENGKA", "PICK-UP");
		car.add(modelCar1);
		car.add(modelCar2);
		return car;
	}
}
