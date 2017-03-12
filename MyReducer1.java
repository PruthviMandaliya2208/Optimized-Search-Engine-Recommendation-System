package second;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.ContentSummary;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

public class MyReducer1 extends Reducer<Text, Text, Text, DoubleWritable>{
	public void reduce(Text word, Iterable<Text> values, Context context) throws IOException, InterruptedException{	
	DoubleWritable one = new DoubleWritable();
	Configuration conf = context.getConfiguration();
	FileSystem fs = FileSystem.get(conf);
	Path pt = new Path("/home/swapnil/Desktop/i");
	ContentSummary cs = fs.getContentSummary(pt);
	long totalDocuments = cs.getFileCount();	
	int sum=0;double idf;
	HashSet<Text> uniqueDocIds = new HashSet<Text>();
	for(Text docId : values)
	{
	  uniqueDocIds.add(new Text(docId));
	}
	 for(Text v : uniqueDocIds)
	  {
		  sum++;
	  }
	 idf = Math.log((double) (totalDocuments+1) / (double) (sum));
	 one.set(idf);
	 context.write(word,one);
	
	}
	
}
