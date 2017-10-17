package tujuh.suganda.mongo.spark;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.SparkSession;
import org.bson.Document;

import com.mongodb.spark.MongoSpark;

import scala.Tuple2;

public class SparkGetMongo {
	final static String COLL = "ipa_clustring";
	final static SparkSession SPARK = SparkSession.builder().master("local[2]").appName("MongoSparkLoad")
			.config("spark.mongodb.input.uri", "mongodb://localhost:27017/stream." + COLL)
			.config("spark.mongodb.output.uri", "mongodb://localhost:27017/stream." + COLL)
			.config("mongo.input.limit","10").getOrCreate();

	final static JavaSparkContext JSC = new JavaSparkContext(SPARK.sparkContext());

	public static void main(String[] args) {

		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.ERROR);

		JavaRDD<Document> rdd = MongoSpark.load(JSC).filter(new Function<Document, Boolean>() {
			@Override
			public Boolean call(Document value) throws Exception {
				String jsonValue = value.toJson();
				org.json.JSONObject objeck = null;
				try {
					objeck = new org.json.JSONObject(jsonValue);
				} catch (Exception e) {

				}
				return objeck != null;
			}
		});

		
		JavaPairRDD<Document, Iterable<Document>> pairs = rdd.mapToPair(new PairFunction<Document, Document, Document>() {
			@Override
			public Tuple2<Document, Document> call(Document arg0) throws Exception {
				String id = "";
				String tweet = "";
				try {
					String jsonValue = arg0.toJson();
					org.json.JSONObject object = new org.json.JSONObject(jsonValue);
					id = object.get("id_str").toString();
					tweet = object.get("text").toString();					
				} catch (Exception e) {
					// TODO: handle exception
				}

				return new Tuple2(id,tweet);
			}	
		}).filter(new Function<Tuple2<Document,Document>, Boolean>() {
			@Override
			public Boolean call(Tuple2<Document, Document> arg0) throws Exception {
				// TODO Auto-generated method stub
				String json = com.mongodb.util.JSON.serialize(arg0._2());
				return json.contains("Persib")||json.contains("Persija")&&!json.equals("");
			}
		}).groupByKey();
		
		pairs.foreach(new VoidFunction<Tuple2<Document,Iterable<Document>>>() {

			@Override
			public void call(Tuple2<Document, Iterable<Document>> arg0) throws Exception {
			
				System.out.println("DATA ==== \n\n\n"+arg0);
				
			}
		}); 
		
		
		/*// print first data
		System.out.println("first -> " + rdd.first());
		// count data Rdds
		System.out.println("TOTAL -> " + rdd.count());

		// Loop rdd
		rdd.foreach(new VoidFunction<Document>() {
			public void call(Document value) throws Exception {
				System.out.println("value " + value.toJson());
			}
		});*/
		System.out.println("Finish....");
		JSC.close();
	}
}
