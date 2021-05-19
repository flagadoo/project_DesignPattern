package appli;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Classe régissant la structure HTML d'une sortie fichier de cette extension.
 * @author geo02
 *
 */
public class fileWriteHTML implements FileWriterStrategy {
	
	private Collection<People> _allpeople = null;
    private String _fileName;
    private String _startTime;
    private String _endTime;

    
    
    
    
	@Override
	public void writeFile(Collection<People> people, String filename, String startTime, String endTime) {
		// TODO Auto-generated method stub
		
		this._allpeople = people;
		this._fileName = filename;
		this._startTime = startTime;
		this._endTime = endTime;
		
		
		File file = new File("placeholder.html");
		
		try {
			if (file.createNewFile()) {
			    System.out.println("File created: " + file.getName());
			  } else {
			    System.out.println("HTML File already exists.");
			  }
			
			FileWriter htmlWriter = new FileWriter("placeholder.html");
			htmlWriter.write(doHTML());
			htmlWriter.close();
			System.out.println("Successfully wrote to html HTML file.");

			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Une erreur est survenue.");
			e.printStackTrace();
		}
		
		

	}
	
	/**
	 * Génère le contenu du fichier HTML en respectant les balises et l'intégration des données
	 * provenant du fichier.
	 * @return une chaîne de caractères nommée html
	 */
	public String doHTML()
	{
		String html = "<!DOCTYPE html> \n <html lang=\"fr\"> \n <head> \n <meta charset=\"utf-8\"> ";
        html += "<title> Attendance Report </title> \n <link rel=\"stylesheet\" media=\"all\" href=\"visu.css\"> \n";
        html += "</head> \n <body> \n ";
        html += "<h1> Rapport de connexion </h1>\n" +
                "\n" +
                "<div id=\"blockid\">\n" +
                "<table>\n" +
                "\t<tr>\n" +
                "\t\t<th> Date : </th>\n" +
                "\t\t<td> " + this._allpeople.iterator().next().getDate() + " </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th> Heure début : </th>\n" +
                "\t\t<td> " + _startTime + " </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th> Heure fin : </th>\n" +
                "\t\t<td> " + _endTime + " </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th> Cours : </th>\n" +
                "\t\t<td> CM Bases de données et programmation Web </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th> Fichier analysé : </th>\n" +
                "\t\t<td> " + _fileName + " </td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th> Nombre de connectés : </th>\n" +
                "\t\t<td> " + _allpeople.size() + "  </td>\n" +
                "\t</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "\n" +
                "<h2> Durées de connexion</h2>\n" +
                "\n" +
                "<p> Pour chaque personne ci-dessous, on retrouve son temps total de connexion sur la plage déclarée du cours, ainsi qu'un graphe qui indique les périodes de connexion (en vert) et d'absence de connexion (en blanc). En pointant la souris sur une zone, une bulle affiche les instants de début et de fin de période. \n" +
                "</p>";
        html += "<div id=\"blockpeople\"> ";

        for (People people : _allpeople) {

            //html += people.getHTMLCode();
        	
        	if ( people.isOutOfPeriod() ) return ("");

            // duration max, in order to compute images width in percent
            LocalDateTime startTime = TEAMSDateTimeConverter.StringToLocalDateTime(people.get_start());
            LocalDateTime endTime = TEAMSDateTimeConverter.StringToLocalDateTime(people.get_stop());
            Duration delayMax = Duration.between(startTime, endTime);
            double durationMaxMinutes = Math.abs(delayMax.toSeconds()/60.);

    		html += "<div class=\"datapeople\"> \n";
    		html += "<div class=\"name\"> " + people.getName() + " </div> \n";
    		html +=	"<div class=\"timebar\">";

            double totalDuration = 0;
            LocalDateTime refTime = TEAMSDateTimeConverter.StringToLocalDateTime(people.get_start());
            for (TEAMSPeriod period : people.get_periodList()) {

                LocalDateTime begin = period.get_start();
                LocalDateTime end = period.get_end();
                double duration = period.getDurationInMinutes();
                totalDuration += duration;
                // begin > reftime : white bar
                Duration delay = Duration.between(refTime, begin);
                double delayMinutes = Math.abs(delay.toSeconds()/60.);
                if (delayMinutes>0.0) {
                    html += "<img src=\"off.png\" ";
                    html += "width=\"" + (100.*delayMinutes/durationMaxMinutes) + "%\" ";
                    html += "height=\"20\" title=\"absent(e) de " + refTime.toString();
                    html += " à " + begin.toString() + " \"> \n";
                }
                // green bar for the current period
                html += "<img src=\"on.png\" ";
                html += "width=\"" + (100.*duration/durationMaxMinutes) + "%\" ";
                html += "height=\"20\" title=\"connecté(e) de " + begin.toString();
                html += " à " + end.toString()+ "\"> \n";
                refTime = end;
            }
            // last period aligned on end time ?
            //LocalDateTime endTime = TEAMSDateTimeConverter.StringToLocalDateTime(this._stop);
            Duration delay = Duration.between(refTime, endTime);
            double delayMinutes = Math.abs(delay.toSeconds()/60.);
            if (delayMinutes>0.0) {
                html += "<img src=\"off.png\" ";
                html += "width=\"" + (100.*delayMinutes/durationMaxMinutes) + "%\" ";
                html += "height=\"20\" title=\"absent(e) de " + refTime.toString();
                html += " à " + endTime.toString() + " \"> \n";
            }
    		html += "</div> \n"; // end of div timebar
            html +=	"<div class=\"duration\"> " + (long)Math.round(totalDuration) + " </div> \n";
            html +=	"<div class=\"percentd\"> " + (long)Math.round(100.*totalDuration/durationMaxMinutes) + "% </div> \n";
            html += "</div>\n"; // end of div datapeople
        }

	    html += "</div> \n </body> \n </html>";
		

		
		return html;
	}
	

}
