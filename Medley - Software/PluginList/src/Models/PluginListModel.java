package Models;

import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Helpers.PluginLoader;
import plugincontract.APlugin;

public class PluginListModel {

	Models.PluginsConf pluginConf;
	ObservableList<APlugin> pluginList;

	/*
	 * Returns pluginList
	 */
	public ObservableList<APlugin> getPluginList() {
		return pluginList;
	}

	/*
	 * Sets pluginList
	 */
	public void setPluginList(ObservableList<APlugin> pluginList) {
		this.pluginList = pluginList;
	}

	/*
	 * Constructor initializes pluginList and fills it
	 */
	public PluginListModel() {

		pluginList = FXCollections.observableArrayList();

		pluginConf = new Models.PluginsConf();

		PluginLoader ploader = new PluginLoader(pluginConf);
		
		Collection<APlugin> pls = ploader.buildAll();
		for (APlugin aplugin : pls){
			pluginList.add(aplugin);
		}
		

	}

	/*
	 * Adds the plugin to the pluginList
	 */
	public void addPluginToList(APlugin plugin) {
		this.pluginList.add(plugin);
	}

	/*
	 * Deletes the plugin from pluginList
	 */
	public void deletePluginFromList(APlugin plugin) {
		this.pluginList.remove(plugin);
	}
	
}

