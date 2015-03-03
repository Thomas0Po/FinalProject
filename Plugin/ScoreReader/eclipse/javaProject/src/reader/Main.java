package reader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import reader.controller.plugScoreController;
import reader.model.business.StageManager;
import reader.view.ScoreCell;

public class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception{

            plugScoreController controller  = new plugScoreController();
            FXMLLoader          fxmlLoader  = new FXMLLoader(getClass().getResource("view/layout/plugScore.fxml"));
            Parent              root        = fxmlLoader.load();

            ScoreReader scoreReader = new ScoreReader();


            //Parent p = scoreReader.getParent();
            //scoreReader.
            scoreReader.initialiseComponent();
            Scene               scene       = new Scene(scoreReader.getMainComponent(), 300, 275);
            StageManager.getInstance().setStage(primaryStage);

           // scene.getStylesheets().add("sample/css/style_tableview.css");
            primaryStage.setTitle("ScoreReader");
            primaryStage.setScene(scene);
            primaryStage.show();

        }

        public static void main(String[] args) {
            launch(args);
        }

}
