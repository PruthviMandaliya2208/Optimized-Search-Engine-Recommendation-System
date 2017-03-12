package second;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.HashMap;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class MyMapper2 extends Mapper<LongWritable, Text, Text, DoubleWritable>{
	DoubleWritable one = new DoubleWritable();
	FileReader file,file1;
    String key = "",query,line;
    BufferedReader reader,reader1;
	int i,j,s,c=0;
	Double d;
	private Text token = new Text(); 
	private String para; 
	String[] tokens,tokens1,str;
	//String query = "news about presidential campaign";
	String[] queryTokens;
	public void map(LongWritable offset, Text lineText, Context context) throws IOException, InterruptedException{
	    file = new FileReader("/home/swapnil/Desktop/inputfile");
	    file1 = new FileReader("/home/swapnil/Desktop/output/part-r-00000");
	    BufferedReader reader = new BufferedReader(file);
	    BufferedReader reader1 = new BufferedReader(file1);
	    HashMap<String,Double> map = new HashMap<String,Double>();
	    reader = new BufferedReader(file);
	    String query = reader.readLine();
	    queryTokens=query.split(" ");
	    while((line=reader1.readLine())!=null){
	    str=line.split("\t");
		map.put(str[0],new Double(str[1]));
	    }
	    System.out.println(map);
		String filenameStr=((FileSplit) context.getInputSplit()).getPath().getName();
		String line = lineText.toString().toLowerCase();
		Document document = Jsoup.parse(line);   
		Elements paragraphs = document.select("p");
			for(Element p : paragraphs){
			para = p.text().toString();
		tokens = para.split(" ");
		for(i=0;i<tokens.length;i++)
		{
			for(j=0;j<queryTokens.length;j++)
			{
				if(tokens[i].equals(queryTokens[j]))
				{
					token.set(filenameStr);
					d=map.get(tokens[i]);
					one.set(d);
					context.write(token, one);
				}
			}
		}
		}				
	}

}