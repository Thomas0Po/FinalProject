package com.soundrecorder.view;

import java.io.File;
import java.util.Observable;

import Communication.MedleyTempo;
import plugincontract.APlugin;
import javafx.beans.InvalidationListener;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class View extends APlugin {

	private MediaControl mediaControl;
	private MediaPlayer mediaPlayer;
	private VBox vbox;
	private SpectrumAudio spectrumAudio;
	private AnchorPane anchorPane;

	public View() {
		super();
		spectrumAudio = new SpectrumAudio();
		this.mediaPlayer = null;
	}

	public void initialiseComponent() {
		// this.setStyle("-fx-border-color: red");
		anchorPane = new AnchorPane();
		System.out.println("entree dans initialise component");
		vbox = new VBox();
		vbox.setStyle("-fx-background-color: transparent");
		mediaControl = new MediaControl(null);
		System.out.println("new media controller c'est fait !");
		vbox.getChildren().add(mediaControl);
		this.anchorPane.getChildren().add(vbox);
		AnchorPane.setBottomAnchor(vbox, 0.0);
		AnchorPane.setTopAnchor(vbox, 0.0);
		AnchorPane.setLeftAnchor(vbox, 0.0);
		AnchorPane.setRightAnchor(vbox, 0.0);
		this.mainComponent = anchorPane;
		this.mainComponent.setStyle("-fx-background-color: transparent");
	}

	private void changeSound(File newSound) {
		File file = newSound;
		Media media;
		media = new Media((file.toURI()).toString());
		System.out.println(file.toURI());
		if (this.vbox.getChildren().get(0) != this.spectrumAudio)
			this.vbox.getChildren().add(0, this.spectrumAudio);
		this.vbox.getChildren().remove(mediaControl);
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		this.mediaControl.stop();
		this.mediaControl = new MediaControl(this.mediaPlayer);
		this.vbox.getChildren().add(1, mediaControl);
		this.mediaPlayer.setAudioSpectrumListener(this.spectrumAudio
				.getAudioSpectrumListener());
	}

	@Override
	public void stop() {
		this.mediaControl.stop();
	}

	@Override
	public void eventDropObject() {
		if (this.arguments[0].getClass() == File.class) {
			changeSound((File) this.arguments[0]);
		}
	}

	@Override
	public void modifyLanguage(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveAFile(Observable arg0, File arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveAnObject(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveATempo(Observable arg0, MedleyTempo arg1) {
		// TODO Auto-generated method stub
		
	}

}
