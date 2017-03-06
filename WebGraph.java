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
			String a[] = webGraph(URLInput);
			int aSize = a.length;
			int[][] aa = new int[aSize][aSize];
			for(int k=1;k<a.length;k++){
				System.out.println("a: "+a[k]);
			}
			webGraph(URLInput);
		}catch(Exception e){
			System.out.println(e);
		}
	}

	public static String[] URL(String BaseFile) throws IOException{
		File input = new File(BaseFile);
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
		Elements links = doc.select("a");
		String textURL[] = new String[links.size()];
		int i=0;
		for (Element link : links){
			textURL[i++] = link.attr("href").toString();
		}
		return textURL;	
	}
	private static String[] webGraph(String page) throws IOException{
		ArrayList<String> list=new ArrayList<String>();
		list.add(page);

		String[] URLS = URL(page); 
		if(URLS != null){
			int URLSsize = URLS.length;	
			
			/*System.out.println("Legth:"+URLSsize);*/

			for(String opurl:URLS){
				if(!list.contains(opurl)){
					list.add(opurl);
				}
			}
			for(int i=0;i<URLSsize;i++){	
				/*System.out.println("1:"+URLS[i]);*/
				String[] URLS1 = URL(URLS[i]);
				//System.out.println("Legth:"+URLS.length);
				if(URLS1 != null){
					for(int j=0;j<URLS1.length;j++){
						/*System.out.println("URLS1:"+URLS1[j]);*/
						String[] URLS11 = URL(URLS1[j]);
						if(URLS11 != null){
							for(String opurl1:URLS11){
								if(!list.contains(opurl1)){
									list.add(opurl1);
								}
							}
						}
						for(String opurl1:URLS1){
							if(!list.contains(opurl1)){
								list.add(opurl1);
							}
						}
					} //URLS1
				} // URLS1
				//System.out.println("i:"+URLS[i]);	
			}
		}//URLS

		String returnArray[] = new String[list.size()];              
		for(int j =0;j<list.size();j++){
			returnArray[j] = list.get(j);
		}
		return returnArray;
	}

	
}