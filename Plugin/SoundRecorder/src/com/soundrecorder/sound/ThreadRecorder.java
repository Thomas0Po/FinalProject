package com.soundrecorder.sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

public class ThreadRecorder extends Thread {

	private Recorder recorder;

	public ThreadRecorder(Recorder rec) {
		this.recorder = rec;
	}

	public void run() {
		try {
			AudioFormat format = this.recorder.getAudioFormat();
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
			if (!AudioSystem.isLineSupported(info)) {
				System.out.println("Line not supported");
				System.exit(0);
			}

			this.recorder.setLine((TargetDataLine) AudioSystem.getLine(info));
			this.recorder.getLine().open(format);
			this.recorder.getLine().start(); // start capturing

			System.out.println("Start capturing...");

			AudioInputStream ais = new AudioInputStream(this.recorder.getLine());

			System.out.println("Start recording...");

			AudioSystem.write(ais, this.recorder.getFileType(),
					this.recorder.getDestFile());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
