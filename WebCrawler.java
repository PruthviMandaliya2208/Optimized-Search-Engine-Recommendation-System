import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.util.*;
import java.io.IOException;

public class WebCrawler {
public String[] URL(String BaseFile) throws IOException{
	//File input = new File(BaseFile);

	Document doc = Jsoup.connect(BaseFile).get();
	//Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
	
	Elements links = doc.select("a[href]");
	String textURL[] = new String[links.size()];
	int i=0;
        for (Element link : links){
		textURL[i++] = link.attr("href").toString();
	}
return textURL;	
}
private static void countStringOccurences(String[] strArray) {
        HashMap<String, Integer> countMap = new HashMap<String, Integer>();
        for (String string : strArray) {
            if (!countMap.containsKey(string)) {
                countMap.put(string, 1);
            } else {
                Integer count = countMap.get(string);
                count = count + 1;
                countMap.put(string, count);
            }
        }
        printCount(countMap);
    }
private static void printCount(HashMap<String, Integer> countMap) {
        Set<String> keySet = countMap.keySet();
        for (String string : keySet) {
            System.out.println(string + " : " + countMap.get(string));
        }
    }
public static void main(String[] args) {

	Scanner sc = new Scanner(System.in);
	System.out.println("Enter URL from 'http': ");
	String URLInput = sc.nextLine();
	WebCrawler obj1 = new WebCrawler();
	try{	
		// First iteration finding out total links of basePage	
		String[] URLS = obj1.URL(URLInput); //This is path which i have used for my configuration change this before using it.
		int count = 0,rank=0;
		int URLSsize = URLS.length;	
		
		countStringOccurences(URLS);

		/*for(String opurl:URLS){
			System.out.println("Base Page links "+ count++ +":"+opurl+" rank: "+rank);
		}*/
		
		for(int i=1;i<URLSsize;i++){	
			// First iteration finding out total links of basePage	
			if(URLS[i].startsWith("http://") || URLS[i].startsWith("www") || URLS[i].startsWith("/")){
				String[] URLS1 = obj1.URL(URLS[i]);
				/*int count1 = 0,URLCount = 0;
				for(String opurl1:URLS1){
				System.out.println("Page : "+i+ " link "+ count1++ +":"+opurl1+"Count: "+URLCount++);
				}*/	
				countStringOccurences(URLS1);
			}
		}
	}catch(ScoketException se){
		System.out.println("The System is not connected to the internet,please connect it with the network.");
	}catch(IllegalArgumentException IAE){
		System.out.println("System cannot voilate security conditions");
	}catch(Exception e){
		System.out.println(e);
	}
}
}

