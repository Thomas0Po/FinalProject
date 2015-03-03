package Main;

import java.io.File;
import java.util.Observable;

import Communication.MedleyTempo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import plugincontract.APlugin;

/**
 * Classe principale du plugin, elle herite de APlugin c'est dans cette classe
 * qu'on gere le graphique
 * 
 * @author Flaos
 * 
 */
public class AccordeurPlug extends APlugin {

	SoundRecorder opTuner;
	DialView dialView;
	BorderPane layout;

	Boolean alreadyLaunched;

	/**
	 * Les images sont contenues dans "src/resources" Elles correspondent à
	 * l'image de fond et aux boutons.
	 */


	
	/**
	 * Initialise la fenetre et met en place un handler sur les evenements de la
	 * souris
	 */
	@Override
	public void initialiseComponent() {
		System.out.println("appel a initialise component");
		Platform.setImplicitExit(false);
		this.dialView = new DialView();
		this.layout = new BorderPane();
		startThisThingYouCallAstarter();
		this.layout.setOnMouseClicked(new EventHandler<MouseEvent>() {

			/**
			 * handler pour les mouse click, suivant les coordonnees du clique,
			 * on met a jouer la note à reconnaitre dans le Sound Recorder
			 * 
			 * @param arg0
			 */
			@Override
			public void handle(MouseEvent arg0) {
				if (opTuner.isError()) {
					final Stage dialog = new Stage();
					Button button = new Button("Ok");
					button.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent arg0) {
							dialog.close();
						}
					});
					Scene myDialogScene = new Scene(VBoxBuilder.create()
		                     .children(new Text("Une erreur est survenue, l'accordeur est surement déjà lancé, veuillez le fermer."), button)
		                     .alignment(Pos.CENTER)
		                     .padding(new Insets(10))
		                     .build());
		           
		             dialog.setScene(myDialogScene);
					dialog.show();
					return ;
				}
			}
		});
		this.dialView.setSoundRecorder(opTuner);
		this.layout.setCenter(dialView);
		this.mainComponent = layout; 
	}

	private void startThisThingYouCallAstarter() {
		if (alreadyLaunched == false) {
			System.out.println("AccordeurPlug : Appel a start()");
			opTuner = new SoundRecorder(this.dialView);
			opTuner.start();
			alreadyLaunched = true;
		}		
	}

	/**
	 * cette méehode est e appeler obligatoirement a la fin du programme, elle
	 * met fin aux threads et a la prise de son.
	 */
	@Override
	public void stop() {
		System.out.println("Appel a stop");
		this.layout.setVisible(false);
		try {
			this.opTuner.stop();
		} catch (Exception e) {
		}
	}
	
	/**
	 * Cette methode est appelee en cas d'exception, elle permet d'afficher une
	 * alert box avec le contenu de l'exception
	 * 
	 * @param e
	 */
	public void displayException(final Exception e) {
		Platform.runLater(new Runnable() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				Stage dialogStage = new Stage();
				dialogStage.initModality(Modality.WINDOW_MODAL);
				dialogStage.setScene(new Scene(VBoxBuilder.create()
						.children(new Text(e.getMessage()), new Button("Ok."))
						.alignment(Pos.CENTER).padding(new Insets(5)).build()));
				dialogStage.show();
			}
		});
		
	}

	
	
	public AccordeurPlug() {
		super();
		this.alreadyLaunched = false;
	}

	@Override
	public void eventDropObject() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void modifyLanguage(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveAFile(Observable arg0, File arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveATempo(Observable arg0, MedleyTempo arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveAnObject(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
