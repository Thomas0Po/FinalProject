package sample.controller;

import com.audiveris.proxymusic.ScorePartwise;
import com.audiveris.proxymusic.util.Marshalling;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import org.jfugue.MusicStringRenderer;
import org.jfugue.MusicXmlParser;
import org.jfugue.Player;
import sample.model.business.InfoScore;
import sample.model.business.StageManager;
import sample.model.business.ThreadedPlayer;
import sample.model.business.XmlManager;
import sample.model.object.Score;
import sample.model.object._Part;
import sample.utils.SubPartHelper;
import sample.view.ScoreCell;
import sample.model.object.ScoreInfos;
import sample.model.object.SubPart;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class plugScoreController {

	@FXML private Button                playButton;
	@FXML private Button                stopButton;
	@FXML private Button                importButton;
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


    private ScorePartwise scorePartwise;

    private String filename  = "/Users/favre/Documents/Project/ScoreReader/resources/musicxml/BeetAnGeSample.xml";
    private org.jfugue.Pattern pattern;
    @FXML
    void initialize(){

        this.playButtonString   = new String("Play");
        this.stopButtonString   = new String("Stop");
        this.importButtonString = new String("Import");
        this.scoreInfos         = new ScoreInfos("titre", "auteur", "date");
        this.fileChooser        = new FileChooser();
        this.player             = new ThreadedPlayer();
        //this.list               = FXCollections.observableArrayList();
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

	@FXML protected void playButtonClicked(ActionEvent event)
	{
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

    @FXML protected void repeatButtonClicked(ActionEvent event)
    {
        this.player = new ThreadedPlayer();
        this.pattern.repeat(99);
        this.player.setPattern(this.pattern);
        this.player.start();
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
