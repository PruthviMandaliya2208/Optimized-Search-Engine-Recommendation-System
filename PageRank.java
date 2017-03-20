import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.util.*;
import java.io.IOException;
class RankCalculation{

public int path[][] = new int[10][10];
public double pagerank[] = new double[10];
  
public void calc(double totalNodes){
     
double InitialPageRank;
double OutgoingLinks=0; 
double DampingFactor = 0.85; 
double TempPageRank[] = new double[10];
 
int ExternalNodeNumber;
int InternalNodeNumber; 
int k=1;
int ITERATION_STEP=1;
 
InitialPageRank = 1/totalNodes;
System.out.printf("  Number of Nodes :"+totalNodes+"\t Initial PageRank   :"+InitialPageRank+"\n");
  

for(k=1;k<=totalNodes;k++)
{
  this.pagerank[k]=InitialPageRank;
}   
   
System.out.printf("\n Initial PageRank Values , 0th Step \n");
for(k=1;k<=totalNodes;k++)
{
  System.out.printf(" Page Rank of "+k+" is :\t"+this.pagerank[k]+"\n");
}  
   
 while(ITERATION_STEP<=2) 
 {
 
  for(k=1;k<=totalNodes;k++)
 {  
   TempPageRank[k]=this.pagerank[k];
   this.pagerank[k]=0;
  }
     
 for(InternalNodeNumber=1;InternalNodeNumber<=totalNodes;InternalNodeNumber++)
 {
  for(ExternalNodeNumber=1;ExternalNodeNumber<=totalNodes;ExternalNodeNumber++)
   {
    if(this.path[ExternalNodeNumber][InternalNodeNumber] == 1)
    { 
      k=1;
      OutgoingLinks=0;  // Count the Number of Outgoing Links for each ExternalNodeNumber
      while(k<=totalNodes)
      {
        if(this.path[ExternalNodeNumber][k] == 1 )
        {
          OutgoingLinks=OutgoingLinks+1; 
        }  
        k=k+1;  
      } 
          
         this.pagerank[InternalNodeNumber]+=TempPageRank[ExternalNodeNumber]*(1/OutgoingLinks);    
     }
   }  
 }    
      
    
   
     for(k=1;k<=totalNodes;k++) 
      System.out.printf(" Page Rank of "+k+" is :\t"+this.pagerank[k]+"\n"); 
   
     ITERATION_STEP = ITERATION_STEP+1;
}
 
// Add the Damping Factor to PageRank
for(k=1;k<=totalNodes;k++)
{
  this.pagerank[k]=(1-DampingFactor)+ DampingFactor*this.pagerank[k]; 
  } 
   
// Display PageRank
System.out.printf("\n Final Page Rank : \n"); 
for(k=1;k<=totalNodes;k++)
{
 System.out.printf(" Page Rank of "+k+" is :\t"+this.pagerank[k]+"\n"); 
  }
   
 }
}
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
		ArrayList<String> finalist = new ArrayList<String>();
		ArrayList<String> list=new ArrayList<String>();
		list.add(page);
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
		String returnArray[] = new String[finalist.size()];              
		for(int j =0;j<finalist.size();j++){
			returnArray[j] = finalist.get(j);
			System.out.println("finallist:"+returnArray[j]);
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
			int[] pageRankMatrix = new int[aSize*aSize];
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
			/*for(int pp=0;pp<aSize;pp++){
				System.out.println("total: " + total[pp]);
			}*/
			for(int p=0;p<(index.length*index.length);p++){
				int trow = index.length;
				int ttrow = 0;
				if(p % trow == 0){
					mm = p;
					ttrow = p/trow;
					//System.out.println("p: "+ p +" ttrow: " + ttrow);
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
				System.out.println(total[i]+"");
			}
			int nodes,i,j,cost;
        		nodes = a.length;
        		RankCalculation p = new RankCalculation();
        		for(i=0;i<nodes;i++)
         		for(j=0;j<nodes;j++)
          		{
            			p.path[i][j]=pageRankMatrix[i * a.length + j];
            			if(j==i)
              			p.path[i][j]=0;
          		}
        		p.calc(nodes);
			/*System.out.println(" changed ");
			for(int i=0;i<a.length;i++){
				for(int j=0;j<a.length;j++){
					if(total[i]!=0){
						pageRankMatrix[i * a.length + j] = pageRankMatrix[i * a.length + j] / total[i] ;
					}
					System.out.print(pageRankMatrix[i * a.length + j] + " ");
				}
				System.out.println(" total: " + total[i]);
			}*/
		}catch(Exception e){
			System.out.println(e);
		}
	
	}
}
