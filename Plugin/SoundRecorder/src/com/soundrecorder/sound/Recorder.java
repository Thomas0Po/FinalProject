package com.soundrecorder.sound;

import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.TargetDataLine;

public class Recorder {

	private TargetDataLine line;

	private AudioFileFormat.Type fileType;
	private File destFile;
	private float sampleRate;
	private int sampleSizeInBits;
	private int channels;
	private boolean signed;
	private boolean bigEndian;

	public Recorder() {
		super();
		this.fileType = AudioFileFormat.Type.WAVE;
		this.sampleRate = 16000;
		this.sampleSizeInBits = 8;
		this.channels = 2;
		this.signed = true;
		this.bigEndian = true;
	}

	public void record(File dest) {
		this.destFile = dest;
		ThreadRecorder thread = new ThreadRecorder(this);
		thread.start();
	}

	public void stopRecord() {
		line.stop();
		line.close();
		System.out.println("stop recording");
	}

	public AudioFileFormat.Type getFileType() {
		return fileType;
	}

	public float getSampleRate() {
		return sampleRate;
	}

	public void setSampleRate(float sampleRate) {
		this.sampleRate = sampleRate;
	}

	public int getSampleSizeInBits() {
		return sampleSizeInBits;
	}

	public void setSampleSizeInBits(int sampleSizeInBits) {
		this.sampleSizeInBits = sampleSizeInBits;
	}

	public int getChannels() {
		return channels;
	}

	public void setChannels(int channels) {
		this.channels = channels;
	}

	public boolean isSigned() {
		return signed;
	}

	public void setSigned(boolean signed) {
		this.signed = signed;
	}

	public boolean isBigEndian() {
		return bigEndian;
	}

	public void setBigEndian(boolean bigEndian) {
		this.bigEndian = bigEndian;
	}

	public AudioFormat getAudioFormat() {
		AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
				channels, signed, bigEndian);
		return format;
	}

	public void setLine(TargetDataLine line2) {
		this.line = line2;
	}

	public File getDestFile() {
		return destFile;
	}

	public void setDestFile(File destFile) {
		this.destFile = destFile;
	}

	public TargetDataLine getLine() {
		return line;
	}

	public void setFileType(AudioFileFormat.Type fileType) {
		this.fileType = fileType;
	}

	
}
