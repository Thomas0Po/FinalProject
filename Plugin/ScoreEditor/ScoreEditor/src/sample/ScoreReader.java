package sample;

import Communication.MedleyTempo;
import javafx.fxml.FXMLLoader;
import plugincontract.APlugin;

import java.io.File;
import java.io.IOException;
import java.util.Observable;

/**
 * Created by favre on 08/11/14.
 */
public class ScoreReader extends APlugin {

    @Override
    public void initialiseComponent() {

        try
        {
            //plugScoreController     controller  = new plugScoreController();
            FXMLLoader              fxmlLoader  = new FXMLLoader(getClass().getResource("view/layout/plugScore.fxml"));

            mainComponent       = fxmlLoader.load();
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void modifyLanguage(String s) {

    }


    @Override
    public void eventDropObject() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void receiveATempo(Observable observable, MedleyTempo medleyTempo) {

    }

    @Override
    public void receiveAFile(Observable observable, File file) {

    }

    @Override
    public void receiveAnObject(Observable observable, Object o) {

    }

}
