package appli;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Classe gérant la génération du code CSS, ainsi que l'écritue dans un
 * fichier du dit code.
 * @author Jade
 */

public class TEAMSProcessorCSS implements TEAMSProcessor{
    private static Collection<People> _allpeople = null;
    private static String _fileName;
    private static String _startTime;
    private static String _endTime;

    public TEAMSProcessorCSS(File _file, String _start, String _stop) {
        /*
         csv file to read
         start time of the course
         end time of the source
        */
        this._startTime = _start;
        this._endTime = _stop;

        // load CSV file
        this._fileName = _file.getName();
        var teamsFile = new TEAMSAttendanceList(_file);

        // filter to extract data for each people
        var lines = teamsFile.get_attlist();
        if (lines != null) {
            // convert lines in data structure with people & periods
            var filter = new TEAMSAttendanceListAnalyzer(lines);
            // cut periods before start time and after end time
            filter.setStartAndStop(_start, _stop);
            // sort
            List<People> peopleByDuration = new ArrayList<>(filter.get_peopleList().values());
            Collections.sort(peopleByDuration);
            // init the people collection
            this._allpeople = peopleByDuration;//filter.get_peopleList().values();
        }
    }

    @Override
    public Collection<People> get_allpeople() {
        return _allpeople;
    }

    /**
     * Génère le code CSS et le retourne dans une chaîne de caractères.
     * @return la chaîne de caractères css
     */
    @Override
    public String toCode() {
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

    /**
     * Ecrit le code CSS dans un fichier.
     * @author geo02
     */
    @Override
    public void writeFile() {
        // TODO Auto-generated method stub

        fileWriteCSS write = new fileWriteCSS();
        write.writeFile(_allpeople, _fileName, _startTime, _endTime);

    }
}
