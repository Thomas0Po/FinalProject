package Views;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Abstractions.Models.LanguageModel;
import Models.NavigationFile;
import Models.NavigationFolder;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;

public class NavigationTab extends Tab {
	NavigationFolder navigFolder;
	GridPane layout;
	Separator sepTreeAndScoreList;
	Separator sepScoreListAndTabList;
	Separator sepTabListAndMusicList;
	Separator sepLeftPaneAndTree;
	Separator sepMusicListAndPane;
	Separator sepHorizontalTextUp;
	Separator sepHorizontalTextDown;
	Separator sepHorizontalDown;
	ListView<NavigationFile> tabList;
	ListView<NavigationFile> scoreList;
	ListView<NavigationFile> musicList;
	Label labTree;
	Label labScore;
	Label labTab;
	Label labMusic;
	NavigationTreeView tree;
	
	public NavigationTab(NavigationFolder navigFolder, LanguageModel languageModel, String currentLanguage)
	{	
		this.navigFolder = navigFolder;	
		this.init(languageModel, currentLanguage);
	}
	
	private void init(LanguageModel language, String currentLanguage)
	{
		this.layout = new GridPane();
		this.layout.getStyleClass().add("navigGridLayout");
		this.tabList = new ListView<NavigationFile>(this.navigFolder.getTablatureFileList());
		this.scoreList = new ListView<NavigationFile>(this.navigFolder.getScoreMusicFileList());
		this.musicList = new ListView<NavigationFile>(this.navigFolder.getMusicFileList());
		
		this.tabList.getStyleClass().add("navigTabList");
		this.scoreList.getStyleClass().add("navigTabList");
		this.musicList.getStyleClass().add("navigTabList");
		
		this.tree = new NavigationTreeView(this.navigFolder.getAbsolutePath());
		
		this.sepScoreListAndTabList = new Separator();
		this.sepTabListAndMusicList = new Separator();
		this.sepTreeAndScoreList = new Separator();
		this.sepLeftPaneAndTree = new Separator();
		this.sepMusicListAndPane = new Separator();
		this.sepHorizontalTextUp = new Separator(Orientation.HORIZONTAL);
		this.sepHorizontalTextDown = new Separator(Orientation.HORIZONTAL);
		this.sepHorizontalDown = new Separator(Orientation.HORIZONTAL);
		this.labMusic = new Label("Musiques");
		this.labMusic.setText(language.getText("MUSICLABEL", currentLanguage));
		this.labScore = new Label("Partitions");
		this.labScore.setText(language.getText("SCORELABEL", currentLanguage));
		this.labTree = new Label("Mes Dossiers");
		this.labTree.setText(language.getText("MYDIRLABEL", currentLanguage));
		this.labTab = new Label("Tablatures");
		this.labTab.setText(language.getText("TABLABEL", currentLanguage));
		
		this.layout.add(sepLeftPaneAndTree, 0, 2);
		
		
		BorderPane treeBorderpane = new BorderPane();
		treeBorderpane.setCenter(this.tree);
		treeBorderpane.getStyleClass().add("navigList");
		this.layout.add(treeBorderpane, 1, 3);
		//this.layout.add(this.tree, 1, 3);
		
		
		
		BorderPane treePane = new BorderPane();
		treePane.setCenter(this.labTree);
		treePane.getStyleClass().add("Test");
		this.layout.add(treePane, 1, 1);
		
		//this.layout.add(this.labTree, 1, 1);
		this.layout.add(this.sepTreeAndScoreList, 2, 0);
		
		BorderPane scorePane = new BorderPane();
		scorePane.setCenter(this.labScore);
		scorePane.getStyleClass().add("Test");
		this.layout.add(scorePane, 3, 1);
		//this.layout.add(this.labScore, 3, 1);
		
		
		BorderPane scoreListPane = new BorderPane();
		scoreListPane.setCenter(this.tabList);
		scoreListPane.getStyleClass().add("navigList");
		this.layout.add(scoreListPane, 3, 3);
//		this.layout.add(this.scoreList, 3, 3);
		this.layout.add(this.sepScoreListAndTabList,4, 0);
		
		
		BorderPane tabPane = new BorderPane();
		tabPane.setCenter(this.labTab);
		tabPane.getStyleClass().add("Test");
		this.layout.add(tabPane, 5, 1);
//		this.layout.add(this.labTab, 5, 1);
		
		
		BorderPane tabListPane = new BorderPane();
		tabListPane.setCenter(this.tabList);
		tabListPane.getStyleClass().add("navigList");
		this.layout.add(tabListPane, 5, 3);
		//this.layout.add(this.tabList, 5, 3);
		
		
		this.layout.add(this.sepTabListAndMusicList,6, 0);
		BorderPane musicPane = new BorderPane();
		musicPane.setCenter(labMusic);
		musicPane.getStyleClass().add("Test");
		this.layout.add(musicPane, 7, 1);
		//this.layout.add(this.labMusic, 7, 1);
		
		
		BorderPane musicListPane = new BorderPane();
		musicListPane.setCenter(this.musicList);
		musicListPane.getStyleClass().add("navigList");
		this.layout.add(musicListPane, 7, 3);
		//this.layout.add(this.musicList, 7, 3);
		
		this.layout.add(this.sepMusicListAndPane,8,0);
		this.layout.add(this.sepHorizontalTextUp, 0, 0);
		this.layout.add(this.sepHorizontalTextDown, 0, 3);
		this.layout.add(this.sepHorizontalDown, 0, 9);
		
		this.labTree.getStyleClass().add("navigLabels");
		this.labScore.getStyleClass().add("navigLabels");
		this.labTab.getStyleClass().add("navigLabels");
		this.labMusic.getStyleClass().add("navigLabels");
		
		GridPane.setHalignment(this.labTab, HPos.CENTER);
		GridPane.setHalignment(this.labScore, HPos.CENTER);
		GridPane.setHalignment(this.labTree, HPos.CENTER);
		GridPane.setHalignment(this.labMusic, HPos.CENTER);
		
		ColumnConstraints columnSep = new ColumnConstraints(5);
		ColumnConstraints columnSepOut = new ColumnConstraints(1);
		ColumnConstraints columnListView = new ColumnConstraints(100,100,Double.MAX_VALUE);
	    columnListView.setHgrow(Priority.ALWAYS);
	    layout.getColumnConstraints().addAll(columnSepOut,columnListView,columnSep,columnListView, columnSep, columnListView,columnSep, columnListView, columnSepOut); // first column gets any extra width
		
	    RowConstraints rowSepOut = new RowConstraints(1);
	    RowConstraints rowLabel = new RowConstraints(this.labMusic.getHeight() + 10);
	    RowConstraints rowSep = new RowConstraints(5);
	    RowConstraints rowListView = new RowConstraints(100,100,Double.MAX_VALUE);
	    rowListView.setVgrow(Priority.ALWAYS);
	    layout.getRowConstraints().addAll(rowSep, rowLabel, rowSep, rowListView, rowSepOut);
	    
		this.tree.getSelectionModel().selectedItemProperty().addListener(new NavigationTreeEvent(this));
		this.setContent(layout);
		this.setText(this.navigFolder.getPath());
		
		this.musicList.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				fileDragged(event, musicList);
			}
			
		});
		this.scoreList.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				fileDragged(event, scoreList);
				
			}	
		});
		this.tabList.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				fileDragged(event, tabList);
			}
		});
	}
	
	public void update()
	{
		this.musicList.setItems(this.navigFolder.getMusicFileList());
		this.scoreList.setItems(this.navigFolder.getScoreMusicFileList());
		this.tabList.setItems(this.navigFolder.getTablatureFileList());
	}
	
	public void updateTree()
	{
		this.tree.setPathRoot(this.navigFolder.getAbsolutePath());
		this.setText(this.navigFolder.getPath());
	}
	
	public void setTexts(LanguageModel languages, String newLanguage){
		this.labMusic.setText(languages.getText("MUSICLABEL", newLanguage));
		this.labScore.setText(languages.getText("SCORELABEL", newLanguage));
		this.labTab.setText(languages.getText("TABLABEL", newLanguage));
		this.labTree.setText(languages.getText("MYDIRLABEL", newLanguage));

	}
	
	private void fileDragged(MouseEvent event, ListView source){
		 Dragboard db = source.startDragAndDrop(TransferMode.ANY);
	        
	        /* Put a string on a dragboard */
	        ClipboardContent content = new ClipboardContent();
	        List<File> l = new ArrayList<File>();
	        NavigationFile f = (NavigationFile)source.getSelectionModel().getSelectedItem();
	        l.add(f.getFile());
	        content.putString("File");
	        content.putFiles(l);
	        db.setContent(content);
	        
	        event.consume();
	}
}
