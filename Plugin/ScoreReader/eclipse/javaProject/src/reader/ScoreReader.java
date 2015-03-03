package reader;

import javafx.beans.InvalidationListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import plugincontract.APlugin;
import reader.controller.plugScoreController;

import java.io.File;
import java.io.IOException;
import java.util.Observable;

import Communication.MedleyTempo;

/**
 * Created by favre on 08/11/14.
 */
public class ScoreReader extends APlugin {

    public void initialiseComponent() {

        try
        {
            //plugScoreController     controller  = new plugScoreController();
            FXMLLoader fxmlLoader  = new FXMLLoader(getClass().getResource("view/layout/plugScore.fxml"));

            mainComponent       = fxmlLoader.load();
        }
        catch (IOException e) { e.printStackTrace(); }

    }

    public void modifyLanguage(String s) {

    }

    public void eventDropObject() {

    	System.out.println("ScoreReader : eventDropObject");
    	if (this.arguments[0].getClass() == File.class) {
    	 	//changeSound((File) this.arguments[0]);
    		System.out.println("ScoreReader : arguments[0] not null");
    		Data.getInstance().controller.importFile((File)this.arguments[0]);
    	 }
    }

    public void stop() {

    }

    public void receiveATempo(Observable observable, MedleyTempo medleyTempo) {
    	
    	System.out.println("ScoreReader : receiveATempo");
    	//MedleyTempo m = new MedleyTempo();
    	int i = medleyTempo.getTempo();
    	System.out.println("ScoreReader : " + i);
    	Data.getInstance().controller.newTempo = String.valueOf(i);
    	Data.getInstance().controller.play();
    }

    public void receiveAFile(Observable observable, File file) {
    	
    }

    public void receiveAnObject(Observable observable, Object o) {

    }
}
