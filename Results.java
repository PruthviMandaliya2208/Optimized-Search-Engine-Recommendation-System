package second;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import java.io.*;
import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Results extends JFrame{ 
	
	private static final long serialVersionUID = 1L;
		Container c;
		
		String fileName ="/home/swapnil/Desktop/output1/part-r-00000";  
		Runtime rt = Runtime.getRuntime();
		JLabel jlbLogo;
		FileReader fileReader;
		File file;
		BufferedReader br;
		String query,url,name;
		String arr[];
		JLabel jlb[],jlb1[];
		JPanel cont;
		String word[] = new String[100];  
		int i=0;
		
			static boolean show(String input)
			{
			try
				{
					Double.parseDouble(input);
				}
			catch(NumberFormatException ex)
			{
				return false;
			}
			return true;
			}

			public Results()
			{	
				super("Results");
				setSize(1024,600);
				setResizable(false);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				c = getContentPane();
				c.setLayout(null);
				jlbLogo = new JLabel(new ImageIcon("images/Nexter.PNG"));
				jlbLogo.setBounds(300,10,400,60);
				c.add(jlbLogo);           
        try {
        	fileReader = new FileReader(fileName);
        	BufferedReader bufferedReader = new BufferedReader(fileReader);
        	StringBuffer stringBuffer = new StringBuffer();
        	int count=0;
        	String line;
            while((line = bufferedReader.readLine()) != null)
            {
               stringBuffer.append(line);
               stringBuffer.append("\t");
               count++;
            }   
            jlb= new JLabel[count];
            jlb1= new JLabel[count];
            StringTokenizer str = new StringTokenizer(stringBuffer.toString(),"\t");
            int wordSize = 0;
            while (str.hasMoreTokens()) 
            {
            String w = str.nextToken();
            boolean b= show(w);
            if(b==false){
            	word[wordSize] = w;
            	wordSize++;
            } }
       bufferedReader.close();
       int wid=70,wid1=110;
       for (i = 0; i < count; i++) {
     System.out.println(word[i]);
     file = new File("/home/swapnil/Desktop/i/"+word[i]);
     Document document = Jsoup.parse(file,null);   
	 Element p = document.select("p").first();     
	 jlb[i]=new JLabel();
	 jlb1[i]=new JLabel();
	 jlb[i].setText(word[i]);	
	 jlb1[i].setText(p.text());					
     jlb[i].setBounds(10,wid,1000,60);
     jlb1[i].setBounds(10,wid1,1000,60);
     c.add(jlb[i]);	
     c.add(jlb1[i]);	
     jlb[i].addMouseListener(new MouseAdapter() 
     {  
		public void mouseClicked(MouseEvent e)  
		{	  

        try {           
        	JLabel jlb = (JLabel)e.getComponent();
        	url = "/home/swapnil/Desktop/i/"+jlb.getText();
            rt.exec("xdg-open " + url);            
        } catch (IOException ex) {
        }
		}  
});

   wid=wid+100;
   wid1=wid1+100;
    
       }

 }      
     catch(IOException ex) {
            System.out.println(
                "Error reading file '" + fileName + "'");       
      }
        
        setVisible(true);
	}
	public static void main(String[] args)
		{
			Results rs= new Results();
			
		}
}
