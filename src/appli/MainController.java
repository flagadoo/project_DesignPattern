package appli;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class MainController {
    public Label HelloWorld;

    public void sayHelloWorld(ActionEvent actionEvent) {


        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        fileWriteHTML creator = new fileWriteHTML();

        // process the file, and limit periods to given time interval
        var teamsProcessorHTML = new TEAMSProcessorHTML(selectedFile,"19/01/2021 à 10:15:00", "19/01/2021 à 11:45:00");
        var teamsProcessorCSS = new TEAMSProcessorCSS(selectedFile,"19/01/2021 à 10:15:00", "19/01/2021 à 11:45:00");
/*
        var allpeople = teamsProcessor.get_allpeople();
        for (People people : allpeople) {
            System.out.println( people );
        }
*/
        //System.out.println( teamsProcessor.toHTMLCode() );
       teamsProcessorHTML.writeFile();
       teamsProcessorCSS.writeFile();
    }
}
