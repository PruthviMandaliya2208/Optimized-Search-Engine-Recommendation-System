//importing packages for user interface and I/O interaction
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
		
	public Nexter()// public constructor for class.
	{	
		super("Nexter");
		setSize(1024,300);// sets window size to 1024 width and 300 as height.
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c = getContentPane();
		c.setLayout(null);
		
		jlbLogo = new JLabel(new ImageIcon("Nexter.PNG"));// logo is added.
		jlbLogo.setBounds(245,25,500,90);// sets logo width and height.
		c.add(jlbLogo);
		
		jlbQuery = new JLabel("Query : ");// label for user consideration.
		jlbQuery.setBounds(10,125,80,25);
		c.add(jlbQuery);
		
		jtfQueries = new JTextField();	// textfield where user will enter search query.
		jtfQueries.setBounds(80,125,915,25);
		c.add(jtfQueries);
		
		jbtnSearch = new JButton("Search"); // button for submitting given query to system.
		jbtnSearch.setBounds(445,155, 90, 25);
		c.add(jbtnSearch);
		
		jbtnSearch.addActionListener(new ActionListener()// this block perfoms what to be done when button is clicked.
		{
			public void actionPerformed(ActionEvent e)
			{
				String queries = jtfQueries.getText().toLowerCase().trim();// query is retrived from textfield.
				System.out.println("Queries = " + queries);
				
				try
				{
					bWtr = new BufferedWriter(new FileWriter("queries.dat"));//a file named queries.dat is created which consists user query.
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
	public static void main(String[] args)//main method
	{
		Nexter app = new Nexter();
	}
}
