package second;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;

import java.io.*;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class MyMapper1 extends Mapper<LongWritable, Text, Text, Text>{
	FileReader file,file1;
    String key = "",query,line;
    BufferedReader reader,reader1;
	int i,j,s=0,c=0;
	private Text token = new Text(); 
	private Text token1 = new Text(); 
	private String para; 
	String[] tokens,tokens1,str;
	//String query = "news about presidential campaign";
	String[] queryTokens;
	public void map(LongWritable offset, Text lineText, Context context) throws IOException, InterruptedException{
	    file = new FileReader("/home/swapnil/Desktop/inputfile");
	    BufferedReader reader = new BufferedReader(file);
	    reader = new BufferedReader(file);
	    String query = reader.readLine();
	    queryTokens=query.split(" ");
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
						token.set(tokens[i]);
						token1.set(filenameStr);						
						context.write(token, token1);
					}
				}
			}
		
		}				
	}	
}