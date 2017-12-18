package sample.wordcount;

/*https://www.edureka.co/blog/mapreduce-tutorial/
*
*/
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.fs.Path;

public class WordCount {
	public static void main(String[] args) throws Exception {

		 String in = args[0];
		 String out = args[1];

//		String in = "/home/tujuh/Documents/Example/news";
//		String out = "/home/tujuh/Documents/Example/outputExample";

		Configuration conf = new Configuration();
		Job job = new Job(conf, "Word Count Job");
		job.setJarByClass(WordCount.class);
		job.setMapperClass(Map.class);

		job.setReducerClass(Reduce.class);

		job.setOutputKeyClass(Text.class);

		job.setOutputValueClass(IntWritable.class);

		job.setInputFormatClass(TextInputFormat.class);

		job.setOutputFormatClass(TextOutputFormat.class);

		Path outputPath = new Path(out);

		FileInputFormat.addInputPath(job, new Path(in));
		FileOutputFormat.setOutputPath(job, new Path(out));

		outputPath.getFileSystem(conf).delete(outputPath);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

	// For execute : hadoop jar hadoop-mapreduce-example.jar
	// tujuh.suganda.wordcount.WordCount /sample/input /sample/output
}