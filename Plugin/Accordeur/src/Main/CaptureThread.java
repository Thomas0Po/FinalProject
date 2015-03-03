package Main;

import javax.sound.sampled.TargetDataLine;

/**
 * La classe CaptureThread va analyser les sons enregistres afin de determiner
 * la justesse de la note jouee
 * 
 * @author Flaos
 *
 */
public class CaptureThread extends Thread {

	private SoundRecorder recorder;
	private DialView dialView;
	private double freqMin;
	private double freqMax;
	private double freqOK;
	private boolean running;

	/**
	 * Le constructeur du Thread prend en parametre le {@link SoundRecorder} et
	 * le {@link AccordeurPlug}
	 * 
	 * @param prevRecorder
	 * @param dialView
	 */
	public CaptureThread(SoundRecorder prevRecorder, DialView dialView) {
		super();
		this.recorder = prevRecorder;
		this.dialView = dialView;
		System.out.println("CaptureThread created");
	}

	/**
	 * Methode appelee au demarage du thread. Elle va enregistrer du son puis
	 * l'analyser pour savoir si la note est juste
	 */
	public void run() {
		try {
			this.running = true;
			int spectreSize = 2048 * 2 * 2 * 2 * 2;
			int sampleSize = 2048 * 2 * 2;
			double divi = 4 * 2 * (4096 / 4000);
			byte data[] = new byte[spectreSize];
			TargetDataLine targetDataLine = this.recorder.getTargetDataLine();
			targetDataLine.start();
			int nbValues = 0;
			double tempValue = 0;
			int nbMesures = 1;
			double[] ar = new double[spectreSize];
			double[] ai = new double[spectreSize];

			while (((targetDataLine.read(data, 0, sampleSize)) > 0)
					&& this.running) {

				try {
					for (int i = 0; i < sampleSize; i++) {
						ar[i] = (double) data[i];
						ai[i] = 0.0;
					}
					for (int i = sampleSize; i < spectreSize; i++) {
						ar[i] = 0.0;
						ai[i] = 0.0;

					}
					computeFFT(1, spectreSize, ar, ai);

					double maxAmpl = 0;
					double maxIndex = 0;
					double erreur = 0;

					for (int i = (int) (freqMin * divi); i < (freqMax * divi); i++) {
						if (Math.abs(ai[i]) > maxAmpl) {
							maxAmpl = Math.abs(ai[i]);
							maxIndex = i;

						}
						//
						// for (int i = 0; i < spectreSize / 2 - 1; i++) {
						// if (Math.abs(ai[i]) > maxAmpl) {
						// maxAmpl = Math.abs(ai[i]);
						// maxIndex = i;
						//
						// }
						System.out.println("maxIndex = " + maxIndex
								+ " freqOK = " + freqOK + " maxApml = "
								+ maxAmpl + "FREQUENCE = " + maxIndex * 8000.0F
								/ ((freqMax * divi)) + "Hz");

					}
					/**
					 * Ce block permet de mettre a jour les informations sur
					 * l'aiguille de l'accordeur
					 */
					if (maxAmpl > 0.01) {
						erreur = ((maxIndex / divi - freqOK) / (freqOK - freqMin));

						tempValue += erreur;

						nbValues += 1;
						if (nbValues > (nbMesures - 1)) {

							double angle = ((tempValue / nbMesures)) - 0.25;

							int xPos = (int) (40 * Math.sin(angle) + 61);
							int yPos = (int) (70 - (40 * Math.cos(angle)));
							nbValues = 0;
							tempValue = 0;
							dialView.update(xPos, yPos);
						}
					}

				} catch (Exception e2) {
					e2.printStackTrace();
					this.dialView.displayException(e2);
				}

				targetDataLine.flush();
			}
			targetDataLine.flush();
		} catch (Exception e3) {
			e3.printStackTrace();
			this.dialView.displayException(e3);
		}

	}

	/**
	 * Fast fourier transform, c'est grace a cette methode que l'on va savoir si
	 * la note jouee est correcte
	 ** 
	 * @param sign
	 * @param n
	 * @param ar
	 * @param ai
	 */
	private void computeFFT(int sign, int n, double[] ar, double[] ai) {
		double scale = 2.0 / (double) n;
		int i, j;
		for (i = j = 0; i < n; ++i) {
			if (j >= i) {
				double tempr = ar[j] * scale;
				double tempi = ai[j] * scale;
				ar[j] = ar[i] * scale;
				ai[j] = ai[i] * scale;
				ar[i] = tempr;
				ai[i] = tempi;
			}
			int m = n / 2;
			while ((m >= 1) && (j >= m)) {
				j -= m;
				m /= 2;
			}
			j += m;
		}
		int mmax, istep;
		for (mmax = 1, istep = 2 * mmax; mmax < n; mmax = istep, istep = 2 * mmax) {
			double delta = sign * Math.PI / (double) mmax;
			for (int m = 0; m < mmax; ++m) {
				double w = m * delta;
				double wr = Math.cos(w);
				double wi = Math.sin(w);
				for (i = m; i < n; i += istep) {
					j = i + mmax;
					double tr = wr * ar[j] - wi * ai[j];
					double ti = wr * ai[j] + wi * ar[j];
					ar[j] = ar[i] - tr;
					ai[j] = ai[i] - ti;
					ar[i] += tr;
					ai[i] += ti;
				}
			}
			mmax = istep;
		}
	}

	/**
	 * Permet de set la frequence minimum de la note voulu
	 * 
	 * @param freqMin
	 */
	public void setFreqMin(double freqMin) {
		this.freqMin = freqMin;
	}

	/**
	 * Permet de set la frequence maximum de la note voulu
	 * 
	 * @param freqMax
	 */
	public void setFreqMax(double freqMax) {
		this.freqMax = freqMax;
	}

	/**
	 * Permet de set la frequence parfaite de la note voulu
	 * 
	 * @param freqOK
	 */
	public void setFreqOK(double freqOK) {
		this.freqOK = freqOK;
	}

	/**
	 * permet de changer l'état du thread, mettre false si vous voulez l'arreter
	 * 
	 * @param newState
	 */
	public void setRunning(boolean newState) {
		this.running = newState;
	}
}