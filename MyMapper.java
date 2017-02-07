package second;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;

import java.util.*;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

	public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
		private final static IntWritable one = new IntWritable(1);
		private Text token = new Text(); 
		private String para; 
		String[] tokens,tokens1;
		int docid=1;
		int id,id1;
		private HashSet<String> stopWordes = new HashSet<String>();

		public void setup(Context ctx) throws IOException{
			stopWordes = new HashSet<String>();
        		stopWordes.add("I"); stopWordes.add("a");
        		stopWordes.add("about"); stopWordes.add("an");
        		stopWordes.add("are"); stopWordes.add("as");
        		stopWordes.add("at"); stopWordes.add("be");
        		stopWordes.add("by"); stopWordes.add("com");
        		stopWordes.add("de"); stopWordes.add("en");
        		stopWordes.add("for"); stopWordes.add("from");
        		stopWordes.add("how"); stopWordes.add("in");
        		stopWordes.add("is"); stopWordes.add("it");
        		stopWordes.add("la"); stopWordes.add("of");
        		stopWordes.add("on"); stopWordes.add("or");
        		stopWordes.add("that"); stopWordes.add("the");
        		stopWordes.add("this"); stopWordes.add("to");
        		stopWordes.add("was"); stopWordes.add("what");
        		stopWordes.add("when"); stopWordes.add("where");
        		stopWordes.add("who"); stopWordes.add("will");
        		stopWordes.add("with"); stopWordes.add("and");
        		stopWordes.add("the"); stopWordes.add("www");	
			stopWordes.add(".");
			stopWordes.add("!");
			stopWordes.add(",");
			stopWordes.add("then");
			stopWordes.add("?");
		}
		
		public void map(LongWritable offset, Text lineText, Context context) throws IOException, InterruptedException{
			String filenameStr=((FileSplit) context.getInputSplit()).getPath().getName();
			String line = lineText.toString().toLowerCase();
			Document document = Jsoup.parse(line);   
			String title1 = document.title();   //Print title.
			Elements paragraphs = document.select("p");
 			for(Element p : paragraphs){
  			para = p.text().toString();   
			//Optimization Type 1 : Split method
				
			tokens = para.split(" ");
			for(int i=0; i<tokens.length; i++)
			{
				if(tokens[i].isEmpty())
				{
					continue;
				}
				else if(!stopWordes.contains(tokens[i]))
				token.set(""+tokens[i]+" "+filenameStr);
				context.write(token, one);
			}
			}
			tokens1 = title1.split(" ");
			for(int i=0; i<tokens1.length; i++)
			{
				if(tokens1[i].isEmpty())
				{
					continue;
				}
				else if(!stopWordes.contains(tokens1[i]))		
				token.set(""+tokens1[i]+" "+filenameStr);
				context.write(token, one);
			}
				 
		}
	}