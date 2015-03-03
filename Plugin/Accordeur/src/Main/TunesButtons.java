package Main;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TunesButtons extends HBox {

	private List<Button> buttons;
	private EventHandler<ActionEvent> eventHandler;
	private Hashtable<String, ArrayList<Double>> mapTune;
	private SoundRecorder opTuner;

	public TunesButtons(SoundRecorder opTuner) {
		super(5);

		this.setStyle("-fx-border-color: #bfc2c7;");
		this.opTuner = opTuner;
		buttons = new ArrayList<Button>();
		mapTune = new Hashtable<String, ArrayList<Double>>();
		initMapTune();
		initEventHandler();
		initButtons();
		this.setPadding(new Insets(5, 10, 5, 10));
	}

	private void initButtons() {
		this.buttons.add(new Button("E"));
		this.buttons.add(new Button("A"));
		this.buttons.add(new Button("D"));
		this.buttons.add(new Button("G"));
		this.buttons.add(new Button("B"));
		this.buttons.add(new Button("e"));

		for (Button b : this.buttons) {
			b.setOnAction(eventHandler);
			this.getChildren().add(b);
		}
	}

	private void initMapTune() {
		ArrayList<Double> tmpArrayList = new ArrayList<Double>();

		tmpArrayList.add(77.781);
		tmpArrayList.add(87.307);
		tmpArrayList.add(82.406);
		this.mapTune.put("E", tmpArrayList);

		tmpArrayList = new ArrayList<Double>();

		tmpArrayList.add(103.826);
		tmpArrayList.add(116.540);
		tmpArrayList.add(110.0);
		this.mapTune.put("A", tmpArrayList);

		tmpArrayList.add(138.591);
		tmpArrayList.add(155.563);
		tmpArrayList.add(146.832);
		this.mapTune.put("D", tmpArrayList);

		tmpArrayList.add(1840.997);
		tmpArrayList.add(207.652);
		tmpArrayList.add(195.997);
		this.mapTune.put("G", tmpArrayList);

		tmpArrayList.add(233.081);
		tmpArrayList.add(261.625);
		tmpArrayList.add(246.941);
		this.mapTune.put("B", tmpArrayList);

		tmpArrayList.add(311.126);
		tmpArrayList.add(349.228);
		tmpArrayList.add(329.627);
		this.mapTune.put("e", tmpArrayList);

	}

	private void initEventHandler() {
		this.eventHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Button src = (Button) arg0.getSource();
				ArrayList<Double> tunesGot = mapTune.get(src.getText());
				opTuner.setFreqMin(tunesGot.get(0));
				opTuner.setFreqMax(tunesGot.get(1));
				opTuner.setFreqOk(tunesGot.get(2));
			}
		};
	}
}
