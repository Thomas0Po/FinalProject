package Views;


import Models.PluginConf;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SelectablePane extends TitledPane {

	private CheckBox checkBox;

	/*
	 * Constructor
	 */
	public SelectablePane(final PluginConf plugin) {
		//super(plugin.getName(), new Label(plugin.getDescription()));

		HBox hbox = new HBox();
		Label title = new Label(plugin.getName());
		Label description = new Label(plugin.getDescription());
		this.setContent(description);
		checkBox = new CheckBox();
		Image image = new Image(plugin.getIconName());
		ImageView imgView = new ImageView(image);
		imgView.setFitWidth(20);
		imgView.setFitHeight(20);
		checkBox.setGraphic(imgView);
		checkBox.setPrefSize(15, 15);
		checkBox.setContentDisplay(ContentDisplay.RIGHT);

		if (plugin.isToRun())
			checkBox.setSelected(isSelected());
		checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				plugin.setToRun(newValue.toString());
			}
		});
		
		
		hbox.getChildren().add(checkBox);
		hbox.getChildren().add(imgView);
		hbox.getChildren().add(title);
		
		
		setGraphic(hbox);
		setContentDisplay(ContentDisplay.RIGHT);
		setStyle("-fx-font-size: 18;");
	}

	public BooleanProperty getSelectedProperty() {
		return checkBox.selectedProperty();
	}

	public boolean isSelected() {
		// return checkBox.isSelected();
		return true;
	}

	public void setSelected(boolean selected) {
		// checkBox.setSelected(selected);
	}
}