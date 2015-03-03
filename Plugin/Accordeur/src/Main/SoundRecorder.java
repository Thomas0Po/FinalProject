package Main;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 * La classe SoundRecorder va instancier l'enregistrement du son et lancer le
 * thread.
 * 
 * @author Flaos
 * 
 */
public class SoundRecorder {
	TargetDataLine targetDataLine;
	CaptureThread captureThread;
	AudioFormat audioFormat;
	DialView dialView;
	private boolean error;

	/**
	 * Le constructeur prend en parametre un {@link AccordeurPlug}
	 * 
	 * @param accordeurPlug
	 */
	public SoundRecorder(DialView dialView) {
		this.dialView = dialView;
		this.setError(false);
	}

	/**
	 * Cette methode permet de lancer le thread qui va enregistrer le son.
	 */
	public void start() {
		System.out.println("start du sound recorder");
		audioFormat = new AudioFormat(8000.0F, 8, 1, true, false);
		DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class,
				audioFormat);
		try {
			targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);

			targetDataLine.open(audioFormat);

			captureThread = new CaptureThread(this, dialView);
			captureThread.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			this.setError(true);
		}
	}

	/**
	 * Permet d'avoir acces au targetDataLine
	 * 
	 * @return
	 */
	public TargetDataLine getTargetDataLine() {
		return targetDataLine;
	}

	/**
	 * Methode obligatoire a appeler en cas d'arret du plugin, met fin aux
	 * thread et a la prise de son
	 * 
	 * @throws InterruptedException
	 */
	public void stop() throws Exception {
		this.captureThread.setRunning(false);
		targetDataLine.stop();
		int count;
		byte[] b = new byte[256];
		do {
			count = targetDataLine.read(b, 0, 256);
		} while (count > 0);
		targetDataLine.close();
	}

	/**
	 * set la frequence maximum de la note à reconnaitre
	 * 
	 * @param newFreq
	 */
	public void setFreqMax(double newFreq) {
		this.captureThread.setFreqMax(newFreq);
	}

	/**
	 * set la frequence minimum de la note à reconnaitre
	 * 
	 * @param newFreq
	 */
	public void setFreqMin(double newFreq) {
		this.captureThread.setFreqMin(newFreq);
	}

	/**
	 * set la frequence parfaite de la note à reconnaitre
	 * 
	 * @param newFreq
	 */
	public void setFreqOk(double newFreq) {
		this.captureThread.setFreqOK(newFreq);
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
}
