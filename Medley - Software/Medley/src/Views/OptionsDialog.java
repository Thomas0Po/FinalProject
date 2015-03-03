package Views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Abstractions.Models.LanguageModel;
import Abstractions.Models.ShortCutBindModel;
import Abstractions.Models.ShortCutsListModel;
import Controllers.MainController;
import Models.PluginConf;
import Models.PluginsConf;
import Utils.Helpers.MedleyPopup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OptionsDialog {

	MainController controllerRef;
	public OptionsDialog(LanguageModel consts, String currentLanguage, final MainController controllerRef, ShortCutsListModel shortcutsModel) {
		this.controllerRef = controllerRef;
		final Stage dialog =	new Stage(StageStyle.TRANSPARENT);
		BorderPane bp = 		new BorderPane();
		TabPane p =				new TabPane();
		bp.setCenter(p);
		Scene scene =			new Scene(bp, 700, 400);
		Tab launchPlugins =		new Tab();
		Tab general =			new Tab();
		Tab shorcuts =			new Tab();
		
		scene.getStylesheets().add("Views/Option.css");
		bp.getStyleClass().add("optionBorderPane");
		//p.setStyle(".tabpane{-fx-padding : 0;-fx-margin : 0;}");
		
		Label l = new Label(consts.getText("MENUPLUGINS", currentLanguage));
		StackPane launchtabpane = new StackPane(l);

		launchPlugins.setGraphic(launchtabpane);
		launchPlugins.setClosable(false);
		
		Label l2 = new Label(consts.getText("GENERAL", currentLanguage));
		
//		general.setStyle("-fx-rotate : 90;-fx-padding : 7;");
		general.setGraphic(l2);
//		general.setClosable(false);
		
		Label l3 = new Label(consts.getText("SHORTCUTS", currentLanguage));
		l3.setStyle("-fx-padding: 0; -fx-margin: 0;-fx-max-height: 200");
		l3.setPrefHeight(20);
		StackPane shortcuttabpane = new StackPane(l3);
		shorcuts.setGraphic(shortcuttabpane);
		shorcuts.setClosable(false);

		p.setTabMinHeight(200);
		p.setTabMinWidth(30);
		//p.getTabs().add(general);
		p.getTabs().add(launchPlugins);
		p.getTabs().add(shorcuts);
		p.setSide(Side.LEFT);
		
		p.setRotateGraphic(true);
		
		
		/*** PLUGINS AT SOFTWARE LAUNCH ***/
		
		final PluginsConf pluginsConf = new PluginsConf();
		
		BorderPane borderPane = new BorderPane();
		if (pluginsConf == null || pluginsConf.getPluginsConf().isEmpty()){
			
			Label noPlugins = new Label("You don't have any plugin");
			noPlugins.setPrefWidth(scene.getWidth() - 10);
			noPlugins.setWrapText(true);
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
			
		    dialog.setScene(newscene);
		    //dialog.show();
			//return;
		}
		else{
		Accordion accord = new Accordion();
		
		for (PluginConf plugin : pluginsConf.getPluginsConf()){
			
			SelectablePane pane = new SelectablePane(plugin);

			accord.getPanes().add(pane);
		}
		borderPane.setTop(new Label(consts.getText("PLUGINSATLAUNCH", currentLanguage)));
		
		ScrollPane scroll = new ScrollPane();
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		scroll.setContent(accord);		
		
		borderPane.setCenter(accord);
		
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
				for (PluginConf conf : pluginsConf.getPluginsConf()){
					Boolean b = conf.isToRun();
					pluginsConf.setValue(conf, "toRun", b.toString());
				}
				pluginsConf.saveConf();
				dialog.hide();
			}
		});
        hBox.getChildren().add(applyButton);
		hBox.setPadding(new Insets(0, 0, 5, 0));
        borderPane.setBottom(hBox);
		
        
		}
		launchPlugins.setContent(borderPane);
		//launchPlugins.getContent().setStyle("-fx-padding: 0 0 0 100");

        /****  SHORTCUTS  ****/
        
        final ShortCutsListModel model = shortcutsModel;
        final List<ShortCutBindModel> bindmodel = new ArrayList<ShortCutBindModel>(model.getShortcuts().values()); //.getBindModel();
        final List<ShortCutBindModel> bindmodelSave = new ArrayList<ShortCutBindModel>();        
        for (ShortCutBindModel mod : bindmodel){
        	bindmodelSave.add(new ShortCutBindModel(mod.getOne().getValue(), mod.getDescription(), mod.getName(), mod.getCommand().getValue(), mod.getLetter().getValue(), mod.getValue()));
        }
        final BorderPane shortcutsBorder = new BorderPane();
        Button applyShortcuts = new Button(consts.getText("LABELAPPLY", currentLanguage));
        Button cancelShortcuts = new Button(consts.getText("LABELCANCEL", currentLanguage));
        
        HBox hbox = new HBox();
        hbox.getChildren().add(applyShortcuts);
        hbox.getChildren().add(cancelShortcuts);
        shortcutsBorder.setBottom(hbox);
        ShortcutsList list = new ShortcutsList(bindmodel);
        shortcutsBorder.setCenter(list);
        cancelShortcuts.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				List<ShortCutBindModel> newModels = new ArrayList<ShortCutBindModel>();
				for (ShortCutBindModel mod : bindmodelSave){
			        	newModels.add(new ShortCutBindModel(mod.getOne().getValue(), mod.getDescription(), mod.getName(), mod.getCommand().getValue(), mod.getLetter().getValue(), mod.getValue()));
			        }
				ShortcutsList list = new ShortcutsList(newModels);
				shortcutsBorder.setCenter(list);
			}
		});
        
        applyShortcuts.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				model.save();
				bindmodelSave.clear();
				Map<String, String> doubles = new HashMap<String, String>();
				 for (ShortCutBindModel mod : bindmodel){
					 if (!doubles.containsKey(mod.getValue())){
						 doubles.put(mod.getValue(), "");
			        	bindmodelSave.add(new ShortCutBindModel(mod.getOne().getValue(), mod.getDescription(), mod.getName(), mod.getCommand().getValue(), mod.getLetter().getValue(), mod.getValue()));
					 }
					 else{
						 MedleyPopup pop = new MedleyPopup("Vous ne pouvez pas attribuer le meme raccourci clavier a plusieurs actions");
						 return;
					 }
			        }
				 controllerRef.setShortcuts();
				 dialog.hide();
			}
		});
        
        
        shorcuts.setContent(shortcutsBorder);
        
		/* DIALOG SHOW */
	    dialog.initModality(Modality.APPLICATION_MODAL);
	    dialog.initStyle(StageStyle.DECORATED);
	    dialog.setScene(scene);
	    dialog.show();
	    
	}
}
