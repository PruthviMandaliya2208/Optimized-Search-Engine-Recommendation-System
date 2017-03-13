package second;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReducer2 extends Reducer<Text, DoubleWritable, Text, DoubleWritable>{
	public void reduce(Text word, Iterable<DoubleWritable> counts, Context context) throws IOException, InterruptedException{	
		DoubleWritable one = new DoubleWritable();
		double sum=0,T,k=1.2;
		for(DoubleWritable count : counts){
			sum += count.get();
		}
		T=(((k+1)*sum)/(sum+k));
		one.set(T);
		context.write(word,one);
	}

}
