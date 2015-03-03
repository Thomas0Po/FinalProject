package Controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;

import plugincontract.APlugin;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Menu;
import Abstractions.Controllers.APluginListController;
import Abstractions.Models.LanguageModel;
import Abstractions.Models.ShortCutsListModel;
import Abstractions.Views.IMenuBar;
import Models.PluginConf;
import Models.PluginInfoModel;
import Models.PluginListModel;
import Models.PluginsConf;
import Utils.Helpers.LanguageLoader;
import Views.PluginListView;

public class PluginListController extends APluginListController {
	PluginListView pluginListView;
	PluginListModel model;
	LanguageModel languages;
	String currentLanguage;
	Models.PluginsConf plugconf;
	List<PluginInfoModel> pluginInfos;

	/*
	 * 
	 * Initialization. Calls view initialization method.
	 * 
	 * @see Controllers.IController#init(java.util.Observer)
	 */
	public boolean init(Observer o) {
		this.addObserver(o);
		try {
			this.init();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	/*
	 * Returns the listView
	 * 
	 * @see Controllers.IController#getMainComponent()
	 */
	public Node getMainComponent() {
		try {
			Node c = pluginListView.getMainComponent();
			return c;
		}
		catch (Exception e){
			return null;
		}
	}

	public Menu getMenuBarComponent() {
		return null;
	}

	/*
	 * Gets the selected plugin and sends it to the mainController via Observer
	 * pattern.
	 * 
	 * @see Controllers.IPluginListController#openPluginClick()
	 */
	public void openPluginClick(APlugin plugin) {
		APlugin selectedPlugin = plugin;//this.pluginListView.getSelectedPlugin();

		if (selectedPlugin != null) {
			this.setChanged();
			this.notifyObservers(selectedPlugin);
		}
	}

	/*
	 * Initialize the model then initialize the view.
	 * 
	 * @see Controllers.IController#init()
	 */
	public boolean init() throws Exception {
		model = new PluginListModel();
		this.plugconf = new Models.PluginsConf();
		pluginInfos = new ArrayList<PluginInfoModel>();
		LanguageLoader loader = new LanguageLoader();

		String pa = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
		if ((languages = loader.load(pa)) == null)
			return false;

		return this.initView();
	}

	/*
	 * Initialize the view and give it the model
	 * 
	 * @see Controllers.IController#initView()
	 */
	@Override
	public boolean initView() {
		try {
			pluginListView = new PluginListView();
			pluginListView.init(this);

			pluginListView.fillPluginList(model);
			pluginListView.setConsts(languages);
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

	/*
	 * Returns a list of IMenuBar
	 * 
	 * @see Controllers.AController#getMenuBar()
	 */
	@Override
	public List<IMenuBar> getMenuBar() {
		return pluginListView.getMenuBarItems();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Controllers.IController#close()
	 */
	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Controllers.IController#save()
	 */
	@Override
	public void save() {
		// TODO Auto-generated method stub
	}

	/*
	 * Called when modifyListPlugin click is handled.
	 * Fill pluginsInfos and sends it to the view.
	 */
	public void modifyPluginListClick(){
try{
		File b = new File("src/Ressources/");
		String absolute = b.getAbsolutePath();
		pluginInfos = new ArrayList<PluginInfoModel>();

		File f = new File(absolute);
		if (!f.exists())
			pluginListView.modifyPluginListClick(null);
		else {
			for (final File fileEntry : f.listFiles()) {
				if (fileEntry.isFile() && fileEntry.getName().contains(".jar")) {
					PluginInfoModel fileModel = getFilePluginInfos(fileEntry);
					fileModel.isAlreadyInList = isPluginAlreadyInList(fileModel.name);
					fileModel.checked = fileModel.isAlreadyInList;
					pluginInfos.add(fileModel);
				}
			}
			pluginListView.modifyPluginListClick(pluginInfos);
		}
}catch(Exception e){
	
}
	}

	/*
	 * Returns plugin informations from file
	 */
	private PluginInfoModel getFilePluginInfos(File file) {
		PluginInfoModel model = new PluginInfoModel();
		model.name = FilenameUtils.removeExtension(file.getName());
		model.absolutePath = file.getAbsolutePath();
		try {
			int i = 0;
			URL urlDescription;
			String line;

			urlDescription = new URL("jar:file:" + file.getAbsolutePath()
					+ "!/Description.txt");
			System.out.println(urlDescription);
			InputStream input = urlDescription.openStream();
			Reader reader = new InputStreamReader(input, "UTF8");
			BufferedReader bufferReader = new BufferedReader(reader);
			while ((line = bufferReader.readLine()) != null) {
				if (i != 0) {
					model.description += System.getProperty("line.separator");
				}
				model.description += line;
				++i;
			}
			bufferReader.close();
		} catch (Exception e) {
			model.description = "Pas de description disponible";
		}

		try {
			URL url = new URL("jar:file:" + file.getAbsolutePath()
					+ "!/Icon.png");
			ImageIO.read(url);
			model.iconPath = "jar:file:" + file.getAbsolutePath()
					+ "!/Icon.png";
		} catch (Exception e) {
			File fl = new File("src/Ressources/Images/notfound.jpg");
			model.iconPath = /*"jar:file:" +*/ "file:///" + fl.getAbsolutePath();
		}
		System.out.println("JE RETURN MODEL");
		return model;
	}

	/*
	 * Return true if pluginName is already in the plugin list. Otherwise
	 * returns false
	 * 
	 * @see
	 * Controllers.IPluginListController#isPluginAlreadyInList(java.lang.String)
	 */
	@Override
	public boolean isPluginAlreadyInList(String pluginName) {
		for (APlugin plug : model.getPluginList()) {
			if (plug.getName().equals(pluginName))
				return true;
		}
		return false;
	}

	/*
	 * Re-initializes the model and sends it to the view to update it
	 * 
	 * @see Controllers.IPluginListController#updateView()
	 */
	@Override
	public void updateView() {
		model = new PluginListModel();
		this.pluginListView.fillPluginList(model);
	}

	/*
	 * Save the pluginList state and update view
	 */
	public void SaveAndApplyPluginList() {
		for (PluginInfoModel plug : pluginInfos) {
			if (plug.checked && !plug.isAlreadyInList) {
				addPluginsConf(plug.name, plug.absolutePath, plug.description,
						plug.iconPath);
			} else if (!plug.checked && plug.isAlreadyInList) {
				removePluginsConf(plug.name);
			}
		}
		this.updateView();
	}

	/*
	 * Remove plugin from plugin configuration file
	 */
	public void removePluginsConf(String name) {

		plugconf.delPluginConf(name);
		plugconf.saveConf();		
	}

	/*
	 * Add plugin to plugin configuration file
	 */
	public void addPluginsConf(String name, String path, String description,
			String iconName) {
		plugconf.addPluginConf(name);
		System.out.println("ADD PLUG CONF NAME = " + name);
		plugconf.setValue(name, "path",
				path.substring(0, path.lastIndexOf(File.separator) + 1));
		plugconf.setValue(name, "iconName", iconName);
		plugconf.setValue(name, "description", description);
		plugconf.setValue(name, "toCharge", "true");
		plugconf.setValue(name, "toHide", "false");
		plugconf.setValue(name, "toRun", "false");
		plugconf.saveConf();
		
	}

	/*
	 * (non-Javadoc)
	 * @see Abstractions.Controllers.IController#setLanguage(java.lang.String)
	 */
	@Override
	public void setLanguage(String language) {
		pluginListView.setCurrentLanguage(language);
		pluginListView.setTexts(languages, language);
		//for (APlugin plug : this.model.getPluginList())
		//{
		//	plug.setLanguage(language);
		//}
	}

	@Override
	public double getVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * Returns plugin currently dragged
	 */
	@Override
	public APlugin getDraggedPlugin() {
		return this.pluginListView.getDraggedPlugin();
	}

	@Override
	public void getPluginSelected() {
		APlugin selectedPlugin = this.pluginListView.getSelectedPlugin();

		if (selectedPlugin != null) {
			this.setChanged();
			this.notifyObservers(selectedPlugin);		
	}
	}

	@Override
	public Object getPluginsInfoModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void launchPluginsToRun() {
		
		for (APlugin plugin : model.getPluginList()){
			if (plugconf.getPluginConf(plugin.getPath().substring(plugin.getPath().lastIndexOf(File.separator) + 1, plugin.getPath().length()-4)).isToRun())
				this.openPluginClick(plugin);
		}
	}

	@Override
	public void setShortCuts(ShortCutsListModel arg0) {
		this.pluginListView.setShortcuts(arg0);
		
	}
}
