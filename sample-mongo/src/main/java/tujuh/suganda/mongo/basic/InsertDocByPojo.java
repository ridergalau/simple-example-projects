package tujuh.suganda.mongo.basic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import tujuh.suganda.mongo.model.SimpleModel;

public class InsertDocByPojo {
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB db = mongoClient.getDB("mydb");
		DBCollection col = db.getCollection("test");
		SimpleModel sModel = exampleData();
		String jsonString = mapper.writeValueAsString(sModel);
		
		System.out.println("Will be save -> "+jsonString);
		DBObject dbObject = (DBObject) JSON.parse(jsonString);
		col.insert(dbObject);
		mongoClient.close();
		System.out.println("---DONE---");
	}

	private static SimpleModel exampleData() {
		SimpleModel sModel = new SimpleModel();
		sModel.set_id("id07");
		sModel.setName("Rider Galau");
		sModel.setAge(23);

		List<String> parent = new ArrayList<>();
		parent.add("my Parent ");
		parent.add("parent 2");
		sModel.setParent(parent);

		Map<String, String> addres = new HashMap<String, String>();
		addres.put("city", "Ciamis");
		addres.put("zipcode", "46252");
		addres.put("no", "7");
		sModel.setAddres(addres);

		List<String> school = new ArrayList<>();
		school.add("SD X 2");
		school.add("SMP X 3");
		school.add("SMA X18 ");

		List<String> friend = new ArrayList<>();
		friend.add("Jaka");
		friend.add("Isabela");
		friend.add("Laura");
		friend.add("Anni");

		Map<String, List<String>> other = new HashMap<String, List<String>>();
		other.put("school", school);
		other.put("friend", friend);
		sModel.setOtherdata(other);
		
		Map<String, String> dataOther = new HashMap<String, String>();
		dataOther.put("fb", "RiderGalau");
		dataOther.put("twitter", "@SugandaSulung");
		dataOther.put("whatsapp", "072169712709");
		
		Map<String,Map<String,String>> other2 = new HashMap<String, Map<String,String>>();
		other2.put("account", dataOther);
		sModel.setOtherdata2(other2);
		return sModel;
	}
}