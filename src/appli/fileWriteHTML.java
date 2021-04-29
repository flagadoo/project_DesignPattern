package appli;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe régissant la structure HTML d'une sortie fichier de cette extension.
 * @author geo02
 *
 */
public class fileWriteHTML implements FileWriterStrategy {

	@Override
	public void writeFile() {
		// TODO Auto-generated method stub
		
		File file = new File("placeholder.html");
		File css = new File("visu.css");
		
		try {
			if (file.createNewFile()) {
			    System.out.println("File created: " + file.getName());
			  } else {
			    System.out.println("File already exists.");
			  }
			
			if (css.createNewFile()) {
			    System.out.println("File created: " + css.getName());
			  } else {
			    System.out.println("File already exists.");
			  }
			
			FileWriter writer = new FileWriter("placeholder.html");
			writer.write(TEAMSProcessor.toHTMLCode());
			writer.close();
			System.out.println("Successfully wrote to the file.");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Une erreur est survenue.");
			e.printStackTrace();
		}
		
		

	}
	
	
	public String doHTML()
	{
		String html = null;
		
		
		
		
		
		
		return html;
	}
	
	

}
