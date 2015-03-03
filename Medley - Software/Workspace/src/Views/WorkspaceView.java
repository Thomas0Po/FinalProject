package Views;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Abstractions.Models.LanguageModel;
import Abstractions.Models.ShortCutsListModel;
import Abstractions.Views.AMenuBarItem;
import Abstractions.Views.IMenuBar;
import Abstractions.Views.IView;
import Controllers.WorkspaceController;
import Helpers.PluginHelper;
import Models.PluginsRunningStatus;
import plugincontract.APlugin;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class WorkspaceView implements IView {
	
	List<IMenuBar> menus;
	
	SplitPane horizontalSplit;
	SplitPane verticalTopSplit;
	SplitPane verticalBottomSplit;
	
	TabPane topLeft;
	TabPane topRight;
	TabPane bottomLeft;
	TabPane bottomRight;
	
	TabPane tabpanePluginDropped;
	WorkspaceController controllerRef;
	private final Map<Pane, Integer> mapRunningPlugins = new HashMap<Pane, Integer>();

	public void init(WorkspaceController ref){
		this.controllerRef = ref;
		
		this.initMainComponent();
		this.initDragAndDrop();
		this.initMenuBar();
	}

	private void initMainComponent()
	{
		horizontalSplit = new SplitPane();
		horizontalSplit.setOrientation(Orientation.VERTICAL);
		verticalTopSplit = new SplitPane();
		verticalTopSplit.setOrientation(Orientation.HORIZONTAL);
		verticalBottomSplit = new SplitPane();
		verticalBottomSplit.setOrientation(Orientation.HORIZONTAL);
		
		
		topLeft = new TabPane();
		topRight = new TabPane();
		bottomLeft = new TabPane();
		bottomRight = new TabPane();
		
		
		BorderPane verticaltopborder = new BorderPane();
		verticaltopborder.getStyleClass().add("topborder");
		BorderPane verticalbottomborder = new BorderPane();
		
		verticaltopborder.setCenter(verticalTopSplit);
		verticalbottomborder.setCenter(verticalBottomSplit);
		

		//SplitPane.setResizableWithParent(verticaltopborder, false);
		//SplitPane.setResizableWithParent(verticalbottomborder, false);
		
		horizontalSplit.getItems().add(verticaltopborder);

		horizontalSplit.getItems().add(verticalbottomborder);
		SplitPane.setResizableWithParent(horizontalSplit.getItems().get(1), false);
//		SplitPane.setResizableWithParent(verticalTopSplit.getItems().get(1), false);
//		SplitPane.setResizableWithParent(horizontalSplit, false);
//		SplitPane.setResizableWithParent(horizontalSplit.getItems().get(1), false);
		
		
		// on utilise des borderpane pour mettre un layout entre le splitpane item et le tabpane
		BorderPane bordertopright = new BorderPane();
		BorderPane bordertopleft = new BorderPane();
		BorderPane borderbottomright = new BorderPane();
		BorderPane borderbottomleft = new BorderPane();
		
		bordertopright.setCenter(topRight);
		bordertopleft.setCenter(topLeft);
		borderbottomright.setCenter(bottomRight);
		borderbottomleft.setCenter(bottomLeft);

//		SplitPane.setResizableWithParent(bordertopright, false);
//		SplitPane.setResizableWithParent(horizontalSplit, true);
		
//		SplitPane.setResizableWithParent(bordertopleft, false);
//		SplitPane.setResizableWithParent(borderbottomright, false);
//		SplitPane.setResizableWithParent(borderbottomleft, false);
//		2
//		SplitPane.setResizableWithParent(verticalBottomSplit, false);
//		SplitPane.setResizableWithParent(verticalTopSplit, false);
		
		verticalTopSplit.getItems().add(bordertopleft);
		verticalTopSplit.getItems().add(bordertopright);
		verticalBottomSplit.getItems().add(borderbottomleft);
		verticalBottomSplit.getItems().add(borderbottomright);

		SplitPane.setResizableWithParent(verticalTopSplit.getItems().get(1), false);

		verticalTopSplit.setDividerPositions(1);
		verticalTopSplit.getStyleClass().add("split-paneinvisible");

		verticalBottomSplit.setDividerPositions(1);
		verticalBottomSplit.getStyleClass().add("split-paneinvisible");

		horizontalSplit.setDividerPositions(1);
		horizontalSplit.getStyleClass().add("split-paneinvisible");
		topRight.setDisable(true);
		bottomRight.setDisable(true);
		verticalBottomSplit.setDisable(true);

	}
	
	private void initDragAndDrop()
	{
		topLeft.setOnDragOver(new EventHandler<DragEvent>()
		        {
		            @Override
		            public void handle(DragEvent event)
		            {
		            	
		            	System.out.println("topleft dragover");
		            	tabpaneOnDragOver(event);
		            }
		        }); 		
		topRight.setOnDragOver(new EventHandler<DragEvent>()
		        {
		            @Override
		            public void handle(DragEvent event)
		            {
		            		tabpaneOnDragOver(event);
		            }
		        });
		bottomLeft.setOnDragOver(new EventHandler<DragEvent>()
		        {
		            @Override
		            public void handle(DragEvent event)
		            {
		            		tabpaneOnDragOver(event);
		            }
		        });
		
		bottomRight.setOnDragOver(new EventHandler<DragEvent>()
		        {
		            @Override
		            public void handle(DragEvent event)
		            {
		            		tabpaneOnDragOver(event);
		            }
		        });
		
		topLeft.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				System.out.println("topleft plugin dropped");
				tabPaneOnDragDropped(event, topLeft);
			}
		});

		topRight.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				tabPaneOnDragDropped(event, topRight);
			}
		});
		
		bottomLeft.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				tabPaneOnDragDropped(event, bottomLeft);
			}
		});
		
		bottomRight.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				tabPaneOnDragDropped(event, bottomRight);
			}
		});
	}
	
	private void initMenuBar()
	{
		this.menus = new ArrayList<IMenuBar>();
		IMenuBar pluginCommunication = new MenuBar();
		AMenuBarItem pluginCommunicationItem = new MenuBarItem("Plugins");
		pluginCommunication.setName("Plugins");
		pluginCommunicationItem.setTextMenuItem("Plugin Communication");
		//TODO LANGUAGE
		pluginCommunicationItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				controllerRef.setPluginsCommunication();
			}
		});
		pluginCommunication.setMenuItem(pluginCommunicationItem);
		this.menus.add(pluginCommunication);
	}
	
	public void tabpaneOnDragOver(DragEvent event){
		event.acceptTransferModes(TransferMode.ANY);
		event.consume();
	}
	
	public void tabPaneOnDragDropped(DragEvent event, TabPane tabPaneTarget){
		System.out.println(" setondragdropped !");
		Dragboard db = event.getDragboard();

			List<File> files = db.getFiles();
			if (!files.isEmpty()){
				if (tabPaneTarget.getTabs().size() == 0)
					return;
				
				Pane pane = (Pane) tabPaneTarget.getSelectionModel().getSelectedItem().getContent();
				int pluginID = mapRunningPlugins.get(pane);
				Object[] objs = new Object[1];
				objs[0] = files.get(0);
				controllerRef.dropObjectToAPlugin(pluginID, objs);
				event.setDropCompleted(true);
				event.consume();
				return;
			}
			else if (!db.getString().equals("TabDragged")){
				System.out.println("pas tabdragged");
				TabPane target = (TabPane) event.getGestureTarget();
				tabpanePluginDropped = target;
				controllerRef.getPluginDropped();
				event.setDropCompleted(true);
				event.consume();
			}
	}
	
	public Node getMainComponent(){
		return horizontalSplit;
	}

	@Override
	public List<IMenuBar> getMenuBarItems() {
		return this.menus;
	}

	//private boolean tooManyPlugins() {
	//	int nbPlugins = mapRunningPlugins.size();
	//	if (nbPlugins == NB_PLUGINS_MAX)
	//		return true;
	//	return false;
	//	
	//}
	
	public void addPlugin(PluginsRunningStatus newPlug) {
		this.mapRunningPlugins.put(newPlug.getPluginView(), newPlug.getId());
		
		final DraggableTab tab = new DraggableTab(newPlug.getPluginName());
		tab.setViewRef(this);
		tab.setContent(newPlug.getPluginView());
		tab.setOnClosed(new EventHandler<Event>() {
		    @Override
		    public void handle(Event t) {
		    	Pane panePlugin = (Pane) tab.getContent();
		    	int pluginID = mapRunningPlugins.get(panePlugin);
		    	controllerRef.closePlugin(pluginID);
		    	mapRunningPlugins.remove(panePlugin);
		        unsplit();
		    }
		});
		
		if (tabpanePluginDropped == null){ // TODO : bug to finish !!
			tabpanePluginDropped = topLeft;
		}
		tabpanePluginDropped.getTabs().add(tab);
		tabpanePluginDropped.getSelectionModel().select(tab);
		tabpanePluginDropped = null;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTexts(LanguageModel languages, String language) {
		// TODO Auto-generated method stub
		
	}

	public void setShortcuts(ShortCutsListModel arg0) {
		
		
	}
	
	/*
	 * Check if a tabpane contains no tab and unsplit appropriatly
	 */
	public void unsplit() {
		System.out.println("unsplit !");
		if (topLeft.getTabs().size() == 0 && !topRight.isDisable()){
			System.out.println("1");
			topLeft.setDisable(true);
			verticalTopSplit.getStyleClass().clear();

			verticalTopSplit.getStyleClass().add("split-paneinvisible");

			verticalTopSplit.setDividerPositions(0);
		}
		if (topRight.getTabs().size() == 0 && !topLeft.isDisable()) {
			System.out.println("2");

			topRight.setDisable(true);
			verticalTopSplit.getStyleClass().clear();
			
			verticalTopSplit.getStyleClass().add("split-paneinvisible");

			verticalTopSplit.setDividerPositions(1);
		}
		if (topLeft.getTabs().size() == 0 && topRight.getTabs().size() == 0) {
			System.out.println("3");


			verticalTopSplit.setDividerPositions(1);
			verticalTopSplit.getStyleClass().clear();

			verticalTopSplit.getStyleClass().add("split-paneinvisible");

			verticalTopSplit.setDisable(true);

			horizontalSplit.setDividerPositions(0);
			horizontalSplit.getStyleClass().clear();

			horizontalSplit.getStyleClass().add("split-paneinvisible");

		}
		if (bottomLeft.getTabs().size() == 0 && !bottomRight.isDisable()) {
			System.out.println("4");

			bottomLeft.setDisable(true);

			verticalBottomSplit.setDividerPositions(0);
			verticalBottomSplit.getStyleClass().clear();

			verticalBottomSplit.getStyleClass().add("split-paneinvisible");

		}
		if (bottomRight.getTabs().size() == 0 && !bottomLeft.isDisable()) {
			System.out.println("5");

			bottomRight.setDisable(true);

			verticalBottomSplit.setDividerPositions(1);
			verticalBottomSplit.getStyleClass().clear();

			verticalBottomSplit.getStyleClass().add("split-paneinvisible");

		}
		if (bottomLeft.getTabs().size() == 0 && bottomRight.getTabs().size() == 0){
			System.out.println("6");

			verticalBottomSplit.setDividerPositions(1);
			verticalBottomSplit.getStyleClass().clear();
			verticalBottomSplit.getStyleClass().add("split-paneinvisible");

			verticalBottomSplit.setDisable(true);

			horizontalSplit.setDividerPositions(1);
			horizontalSplit.getStyleClass().clear();

			horizontalSplit.getStyleClass().add("split-paneinvisible");

		}
		if (bottomLeft.getTabs().size() == 0 && bottomRight.getTabs().size() == 0 && topLeft.getTabs().size() == 0 && topRight.getTabs().size() == 0) {
			System.out.println("7");

			topLeft.setDisable(false);

			verticalTopSplit.setDividerPositions(1);
			verticalTopSplit.getStyleClass().clear();

			verticalTopSplit.getStyleClass().add("split-paneinvisible");


			verticalBottomSplit.setDividerPositions(1);
			verticalBottomSplit.getStyleClass().clear();

			verticalBottomSplit.getStyleClass().add("split-paneinvisible");


			horizontalSplit.setDividerPositions(1);
			horizontalSplit.getStyleClass().clear();

			horizontalSplit.getStyleClass().add("split-paneinvisible");

			verticalBottomSplit.setDisable(true);
			topRight.setDisable(true);
			verticalTopSplit.setDisable(false);
			topLeft.setDisable(false);

		}
		
	}


	public TabPane test(DraggableTab sourcetab, double x, double y) {
		
		System.out.println("METHODE TEST !");
		/*  x et y = en fonction de la scene */
		
		System.out.println("LAYOUT X = " + horizontalSplit.getLayoutX() + "    LAYOUT Y = " + horizontalSplit.getLayoutY());
		
		double width = horizontalSplit.getWidth();
		double height = horizontalSplit.getHeight();
		
		double posX = width - x;
		double posY = height - y;
		
		System.out.println("x = " + x + "   wi = " + width);
		System.out.println("y = " + y + "   he = " + height);
		TabPane sourceTabpane = sourcetab.getTabPane();

		if (posX <= 100){ // tout a droite
			
			if (verticalTopSplit.isDisable()){ //(horizontalSplit.getDividerPositions()[0] == 0){ // barre horizontale en haut
				System.out.println("horizontaldividerpos = 0");
				
				bottomRight.setDisable(false);
				verticalBottomSplit.getStyleClass().add("split-panevisible");

				verticalBottomSplit.setDividerPositions(0.5);
				return bottomRight;
			}
			else if (verticalBottomSplit.isDisable()){//(horizontalSplit.getDividerPositions()[0] ==1){ // barre horizontale en bas
				System.out.println("horizontaldividerpos = 1");

				topRight.setDisable(false);
				verticalTopSplit.getStyleClass().add("split-panevisible");

				verticalTopSplit.setDividerPositions(0.5);
				return topRight;
			}
			else { // barre horizontale au milieu
				System.out.println("horizontaldividerpos = 0.5");

				if (y <= verticalTopSplit.getHeight())
				{
					System.out.println("en haut");

					if (topRight.isDisable()){
						topRight.setDisable(false);
						verticalTopSplit.getStyleClass().add("split-panevisible");

						verticalTopSplit.setDividerPositions(0.5);
						return topRight;
					}
					else if (topLeft.isDisable()){
						topLeft.setDisable(false);
						verticalTopSplit.getStyleClass().add("split-panevisible");

						verticalTopSplit.setDividerPositions(0.5);
						return topLeft;						
					}
					
				}
				else
				{
					System.out.println("en bas");
					if (bottomLeft.isDisable()){
						System.out.println("enn bas bottomleft disable");
						bottomLeft.setDisable(false);
						verticalBottomSplit.getStyleClass().add("split-panevisible");

						verticalBottomSplit.setDividerPositions(0.5);
						return bottomLeft;
					}
					else if (bottomRight.isDisable()){
						System.out.println("en bas bottomright disable");
						bottomRight.setDisable(false);
						verticalBottomSplit.getStyleClass().add("split-panevisible");

						verticalBottomSplit.setDividerPositions(0.5);
						return bottomRight;
					}
				}
			}

		}
		else if (posY <=100){ // tout en bas
			System.out.println("Tout en bas");					

			if (verticalBottomSplit.isDisable()){ // bas pas affiche
				
				System.out.println("verticalbottomsplit is disabled");
				
				verticalBottomSplit.setDisable(false);
				horizontalSplit.getStyleClass().add("split-panevisible");

				horizontalSplit.setDividerPositions(0.5);

				verticalBottomSplit.setDividerPositions(1);

				if (!bottomRight.isDisable()){
					System.out.println("bottomright disable");
					bottomRight.setDisable(false);
					return bottomRight;
				}
				else{
					return bottomLeft;
				}
			}

		}
				
		unsplit();
		return null;
	}
}
