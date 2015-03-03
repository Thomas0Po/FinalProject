package Main;
import Utils.Helpers.MedleyPopup;
import Controllers.MainController;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

	/*
	 * Main function
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/*
	 * Initializes main controller and launches it
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage arg0) throws Exception {
		
		try {
			MainController maincontroller = new MainController();
			maincontroller.launch(arg0);
		} catch (Exception e) {
			MedleyPopup pop = new MedleyPopup(e.getMessage());
			e.printStackTrace();
		}
	}
}
