package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import Abstractions.Controllers.ANavigationController;
import Abstractions.Models.LanguageModel;
import Abstractions.Models.ShortCutsListModel;
import Abstractions.Views.IMenuBar;
import Models.NavigationFolder;
import Models.NavigationModel;
import Utils.Helpers.LanguageLoader;
import Views.NavigationView;

public class NavigationController extends ANavigationController {
NavigationView	navigationView;
NavigationModel navigationModel;
LanguageModel language;
String currentLanguage;


	public boolean init(){
		navigationModel = new NavigationModel();
		LanguageLoader loader = new LanguageLoader();

		String pa = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
		language = loader.load(pa);
		this.initView();
		return true;
	}
	
	@Override
	public boolean initView() {
		navigationView = new NavigationView(this);
		navigationView.setConsts(language);
		navigationView.init(this.navigationModel.getListNavigFolder());

		return true;
	}

	@Override
	public void close() {
		this.navigationModel.save();
	}

	@Override
	public void save() {
		this.navigationModel.save();
	}

	@Override
	public List<IMenuBar> getMenuBar() {
		return this.navigationView.getMenuBarItems();
	}

	@Override
	public File getImportedFile() {
		//return this.navigationModel
		return null;
	}

	@Override
	public Node getMainComponent() {
		return navigationView.getMainComponent();
	}

	@Override
	public boolean init(Observer ob) {
		this.addObserver(ob);
		navigationModel = new NavigationModel();
		LanguageLoader loader = new LanguageLoader();
		URL ur = this.getClass().getResource("/Ressources/Languages/Francais.xml");
		String pa = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();

		System.out.println("NAVIG URL : " + ur.getPath().toString());
		language = loader.load(pa);
		this.initView();
		return true;
	}

	public void addNewTab()
	{	
		this.navigationModel.addNavigationFolder();
		this.navigationView.addNewTab(navigationModel.getLastNavigFolder());
	}

	public void delSelectedTab(int index)
	{
		this.navigationView.delTabAt(index);
		this.navigationModel.deleteNavigationFolderAt(index);
	}
	
	public void delTabAt(int index)
	{
		System.out.println("DelLast index = " + index);
		this.navigationModel.deleteNavigationFolderAt(index);
	}

	public void addNewTabWithPath()
	{
		DirectoryChooser directoryChooser = new DirectoryChooser();
		
		directoryChooser.setTitle("Sélectionner un dossier");
		File folder = directoryChooser.showDialog(null);
		if (folder != null)
		{
			navigationModel.addNavigationFolder(folder.getAbsolutePath());
			this.navigationView.addNewTab(navigationModel.getLastNavigFolder());
		}
	}

	public void setRootPathofSelectedTab(int index)
	{
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Sélectionner un dossier");
		File folder = directoryChooser.showDialog(null);
		if (folder != null)
		{
			NavigationFolder navigFolder = navigationModel.getNavigationFolderAt(index);
			navigFolder.setPath(folder.getAbsolutePath());
			navigationView.updateSelectedTab(index);
		}
	}
	
	public void setExtensions()
	{
		NavigationSetExtController setExtController = new NavigationSetExtController(this.navigationModel.getListScoreMusicExt(), this.navigationModel.getListTablatureExt(), this.navigationModel.getListMusicExt());
		setExtController.initView(this.language, this.currentLanguage);
		if (setExtController.isChanged() == true)
		{
			System.out.println("update :):)");
			this.navigationModel.setExts(setExtController.getNewScoreExt(), setExtController.getNewTabExt(), setExtController.getNewMusicExt());
			this.navigationView.refreshAll();
		}
		else
			System.out.println("cancel");
	}
	
	public void hideNavigation()
	{
		this.setChanged();
		this.notifyObservers("hide");
		this.navigationView.hide();
	}
	
	public void showNavigation()
	{
		this.setChanged();
		this.notifyObservers("show");
		this.navigationView.show();
	}

	@Override
	public void setLanguage(String language) {
		this.currentLanguage = language;
		navigationView.setCurrentLanguage(language);
		navigationView.setTexts(this.language, language);
	}

	@Override
	public double getVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setShortCuts(ShortCutsListModel arg0) {
		this.navigationView.setShortcuts(arg0);
		
	}
}
