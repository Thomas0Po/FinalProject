package metronome;

import java.io.File;
import java.util.Observable;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

import Communication.MedleyTempo;
import plugincontract.APlugin;

public class Metronome extends APlugin {
	public Metronome() {
		// TODO Auto-generated constructor stub
	}

	private PlayBeats playBeat = new PlayBeats();
	// ConfigSound
	private final long minBpm = 30;
	private final long maxBpm = 500;
	private boolean play = false;
	private CurrentConfig config = null;
	ObservableList<TypeSound> allSound = null;
	private long betweenTapButton = 0;
	private long timeBetweenBeats = 1000;
	private MedleyTempo comTempo = new MedleyTempo();
	private MidiChannel channel = null;

	private Design m_design = null;

	private final ImageView imageStart = new ImageView(
		      new Image("Play_Metronome.png"));
	private final ImageView imageStop = new ImageView(
		      new Image("Stop_Metronome.png"));

	/*private AnchorPane anchonePane;
	private VBox vbox;*/
	
	public void updateThread() {
		 processTempoChange();
		if (this.m_design.getSoundChooser().getValue() == null)
			this.config.setSound(allSound.get(0));
		else
			this.config.setSound((TypeSound) this.m_design.getSoundChooser().getValue());
		playBeat.setConfig(config);
		playBeat.setTimeBetweenBeats(timeBetweenBeats);
		if (this.isToSend())
		{
			comTempo.setTempo(config.getSpeed());
		}
	}

	public void playSound() {
		play = true;
		playBeat.setPlay(play);
		updateThread();
	}

	public void stopSound() {
		play = false;
		playBeat.setPlay(play);
	}

	@Override
	public void initialiseComponent() {
		BorderPane root = new BorderPane();;
		Platform.setImplicitExit(false);
		play = false;
		root.setStyle("-fx-background-color: grey;");
		/*
		 * OPEN MIDI SOUND TO MAKE SOUND
		 */
		try {
			final Synthesizer synthesizer = MidiSystem.getSynthesizer();
			synthesizer.open();
			channel = synthesizer.getChannels()[9];
		} catch (Exception e) {
			System.out.println("[Metronome Exception]\t" + e.toString() + " : "
					+ e.getMessage());
		}
		this.config = new CurrentConfig(new TypeSound("toto", 100), 100,
				"Basique");
		/*
		 * ALL DIFFERENT SOUND WE HAVE FOR METRONOME
		 */
		allSound = FXCollections.observableArrayList(
				new TypeSound("Claves", 75), new TypeSound("Cow Bell", 56),
				new TypeSound("High Bongo", 60),
				new TypeSound("Low Bongo", 61), new TypeSound(
						"High Wood Block", 76), new TypeSound("Low Wood Block", 77));

		playBeat.setChannel(channel);
		
		/*anchonePane = new AnchorPane();
		vbox = new VBox();*/
		m_design = new Design(config.getSpeed(), minBpm, maxBpm, imageStart, allSound);
		
		/*this.anchonePane.getChildren().add(vbox);
		AnchorPane.setBottomAnchor(vbox, 0.0);
		AnchorPane.setTopAnchor(vbox, 0.0);
		AnchorPane.setLeftAnchor(vbox, 0.0);
		AnchorPane.setRightAnchor(vbox, 0.0);*/
		root.getChildren().add(m_design);
		createActionOnDesign();

		this.mainComponent = root;
		//this.mainComponent = anchonePane;
		startThread();
	}
	
	public void createActionOnDesign()
	{
		m_design.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				if (play == false) {
					playSound();
					m_design.getStartButton().setGraphic(imageStop);
				} else {
					stopSound();
					m_design.getStartButton().setGraphic(imageStart);
				}
			}
		});
		
		m_design.getTapButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				long tmp = System.currentTimeMillis();
				if (betweenTapButton == 0 || tmp - betweenTapButton > 1000
						|| tmp - betweenTapButton <= 0)
					betweenTapButton = tmp;
				else {
					timeBetweenBeats = tmp - betweenTapButton;
					config.setSpeed((int) (1000 * 60 / timeBetweenBeats));
					betweenTapButton = tmp;
					updateThread();
					updateDesign();
				}
			}
		});
	
		m_design.getBmpSlider().valueProperty().addListener((new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				Metronome.this.config.setSpeed(newValue.intValue());
				updateThread();
				updateDesign();
			}
		}));

		m_design.getAddOneTempo().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Metronome.this.config.setSpeed(Metronome.this.config.getSpeed() + 1);
				updateDesign();
			}
		});
		
		m_design.getRemoveOneTempo().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Metronome.this.config.setSpeed(Metronome.this.config.getSpeed() -1);
				updateDesign();
			}
		});
		
		m_design.getAddFiveTempo().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Metronome.this.config.setSpeed(Metronome.this.config.getSpeed() + 5);
				updateDesign();
			}
		});
		
		m_design.getRemoveFiveTempo().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Metronome.this.config.setSpeed(Metronome.this.config.getSpeed() - 5);
				updateDesign();
			}
		});
		
		m_design.getSoundChooser().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				updateThread();
			}
		});
	}
	
	public void		updateDesign()
	{
		m_design.updateTempoText(config.getSpeed());
		m_design.updateSliderValue(config.getSpeed());
	}

	public void start() {
	}

	@Override
	public void stop() {
		stopSound();
		playBeat.setToOff();
		try {
			playBeat.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processTempoChange() {
		timeBetweenBeats = 1000 * 60 / this.config.getSpeed();
	}

	private void startThread() {
		if (channel != null) {
			updateThread();
			playBeat.start();
		}
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
