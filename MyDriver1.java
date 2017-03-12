package second;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;

public class MyDriver1 {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		Job job1 = Job.getInstance(conf, "IDF");
		job1.setJarByClass(second.MyDriver1.class);
		// TODO: specify a mapper
		job1.setMapperClass(MyMapper1.class);
		// TODO: specify a reducer
		job1.setReducerClass(MyReducer1.class);

		// TODO: specify output types
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(Text.class);

		// TODO: specify input and output DIRECTORIES (not files)
		FileInputFormat.setInputPaths(job1, new Path("/home/swapnil/Desktop/i"));
		FileOutputFormat.setOutputPath(job1, new Path("/home/swapnil/Desktop/output"));
		if(fs.exists(new Path("/home/swapnil/Desktop/output"))){
			   /*If exist delete the output path*/
			   fs.delete(new Path("/home/swapnil/Desktop/output"),true);
			}
		/*Job 1 ends */
		Job job2 = Job.getInstance(conf, "TF");
		job2.setJarByClass(second.MyDriver1.class);
		// TODO: specify a mapper
		job2.setMapperClass(MyMapper2.class);
		// TODO: specify a reducer
		job2.setReducerClass(MyReducer2.class);
		// TODO: specify output types
		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(DoubleWritable.class);

		// TODO: specify input and output DIRECTORIES (not files)
		FileInputFormat.setInputPaths(job2, new Path("/home/swapnil/Desktop/i"));
		FileOutputFormat.setOutputPath(job2, new Path("/home/swapnil/Desktop/output1"));
		if(fs.exists(new Path("/home/swapnil/Desktop/output1"))){
			   /*If exist delete the output path*/
			   fs.delete(new Path("/home/swapnil/Desktop/output1"),true);
			}
		/**end of job2**/
		
		ControlledJob cj1 = new ControlledJob(conf);
		cj1.setJob(job1);
		
		ControlledJob cj2 = new ControlledJob(conf);
		cj2.setJob(job2);
		
		cj2.addDependingJob(cj1);
		
		JobControl jobControl = new JobControl("MatrixMultiplicaiton");
		jobControl.addJob(cj1);
		jobControl.addJob(cj2);
					
		Thread thread = new Thread(jobControl); 
		thread.setDaemon(true);
		thread.start();
		
		while(!jobControl.allFinished()){
			Thread.sleep(4000);
		}
	
	}

}
