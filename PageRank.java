import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.util.*;
import java.io.IOException;

public class PageRank {
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
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Starting URL from 'http',to calculate Graph :");
		String URLInput = sc.nextLine();
		try{
			String a[] = webGraph(URLInput);
			int aSize = a.length;
			int indexCounter = 1;	
			Arrays.sort(a);
			int index[][] = new int[aSize][aSize];
			float[] pageRankMatrix = new float[aSize*aSize];
			int total[] = new int[aSize];
			for(int k=0;k<a.length;k++){
				total[k] = URL(a[k]).length;
				String[] indexCal = URL(a[k]);
				for(int i=0;i<indexCal.length;i++){
					index[k][i] = Arrays.binarySearch(a,indexCal[i])+1; //index value
				}
			}
			int temp[] = new int[index.length * index.length];
			for(int l=0;l<a.length;l++){
				for(int m=0;m<index.length;m++){
					for(int n=0;n<a.length;n++){
						for(int o=0;o<index.length;o++){
							temp[(n * index.length) + o] = index[n][o];
						}
					}
				}
			}
			int mm = 0;
			for(int pp=0;pp<aSize;pp++){
				System.out.println("total: " + total[pp]);
			}
			for(int p=0;p<(index.length*index.length);p++){
				int trow = index.length;
				int ttrow = 0;
				if(p % trow == 0){
					mm = p;
					ttrow = p/trow;
					System.out.println("p: "+ p +" ttrow: " + ttrow);
				}
				if(temp[p]!=0){
					int tempp = temp[p];
					pageRankMatrix[(mm+tempp-1)] = 1;
				}
			}

			for(int i=0;i<a.length;i++){
				for(int j=0;j<a.length;j++){
					System.out.print(pageRankMatrix[i * a.length + j] + " ");
				}
				System.out.println();
			}
			System.out.println(" changed ");
			for(int i=0;i<a.length;i++){
				for(int j=0;j<a.length;j++){
					if(total[i]!=0){
						pageRankMatrix[i * a.length + j] = pageRankMatrix[i * a.length + j] / total[i] ;
					}
					System.out.print(pageRankMatrix[i * a.length + j] + " ");
				}
				System.out.println(" total: " + total[i]);
			}
			String [][] matrix = new String[aSize][aSize];
		}catch(Exception e){
			System.out.println(e);
		}
	
	}
}

