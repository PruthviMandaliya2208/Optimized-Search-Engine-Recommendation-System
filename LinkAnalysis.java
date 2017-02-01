import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

import java.util.*;
import java.io.IOException;

public class LinkAnalysis {
public String[] URL(String BaseFile) throws IOException{
	File input = new File(BaseFile);
	Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
	
	Elements links = doc.select("a[href]");
	String textURL[] = new String[links.size()];
	int i=0;
        for (Element link : links){
		textURL[i++] = link.attr("href").toString();
	}
return textURL;	
}
public static void main(String[] args) {
	LinkAnalysis obj1 = new LinkAnalysis();
	try{	
		// First iteration finding out total links of basePage	
		String[] URLS = obj1.URL("/home/dbit/Desktop/Pruthvi/Sample1.html");
		int count = 0;
		for(String opurl:URLS){
			System.out.println("Base Page links "+ count++ +":"+opurl);
		}	
	}catch(Exception e){
			System.out.println(e);
	}
   }
}
