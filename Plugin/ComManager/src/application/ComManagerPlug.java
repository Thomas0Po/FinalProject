package application;

import java.util.ArrayList;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import plugincontract.APlugin;

public class ComManagerPlug extends APlugin{

	private ObservableList<ColumnModel> data;
	
	@Override
	public void eventDropObject() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialiseComponent() {
		data = FXCollections.observableArrayList();
		
		BorderPane root = new BorderPane();
		TableView<ColumnModel> tableView = new TableView<ColumnModel>();
		
		TableColumn<ColumnModel, String> columnPlugin = new TableColumn<ColumnModel, String>("Plugin Name");
		TableColumn<ColumnModel, String> columnType = new TableColumn<ColumnModel, String>("Type Received");
		TableColumn<ColumnModel, String> columnDate = new TableColumn<ColumnModel, String>("Date");
		
		columnPlugin.setCellValueFactory(new PropertyValueFactory<ColumnModel,String>("name"));
		columnType.setCellValueFactory(new PropertyValueFactory<ColumnModel,String>("type"));
		columnDate.setCellValueFactory(new PropertyValueFactory<ColumnModel,String>("date"));
		
		columnPlugin.setMinWidth(100);
		columnType.setMinWidth(100);
		columnDate.setMinWidth(120);
		
		tableView.getColumns().add(columnPlugin);
		tableView.getColumns().add(columnType);
		tableView.getColumns().add(columnDate);
		tableView.setItems(data);
		
		
		root.setCenter(tableView);
		this.mainComponent = root;
	}

	@Override
	public void setLanguage(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.data.add(new ColumnModel(arg0.getClass().getSimpleName(), arg1.getClass().getSimpleName())); 
	}

}
