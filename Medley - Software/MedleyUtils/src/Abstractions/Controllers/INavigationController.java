package Abstractions.Controllers;

import java.io.File;

/**
 * 
 * @author Persus
 * this interface is for the "Navigation" controller class.
 * 
 */
public interface INavigationController extends IController {
	/**
	 * this function is call by the MainController class when an user whan to import a File into the selected plugin in the workspace module. 
	 * @return get the file to import
	 */
	File getImportedFile();
}