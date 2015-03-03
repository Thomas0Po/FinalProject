package Utils.Helpers;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MedleyPopup {

	Stage stage;
	Scene scene;
	BorderPane border;
	ImageView warningImage;
	Button okbutton;
	Label label;
	
	public MedleyPopup(String text){
		stage = new Stage();
		border = new BorderPane();
		scene = new Scene(border, 500, 200);
		File ex = new File("src/Ressources/Images/WarningIcon.png");
		
		warningImage = new ImageView(new Image("file:///" + ex.getAbsolutePath()));
		warningImage.setFitHeight(50);
		warningImage.setFitWidth(50);
		okbutton = new Button("Ok");
		okbutton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				stage.hide();
			}
		});
		label = new Label(text);
		border.setLeft(warningImage);
		border.setCenter(label);
		border.setBottom(okbutton);
		stage.setScene(scene);
		stage.show();
	}
	
}
