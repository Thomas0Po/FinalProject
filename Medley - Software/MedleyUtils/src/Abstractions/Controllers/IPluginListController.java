package Abstractions.Controllers;

import plugincontract.APlugin;

/**
 * 
 * @author Persus
 * this interface is for the "Plugin List" controller class.
 * 
 */
public interface IPluginListController extends IController {
	/**
	 * launch the plugin clicked from the plugin list
	 */
/*	public void openPluginClick(APlugin pl);
	
*/	public void getPluginSelected();
	/**
	 * check if a plugin is already in the plugin list
	 * @param pluginName name of a plugin
	 * @return true if the action succeed or false if it failed
	 */
	public boolean isPluginAlreadyInList(String pluginName);
	/**
	 * update the plugin list view
	 */
	public void updateView();
	
	/**
	 * Returns dragged and dropped plugin
	 */
	public APlugin getDraggedPlugin();
	
	public Object getPluginsInfoModel();
	
	public void launchPluginsToRun();
}