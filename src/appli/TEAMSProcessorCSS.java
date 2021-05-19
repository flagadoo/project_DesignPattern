package appli;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

    @Override
    public String toCode() {
        return "";
    }

    @Override
    public void writeFile() {
        // TODO Auto-generated method stub

        fileWriteCSS write = new fileWriteCSS();
        write.writeFile(_allpeople, _fileName, _startTime, _endTime);

    }
}
