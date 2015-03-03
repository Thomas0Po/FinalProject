package com.soundrecorder.main;

import com.soundrecorder.view.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		View plug = new View();
		plug.initialiseComponent();
		Scene scene = new Scene(plug.getMainComponent(), 800, 600);
		root.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		root.setMinSize(0.0, 0.0);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
