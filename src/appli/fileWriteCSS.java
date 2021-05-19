package appli;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class fileWriteCSS implements FileWriterStrategy{

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


        File css = new File("visu.css");

        try {
            if (css.createNewFile()) {
                System.out.println("File created: " + css.getName());
            } else {
                System.out.println("CSS File already exists.");
            }

            FileWriter cssWriter = new FileWriter("visu.css");
            cssWriter.write(doCSS());
            cssWriter.close();
            System.out.println("Successfully wrote to html CSS file.");


        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Une erreur est survenue.");
            e.printStackTrace();
        }



    }

    private String doCSS()
    {
        String css = "body{\r\n"
                + "	background-color: #ddd;\r\n"
                + "	font-family: Verdana, sans-serif;\r\n"
                + "}\r\n"

                + "#blockpeople {\r\n"
                + "	width: 1130px;\r\n"
                + "	background-color: #fff;\r\n"
                + "	margin-bottom: 30px;\r\n"
                + "	padding: 10px 0;\r\n"
                + "}\r\n"

                + "th {\r\n"
                + "	text-align: left;\r\n"
                + "	padding-right: 10px;\r\n"
                + "}\r\n"

                + "p {\r\n"
                + "	width:1130px;\r\n"
                + "}\r\n"

                + ".datapeople {\r\n"
                + "	display: grid;\r\n"
                + "	grid-column-gap: 5px;\r\n"
                + "	grid-template-areas: \"col1 col2 col3 col4\";\r\n"
                + "	height:20px;\r\n"
                + "	margin:0 10px;\r\n"
                + "	border: solid 1px #fff;\r\n"
                + "}\r\n"

                + ".datapeople:hover {\r\n"
                + "	border: solid 1px red;\r\n"
                + "}\r\n"

                + ".name {\r\n"
                + "	grid-area: \"col1\";\r\n"
                + "	width : 200px;\r\n"
                + "	text-overflow: ellipsis;\r\n"
                + "	white-space: nowrap;\r\n"
                + "	padding-left: 3px;\r\n"
                + "}\r\n"

                + ".duration {\r\n"
                + "	grid-area: \"col2\";\r\n"
                + "	text-align: center;\r\n"
                + "	width: 40px;\r\n"
                + "	background-color: #ddd;\r\n"
                + "}\r\n"

                + ".percentd {\r\n"
                + "	grid-area: \"col3\";\r\n"
                + "	text-align: center;\r\n"
                + "	width: 60px;\r\n"
                + "	background-color: #ddd;	\r\n"
                + "}\r\n"

                + ".timebar {\r\n"
                + "	grid-area: \"col4\";\r\n"
                + "	width: 780px;\r\n"
                + "	background-color: #DDD;\r\n"
                + "	border-left: solid 2px black;\r\n"
                + "	border-right: solid 2px black;\r\n"
                + "}\r\n"

                + ".timebar img {\r\n"
                + "	padding: 0;\r\n"
                + "	margin: 0;\r\n"
                + "	float:left;\r\n"
                + "\r\n"

                + "}\r\n"

                + ".name, .duration, .percentd {\r\n"
                + "	vertical-align: middle;\r\n"
                + "	line-height: 20px;\r\n"
                + "}\r\n";

        return css;
    }

}
