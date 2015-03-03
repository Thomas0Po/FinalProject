package plugincontract;

import java.io.File;
import java.util.Observable;

import Communication.MedleyTempo;
import javafx.scene.layout.Pane;

public interface IPlugin {
	public void initialiseComponent();
	public Pane getMainComponent();
	public void modifyLanguage(String keyLanguage);
	public void eventDropObject();
	public void stop();
	
	/*
	 * Communication
	 */
	public void sendATempo(MedleyTempo tempoToSend);
	public void receiveATempo(Observable obsFrom, MedleyTempo tempo);
	public void sendAFile(File file);
	public void receiveAFile(Observable obsFrom, File file);
	
	/**
	 * Com for other object
	 */
	public void sendAnObject(Object obj);
	public void receiveAnObject(Observable obsFrom, Object obj);
}
