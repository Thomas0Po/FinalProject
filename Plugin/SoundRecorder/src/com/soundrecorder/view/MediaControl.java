package com.soundrecorder.view;

import java.io.File;

import com.soundrecorder.sound.Recorder;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class MediaControl extends BorderPane {
	private Recorder recorder;
	private MediaPlayer mp;
	private MediaView mediaView;
	private final boolean repeat = false;
	private boolean stopRequested = false;
	private boolean atEndOfMedia = false;
	private Duration duration;
	private Slider timeSlider;
	private Label playTime;
	private Slider volumeSlider;
	private HBox mediaBar;
	private boolean isRecording;
	private Label recordingLabel;
	private FileChooserSound fc;

	public MediaControl(final MediaPlayer mp) {
		this.mp = mp;
		this.fc = new FileChooserSound();
		this.recorder = new Recorder();
		this.recordingLabel = new Label("Recording...");
		setBottom(recordingLabel);
		this.recordingLabel.setVisible(false);
		this.isRecording = new Boolean(false);

		setStyle("-fx-background-color: transparent;");

		mediaBar = new HBox();
		mediaBar.setAlignment(Pos.CENTER);
		mediaBar.setPadding(new Insets(5, 10, 5, 10));
		BorderPane.setAlignment(mediaBar, Pos.CENTER);
		final RecordButton recordButton = new RecordButton();

		recordButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (isRecording == false) {
					File file = fc.save();
					if (file != null) {
						recorder.record(file);
						isRecording = true;
						recordingLabel.setVisible(true);
					}
				} else {
					recorder.stopRecord();
					isRecording = false;
					recordingLabel.setVisible(false);
				}
			}
		});

		mediaBar.getChildren().add(recordButton);

		final PlayPauseButton playButton = new PlayPauseButton();

		playButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (mp == null)
					return;
				Status status = mp.getStatus();

				if (status == Status.UNKNOWN || status == Status.HALTED) {
					// don't do anything in these states
					return;
				}

				if (status == Status.PAUSED || status == Status.READY
						|| status == Status.STOPPED) {
					// rewind the movie if we're sitting at the end
					if (atEndOfMedia) {
						mp.seek(mp.getStartTime());
						atEndOfMedia = false;
					}
					mp.play();
				} else {
					mp.pause();
				}
			}
		});

		if (mp != null) {
			mp.currentTimeProperty().addListener(new InvalidationListener() {
				public void invalidated(Observable ov) {
					updateValues();
				}
			});

			mp.setOnPlaying(new Runnable() {
				public void run() {
					if (stopRequested) {
						mp.pause();
						stopRequested = false;
					} else {
						playButton.setPause();
						;
					}
				}
			});

			mp.setOnPaused(new Runnable() {
				public void run() {
					System.out.println("onPaused");
					playButton.setPlay();
				}
			});

			mp.setOnReady(new Runnable() {
				public void run() {
					duration = mp.getMedia().getDuration();
					updateValues();
				}
			});

			mp.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
			mp.setOnEndOfMedia(new Runnable() {
				public void run() {
					if (!repeat) {
						playButton.setPlay();
						stopRequested = true;
						atEndOfMedia = true;
					}
				}
			});
		}


		final StopButton stopButton = new StopButton();

		stopButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (mp != null) {
					mp.seek(mp.getStartTime());
					atEndOfMedia = false;
					mp.pause();
				}
				if (isRecording == true) {
					recorder.stopRecord();
					isRecording = false;
					recordingLabel.setVisible(false);
				}
			}
		});

		mediaBar.getChildren().add(playButton);
		mediaBar.getChildren().add(stopButton);
		setCenter(mediaBar);
		// Add spacer
		Label spacer = new Label("   ");
		mediaBar.getChildren().add(spacer);

		// Add Time label
		Label timeLabel = new Label("Time: ");
		mediaBar.getChildren().add(timeLabel);

		// Add time slider
		timeSlider = new Slider();
		HBox.setHgrow(timeSlider, Priority.ALWAYS);
		timeSlider.setMinWidth(0.0);
		timeSlider.setMaxWidth(Double.MAX_VALUE);
		timeSlider.valueProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				if (mp == null)
					return;
				if (timeSlider.isValueChanging()) {
					// multiply duration by percentage calculated by slider
					// position
					mp.seek(duration.multiply(timeSlider.getValue() / 100.0));
				}
			}
		});
		mediaBar.getChildren().add(timeSlider);


		// Add Play label
		playTime = new Label();
		playTime.setPrefWidth(130);
		playTime.setMaxWidth(Region.USE_COMPUTED_SIZE);
		playTime.setMinWidth(0.0);

		mediaBar.getChildren().add(playTime);

		// Add the volume label
		Label volumeLabel = new Label("Vol: ");

		mediaBar.getChildren().add(volumeLabel);

		// Add Volume slider
		volumeSlider = new Slider();
		volumeSlider.setPrefWidth(70);
		volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
		volumeSlider.setMinWidth(0.0);
		volumeSlider.valueProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				if (mp != null) {
					if (volumeSlider.isValueChanging()) {
						mp.setVolume(volumeSlider.getValue() / 100.0);
					}
				}
			}
		});
		mediaBar.getChildren().add(volumeSlider);

	}

	protected void updateValues() {
		if (playTime != null && timeSlider != null && volumeSlider != null) {
			Platform.runLater(new Runnable() {
				public void run() {
					if (mp == null)
						return;
					Duration currentTime = mp.getCurrentTime();
					playTime.setText(formatTime(currentTime, duration));
					timeSlider.setDisable(duration.isUnknown());
					if (!timeSlider.isDisabled()
							&& duration.greaterThan(Duration.ZERO)
							&& !timeSlider.isValueChanging()) {
						timeSlider.setValue(currentTime.divide(duration)
								.toMillis() * 100.0);
					}
					if (!volumeSlider.isValueChanging()) {
						volumeSlider.setValue((int) Math.round(mp.getVolume() * 100));
					}
				}
			});
		}
	}

	protected String formatTime(Duration elapsed, Duration duration) {
		int intElapsed = (int) Math.floor(elapsed.toSeconds());
		int elapsedHours = intElapsed / (60 * 60);
		if (elapsedHours > 0) {
			intElapsed -= elapsedHours * 60 * 60;
		}
		int elapsedMinutes = intElapsed / 60;
		int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
				- elapsedMinutes * 60;

		if (duration.greaterThan(Duration.ZERO)) {
			int intDuration = (int) Math.floor(duration.toSeconds());
			int durationHours = intDuration / (60 * 60);
			if (durationHours > 0) {
				intDuration -= durationHours * 60 * 60;
			}
			int durationMinutes = intDuration / 60;
			int durationSeconds = intDuration - durationHours * 60 * 60
					- durationMinutes * 60;
			if (durationHours > 0) {
				return String.format("%d:%02d:%02d/%d:%02d:%02d", elapsedHours,
						elapsedMinutes, elapsedSeconds, durationHours,
						durationMinutes, durationSeconds);
			} else {
				return String.format("%02d:%02d/%02d:%02d", elapsedMinutes,
						elapsedSeconds, durationMinutes, durationSeconds);
			}
		} else {
			if (elapsedHours > 0) {
				return String.format("%d:%02d:%02d", elapsedHours,
						elapsedMinutes, elapsedSeconds);
			} else {
				return String.format("%02d:%02d", elapsedMinutes,
						elapsedSeconds);
			}
		}
	}

	public void setMediaPlayer(MediaPlayer mp) {
		this.mp = mp;
		this.mediaView.setMediaPlayer(mp);
	}

	public void stop() {
		if (this.mp != null)
			this.mp.stop();
	}

	public boolean playManagement() {
		// TODO Auto-generated method stub
		return false;
	}
}
