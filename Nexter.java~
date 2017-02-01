import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Nexter extends JFrame
{
	Container c;
	JLabel jlbLogo, jlbQuery;
	JButton jbtnSearch;
	JTextField jtfQueries;
	
	BufferedWriter bWtr;
		
	public Nexter()
	{	
		super("Nexter");
		setSize(1024,300);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c = getContentPane();
		c.setLayout(null);
		
		jlbLogo = new JLabel(new ImageIcon("Nexter.PNG"));
		jlbLogo.setBounds(245,25,500,90);
		c.add(jlbLogo);
		
		jlbQuery = new JLabel("Query : ");
		jlbQuery.setBounds(10,125,80,25);
		c.add(jlbQuery);
		
		jtfQueries = new JTextField();		
		jtfQueries.setBounds(80,125,915,25);
		c.add(jtfQueries);
		
		jbtnSearch = new JButton("Search");
		jbtnSearch.setBounds(445,155, 90, 25);
		c.add(jbtnSearch);
		
		jbtnSearch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String queries = jtfQueries.getText().toLowerCase().trim();
				System.out.println("Queries = " + queries);
				
				try
				{
					bWtr = new BufferedWriter(new FileWriter("queries.dat"));
					bWtr.write(queries + "\n");
					bWtr.close();
				}
				catch(Exception ex)
				{
				System.out.println("Exception : " + ex.getMessage());
				}
			}
		});
		
		setVisible(true);
	}
	public static void main(String[] args)
	{
		Nexter app = new Nexter();
	}
}
