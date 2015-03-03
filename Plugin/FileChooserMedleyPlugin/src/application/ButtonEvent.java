package application;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;

public class ButtonEvent implements EventHandler<ActionEvent>{

	private FileChooserMedleyPlugin contRef;
	private FileChooser fc;
	
	
	public ButtonEvent(FileChooserMedleyPlugin contRef)
	{
		this.contRef = contRef;
		this.fc = new FileChooser();
	}
	
	@Override
	public void handle(ActionEvent event) {
		
		System.out.println("TEST ");
		File file = this.fc.showOpenDialog(null);
		if (file != null)
		{
			this.contRef.setFileSelected(file);
		}
		else
			System.out.println("Fils de pute");
	}
}
