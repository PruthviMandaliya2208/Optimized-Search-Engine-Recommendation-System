package second;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NexterUI extends JFrame{ 

		private static final long serialVersionUID = 1L;
		Container c;
		JLabel jlbLogo, jlbQuery;
		JButton jbtnSearch;
		JTextField jtfQueries;
		BufferedWriter bWtr;
		public void close(){
			WindowEvent  winClosingEvent=new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
			Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
		}	
		public NexterUI()
		{	
			super("NexterUI");
			setSize(1024,600);
			setResizable(false);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
			c = getContentPane();
			c.setLayout(null);
			c.setBackground(Color.WHITE);
			
			jlbLogo = new JLabel(new ImageIcon("images/Nexter.PNG"));
			jlbLogo.setBounds(245,125,500,90);
			c.add(jlbLogo);
			
			jlbQuery = new JLabel("Query : ");
			jlbQuery.setBounds(10,300,80,25);
			c.add(jlbQuery);
			
			jtfQueries = new JTextField();		
			jtfQueries.setBounds(80,300,915,25);
			c.add(jtfQueries);
			
			jbtnSearch = new JButton(new ImageIcon("images/search-button.png"));
			jbtnSearch.setBounds(375,400, 231, 48);
			c.add(jbtnSearch);
			
			jbtnSearch.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
						
					if((jtfQueries.getText()).equals("")){
						JOptionPane.showMessageDialog(null,"Please enter Query");}
					else {
						String queries = jtfQueries.getText().toLowerCase().trim();
						System.out.println("Queries = " + queries);
					
						try
						{
							bWtr = new BufferedWriter(new FileWriter("queries"));
							bWtr.write(queries + "\n");
							bWtr.close();
							String[] args={};
							Test.main(args);
							//close();
							Results rs=new Results();
						}
						catch(Exception ex)
						{
						System.out.println("Exception : " + ex.getMessage());
						}
					}
				 }
			});
			
			setVisible(true);
			
			}
		public static void main(String[] args)
		{
			NexterUI app = new NexterUI();
			
		}
}
