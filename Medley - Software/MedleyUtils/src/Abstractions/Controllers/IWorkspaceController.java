package Abstractions.Controllers;

import java.io.File;

import plugincontract.APlugin;

/**
 * 
 * @author Persus
 * this interface is for the "Workspace" controller class.
 * 
 */
public interface IWorkspaceController extends IController {
	/**
	 * import the file from the navigation part to the selected plugin
	 * @param file : file to import
	 */
	void ImportFileToSelectedPlugin(File file);
	/**
	 * launch plugin into workspace view
	 * @param pl : plugin to launch.
	 */
	void launchPlugin(APlugin pl);
}
