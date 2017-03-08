package second;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReducer1 extends Reducer<Text, IntWritable, Text, IntWritable>{
	public void reduce(Text word, Iterable<IntWritable> counts, Context context) throws IOException, InterruptedException{	
int sum=0;
	/*Configuration conf = context.getConfiguration();
	FileSystem fs = FileSystem.get(conf);
	Path pt = new Path("/user/swapnil/TF/input");
	ContentSummary cs = fs.getContentSummary(pt);
	long totalDocuments = cs.getFileCount();		
	//int sum = 0;
	//double idf;
	// String[] wordAndDocCounter = word.toString().split("\t");
    /* String[] wordAndDoc = wordAndDocCounter[0].split("@");
     context.write(new Text(wordAndDoc[1]), new Text(wordAndDoc[0] + "=" + wordAndDocCounter[1]));
	/*for()*/
	for(IntWritable count : counts){
		sum += count.get();
	}
	//idf = Math.log((totalDocuments+1) / (sum));
	
	
	context.write(word,new IntWritable(sum));
	}

}
