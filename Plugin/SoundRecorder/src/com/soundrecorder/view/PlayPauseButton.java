package com.soundrecorder.view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayPauseButton extends Button {
	private final String PAUSE_PATH = "Ressources/pausebutton.png";
	private final String PLAY_PATH = "Ressources/playbuttonb.png";
	private ImageView pauseView;
	private ImageView playView;

	public PlayPauseButton() {
		super();
		pauseView = new ImageView(new Image(PAUSE_PATH));
		playView = new ImageView(new Image(PLAY_PATH));
		setGraphic(playView);
	}
	
	public void setPause() {
		setGraphic(pauseView);
	}

	public void setPlay() {
		setGraphic(playView);
	}

}
