package application;

import Controller.LauncherController;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			LauncherController controller = new LauncherController(primaryStage);
			if (controller.updateSoftware() == false)
				System.exit(0);
			else
				System.exit(0);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
