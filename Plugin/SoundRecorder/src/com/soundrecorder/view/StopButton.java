package com.soundrecorder.view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StopButton extends Button {
	
	private final String STOP_PATH = "Ressources/stopbutton.png";
	private ImageView stopView;
	
	public StopButton() {
		super();
		stopView = new ImageView(new Image(STOP_PATH));
		setGraphic(stopView);
	}
}
