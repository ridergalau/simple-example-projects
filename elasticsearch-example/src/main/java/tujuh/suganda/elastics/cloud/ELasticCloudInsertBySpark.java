package tujuh.suganda.elastics.cloud;

import java.io.Serializable;

import org.apache.derby.impl.sql.catalog.SYSFOREIGNKEYSRowFactory;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.hadoop.cfg.ConfigurationOptions;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;
import org.spark_project.guava.collect.ImmutableMap;

import tujuh.suganda.model.MyModel;

public class ELasticCloudInsertBySpark implements scala.Serializable {
	private static final ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) {
		String clusterId = "OuQusKcxTSyXBxFGUqIG0Q"; // Your cluster ID here
		String region = "gcp-europe-west1"; // Your region here
		String enableSsl = "true";
		
//		a1bbaa75f3a2e42499c13173dd23f27b.europe-west1.gcp.cloud.es.io:9243
		
		SparkConf sparkConf = new SparkConf().setAppName("TestElasticSearchSpark")
				.setMaster("local[1]")
				  .set("spark.es.nodes","ac2a167fc139177c6861c0d6dd5f64be.ap-southeast-1.aws.found.io")
				  .set("spark.es.port","9243")
//				  .set("spark.es.nodes.discovery","false")
//				  .set("spark.es.nodes.client.only","false")
				  .set("spark.es.nodes.wan.only","true")
				  .set("spark.es.net.http.auth.user","elastic")
				  .set("spark.es.net.http.auth.pass","iphYRufLZFauIORDnitEkUvr");
		
//		SparkConf sparkConf = new SparkConf().setAppName("TestElasticSearchSpark")
//				.set("es.index.auto.create", "true")
//				.set("spark.driver.allowMultipleContexts", "true")
////				.set("es.nodes", "localhost:9200")
//				.set("transport.ping_schedule", "5s")
//				.set("cluster.name", clusterId)
//				.set("action.bulk.compress", "false")
//				.set("shield.transport.ssl", enableSsl)
//				.set("request.headers.X-Found-Cluster", clusterId)
//				.set("shield.user", "root:rahasia07")
//				.set("transport.sniff", "false")
//				.set("es.nodes.wan.only", "true")
//				.setMaster("local[2]");

	
		
		JavaSparkContext sc = new JavaSparkContext(sparkConf);

		JavaRDD<String> dataRdd = sc.textFile("src/main/resources/exampledata.csv").map(new Function<String, String>() {
			public String call(String value) throws Exception {
				String json = "";
				MyModel model = null;
				try {
					String[] data = value.split(",");
					model = new MyModel(data[0], data[0], data[1], Integer.valueOf(data[2]), data[3],
							Integer.valueOf(data[4]), Integer.valueOf(data[5]), Integer.valueOf(data[6]), data[7],
							data[8], Integer.valueOf(data[9]), data[10], data[11]);
					json = mapper.writeValueAsString(model);
				} catch (Exception e) {
				}
				return json;
			}
		});

		try {
			System.out.println("Saving . . . . .");

			JavaEsSpark.saveJsonToEs(dataRdd, "myfirstindex/myfirsttype", ImmutableMap.of("es.mapping.id", "id"));

			// JavaEsSpark.saveJsonToEs(dataRdd, "myfirstindex/myfirsttype");
			System.out.println("Finish....");
		} catch (Exception e) {
			e.getMessage();
		}

	}

}
