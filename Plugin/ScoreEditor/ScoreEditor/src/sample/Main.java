package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.plugScoreController;
import sample.model.business.StageManager;

public class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception{

            plugScoreController controller  = new plugScoreController();
            FXMLLoader          fxmlLoader  = new FXMLLoader(getClass().getResource("view/layout/plugScore.fxml"));
            Parent              root        = fxmlLoader.load();

            ScoreReader scoreReader = new ScoreReader();

            scoreReader.initialiseComponent();
            Scene               scene       = new Scene(scoreReader.getMainComponent(), 300, 275);
            StageManager.getInstance().setStage(primaryStage);

           // scene.getStylesheets().add("sample/css/style_tableview.css");
            primaryStage.setTitle("ScoreEditor");
            primaryStage.setScene(scene);
            primaryStage.show();

        }

        public static void main(String[] args) {
            launch(args);
        }

}
