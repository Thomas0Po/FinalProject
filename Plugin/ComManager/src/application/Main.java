package application;
	
import java.util.Date;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			ObservableList<ColumnModel> data = FXCollections.observableArrayList(
				    new ColumnModel("Jacob", "Smith"),
				    new ColumnModel("Isabella", "Johnson"),
				    new ColumnModel("Ethan", "Williams"),
				    new ColumnModel("Emma", "Jones"),
				    new ColumnModel("Michael", "Brown"));			
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
			
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
