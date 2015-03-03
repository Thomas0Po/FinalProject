package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Popup;

public class ButtonPopUpOkListener implements EventHandler<ActionEvent> {
	
	Popup popup;
	
	public ButtonPopUpOkListener(Popup popup) {
		this.popup = popup;
	}

	@Override
	public void handle(ActionEvent event) {
		this.popup.hide();
	}

	
}
