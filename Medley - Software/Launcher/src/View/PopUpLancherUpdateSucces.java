package View;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Popup;

public class PopUpLancherUpdateSucces extends Popup {
	Button okButton;
	Label text; 
	
	public PopUpLancherUpdateSucces() {
		this.setX(100);
		this.setY(200);
		this.text = new Label("Update Succed");
		this.okButton = new Button("Ok");
		this.okButton.setOnAction(new ButtonPopUpOkListener(this));
		this.getContent().addAll(this.text, this.okButton);
	}
	
	
}
