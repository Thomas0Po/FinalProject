package metronome;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class Design extends BorderPane {
	
	private final String STYLE_PRESSED = "-fx-background-color: transparent; -fx-padding: 6 4 4 6;";
    
	private	Text		tTempo = null;
	
	private Button		startButton = null;
	private Text		tName = null;
	
	private Button		tapButton = null;

	private Slider		bpmSlider = null;
	private Text		bpmTextSlider = null;
	
	private Button		addOneTempo = null;
	private Button		removeOneTempo = null;
	private Button		addFiveTempo = null;
	private Button		removeFiveTempo = null;
	
	private ComboBox<TypeSound> soundChooser = null;
	
	private final ImageView imageTapTempo = new ImageView(
		      new Image("Tap_Tempo.png"));
	
	//private HBox test = new HBox();
	
	Design(int speed, long minBpm, long maxBpm, ImageView imageStart, ObservableList<TypeSound> allSound)
	{
		/*test.setAlignment(Pos.CENTER);
		test.setPadding(new Insets(5, 10, 5, 10));
		BorderPane.setAlignment(test, Pos.CENTER);*/
		
		createTempoText(speed);
		createNamePluginText();
		createStartButton(imageStart);
		createTapButton();
		createBpmTextSlider();
		createBmpSlider(minBpm, maxBpm, speed);
		createAddOneTempo();
		createRemoveOneTempo();
		createAddFiveTempo();
		createRemoveFiveTempo();
		createSoundChooser(allSound);
/*test.getChildren().addAll(tTempo, tName, startButton, tapButton, bpmTextSlider, bpmSlider,
				addOneTempo, removeOneTempo, addFiveTempo, removeFiveTempo, soundChooser);
*/
		this.getChildren().addAll(tTempo, tName, startButton, tapButton, bpmTextSlider, bpmSlider,
				addOneTempo, removeOneTempo, addFiveTempo, removeFiveTempo, soundChooser);
	
	}

	public void createAddFiveTempo()
	{
		addFiveTempo = new Button();
		addFiveTempo.setLayoutX(560);
		addFiveTempo.setLayoutY(175);
		addFiveTempo.setText("+5");
	}
	
	public Button getAddFiveTempo()
	{
		return addFiveTempo;
	}
	
	public void createRemoveFiveTempo()
	{
		removeFiveTempo = new Button();
		removeFiveTempo.setLayoutX(395);
		removeFiveTempo.setLayoutY(175);
		removeFiveTempo.setText("-5");
	}
	
	public Button getRemoveFiveTempo()
	{
		return removeFiveTempo;
	}
	
	public void createAddOneTempo()
	{
		addOneTempo = new Button();
		addOneTempo.setLayoutX(560);
		addOneTempo.setLayoutY(130);
		addOneTempo.setText("+1");
	}
	
	public Button getAddOneTempo()
	{
		return addOneTempo;
	}
	
	public void createRemoveOneTempo()
	{
		removeOneTempo = new Button();
		removeOneTempo.setLayoutX(395);
		removeOneTempo.setLayoutY(130);
		removeOneTempo.setText("-1");
	}
	
	public Button getRemoveOneTempo()
	{
		return removeOneTempo;
	}
	
	public void updateTempoText(int newSpeed)
	{
		tTempo.setText("Tempo : " + newSpeed);
	}
	
	public void createTempoText(int speed)
	{
		tTempo = new Text(440, 170, "Tempo : " + speed);
		tTempo.setFill(Color.RED);
		tTempo.setFont(new Font(20));
	}
	
	public void createNamePluginText()
	{
		tName = new Text(350, 40, "Metronome by Medley");
		tName.setCache(true);
		tName.setFill(Color.ORANGE);
		tName.setFont(Font.font(null, FontWeight.BOLD, 24));
		Reflection r = new Reflection();
		r.setFraction(0.7f);
		tName.setEffect(r);
	}
	
	public void createStartButton(ImageView imageStart)
	{
		startButton = new Button("", imageStart);
		startButton.setLayoutX(460);
		startButton.setLayoutY(280);
		startButton.setStyle(STYLE_PRESSED);
	}
	
	public Button getStartButton()
	{
		return startButton;
	}

	public void createTapButton()
	{

		tapButton = new Button("", imageTapTempo);
		tapButton.setLayoutX(630);
		tapButton.setLayoutY(130);
		tapButton.setStyle(STYLE_PRESSED);

	}
	
	public Button getTapButton()
	{
		return tapButton;
	}

	public void createBmpSlider(long minBpm, long maxBpm, int speed)
	{
		this.bpmSlider = new Slider();
		this.bpmSlider.setLayoutX(90);
		this.bpmSlider.setLayoutY(260);
		this.bpmSlider.setPrefWidth(800);
		this.bpmSlider.setMin(minBpm);
		this.bpmSlider.setMax(maxBpm);
		this.bpmSlider.setMajorTickUnit(5);
		this.bpmSlider.setMinorTickCount(5);
		this.bpmSlider.setValue(speed);
	}
	
	public Slider getBmpSlider()
	{
		return bpmSlider;
	}
	
	public void updateSliderValue(int speed)
	{
		bpmSlider.setValue(speed);
	}
	
	public void createBpmTextSlider()
	{
		this.bpmTextSlider = new Text();
		this.bpmTextSlider.setLayoutX(850);
		this.bpmTextSlider.setLayoutY(250);
		this.bpmTextSlider.setCache(true);
		this.bpmTextSlider.setText("Allegro");
	}
	
	public void createSoundChooser(ObservableList<TypeSound> allSound)
	{
		this.soundChooser = new ComboBox<TypeSound>();
		this.soundChooser.setLayoutX(180);
		this.soundChooser.setLayoutY(310);
		this.soundChooser.setStyle("-fx-background-color: #00828b;-fx-padding: 0.333333em; ");
		this.soundChooser.setItems(allSound);
		this.soundChooser.setPromptText("Choose your Sound");
		this.soundChooser.setConverter(new StringConverter<TypeSound>() {
		public String toString(TypeSound t) {
			return t.getName();
		}
		@Override
		public TypeSound fromString(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		});
	}
	
	public ComboBox<TypeSound> getSoundChooser()
	{
		return this.soundChooser;
	}

}
