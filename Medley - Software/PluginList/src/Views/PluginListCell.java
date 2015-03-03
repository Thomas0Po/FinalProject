package Views;


import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import plugincontract.APlugin;

public class PluginListCell extends ListCell<APlugin> {
		HBox hbox = new HBox();
		Label label = new Label("(empty)");
		

		Pane pane = new Pane();
		Image image; //= new Image("/Ressources/Images/notfound.jpg");
		Button button = new Button("(>)");
		ImageView iv2;

		public PluginListCell() {
			super();
			
			iv2 = new ImageView();
			iv2.setImage(image);
			iv2.getStyleClass().add("medleyListViewImage"); 
			iv2.setFitWidth(50);
			iv2.setFitHeight(50);
			label.getStyleClass().add("medleyListViewLabel"); 
			
			hbox.getChildren().addAll(iv2, label, pane );
			HBox.setHgrow(pane, Priority.ALWAYS);
			
		}

		@Override
		protected void updateItem(APlugin item, boolean empty) {
			super.updateItem(item, empty);
			setText(null); // No text in label of super class
			if (empty) {
				setGraphic(null);
			} else if(item != null) {
				label.setText(item != null ? item.getName() : "Plugin");
				image = new Image(item.getIconName());
				if (image == null){
					File f = new File("src/Ressources/Images/notfound.jpg");
					image = new Image("file:///" + f.getAbsolutePath());
					//image = new Image("/Ressources/Images/notfound.jpg");
				}
				if (image == null)
					System.out.println("IMAGE ENCORE NULLE");
				iv2.setImage(image);
				setGraphic(hbox);
			}
		}
		
		public Label getLabel() {
			return label;
		}
}
