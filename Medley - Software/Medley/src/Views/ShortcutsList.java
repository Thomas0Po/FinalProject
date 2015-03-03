package Views;

import java.util.List;

import Abstractions.Models.ShortCutBindModel;
import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ShortcutsList extends ListView<ShortCutBindModel>{
	
public ShortcutsList(List<ShortCutBindModel> listModel) {		
		
		this.setItems(FXCollections.observableArrayList(listModel));
		
		this.setCellFactory(new Callback<ListView<ShortCutBindModel>, ListCell<ShortCutBindModel>>() {
			
			@Override
			public ListCell<ShortCutBindModel> call(ListView<ShortCutBindModel> param) {
				final ShortcutsCell pl = new ShortcutsCell();
				return pl;
			}
		});
	}

}
