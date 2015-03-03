package Views;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Abstractions.Models.LanguageModel;
import Abstractions.Models.ShortCutBindModel;
import Abstractions.Models.ShortCutsListModel;
import Abstractions.Views.AMenuBarItem;
import Abstractions.Views.IMenuBar;
import Controllers.MainController;
import Models.AvailableLanguages;
import Utils.Helpers.MedleyPopup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Menu;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

/**
 * 
 * this class contains the main view of the project
 *
 */
public class MainView {

	Stage stage;
	Scene scene;
	Button button;
	BorderPane border;
	javafx.scene.control.MenuBar menuBar;
	SplitPane horizontalSplit;
	SplitPane verticalSplit;
	Map<String, Menu> listMenu;
	MainController controllerRef;
	AMenuBarItem quitItem;
	AMenuBarItem changeLanguageItem;
	AMenuBarItem pluginListVisibilityItem;
	AMenuBarItem navigationVisibilityItem;
	AMenuBarItem optionsItem;
	LanguageModel consts;
	String currentLanguage;
	private ShortCutsListModel shortcutsModel;
	
	/**
	 * Constructor. Sets javafx stage and reference to controller
	 */
	public MainView(Stage stage, MainController controller) {
		this.stage = stage;
		this.stage.getIcons().add(new Image("/Ressources/Images/MedleyLogo.png"));
		this.controllerRef = controller;
	}
	
	/**
	 * Initialization of layouts and menu bar
	 */
	public void init(){
		// Instanciation
		border = new BorderPane();
		menuBar = new javafx.scene.control.MenuBar();
		menuBar.getStyleClass().add("menuBar");
		horizontalSplit = new SplitPane();
		verticalSplit = new SplitPane();
		
		border.setPadding(new Insets(0));
		border.setMinSize(0, 0);
		
		// Les splitpanes vont servir de Layout		
		// On met en place la structure de l'interface
		horizontalSplit.setOrientation(Orientation.VERTICAL);
		verticalSplit.setOrientation(Orientation.HORIZONTAL);
		verticalSplit.getStyleClass().add("verticalSplit");
		
		BorderPane horizontalPane = new BorderPane();
		horizontalPane.setCenter(verticalSplit);
		horizontalSplit.getItems().add(horizontalPane);
				
		border.setTop(menuBar);
		border.setCenter(horizontalSplit);
		
		border.getCenter().getStyleClass().add("rootBorderCenter");
		
		horizontalSplit.setDividerPositions(0.650);
		verticalSplit.setDividerPositions(0.240);
		
		scene = new Scene(border, 1300, 700);
		
		listMenu = new LinkedHashMap<String, Menu>();
		
		listMenu.put("File", new Menu(consts.getText("MENUFILE", currentLanguage)));
		listMenu.put("Navigation", new Menu(consts.getText("MENUNAVIGATION", currentLanguage)));
		listMenu.put("Plugins", new Menu(consts.getText("MENUPLUGINS", currentLanguage)));
		listMenu.put("View", new Menu(consts.getText("MENUVIEW", currentLanguage)));
		listMenu.put("Preferences", new Menu(consts.getText("MENUPREFERENCES", currentLanguage)));
		listMenu.put("Help", new Menu(consts.getText("MENUHELP", currentLanguage)));
		
		for (Map.Entry<String, Menu> menu : listMenu.entrySet()) {
			menuBar.getMenus().add(menu.getValue());
		}
		
		scene.getStylesheets().add("Views/Medley.css"); 
		
		

		
		this.stage.setTitle("Medley");
		this.stage.setScene(scene);
	}
	
	/**
	 * Show method
	 */
	public void show() {
		this.stage.show();
	}

	/**
	 * Set current language variable
	 */
	public void setCurrentLanguage(String currentLanguage) {
		this.currentLanguage = currentLanguage;
	}	
	/*
	 * Set the texts constants of the view
	 */
	public void setConsts(LanguageModel consts) {
		this.consts = consts;
	}
	
	/*
	 * Adds pluginListView main component to layout
	 */
	public void setPluginListView(Node mainComponent) {
	
//		BorderPane t = new BorderPane();
//		t.setCenter(mainComponent);
		verticalSplit.getItems().add(mainComponent);
	}

	/**
	 * Adds navigationView main component to layout
	 */
	public void setNavigationView(Node mainComponent) {
		BorderPane navigPane = new BorderPane();
		navigPane.setCenter(mainComponent);
		horizontalSplit.getItems().add(navigPane);
	}

	/**
	 * Adds workspaceView main component to layout
	 */
	public void setWorkspaceView(Node mainComponent) {
		
		BorderPane bd = new BorderPane();
		bd.setCenter(mainComponent);
		bd.getCenter().getStyleClass().add("borderlistview");
		bd.getStyleClass().add("workspacePane");
		
		verticalSplit.getItems().add(bd);
		SplitPane.setResizableWithParent(verticalSplit.getItems().get(1), false);

		
	}

	/**
	 * Adds menuBarComponents to the menu bar
	 */
	public void addMenu(List<IMenuBar> menuBarComponents) {
		if (menuBarComponents != null) {
			for (IMenuBar menuBar : menuBarComponents) {
				listMenu.get(menuBar.getMenuName()).getItems().add(menuBar.getMenuItem());
			}
		}
	}
	
	/**
	 * Builds the menu bar
	 */
	public void buildMenuBar() {
		
		changeLanguageItem = new MenuBarItem(consts.getText("MENULANGUAGE", currentLanguage));
		changeLanguageItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				final Stage dialog = new Stage(StageStyle.TRANSPARENT);
			    dialog.initModality(Modality.APPLICATION_MODAL);
			    dialog.initStyle(StageStyle.DECORATED);
			    dialog.setWidth(400);
			    dialog.setHeight(200);
			    BorderPane borderPane = new BorderPane();
			    
			    ObservableList<AvailableLanguages> languageList = FXCollections.observableArrayList();
			    Map<String, String> availableLanguages = consts.getAvailableLanguages();
			    for (Entry<String, String> en : availableLanguages.entrySet())
			    	languageList.add(new AvailableLanguages(en.getKey(), en.getValue()));
		    
			    final ChoiceBox<AvailableLanguages> choiceBox = new ChoiceBox<AvailableLanguages>(languageList);
			   
			    class MyClassConverter extends StringConverter<AvailableLanguages> {

			    	  public AvailableLanguages fromString(String string) {
			    		  return null;
			    	    // convert from a string to a myClass instance
			    	  }

			    	  public String toString(AvailableLanguages myClassinstance) {
			    		  return myClassinstance.getName();
			    	    // convert a myClass instance to the text displayed in the choice box
			    	  }
			    	}

			    choiceBox.setConverter(new MyClassConverter());
			    for (AvailableLanguages al : languageList){
			    	if (al.getKey().equals(currentLanguage)){
			    		choiceBox.setValue(al);
			    	}
			    }
			    borderPane.setTop(choiceBox);
			    
			    HBox hBox = new HBox();
				hBox.setSpacing(10);
				hBox.setAlignment(Pos.BOTTOM_RIGHT);
			    
			    Button close = new Button(consts.getText("CLOSELABEL", currentLanguage));
				close.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						dialog.hide();
					}
				});
		        
				Button apply = new Button(consts.getText("APPLYLABEL", currentLanguage));
				apply.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						String newLanguage = choiceBox.getSelectionModel().getSelectedItem().getKey();
						if (currentLanguage == newLanguage){
							dialog.hide();
							return;
							}
							controllerRef.changeLanguage(newLanguage);
						dialog.hide();
					}
				});

				hBox.getChildren().add(apply);

				hBox.getChildren().add(close);
				hBox.setPadding(new Insets(0, 0, 5, 0));
		        borderPane.setBottom(hBox);
				
				BorderPane.setAlignment(apply, Pos.BOTTOM_RIGHT);
				BorderPane.setMargin(apply, new Insets(5, 5, 5, 0));
				
				Scene newscene = new Scene(borderPane);
				
			    dialog.setScene(newscene);
			    dialog.show();
				
			}
		});
		IMenuBar changeLanguage;
		changeLanguage = new MenuBar();
		changeLanguage.setName("File");
		changeLanguage.setMenuItem(changeLanguageItem);
		
		quitItem = new MenuBarItem(consts.getText("MENUQUIT", currentLanguage));
		quitItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				controllerRef.quit();
			}
		});
		IMenuBar quit;
		quit = new MenuBar();
		quit.setName("File");
		quit.setMenuItem(quitItem);
		
		pluginListVisibilityItem = new MenuBarItem(consts.getText((verticalSplit.getDividerPositions()[0] != 0.0) ? "HIDEPLUGINLIST" : "SHOWPLUGINLIST", currentLanguage));
		pluginListVisibilityItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (pluginListVisibilityItem.getText().equals(consts.getText("SHOWPLUGINLIST", currentLanguage))){

					verticalSplit.setDividerPositions(0.240);
					pluginListVisibilityItem.setText(consts.getText("HIDEPLUGINLIST", currentLanguage));
					
				}
				else{

					verticalSplit.setDividerPositions(0.0);
					pluginListVisibilityItem.setText(consts.getText("SHOWPLUGINLIST", currentLanguage));
				}
				
			}
		});
		IMenuBar pluginListVisibility = new MenuBar();
		pluginListVisibility.setName("View");
		pluginListVisibility.setMenuItem(pluginListVisibilityItem);
		
		navigationVisibilityItem = new MenuBarItem(consts.getText((horizontalSplit.getDividerPositions()[0] != 0.0) ? "HIDENAVIGATION" : "SHOWNAVIGATION", currentLanguage));
		navigationVisibilityItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (navigationVisibilityItem.getText().equals(consts.getText("SHOWNAVIGATION", currentLanguage))){
					System.out.println("COUCOU 1");
				
					horizontalSplit.setDividerPositions(0.6);
					System.out.println(horizontalSplit.getDividerPositions()[0]);
					navigationVisibilityItem.setText(consts.getText("HIDENAVIGATION", currentLanguage));
					
				}
				else{
					System.out.println("COUCOU");
		
					horizontalSplit.setDividerPositions(1);
					System.out.println(horizontalSplit.getDividerPositions()[0]);

					navigationVisibilityItem.setText(consts.getText("SHOWNAVIGATION", currentLanguage));
				}
				
			}
			
		});
		IMenuBar navigationVisibility = new MenuBar();
		navigationVisibility.setName("View");
		navigationVisibility.setMenuItem(navigationVisibilityItem);
		
		optionsItem = new MenuBarItem(consts.getText("OPTIONS", currentLanguage));
		optionsItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				OptionsDialog optionsDialog = new OptionsDialog(consts, currentLanguage, controllerRef, shortcutsModel);
				
			}
		});
		IMenuBar options = new MenuBar();
		options.setName("Preferences");
		options.setMenuItem(optionsItem);
		
		List<IMenuBar> l = new ArrayList<IMenuBar>();
		l.add(changeLanguage);
		l.add(quit);
		l.add(pluginListVisibility);
		l.add(navigationVisibility);
		l.add(options);
		addMenu(l);
	}

	/**
	 * Show a popup with the message arg
	 */
	public void popUpMessage(String arg) {
		MedleyPopup p = new MedleyPopup(arg.substring(arg.indexOf(".") + 1));
	}
	
	/**
	 * Close application
	 */
	public void close() {
		stage.close();
	}

	/**
	 * Set the all the texts of the view with the language parameter
	 */
	public void setTexts(LanguageModel mainConsts, String language) {
		if (consts == null)
			consts = mainConsts;
		if (currentLanguage == null || !currentLanguage.equals(language))
			currentLanguage = language;
		
		listMenu.get("File").setText(mainConsts.getText("MENUFILE", language));
		listMenu.get("Navigation").setText(mainConsts.getText("MENUNAVIGATION", language));
		listMenu.get("Plugins").setText(mainConsts.getText("MENUPLUGINS", language));
		listMenu.get("View").setText(mainConsts.getText("MENUVIEW", language));
		listMenu.get("Preferences").setText(mainConsts.getText("MENUPREFERENCES", language));
		
		quitItem.setTextMenuItem(mainConsts.getText("MENUQUIT", language));
		changeLanguageItem.setTextMenuItem(mainConsts.getText("MENULANGUAGE", language));
		
		navigationVisibilityItem.setTextMenuItem(consts.getText((horizontalSplit.getDividerPositions()[0] != 0.0) ? "HIDENAVIGATION" : "SHOWNAVIGATION", currentLanguage));
		pluginListVisibilityItem.setTextMenuItem(consts.getText((verticalSplit.getDividerPositions()[0] != 0.0) ? "HIDEPLUGINLIST" : "SHOWPLUGINLIST", currentLanguage));
		
		
	}

	public void setShortCutsModel(ShortCutsListModel model) {
		this.shortcutsModel = model;
		Map<String, ShortCutBindModel> list = model.getShortcuts();
		this.quitItem.setAccelerator(KeyCombination.keyCombination(list.get("QUIT").getValue()));
		this.changeLanguageItem.setAccelerator(KeyCombination.keyCombination(list.get("CHANGELANGUAGE").getValue()));
		this.navigationVisibilityItem.setAccelerator(KeyCombination.keyCombination(list.get("NAVIGATIONVISIBILITY").getValue()));
		this.pluginListVisibilityItem.setAccelerator(KeyCombination.keyCombination(list.get("PLUGINSLISTVISIBILITY").getValue()));
		this.optionsItem.setAccelerator(KeyCombination.keyCombination(list.get("OPTIONS").getValue()));		
	}

	public ShortCutsListModel getShortcutsModel() {
		return shortcutsModel;
	}
}
