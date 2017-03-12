package second;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReducer2 extends Reducer<Text, DoubleWritable, Text, DoubleWritable>{
	public void reduce(Text word, Iterable<DoubleWritable> counts, Context context) throws IOException, InterruptedException{	
		DoubleWritable one = new DoubleWritable();
		double sum=0;
		for(DoubleWritable count : counts){
			sum += count.get();
		}
		one.set(sum);
		context.write(word,one);
	}

}
