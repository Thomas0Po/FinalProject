package test;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import metronome.*;

public class Main extends Application {
    public static void main(String[] args) 
    {
    	launch(args);
    }

	@Override
	public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 500, Color.WHITE);
        Metronome metronome = new Metronome();
        /*metronome.initialiseComponent();
        stage.setScene(scene);
        stage.show();*/
/*        while(true)
        {
        	System.out.println("pourquoi?");
        	Thread.sleep(500);
        }*/
	}
}
