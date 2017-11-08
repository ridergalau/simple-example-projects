package tujuh.suganda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.*;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import tujuh.suganda.model.SimpleModel;

public class FlatMap {
	static ObjectMapper om = new ObjectMapper();
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setMaster("local[3]").setAppName("SparkWordCountExample");

		JavaSparkContext sc = new JavaSparkContext(conf);
		SQLContext sqlContext = new SQLContext(sc);

		JavaRDD<String> lines = sc.textFile("/home/tujuh/Documents/myjsonflat.json");
		JavaRDD<SimpleModel> words = lines.flatMap(new FlatMapFunction<String, SimpleModel>() {
			public Iterator<SimpleModel> call(String s) {
				System.out.println("Original "+s);
				JSONObject json = new JSONObject(s);
				JSONArray array = json.getJSONArray("topic");
				List<SimpleModel> ArrayTw = new ArrayList<>();

				for (int i = 0; i < array.length(); i++) {
					SimpleModel xx = new SimpleModel();
					xx.setText(json.getString("text"));
					xx.setId(array.getString(i));
					ArrayTw.add(xx);
				}

				return ArrayTw.iterator();
			}
		});
		
		words.foreach(new VoidFunction<SimpleModel>() {			
			@Override
			public void call(SimpleModel t) throws Exception {
				String json = om.writeValueAsString(t);
				System.out.println(json);
//				System.out.println(t.getTopic());
//				System.out.println(t.getId());
			}
		});


	}
}
