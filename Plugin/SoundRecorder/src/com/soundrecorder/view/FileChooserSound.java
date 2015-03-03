package com.soundrecorder.view;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileChooserSound {

	FileChooser fc;

	public FileChooserSound() {
		fc = new FileChooser();
		
		ExtensionFilter extFilter = new ExtensionFilter("WAV file .wav", "*.wav");
		fc.getExtensionFilters().add(extFilter);
		extFilter = new ExtensionFilter("MP3 file .mp3", "*.mp3");
		fc.getExtensionFilters().add(extFilter);
	}

	public File save() {
		File file = fc.showSaveDialog(null);

		return file;
	}
}
