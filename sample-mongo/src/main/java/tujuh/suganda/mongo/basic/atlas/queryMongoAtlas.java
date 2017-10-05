package tujuh.suganda.mongo.basic.atlas;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class queryMongoAtlas {
public static void main(String[] args) {
	try {
		MongoClientURI uri = new MongoClientURI(
				"mongodb://root:<PASS>@galaucluster-shard-00-00-mweoa.mongodb.net:27017,galaucluster-shard-00-01-mweoa.mongodb.net:27017,galaucluster-shard-00-02-mweoa.mongodb.net:27017/my_app?ssl=true&replicaSet=GalauCluster-shard-0&authSource=admin");

		MongoClient mongoClient = new MongoClient(uri);
		
		DB db = mongoClient.getDB("my_app");	
	
		DBCollection col = db.getCollection("mencoba");	
		DBCursor mydata = col.find();//dengan Limit
		int i = 1;	
		  while (mydata.hasNext()) { 
		     System.out.println("Document ke - "+i); 
		     System.out.println(mydata.next()); 
		     i++;
		  }
		System.out.println("ok");
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
