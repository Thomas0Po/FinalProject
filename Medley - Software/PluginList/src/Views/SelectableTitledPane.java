package Views;

import java.io.File;

import Models.PluginInfoModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class SelectableTitledPane extends TitledPane {

	private CheckBox checkBox;

	/*
	 * Contructor
	 */
	public SelectableTitledPane(final PluginInfoModel plugin) {
		
		this.setContent(new Label(plugin.description));
		checkBox = new CheckBox();
	
		Label lab = new Label(plugin.name);
		File f = new File("src/Ressources/Images/notfound.jpg");
//		Image image = new Image("file:///" + f.getAbsolutePath());
		System.out.println("ICONPATH = " + plugin.iconPath);
		Image image = new Image(plugin.iconPath);

		ImageView imgView = new ImageView(image);
		imgView.setFitWidth(20);
		imgView.setFitHeight(20);
		checkBox.setPrefSize(15, 15);
		checkBox.setContentDisplay(ContentDisplay.RIGHT);

		if (plugin.isAlreadyInList)
			checkBox.setSelected(isSelected());
		checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				plugin.checked = newValue;
			}
		});
		
		HBox b = new HBox();
		b.getChildren().add(imgView);
		b.getChildren().add(lab);
		b.getChildren().add(checkBox);
		setGraphic(b);
		setContentDisplay(ContentDisplay.LEFT);
		setStyle("-fx-font-size: 18;");
		System.out.println("TEST 2");

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