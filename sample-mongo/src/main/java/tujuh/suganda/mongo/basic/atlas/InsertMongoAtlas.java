package tujuh.suganda.mongo.basic.atlas;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

public class InsertMongoAtlas {
	public static void main(String[] args) {

		try {
			MongoClientURI uri = new MongoClientURI(
					"mongodb://root:<PASS>@galaucluster-shard-00-00-mweoa.mongodb.net:27017,galaucluster-shard-00-01-mweoa.mongodb.net:27017,galaucluster-shard-00-02-mweoa.mongodb.net:27017/my_app?ssl=true&replicaSet=GalauCluster-shard-0&authSource=admin");

			MongoClient mongoClient = new MongoClient(uri);

			DB db = mongoClient.getDB("my_app");

			DBCollection col = db.getCollection("mycol");

			String json = "{'_id':'tarabas','name':'SUGANDA', 'age':24}";
			DBObject dbObject = (DBObject) JSON.parse(json);
			col.insert(dbObject);
			System.out.println("Insert Json Berhasil!");

			// database.createCollection("mencoba");

			System.out.println("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}