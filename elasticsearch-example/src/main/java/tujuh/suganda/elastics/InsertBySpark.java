package tujuh.suganda.elastics;

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

public class InsertBySpark implements scala.Serializable {
	private static final ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) {
		SparkConf sparkConf = new SparkConf().setAppName("TestElasticSearchSpark").set("es.index.auto.create", "true")
				.set("spark.driver.allowMultipleContexts", "true").set("es.nodes", "localhost:9200")
				.setMaster("local[2]");

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
