package com.soundrecorder.view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RecordButton extends Button {
	private final String RECORD_PATH = "Ressources/recordButton.png";
	private ImageView stopView;
	
	public RecordButton() {
		super();
		System.out.println("START CONSTRUCTOR RECORD BUTTON");
		stopView = new ImageView(new Image(RECORD_PATH));
		setGraphic(stopView);
		System.out.println("END CONSTRUCTOR");
	}
}
