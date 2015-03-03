package View;

import java.awt.event.MouseEvent;

import update.MedleyUpdate;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MedleyLauncherView {
	public ProgressBar progressBar;
	private Label state;
	private Stage stage;
	private VBox vbox;
	private Scene scene;
	private BorderPane borderPane;
	
	public MedleyLauncherView(Stage primaryStage) {
		this.stage = primaryStage;
		this.progressBar = new ProgressBar();
		this.state = new Label("Check Update Medley");
	}
	
	public void addProgress()
	{
		this.progressBar.setProgress(0.17F);
		System.out.println("MOTHERFUCKER");
	}
	
	public void setState(String state) {
		this.state.setText(state);
	}
	
	public void initView()
	{
		this.vbox = new VBox(8); // spacing = 8
		this.borderPane = new BorderPane();
		this.scene = new Scene(this.borderPane,700,400);
	//	this.progressBar = new ProgressBar();
	//	this.state = new Label("Check Update Medley");
		this.stage.setResizable(false);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Image logo = new Image("./src/Ressources/Images/MedleyLogoTypo.png");
		ImageView logoView = new ImageView();
        logoView.setImage(logo);
        logoView.setFitWidth(600);
        logoView.setPreserveRatio(true);
        progressBar.setMinWidth(scene.getWidth());
        this.vbox.getChildren().addAll(this.state,this.progressBar);
        this.borderPane.setBottom(vbox);
		this.borderPane.setCenter(logoView);
		this.stage.setScene(scene);
		//this.medleyUpdate.setProgressBar(progressBar);
		//this.medleyUpdate.setStateUpdate(state);
		this.stage.show();
	//	this.medleyUpdate.updateSoftware();
		
	}
	
	public void exit()
	{
		this.stage.close();
	}

	public void showPopUpSucces() {
		PopUpLancherUpdateSucces popUp = new PopUpLancherUpdateSucces();
		popUp.show(this.borderPane, 10, 10);
		
	}
}
