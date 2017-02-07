package second;

import java.io.IOException;
import java.util.*;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

public class MyReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	TreeMap<String,Integer> tm = new TreeMap<String,Integer>();

	public void reduce(Text word, Iterable<IntWritable> counts, Context context) throws IOException, InterruptedException{
		int sum = 0;
		for(IntWritable count : counts){
			sum += count.get();
		}
	tm.put(word.toString(),sum);		
	//context.write(word, new IntWritable(sum));
	}
	public void cleanup(Context ctx) throws IOException, InterruptedException{
		TreeMap<String,Integer> stm = SortbyValue(tm);
		Set<String> st = stm.keySet();
		Text key = new Text();
		IntWritable val = new IntWritable();
		for(String k:st)
		{	
			key.set(k);
			if(tm.get(k) != null)
			{
				val = new IntWritable(tm.get(k));
				ctx.write(key,val);

				/*if(count == 15)
					break;*/
			}

		} 
	}

	public static TreeMap<String,Integer> SortbyValue(TreeMap<String,Integer> base)
	{
		ValueComparator vc = new ValueComparator(base);
		
		TreeMap<String,Integer> sortedTS = new TreeMap<String,Integer>(vc);
				
		sortedTS.putAll(base);	
		return sortedTS;
	}
	
	public static class ValueComparator implements Comparator<String>
	{
		TreeMap<String,Integer> ts;
		public ValueComparator(TreeMap<String,Integer> base)
		{
			this.ts = base;
		}
		public int compare(String k1, String k2)
		{	
			if(ts.get(k1) >= ts.get(k2))
			 return -1;
			else
			 return 1;
		}
	}
}