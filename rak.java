import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class rak {

	private static final String FILENAME = "/home/dbit/Desktop/file.html";

	public static void main(String[] args) {

		BufferedReader br = null;
		FileReader fr = null;
String result;
		try {

			int count=0;

			String sCurrentLine;

			br = new BufferedReader(new FileReader(FILENAME));

			while ((sCurrentLine = br.readLine()) != null)
{
                       if (sCurrentLine.contains("href"))
{
                     result = sCurrentLine.substring(sCurrentLine.indexOf("<") + 1, sCurrentLine.indexOf(">"));
                           count++;



 
				System.out.println(result);

		}	}System.out.println(count);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

}
