package Main;

import com.sun.javafx.accessible.utils.Rect;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DialView extends VBox {

	private Pane cadran;
	private Line line;
	private TunesButtons tunesButtons;
	private int xPos = 61;
	private int yPos = 70 - 40;
	private SoundRecorder opTuner;
	
	public DialView() {
		super();
		this.cadran = new Pane();
		this.cadran.setStyle("-fx-border-color: #bfc2c7;");
		cadran.setStyle("-fx-background-image: url('resources/cadran.jpg'); -fx-background-position: center center; -fx-background-repeat: stretch;");
		cadran.setPrefSize(120, 80);
		cadran.setMaxSize(120, 80);
		line = new Line(xPos, yPos, 61, 70);
		cadran.getChildren().add(line);
	}
	
	public void setSoundRecorder(SoundRecorder opTunerOther) {
		this.opTuner = opTunerOther;
		this.tunesButtons = new TunesButtons(opTuner);
		this.getChildren().addAll(cadran, tunesButtons);
	}

	/**
	 * Cette methode met a jour l'aiguille de l'accordeur. xPos et yPos
	 * correspondent a la position du bout de l'aiguille.
	 * 
	 * @param xPos
	 * @param yPos
	 */
	public void update(final double xPos, final double yPos) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				line.setStartX(xPos);
				line.setStartY(yPos);
			}
		});

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

}
