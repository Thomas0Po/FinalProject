package application;

import java.io.File;
import java.util.Observable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import plugincontract.APlugin;

public class FileChooserMedleyPlugin extends APlugin{

	private File fileSelected;
	private Label fileSelectedInfo;
	private Button buttonFileChooser;
	
	@Override
	public void eventDropObject() {
		//test 
		if (this.arguments[0] != null)
		{
			System.out.println("FILE DROP CLASS NAME : " + this.arguments.getClass().getName());
		}
		
		if (this.arguments[0] != null && this.arguments.getClass().getName() == "File")
		{
			File file = (File) this.arguments[0];
			this.setFileSelected(file);
		}
	}

	public void setFileSelected(File file) {
		this.fileSelected = file;
		this.fileSelectedInfo.setText("File Selected : " + fileSelected.getName());
		if (this.isToSend())
		{
			this.setChanged();
			this.notifyObservers(fileSelected);
		}
	}

	@Override
	public void initialiseComponent() {
		BorderPane bp = new BorderPane();
		this.mainComponent = new BorderPane();
		this.fileSelectedInfo = new Label("No File Selected");
		this.buttonFileChooser = new Button("Choose a file");
		this.buttonFileChooser.setOnAction(new ButtonEvent(this));
		bp.setCenter(buttonFileChooser);
		bp.setBottom(this.fileSelectedInfo);
		this.mainComponent = bp;
	}

	@Override
	public void setLanguage(String arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void stop() {
		this.mainComponent.setVisible(false);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub	
	}
}
