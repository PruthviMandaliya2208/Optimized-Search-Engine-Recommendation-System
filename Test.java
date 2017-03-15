
package test;
import java.io.*;
import static java.lang.Math.log;
import java.util.StringTokenizer;
import java.util.*;
import java.math.*;
/**
 *
 * @author RV
 */
public class Test {

    
    
    public static void main(String[] args) {
        // The name of the file to open
         String a []=new String[2];
           int[] tdf = new int[10];
         int[] measure = new int[10];
       int p;
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
   for( p=0 ; p <2; p++){
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
//System.out.println("---Frequency---");
  
      // create unique words
   
   /* int uniqueWordSize = 0;
      
  for (int i = 0; i < wordSize; i++) {
     
       boolean found = false;
         
   for (int j = 0; j < uniqueWordSize; j++) {
         
       if (word[i].equals(unique[j])) {
             

       found = true;
              
      break;
                }
     
       }
           
 if (!found) {
     
            unique[uniqueWordSize++] = word[i];
        
   }  
        }*/
 //  for (int i = 0; i < 6; i++) {
//System.out.println(unique[i]);
 //  }
   // measuring frequency
      // tf 
 
  
     for (int i = 0; i < l; i++) {
     
       for (int j = 0; j < wordSize; j++) {
         
       if (Query[i].equals(word[j])) {
           
         measure[i]++;
                }
      
      }
     
   }
     // idf
   
  
     for (int i = 0; i < l; i++) {
     
       for (int j = 0; j < wordSize; j++) {
         
       if (Query[i].equals(word[j])) {
           
         tdf[i]++;
         break;
                }
      
      }
     
   }
     
        //printing results tf
  
    //  for (int i = 0; i < l; i++) {
     
     //  System.out.println(Query[i] + " : " + measure[i]);
   
    // }
      //idf
    //  for (int i = 0; i < l; i++) {
     
    //   System.out.println(Query[i] + " : " + tdf[i]);
   
   ///  }
         
   bufferedReader.close();    
     
            System.out.println("Contents of file:"+ p);
		
	System.out.println(stringBuffer.toString());     
     
   }
   // printing tf
   System.out.println("term frequency of query");
      for (int i = 0; i < l; i++) {
     
      System.out.println(Query[i] + " : " + measure[i]);
   
     } 
       //printinf idf
       System.out.println("Query repeat in document");
       for (int i = 0; i < l; i++) {
     
      System.out.println(Query[i] + " : " + tdf[i]);
   
     }  
       int M=2;
        for (int i = 0; i < l; i++) {
     //calculating idf
     System.out.println("Idf");
      
       float idf;
       idf=(float) log((M+10)/tdf[i]);
       System.out.println(Query[i] + " : " + idf);
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

