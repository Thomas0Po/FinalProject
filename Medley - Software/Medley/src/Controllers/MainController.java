package Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import plugincontract.APlugin;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import Abstractions.Controllers.INavigationController;
import Abstractions.Controllers.IPluginListController;
import Abstractions.Controllers.IWorkspaceController;
import Abstractions.Models.LanguageModel;
import Abstractions.Models.ShortCutsListModel;
import Factory.FactoryMedleyPart;
import Models.PluginsConf;
import Utils.Helpers.LanguageLoader;
import Utils.Helpers.MedleyPopup;
import Views.MainView;

/**
 * 
 * This Class is the main controller of Software
 * Its inherit of Observer for Listen the module's controller
 */
public class MainController implements Observer {

	/**
	 * PluginList Module Controller
	 */
	IPluginListController pluginListController;

	/**
	 * Workspace Module Controller
	 */
	IWorkspaceController workspaceController;

	/**
	 * Navigation Module Controller
	 */

	INavigationController navigationController;
	/**
	 * Factory Module Loader
	 */
	FactoryMedleyPart fact;

	APlugin draggedPlugin;

	/**
	 * Main view of the software
	 */
	MainView mainView;

	/**
	 * Language Model for the Medley.jar module
	 */
	LanguageModel language;
	String currentLanguage;

	PluginsConf pluginsConf;

	ShortCutsListModel shortcutsModel;
	/*public List<PluginInfoModel> getPluginsConf() {
		return pluginsConf;
	}*/

	/**
	 * Initializes controllers, main view and fill main view with sub-views
	 * @throws Exception 
	 */
	public void launch(Stage stage) throws Exception {
		try{
			this.getConfig();
			LanguageLoader loader = new LanguageLoader();

			File f = new File("src/Ressources/Languages");
			String pa = f.getAbsolutePath();
			language = loader.load(pa);

			this.fact = new FactoryMedleyPart();
			fact.init();

			this.pluginsConf = new PluginsConf();
			shortcutsModel = new ShortCutsListModel();
			if (!initSubControllers()) {
				System.out.println("initsubcontrollersFail");
				throw new Exception("Impossible d'initialiser votre logiciel Medley");
			}
			mainView = new MainView(stage, this);
			mainView.setCurrentLanguage(currentLanguage);

			mainView.setConsts(language);
			mainView.init();
			mainView.setPluginListView(pluginListController.getMainComponent());
			mainView.setNavigationView(navigationController.getMainComponent());
			mainView.setWorkspaceView(workspaceController.getMainComponent());
			buildMenuBar();
			this.setShortcuts();
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					System.out.println("Stage is closing");
					workspaceController.close();
				}
			});
			mainView.show();
			pluginListController.launchPluginsToRun();
		}catch(Exception e){
			e.printStackTrace();
			if (e.getMessage() == null)
				throw new Exception("Une erreur est subvenue au demarrage de votre logiciel Medley. Contactez le support");
			else
				throw new Exception(e.getMessage());
		}
	}

	/**
	 * Get content of MainConfig and initialize the dependent variables
	 * @throws Exception 
	 */
	private void getConfig() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			//URL url = getClass().getClassLoader().getResource("Ressources/Configs/MainConfig");
//			File f1 = new File("src/Ressources/Configs/MainConfig");
//			input = f1.
			File f1 = new File("src/Ressources/Configs/MainConfig");

			input = new FileInputStream(f1);//new File(url.getFile()));
			prop.load(input);
			this.currentLanguage = prop.getProperty("currentLanguage");
			input.close();
		} catch (IOException e) { // If config file doesn't exists, default file is generated
			e.printStackTrace();
			OutputStream output = null;

			try {
				if (input != null)
					input.close();
//				URL url = getClass().getClassLoader().getResource("Ressources/Configs/MainConfig");
//				output = new FileOutputStream(new File(url.getFile()));

				File f1 = new File("src/Ressources/Configs/MainConfig");
				output = new FileOutputStream(f1);
				// set the properties value
				prop.setProperty("currentLanguage", "EN");
				// save properties
				prop.store(output, null);

			} catch (IOException io) {
				io.printStackTrace();
			} finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException ioe) {
						throw new Exception("Can't load main configuration");
					}
				}
			}
		}		
	}

	/*
	 * Initializes sub-controllers
	 * @return true on succed or false
	 */
	public boolean initSubControllers() {
		pluginListController = fact.getPluginListController();
		workspaceController = fact.getWorkspaceController();
		navigationController = fact.getNavigationController();

		if (pluginListController == null || workspaceController == null || navigationController == null){

			return false;
		}

		if (pluginListController.init(this) && workspaceController.init(this) && navigationController.init(this)){

			pluginListController.setLanguage(this.currentLanguage);
			workspaceController.setLanguage(this.currentLanguage);
			navigationController.setLanguage(this.currentLanguage);

			return true;
		}

		return false;
	}

	/**
	 * Construction of the menuBar
	 */
	public void buildMenuBar() {
		mainView.buildMenuBar();
		mainView.addMenu(pluginListController.getMenuBar());
		mainView.addMenu(navigationController.getMenuBar());
		mainView.addMenu(workspaceController.getMenuBar());
	}

	//COMMUNICATION INTER-PARTIES
	/**
	 * Method from Observable Pattern with argument
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 * this function is for the communication of different module
	 */
	public void update(Observable o, Object arg) {
		System.out.println("PLUGIN DROPPED");

		if (o == this.navigationController)
			this.navigationCommunication(arg);
		else if (o == this.pluginListController)
			this.pluginListCommunication(arg);
		else if (o == this.workspaceController)
			this.workspaceCommunication(arg);
	}

	/**
	 * this function is for the pluginlist communication
	 * @param arg is the argument send by the PlugListController
	 */
	private void pluginListCommunication(Object arg)
	{
		if (arg.getClass().getName() == "java.lang.String") {
			if ((String) arg == "PluginDropped")
				pluginListController.getPluginSelected();

		} 
		else
		{
			APlugin pl = (APlugin) arg;
			workspaceController.launchPlugin(pl);
		}
	}

	/**
	 * this function is for the Navigation communication
	 * @param arg is the argument send by the NavigationController
	 */
	private void navigationCommunication(Object arg)
	{
		if (arg.getClass().getName().equals("java.lang.String"))
		{
			String msg = (String)arg;
			if (msg.equals("hide"))
			{
			}
			else if (msg.equals("show"))
			{
			}
		}
		else if (arg.getClass().getName().equals("java.io.File"))
		{
			File file = (File) arg;
			this.workspaceController.ImportFileToSelectedPlugin(file);
		}
	}

	/**
	 * this function is for the workspace list communication
	 * @param arg is the argument send by the WorkspaceController
	 */
	private void workspaceCommunication(Object arg)
	{
		if (((String) arg).contains("PopupMessage"))
			mainView.popUpMessage((String) arg);
		else if (arg.equals("PluginDropped")){
			workspaceController.launchPlugin(this.pluginListController.getDraggedPlugin());
		}
	}

	/**
	 * Called when File/Close is clicked.
	 */
	public void quit(){
		workspaceController.close();
		pluginListController.close();
		navigationController.close();
		mainView.close();
	}

	/**
	 * this function Updates the language in each part, and update the texts.
	 * Updates the configuration file too.
	 * @param newLanguage name of the new language 
	 */
	public void changeLanguage(String newLanguage){
		InputStream input = null;
		OutputStream output = null;
		try{
		pluginListController.setLanguage(newLanguage);
		navigationController.setLanguage(newLanguage);
		workspaceController.setLanguage(newLanguage);
		mainView.setTexts(language, newLanguage);
		} catch (Exception e){MedleyPopup pop = new MedleyPopup("Impossible de changer la langue de votre logiciel Medley");}
		try { // save new language in config
			Properties prop = new Properties();

			//URL url = getClass().getClassLoader().getResource("Ressources/Configs/MainConfig");
			File inputFile = new File("src/Ressources/Configs/MainConfig");
			input = new FileInputStream(inputFile);
			prop.load(input);
			input.close();
			
			//URL url2 = getClass().getClassLoader().getResource("Ressources/Configs/MainConfig");
			File test = new File("src/Ressources/Configs/MainConfig");
			output = new FileOutputStream(test);

			prop.setProperty("currentLanguage", newLanguage);
			prop.store(output, null);

		} catch (IOException io) {
			MedleyPopup p = new MedleyPopup("Une erreur est survenue lors de la sauvegarde de votre langue");
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					input.close();
					output.close();
				} catch (IOException ioe) { // TODO : POPUP ERROR
					ioe.printStackTrace();
				}
			}
		}
	}

	/*
	 * Set shortcuts
	 */
	public void setShortcuts() {
		this.mainView.setShortCutsModel(shortcutsModel);
		this.pluginListController.setShortCuts(shortcutsModel);
		this.navigationController.setShortCuts(shortcutsModel);
		this.workspaceController.setShortCuts(shortcutsModel);

	}
}
