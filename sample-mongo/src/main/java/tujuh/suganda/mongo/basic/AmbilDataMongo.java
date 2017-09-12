package tujuh.suganda.mongo.basic;

import java.util.Iterator;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class AmbilDataMongo {
	public static void main(String[] args) {
		try {
			MongoClient cli = new MongoClient("192.168.20.78", 1712);
			DB db = cli.getDB("test");
			DBCollection collection = db.getCollection("data");
//			DBCursor cursor = collection.find().limit(10);
			
			BasicDBObject allQuery = new BasicDBObject();
			BasicDBObject fields = new BasicDBObject();
			fields.put("timestamp", "1490770697153");

			DBCursor cursor = collection.find(allQuery, fields);
	          	          
			int i = 1;
			while (cursor.hasNext()) {
				DBObject data = cursor.next();
				String med_name = data.get("data").toString(); // get Element
			System.out.println(i + ". " + med_name);
				i++;
			}

			// BasicDBObject query = new BasicDBObject("type", "no_hp");

			// DBCursor cursor = collection.find();
			// while (cursor.hasNext()) {
			// DBObject updateDocument = cursor.next();
			// updateDocument.put("likes","200");
			// collection.update(updateDocument, updateDocument);
			// }
			// System.out.println("Document updated successfully");

			// DBCursor docs = collection.find().limit(10); //Limit hasil
			//
			// DBCursor cursor = collection.find().limit(1);
			// int i = 1;
			// while (cursor.hasNext()) {
			// System.out.println("Inserted Document: "+i);
			// System.out.println(cursor.next());
			// i++;
			// }
			//
			// int x=1;
			// while (docs.hasNext()) {
			// DBObject data = docs.next();
			// String med_name = data.get("nama").toString(); //get Element Json
			// System.out.println(x+". "+med_name);
			// x++;
			// }
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
