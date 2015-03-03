package Abstractions.Controllers;

import java.util.List;


import java.util.Observer;

import javafx.scene.Node;
import Abstractions.Models.ShortCutsListModel;
import Abstractions.Views.IMenuBar;

/**
 * 
 * @author Persus
 *
 * This interface is the controller interface for all sub-controller/Modules (Plugins List, Navigation and Workspace) 
 */

public interface IController {
//	public boolean init();
	/**
	 * initialize the controller
	 * @param ob this parameters is the observer of the main controller. 
	 * It is for the communication of different module of the project 
	 * @return true if the action succeed or false if it failed 
	 * 
	 */
	public boolean init(Observer ob);
	/**
	 * initialize the view of the controller
	 * @return true if the action succeed or false if it failed 
	 */
	public boolean initView();
	/**
	 * get the main graphic component of the view 
	 * @return the main graphic component
	 */
	public Node getMainComponent();
	/**
	 * @return List of menu Item 
	 */
	public List<IMenuBar> getMenuBar();
	/**
	 * this function is called when the application is closing. it mainly closed the view
	 */
	public void close();
	/**
	 * save all parameters of the controller
	 */
	public void save();
	/**
	 * set the language of all view component
	 * @param language is the name of new language
	 */
	public void setLanguage(String language);
	
	public void setShortCuts(ShortCutsListModel model);
	
	/**
	 * get the version of the module
	 * @return the version of the module
	 */
	public double getVersion();
}
