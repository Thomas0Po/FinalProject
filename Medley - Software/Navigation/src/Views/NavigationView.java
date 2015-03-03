package Views;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Abstractions.Models.LanguageModel;
import Abstractions.Models.ShortCutBindModel;
import Abstractions.Models.ShortCutsListModel;
import Abstractions.Views.AMenuBarItem;
import Abstractions.Views.IMenuBar;
import Abstractions.Views.IView;
import Controllers.NavigationController;
import Models.NavigationFolder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Region;

public class NavigationView implements IView{
	NavigationController controllerRef;
	TabPane tabPane;
	List<IMenuBar> menus;
	String currentLanguage;
	LanguageModel consts;
	ContextMenu contextMenu;
	AMenuBarItem addNewTabItem;
	AMenuBarItem addTabWithPathItem;
	AMenuBarItem delSelectedTabItem;
	AMenuBarItem setSelectedTabRootItem;
	AMenuBarItem setExtsItem;

	public void setCurrentLanguage(String currentLanguage) {
		this.currentLanguage = currentLanguage;
	}
	
	public LanguageModel getConsts() {
		return consts;
	}

	public void setConsts(LanguageModel consts) {
		this.consts = consts;
	}
	public NavigationView(NavigationController controllerRef)
	{
		this.controllerRef = controllerRef;
	}
	
	public void init(List<NavigationFolder> listNavigFolder){
		tabPane = new TabPane();
		tabPane.getStyleClass().add("navigTabpane");
		tabPane.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		for (NavigationFolder navigFolder : listNavigFolder)
		{
			NavigationTab tab = new NavigationTab(navigFolder, consts, currentLanguage);
			tab.setOnClosed(new NavigationTabCloseEvent(tab, tabPane, controllerRef));
			tabPane.getTabs().add(tab);
		}
		this.initMenu();
	}
	
	public Node getMainComponent(){
		//System.err.println(this.tabPane.getParent().getClass().getName());
		return tabPane;
		
	}

	public void initMenu()
	{
		IMenuBar addNewTab = new MenuBar();
		IMenuBar addTabWithPath = new MenuBar();
		IMenuBar delSelectedTab = new MenuBar();
		IMenuBar setSelectedTabRoot = new MenuBar();
		IMenuBar setExts = new MenuBar();
		/*IMenuBar hideNavigation = new MenuBar();
		IMenuBar showNavigation = new MenuBar();*/
		
		addNewTabItem = new MenuBarItem("Navigation");
		addTabWithPathItem = new MenuBarItem("Navigation");
		delSelectedTabItem = new MenuBarItem("Navigation");
		setSelectedTabRootItem = new MenuBarItem("Navigation");
		setExtsItem = new MenuBarItem("Navigation");
		
		addNewTab.setName("Navigation");
		addTabWithPath.setName("Navigation");
		delSelectedTab.setName("Navigation");
		setSelectedTabRoot.setName("Navigation");
		setExts.setName("Navigation");
		
		addNewTabItem.setTextMenuItem("Ajouter un nouvel onglet");
		addNewTabItem.setTextMenuItem(consts.getText("ADDNEWTAB", currentLanguage));

		addNewTabItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("ADD NEW TAB HANDKE");
				controllerRef.addNewTab();
			}
		});
		
		addTabWithPathItem.setTextMenuItem("Ajouter un nouvel onglet personalise");
		addTabWithPathItem.setTextMenuItem(consts.getText("ADDCUSTOMTAB", currentLanguage));
		
		addTabWithPathItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				controllerRef.addNewTabWithPath();
			}
		});
		
		delSelectedTabItem.setTextMenuItem("Supprimer l'onglet sélectionné");
		delSelectedTabItem.setTextMenuItem(consts.getText("DELTAB", currentLanguage));
		
		delSelectedTabItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				controllerRef.delSelectedTab(tabPane.getSelectionModel().getSelectedIndex());
			}
		});
		
		
		setSelectedTabRootItem.setTextMenuItem("changer le repertoire de base");
		setSelectedTabRootItem.setTextMenuItem(consts.getText("CHANGEDIR", currentLanguage));
		setSelectedTabRootItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				controllerRef.setRootPathofSelectedTab(tabPane.getSelectionModel().getSelectedIndex());
			}
		});
	
		setExtsItem.setTextMenuItem("Extensions");
		setExtsItem.setTextMenuItem(consts.getText("SETEXTMENU", currentLanguage));
		setExtsItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event)
			{
				controllerRef.setExtensions();
			}
		});
		
		
		addNewTab.setMenuItem(addNewTabItem);
		addTabWithPath.setMenuItem(addTabWithPathItem);
		delSelectedTab.setMenuItem(delSelectedTabItem);
		setSelectedTabRoot.setMenuItem(setSelectedTabRootItem);
		setExts.setMenuItem(setExtsItem);
		
		menus = new ArrayList<IMenuBar>();
		menus.add(addNewTab);
		menus.add(addTabWithPath);
		menus.add(delSelectedTab);
		menus.add(setSelectedTabRoot);
		menus.add(setExts);

		
		this.contextMenu = new ContextMenu(addNewTab.getMenuItem(), addTabWithPath.getMenuItem(), 
											delSelectedTab.getMenuItem(), setSelectedTabRoot.getMenuItem(), setExts.getMenuItem());
		this.contextMenu.getStyleClass().add("navigContextMenu");
		this.tabPane.setContextMenu(this.contextMenu);
	}
	
	@Override
	public List<IMenuBar> getMenuBarItems() {
		return this.menus;
	}

	public void addNewTab(NavigationFolder navigFolder)
	{
		NavigationTab tab = new NavigationTab(navigFolder, consts, currentLanguage);
		tabPane.getTabs().add(tab);
		tab.setOnClosed(new NavigationTabCloseEvent(tab, tabPane, controllerRef));
		tabPane.getSelectionModel().select(tabPane.getTabs().size() - 1);
	}
	
	public void delTabAt(int index)
	{
		System.out.println(index);
		tabPane.getTabs().remove(index);
	}
	
	public void refreshAll()
	{
		for (Tab tab : tabPane.getTabs())
		{
			NavigationTab navigTab = (NavigationTab) tab;
			navigTab.update();
		}
	}
	
	public void updateSelectedTab(int index)
	{
		NavigationTab tab = (NavigationTab) tabPane.getTabs().get(index);
		tab.update();
		tab.updateTree();
	}
	
	public void hide()
	{
		this.tabPane.setVisible(false);
	}
	
	public void show()
	{
		this.tabPane.setVisible(true);
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void setTexts(LanguageModel languages, String language) {
		addNewTabItem.setTextMenuItem(consts.getText("ADDNEWTAB", language));
		addTabWithPathItem.setTextMenuItem(consts.getText("ADDCUSTOMTAB", language));
		delSelectedTabItem.setTextMenuItem(consts.getText("DELTAB", language));
		setSelectedTabRootItem.setTextMenuItem(consts.getText("CHANGEDIR", language));
		setExtsItem.setTextMenuItem(consts.getText("SETEXTMENU", language));
		for (Tab tab : this.tabPane.getTabs())
		{
			((NavigationTab)tab).setTexts(languages, language);
		}
	}

	public void setShortcuts(ShortCutsListModel model) {
		Map<String, ShortCutBindModel> list = model.getShortcuts();
		System.out.println("test 2 : " + list.get("ADDTAB").getValue());
		this.addNewTabItem.setAccelerator(KeyCombination.keyCombination(list.get("ADDTAB").getValue()));
		this.addTabWithPathItem.setAccelerator(KeyCombination.keyCombination(list.get("ADDCUSTOMTAB").getValue()));
		this.delSelectedTabItem.setAccelerator(KeyCombination.keyCombination(list.get("DELTAB").getValue()));
	}
}
