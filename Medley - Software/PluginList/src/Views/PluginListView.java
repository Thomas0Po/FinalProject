package src.Views;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import plugincontract.APlugin;
import Abstractions.Models.LanguageModel;
import Abstractions.Models.ShortCutBindModel;
import Abstractions.Models.ShortCutsListModel;
import Abstractions.Views.AMenuBarItem;
import Abstractions.Views.IMenuBar;
import Abstractions.Views.IView;
import src.Controllers.PluginListController;
import src.Models.PluginInfoModel;
import src.Models.PluginListModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.scene.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PluginListView implements IView{

	BorderPane layout;
	VBox verticalBox;
	ImageView pluginsImage;
	ListView<APlugin> listView;
	List<IMenuBar> menus;
	PluginListController controllerRef;
	APlugin pluginDragged;
	AMenuBarItem openPluginItem;
	AMenuBarItem addPluginItem;
	String currentLanguage;
	LanguageModel consts;
	
	/*
	 * Set current language
	 */
	public void setCurrentLanguage(String currentLanguage) {
		this.currentLanguage = currentLanguage;
	}

	/*
	 * Set languages constants
	 */
	public void setConsts(LanguageModel consts) {
		this.consts = consts;
	}

	/*
	 * Initializes the view
	 */
	public void init(final PluginListController controllerRef) {
		verticalBox = new VBox();
		layout = new BorderPane();
		verticalBox.getStyleClass().add("listviewVerticalbox");
		
		HBox hb = new HBox();
		hb.getStyleClass().add("listviewHeader");
		Label l = new Label("Plugins");
		l.getStyleClass().add("listviewHeaderText");
		pluginsImage = new ImageView(new Image("Ressources/Images/powercordblack.png", 30, 30, true, true));
		pluginsImage.getStyleClass().add("listviewHeaderImage");
		hb.getChildren().add(pluginsImage);
		hb.getChildren().add(l);
		hb.setPrefHeight(30);
		hb.setMinSize(0, 0);
		
		listView = new ListView<APlugin>();
		listView.setMinSize(0, 0);
		listView.getStyleClass().add("medleyListView"); 
		this.controllerRef = controllerRef;
		listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		openPluginItem = new MenuBarItem("Plugins");
		addPluginItem = new MenuBarItem("Plugins");
		
		openPluginItem.setTextMenuItem("Open the selected plugin");
		openPluginItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				APlugin pl = (APlugin)listView.getSelectionModel().getSelectedItem();
				controllerRef.openPluginClick(pl);
			}
		});
		openPluginItem.setDisable(true);
		
		addPluginItem.setTextMenuItem("Add/Delete plugins from list");
		addPluginItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
					controllerRef.modifyPluginListClick();
			}

			
		});
				
		
		layout.setTop(hb);
		layout.setCenter(listView);
		
//		verticalBox.getChildren().add(hb);
//
//		
//		verticalBox.getChildren().add(listView);
		
	}

	/*
	 * Returns the listView
	 * @see Views.IView#getMainComponent()
	 */
	public Node getMainComponent() {
		return layout;
		//return verticalBox;
		//return listView;
	}

	/*
	 * Returns the menu bar items
	 * @see Views.IView#getMenuBarItems()
	 */
	public List<IMenuBar> getMenuBarItems() {
		IMenuBar openPlugin;
		IMenuBar addPlugin;
		
		
		
		openPlugin = new MenuBar();
		addPlugin = new MenuBar();
		
		openPlugin.setName("Plugins");
		addPlugin.setName("Plugins");
		
		
		
		openPlugin.setMenuItem(openPluginItem);
		addPlugin.setMenuItem(addPluginItem);
		
		menus = new ArrayList<IMenuBar>();
		menus.add(openPlugin);
		menus.add(addPlugin);
		return menus;
	}

	/*
	 * Fill the listView with the model
	 */
	public void fillPluginList(PluginListModel model) {

		listView.setItems(model.getPluginList());

		listView.setCellFactory(new Callback<ListView<APlugin>, ListCell<APlugin>>() {
			@Override
			public ListCell<APlugin> call(ListView<APlugin> param) {
				final PluginListCell pl = new PluginListCell();
				pl.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						if (pl.getLabel().getText().equals("(empty)"))
								return;
						if (openPluginItem.isDisable() && listView.getSelectionModel().getSelectedItem() != null)
							openPluginItem.setDisable(false);
						if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
							APlugin pl = (APlugin)listView.getSelectionModel().getSelectedItem();
							controllerRef.openPluginClick(pl);
						}
					}
				});
				
				pl.setOnDragDetected(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						if (pl.getLabel().getText().equals("(empty)"))
							return;
						try{
						Dragboard db = listView.startDragAndDrop(TransferMode.ANY);
						pluginDragged = (APlugin)listView.getSelectionModel().getSelectedItem();
						if (pluginDragged != null){
							ClipboardContent content = new ClipboardContent();
							content.putString("Plugin");
							db.setContent(content);
						}
						arg0.consume();
						}
						catch(Exception e){
							
						}
					}
				});
				
				return pl;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see Views.IView#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	/*
	 * Returns the selected plugin
	 */
	public APlugin getSelectedPlugin() {
		APlugin selectedPlugin = (APlugin)listView.getSelectionModel().getSelectedItem();
		return selectedPlugin;
	}

	/*
	 * Returns the dragged plugin
	 */
	public APlugin getDraggedPlugin(){
		return this.pluginDragged;
	}

	/*
	 * Updates the listView
	 */
	public void update(PluginListModel newModel) {
		
		
	}

	/*
	 * Called from menu bar. Creates a popup to manage the listView
	 */
	public void modifyPluginListClick(List<PluginInfoModel> model) {
		final Stage dialog = new Stage(StageStyle.TRANSPARENT);
	    dialog.initModality(Modality.APPLICATION_MODAL);
	    dialog.initStyle(StageStyle.DECORATED);
	    
		BorderPane borderPane = new BorderPane();
		
		if (model == null || model.isEmpty()){
			System.out.println("NO PLUGINS");
			Label noPlugins = new Label("You don't have any plugin");
			noPlugins.setText(consts.getText("LABELNOPLUGINS", currentLanguage));
			
	        borderPane.setCenter(noPlugins);
			Button close = new Button("Close");
			close.setText(consts.getText("LABELCLOSE", currentLanguage));
			close.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					dialog.hide();
				}
			});
	        
			borderPane.setBottom(close);
			BorderPane.setAlignment(close, Pos.BOTTOM_RIGHT);
			BorderPane.setMargin(close, new Insets(5, 5, 5, 0));
			Scene newscene = new Scene(borderPane);
			newscene.getStylesheets().add("Views/Medley.css"); 

			
		    dialog.setScene(newscene);
		    dialog.show();
			return;
		}
		
		Accordion accord = new Accordion();
		
		for (PluginInfoModel plugin : model){
			SelectableTitledPane pane = new SelectableTitledPane(plugin);
			accord.getPanes().add(pane);
		}
		
		ScrollPane scroll=new ScrollPane();
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
	    scroll.setContent(accord);
	    	    
		borderPane.setCenter(scroll);
		
		HBox hBox = new HBox();
		hBox.setSpacing(10);
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
        
        Button cancelButton = new Button("Cancel");
        cancelButton.setText(consts.getText("LABELCANCEL", currentLanguage));
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                dialog.hide();
            }
        });
        hBox.getChildren().add(cancelButton);
        
        Button applyButton = new Button("Apply");
        applyButton.setText(consts.getText("LABELAPPLY", currentLanguage));

        applyButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				controllerRef.SaveAndApplyPluginList();
				dialog.hide();
			}
		});
        hBox.getChildren().add(applyButton);
		hBox.setPadding(new Insets(0, 0, 5, 0));
        borderPane.setBottom(hBox);
        
		Scene newscene = new Scene(borderPane, 600, 400);
		newscene.getStylesheets().add("Views/Medley.css"); 

		System.out.println("HERE");
	    dialog.setScene(newscene);
	    dialog.show();		
	}

	/*
	 * (non-Javadoc)
	 * @see Abstractions.Views.IView#setTexts(Abstractions.Models.LanguageModel, java.lang.String)
	 */
	@Override
	public void setTexts(LanguageModel languages, String language) {
		addPluginItem.setTextMenuItem(languages.getText("MENUADDPLUGIN", language));
		openPluginItem.setTextMenuItem(languages.getText("MENUOPENPLUGIN", language));
	}

	public void setShortcuts(ShortCutsListModel model) {
		Map<String, ShortCutBindModel> list = model.getShortcuts();
		this.openPluginItem.setAccelerator(KeyCombination.keyCombination(list.get("OPENPLUGIN").getValue()));
		
		
	}
	
}
