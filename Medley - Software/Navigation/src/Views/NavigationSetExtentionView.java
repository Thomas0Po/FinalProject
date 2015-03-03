package Views;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

public class NavigationSetExtentionView extends TitledPane {
	Label tabLabel;
	Label scoreLabel;
	Label musicLabel;
	TextField tabTextField;
	TextField scoreTextField;
	TextField musicTextField;
	Separator LabelTextFieldSeparator;
	GridPane layout;
	Button OkButton;
	Button CancelButton;
	
	public NavigationSetExtentionView() {
		this.initComponent();
	}
	
	private void initComponent()
	{
		this.tabLabel = new Label("Tablature extentions");
		this.scoreLabel = new Label("Score extentions");
		this.musicLabel = new Label("music extentions");
		this.tabTextField = new TextField("Test");
		this.scoreTextField = new TextField("de");
		this.musicTextField = new TextField("la mort");
		this.LabelTextFieldSeparator = new Separator(Orientation.VERTICAL);
		this.OkButton = new Button("Apply");
		this.CancelButton = new Button("Cancel");
		this.layout = new GridPane();
		
		this.layout.add(this.scoreLabel, 0, 0);
		this.layout.add(this.tabLabel, 0, 1);
		this.layout.add(this.musicLabel, 0, 2);
		this.layout.add(this.LabelTextFieldSeparator, 1, 0);
		this.layout.add(this.scoreTextField, 2, 0);
		this.layout.add(this.scoreLabel, 2, 1);
		this.layout.add(this.scoreLabel, 2, 2);
	}
	
}
