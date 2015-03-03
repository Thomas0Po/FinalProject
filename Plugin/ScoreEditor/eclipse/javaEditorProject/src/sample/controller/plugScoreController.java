package sample.controller;

import com.audiveris.proxymusic.ScorePartwise;
import com.audiveris.proxymusic.util.Marshalling;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import org.jfugue.MusicStringRenderer;
import org.jfugue.MusicXmlParser;
import org.jfugue.Pattern;
import org.jfugue.Player;
import sample.model.business.StageManager;
import sample.model.business.ThreadedPlayer;
import sample.model.business.XmlManager;
import sample.model.object.Score;
import sample.model.object.ScoreInfos;
import sample.model.object.SubPart;
import sample.model.object._Part;
import sample.utils.SubPartHelper;
import sample.view.ScoreCell;
import com.audiveris.proxymusic.*;
import com.audiveris.proxymusic.ScorePartwise.Part;
import com.audiveris.proxymusic.ScorePartwise.Part.Measure;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class plugScoreController {

	@FXML private Button                playButton;
	@FXML private Button                stopButton;
	@FXML private Button                importButton;
    @FXML private Button                eightNoteButton;

	@FXML private ListView<List<SubPart>>      staveListView;
	@FXML private Label				    scoreInfosLabel;

    private String                      playButtonString;
    private String                      stopButtonString;
    private String                      importButtonString;
    //private ObservableList<ScorePartwise.Part>      list;
    private ScoreInfos scoreInfos;
    private Score score;
    private FileChooser fileChooser;
    private ThreadedPlayer player;
    private StringBuilder currentPlay;

    private ScorePartwise scorePartwise;
    private Measure startMeasure;
    private String filename  = "/Users/favre/Documents/Project/ScoreReader/resources/musicxml/BeetAnGeSample.xml";
    private org.jfugue.Pattern pattern;
    @FXML
    void initialize(){

        this.currentPlay = new StringBuilder();

        this.playButtonString   = new String("Play");
        this.stopButtonString   = new String("Stop");
        this.scoreInfos         = new ScoreInfos("titre", "auteur", "date");
        this.fileChooser        = new FileChooser();
        this.player             = new ThreadedPlayer();
        //this.list               = FXCollections.observableArrayList();

        this.filename = "src/musicxml/tmp.xml";

        this.openFile();
    }

	public plugScoreController()
    {
	}

    private void openFile(){
        // Hydrate ScorPartwise
        try                                             { this.importData();        }
        catch (IOException e)                           { e.printStackTrace();      }
        catch (Marshalling.UnmarshallingException e)    { e.printStackTrace();      }

        this.staveListView.setItems(FXCollections.observableList(this.convertToSubPartList()));

        this.staveListView.setCellFactory(new Callback<ListView<List<SubPart>>, ListCell<List<SubPart>>>() {
            public ListCell<List<SubPart>> call(ListView<List<SubPart>> listListView) {

                final ScoreCell scoreCell = new ScoreCell();

                return scoreCell;
            }
        });
    }

    private void importData() throws IOException, Marshalling.UnmarshallingException
    {
        System.out.println("ImportData Start...");
        try
        {
            XmlManager          xmlManager  = new XmlManager();
            MusicXmlParser      parser 		= new MusicXmlParser();
            MusicStringRenderer renderer 	= new MusicStringRenderer();
            File                file2       = new File(filename);

            this.score = new Score(xmlManager.xmlReader(filename));

            parser.addParserListener(renderer);
            parser.parse(file2);

            this.score.getScorePartwise();
            this.pattern = renderer.getPattern();
        }
        catch (Exception e) {System.err.println("importData : " + e.getMessage()); e.printStackTrace(); }

        System.out.println("ImportData Done.");
    }

    private List<List<SubPart>> convertToSubPartList()
    {
        List<List<SubPart>> ret = new ArrayList<List<SubPart>>();

        int partIndex = 0;
        for(_Part part : this.score.getListPart()){
            System.out.println("PART : " + part.toString());
            //subPart.setMeasures(part.getMeasure());



            for(int i = 0, j = 0; i < part.getNbMeasure(); i+=4, ++j){

                System.out.println("i : " + i + " | j : " + j);

                //Add PartCell if not enough
                if (ret.size() <= j)
                    ret.add(SubPartHelper.buildArray(this.score.getNbPartCouple() + this.score.getNbPart()));



                if (part.isCouple())
                {
                    SubPart subPart         = ret.get(j).get(partIndex);
                    SubPart coupleSubPart   = ret.get(j).get(partIndex + 1);

                    if (part.getListMeasure().size() > i + 4)
                        subPart.setMeasures(part.getListMeasure().subList(i, i + 4));
                    else
                        subPart.setMeasures(part.getListMeasure().subList(i, part.getListMeasure().size()));

                    if (part.getListMeasureIfCouple().size() > i + 4)
                        coupleSubPart.setMeasures(part.getListMeasureIfCouple().subList(i, i + 4));
                    else
                        coupleSubPart.setMeasures(part.getListMeasureIfCouple().subList(i, part.getListMeasureIfCouple().size()));
                }
                else
                {

                    SubPart subPart = ret.get(j).get(partIndex);
                    if (part.getListMeasure().size() > i + 4)
                        subPart.setMeasures(part.getListMeasure().subList(i, i + 4));
                    else
                        subPart.setMeasures(part.getListMeasure().subList(i, part.getListMeasure().size()));
                    System.out.println("subPart.Measures size : " + subPart.getMeasures().size());

                    //ret.get(j).add(subPart);
                }


            }

            ++partIndex;
        }

        return ret;
    }

	public String getPlayButtonString() {
		return playButtonString;
	}


	public String getStopButtonString() {
		return stopButtonString;
	}


	public String getImportButtonString() {
		return importButtonString;
	}


	public ScoreInfos getScoreInfos() {
		return scoreInfos;
	}


    public ListView<List<SubPart>> getStaveListView() {
        return staveListView;
    }

    public void setStaveListView(ListView<List<SubPart>> staveListView) {
        this.staveListView = staveListView;
    }


    private void generateNote(Step step){
        Note        note        = new Note();
        NoteType    noteType    = new NoteType();
        Pitch       pitch       = new Pitch();

        pitch.setStep(step);
        pitch.setOctave(4);
        noteType.setValue("eighth");
        note.setType(noteType);
        note.setPitch(pitch);

        Part    part    = (Part)this.score.getScorePartwise().getPart().toArray()[0];
        Measure measure = (Measure)part.getMeasure().toArray()[part.getMeasure().size()  - 1];

        if (this.startMeasure == null){
          this.startMeasure = new Measure();
          this.startMeasure.getNoteOrBackupOrForward().addAll(measure.getNoteOrBackupOrForward());
        }
        int max = 4;

        if ((part.getMeasure().size()  - 1) == 0)   max = 5;
        else                                        max = 4;

        if (measure.getNoteOrBackupOrForward().size() >= max)
        {
            Measure newMeasure = new Measure();

            newMeasure.getNoteOrBackupOrForward().add(note);
            part.getMeasure().add(newMeasure);
        }
        else
        {
            measure.getNoteOrBackupOrForward().add(note);
            part.getMeasure().toArray()[0] = measure;
        }


        this.score.getScorePartwise().getPart().toArray()[0] = part;

        this.score = new Score(this.score.getScorePartwise());

        this.staveListView.getItems().clear();
        this.staveListView.setItems(FXCollections.observableList(this.convertToSubPartList()));

        this.staveListView.setCellFactory(new Callback<ListView<List<SubPart>>, ListCell<List<SubPart>>>() {
            public ListCell<List<SubPart>> call(ListView<List<SubPart>> listListView) {

                final ScoreCell scoreCell = new ScoreCell();

                return scoreCell;
            }
        });

    }

    @FXML protected void Erase(ActionEvent event){

        Part    part    = (Part)this.score.getScorePartwise().getPart().toArray()[0];


        this.currentPlay = new StringBuilder();
        ((Part)this.score.getScorePartwise().getPart().toArray()[0]).getMeasure().clear();
        ((Part)this.score.getScorePartwise().getPart().toArray()[0]).getMeasure().add(this.startMeasure);
        this.score = new Score(this.score.getScorePartwise());

        this.staveListView.getItems().clear();
        this.staveListView.setItems(FXCollections.observableList(this.convertToSubPartList()));

        this.staveListView.setCellFactory(new Callback<ListView<List<SubPart>>, ListCell<List<SubPart>>>() {
            public ListCell<List<SubPart>> call(ListView<List<SubPart>> listListView) {

                final ScoreCell scoreCell = new ScoreCell();

                return scoreCell;
            }
        });

    }

    @FXML protected void ENoteClicked(ActionEvent event){

        this.generateNote(Step.E);
        this.currentPlay.append(" E4");
    }

    @FXML protected void CNoteClicked(ActionEvent event){

        this.generateNote(Step.C);
        this.currentPlay.append(" C4");
    }

    @FXML protected void DNoteClicked(ActionEvent event){

        this.generateNote(Step.D);
        this.currentPlay.append(" D4");
    }

    @FXML protected void FNoteClicked(ActionEvent event){

        this.generateNote(Step.F);
        this.currentPlay.append(" F4");
    }
    @FXML protected void GNoteClicked(ActionEvent event){

        this.generateNote(Step.G);
        this.currentPlay.append(" G4");
    }
    @FXML protected void ANoteClicked(ActionEvent event){

        this.generateNote(Step.A);
        this.currentPlay.append(" A4");
    }
    @FXML protected void BNoteClicked(ActionEvent event){

        this.generateNote(Step.B);
        this.currentPlay.append(" B4");
    }

    @FXML protected void E5NoteClicked(ActionEvent event){

        this.generateNote(Step.E);
        this.currentPlay.append(" E5");
    }

    @FXML protected void C5NoteClicked(ActionEvent event){

        this.generateNote(Step.C);
        this.currentPlay.append(" C5");
    }

    @FXML protected void D5NoteClicked(ActionEvent event){

        this.generateNote(Step.D);
        this.currentPlay.append(" D5");
    }

    @FXML protected void F5NoteClicked(ActionEvent event){

        this.generateNote(Step.F);
        this.currentPlay.append(" F5");
    }
    @FXML protected void G5NoteClicked(ActionEvent event){

        this.generateNote(Step.G);
        this.currentPlay.append(" G5");
    }
    @FXML protected void A5NoteClicked(ActionEvent event){

        this.generateNote(Step.A);
        this.currentPlay.append(" A5");
    }
    @FXML protected void B5NoteClicked(ActionEvent event){

        this.generateNote(Step.B);
        this.currentPlay.append(" B5");
    }

	@FXML protected void playButtonClicked(ActionEvent event)
	{
        this.pattern = new Pattern(this.currentPlay.toString());
		System.out.println("playButtonClicked");
        this.player = new ThreadedPlayer();

        this.pattern.repeat(1);
        this.player.setPattern(this.pattern);
        this.player.start();
	}

	@FXML protected void stopButtonClicked(ActionEvent event)
	{

        try {
            this.player.getPlayer().stop();
            this.player.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML protected void saveButtonClicked(ActionEvent event)
    {
        try
        {
            File file = fileChooser.showSaveDialog(StageManager.getInstance().getStage());

            // Get a populated score partwise
            ScorePartwise   scorePartwise   = this.score.getScorePartwise();
            File            xmlFile         = file;
            OutputStream    os              = new FileOutputStream(xmlFile);

            Marshalling.marshal(scorePartwise, os, true, 2);
            os.close();

        } catch (Exception ex) { ex.printStackTrace(); }
    }

	@FXML protected void importButtonClicked(ActionEvent event)
	{

        File file = fileChooser.showOpenDialog(StageManager.getInstance().getStage());

        if (file != null) {
            this.scoreInfos = new ScoreInfos(file.getName(), "auteur", "date");
            this.filename = file.getAbsolutePath();
            System.out.println("FILENAME : " + filename);
            openFile();
        }
	}
}
