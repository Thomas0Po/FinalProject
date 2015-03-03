package Views;

import Abstractions.Models.ShortCutBindModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class ShortcutsCell extends ListCell<ShortCutBindModel>{

	private HBox hbox = new HBox();
	private Label labelDescription = new Label("");
	
	private ToggleGroup group = new ToggleGroup();
	private RadioButton radio1 = new RadioButton("1");
	private RadioButton radio2 = new RadioButton("2");
	
	private ComboBox<String>	choice1 = new ComboBox<String>();
	private ChoiceBox<String>	choice2 = new ChoiceBox<String>();

	Pane pane = new Pane();

	private boolean init = true;
	
	public ShortcutsCell() {
		super();

		radio1.setToggleGroup(group);
		radio2.setToggleGroup(group);
		
		radio1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				choice1.setDisable(true);
				
			}
		});
		radio2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				choice1.setDisable(false);

			}
		});

		labelDescription.setPrefWidth(300);
		hbox.getChildren().add(labelDescription);
		
		hbox.getChildren().add(radio1);
		hbox.getChildren().add(radio2);
		hbox.getChildren().add(choice1);
		hbox.getChildren().add(choice2);
		
		HBox.setHgrow(pane, Priority.ALWAYS);
		
	}

	@Override
	protected void updateItem(ShortCutBindModel item, boolean empty) {
		super.updateItem(item, empty);
		
		if (empty) {
			setGraphic(null);
		} else if(item != null) {
			
			if (init){
				choice1.getItems().addAll(item.getCommands());
				choice2.getItems().addAll(item.getLetters());
				
				choice1.valueProperty().bindBidirectional(item.getCommand());
				choice2.valueProperty().bindBidirectional(item.getLetter());
				radio1.selectedProperty().bindBidirectional(item.getOne());
				radio2.setSelected(!item.getOne().getValue());

				if (item.getOne().getValue()){
					choice1.getSelectionModel().select(0);
					choice1.setDisable(true);
				}
				labelDescription.setText(item.getDescription());
			}
			init = false;

			setGraphic(hbox);
		}
	}
	
}
