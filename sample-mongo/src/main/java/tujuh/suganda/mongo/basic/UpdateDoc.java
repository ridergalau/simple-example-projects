package tujuh.suganda.mongo.basic;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class UpdateDoc {
	public static void main(String[] args) {
		MongoClient cli = new MongoClient("localhost", 27017);
		DB db = cli.getDB("pegawai");
		DBCollection collection = db.getCollection("datanya");
		try {
			BasicDBObject updatenya = new BasicDBObject();
			updatenya.put("name", "TIM TARABAS");
			updatenya.put("age", 30);
			updatenya.put("alamat", "Ciamis");
			updatenya.put("e-mail", "ani.ini@gmail.com");
			BasicDBObject CariDataYangDiubah = new BasicDBObject().append("_key", "peg_1");
			collection.update(CariDataYangDiubah, updatenya);
			System.out.println("Data TerUpdate!");
		} catch (Exception e) {
			System.out.println("GAGAL TerUpdate!");}
	}
}
