package second;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyDriver {
	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		Job job = Job.getInstance(conf, "Inverted index");
		job.setJarByClass(second.MyDriver.class);
		// TODO: specify mapper 
		job.setMapperClass(MyMapper.class);
		// TODO: specify a reducer
		job.setReducerClass(MyReducer.class);

		// TODO: specify output types
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		// TODO: specify input and output DIRECTORIES (not files)
		FileInputFormat.setInputPaths(job, new Path("/home/swapnil/Desktop/removetag/input"));
		FileOutputFormat.setOutputPath(job, new Path("/home/swapnil/Desktop/removetag/output"));
		
		if(fs.exists(new Path("/home/swapnil/Desktop/removetag/output"))){
			   /*If exist delete the output path*/
			   fs.delete(new Path("/home/swapnil/Desktop/removetag/output"),true);
			}
		if (!job.waitForCompletion(true))
			return;
	}

}
