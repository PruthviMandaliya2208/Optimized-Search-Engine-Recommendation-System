import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.util.*;
import java.io.IOException;

public class WebCrawler {
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
	WebCrawler obj1 = new WebCrawler();
	try{	
		// First iteration finding out total links of basePage	
		String[] URLS = obj1.URL("/home/dbit/Desktop/Pruthvi/Sample1.html"); //This is path which i have used for my configuration change this before using it.
		int count = 0;
		for(String opurl:URLS){
			System.out.println("Base Page links "+ count++ +":"+opurl);
		}
		int URLSsize = URLS.length;	
		for(int i=1;i<URLSsize;i++){	
			// First iteration finding out total links of basePage	
			String[] URLS1 = obj1.URL(URLS[i]);
			int count1 = 0;
			for(String opurl1:URLS1){
				System.out.println("Page : "+i+ " link "+ count1++ +":"+opurl1);
			}	
		}
	}catch(Exception e){
		System.out.println(e);
	}
}
}
