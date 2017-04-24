import java.lang.*;
import java.io.*;
import static java.lang.Math.log;
import java.util.StringTokenizer;
import java.util.*;
import java.math.*;

public class Test22
{
//String word[] = new String[10];
static int wordSize = 0;
static boolean show(String input)
{   
try  
{     
Integer.parseInt(input); 
}   
catch(NumberFormatException ex) 
{        
return false;  
}   
return true;
}
 
    public static void main(String[] args)
    {       
    // The name of the file to open              
  String      fileName ="//home//dbit//Desktop/Test.txt"; 
  try {    
  // FileReader reads text files in the default encoding.   
         FileReader fileReader =        new FileReader(fileName);
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
            int wordSize = 0;       
            while (str.hasMoreTokens())
            {           
            String w = str.nextToken(); 
            // boolean isInteger = isInteger(w);   
            boolean b= show(w);     
            if(b==false)
            {  
            word[wordSize] = w;       
            wordSize++;        }
            }     
            bufferedReader.close();
for (int i = 0; i < 8; i++) {    
System.out.println(word[i]);        }
 }
        catch(FileNotFoundException ex)
        {   
        System.out.println("Unable to open file '" +                 fileName + "'");               
        }       
        catch(IOException ex) 
        {            System.out.println( "Error reading file '"    + fileName + "'");                     
        }
    }    
    }
