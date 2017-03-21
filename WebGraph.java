import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.util.*;
import java.io.IOException;
class WebGraph{

	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Starting URL from 'http',to calculate Graph :");
		String URLInput = sc.nextLine();
		try{
			String a[] = WebGraph(URLInput);
			int aSize = a.length;
			System.out.println(aSize);
			for(int k=0;k<a.length;k++){
				System.out.println("a: "+a[k]);
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}

	public static String[] URL(String BaseFile) throws IOException{
		//Document doc = Jsoup.connect(BaseFile).get();

		File input = new File(BaseFile);
		if(!input.exists()){
			  throw new FileNotFoundException("Could not find file: " + input);
		}
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
		Elements links = doc.select("a");
		String textURL[] = new String[links.size()];
		int i=0;
		for (Element link : links){
			textURL[i++] = link.attr("href").toString();
		}
		return textURL;	
	}
	private static String[] WebGraph(String page) throws IOException{
		ArrayList<String> finalist = new ArrayList<String>();
		ArrayList<String> list=new ArrayList<String>();
		list.add(page);
		int flag = 0;
		try{
		int counter = 0;
		while(list.size()!=counter){
			String[] URLS = URL(list.get(counter));
			for(String opurl:URLS){
				if(!list.contains(opurl) || !finalist.contains(opurl)){
					finalist.add(opurl);
					list.add(opurl);
				}
			}
			counter = counter+1;
		}
		}catch(FileNotFoundException fnfe){
			flag = flag+1;
		}
		String returnArray[] = new String[finalist.size()];              
		for(int j =0;j<finalist.size();j++){
			returnArray[j] = finalist.get(j);
			System.out.println("finallist:"+returnArray[j]);
		}
		return returnArray;
	}
}
