
package test1;
import java.io.*;
import static java.lang.Math.log;
import java.util.StringTokenizer;
import java.util.*;
import java.math.*;

public class Test1 {

    
    public static void main(String[] args) {
        // TODO code application logic here
        // The name of the file to open
         int b=0;  
         double k=0.5;
         float z=0;
         String a []=new String[2];
           int[] tdf = new int[10];
           float df[]=new float[10]; //storing idf in array
         int count=0;
       int p=0;
  String      fileName ="C:\\Users\\RV\\Desktop\\New folder\\Hello0.txt";  
  String   file="C:\\Users\\RV\\Desktop\\New folder\\Hello1.txt";
int l=0;
         a[0]=fileName;
        a[1]=file;
         Scanner sc =new Scanner(System.in);
         String s=sc.nextLine();
         StringTokenizer scc=new StringTokenizer(s);
           String Query[]=new String[5];
           while(scc.hasMoreTokens())
           {
           String d=scc.nextToken();
               Query[l]=d;
                System.out.println( Query[l]);
    
                          l++;
                     
           }
           
        try {
           
 // FileReader reads text files in the default encoding.
 for( int u=0 ; u <2; u++) //loop to print tf
 {
   for( p=0 ; p <2; p++)//ist loop
 
   {
      
         FileReader fileReader =        new FileReader(a[p]);

       
     // Always wrap FileReader in BufferedReader.
        
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    
        StringBuffer stringBuffer = new StringBuffer();
	  
       String line;
           
       while((line = bufferedReader.readLine()) != null)
 {
                              stringBuffer.append(line);
	
			stringBuffer.append("\n");
            }   
 
           StringTokenizer str = new StringTokenizer(stringBuffer.toString());
  
          String word[] = new String[10];
         
    String unique[] = new String[10];
          
     int wordSize = 0;
      
      while (str.hasMoreTokens()) 
{
            String w = str.nextToken();
      
      word[wordSize] = w;
          
  //System.out.println(wordSize + ": " + word[wordSize]);
    
        wordSize++;
        } 
      //idfv
      if(b<2) //condition to run loop for once
      {
     for(int h=0;h<=p;h++) //2nd loop
     
     {
       for (int i = 0; i < l; i++) {
       for (int j = 0; j < wordSize; j++) {      
       if (Query[i].equals(word[j])) {         
         tdf[i]++;
         break;
                }      
      }   
   }     
     if( count==1)
     {
       int M=2;
        for (int i = 0; i < l; i++) {
     
       float idf;
       idf=(float) log((M+10)/tdf[i]);
       df[i]=idf;
       System.out.println(Query[i] + " : " + idf);
     } 
    
     
     }//printing idf
    count ++;
  
     // bufferedReader.close(); 
     
     }
     
     b=b+1;
      
      }// 2nd loop ending
      else {               //calculating tf
int[] measure = new int[10];
  
     for (int i = 0; i < l; i++) {
     
       for (int j = 0; j < wordSize; j++) {
         
       if (Query[i].equals(word[j])) {
           
         measure[i]++;
                }
      
      }
     
   }
       
       // printing results tf
  
      for (int i = 0; i < l; i++) {
     
      System.out.println(Query[i] + " : " + measure[i]);
   
     }
      
      //calculating bm25
      int v=0;
      double x; 
      double w;
      float bm[]=new float[10];
      for(int d=0;d<l;d++){
       z=z+df[d]*measure[d];
        v=v+measure[d];
      
     }     
   x=(float)(k+1)*v/v+k;
      
 System.out.println("words in query"+":"+l);
        System.out.println("addition of tf"+":"+v);
        System.out.println("multiplicattion of tf idf"+":"+z);
         w=l*z*v; System.out.println("bm25"+":"+ w);
      //idf
    //  for (int i = 0; i < l; i++) {
     
       System.out.println(l);
   
   ///  }
         
   bufferedReader.close();    
     
            System.out.println("Contents of file:"+ p);
		
	System.out.println(stringBuffer.toString());     
     
   } //1st loop end here
    
   }     
 } 
 
   for(int e=0;e<l;e++){
   System.out.println(df[e]);
   }
        }
       
 catch(FileNotFoundException ex)
 {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
      
  catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");       
                  
 }
    }
    
}
