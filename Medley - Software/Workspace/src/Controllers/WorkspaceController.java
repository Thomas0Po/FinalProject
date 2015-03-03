package Controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import plugincontract.APlugin;
import javafx.scene.Node;
import javafx.scene.control.Control;
import Abstractions.Controllers.AWorkspaceController;
import Abstractions.Models.ShortCutsListModel;
import Abstractions.Views.IMenuBar;
import Models.PluginsRunningStatus;
import Views.WorkspaceView;

public class WorkspaceController extends AWorkspaceController {
	WorkspaceView workspaceView;
	PluginCommunicationController comController;
	List<PluginsRunningStatus> RunningPlugins;
	String currentLanguage;
	private final int NB_PLUGINS_MAX = 5;
	
	
	/*
	 * Returns the main component of the view
	 * 
	 * @see Controllers.IController#getMainComponent()
	 */
	@Override
	public Node getMainComponent() {
		return workspaceView.getMainComponent();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see Controllers.IController#initView()
	 */	
	@Override
	public boolean initView() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Controllers.IController#close()
	 */
	@Override
	public void close() {
		System.out.println("Close all plugins");
		for (PluginsRunningStatus plug : this.RunningPlugins)
		{
			plug.resetPluginsCommunication();
			plug.stopPlugin();
		}
		this.RunningPlugins.clear();
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

	@Override
	public void ImportFileToSelectedPlugin(File file) {
		// TODO Auto-generated method stub
	}

	/*
	 * Initialization
	 * 
	 * @see Controllers.IController#init()
	 */
	public boolean init() {
		workspaceView = new WorkspaceView();
		workspaceView.init(this);
		return true;
	}

	/*
	 * Initialization with observer
	 * 
	 * @see Controllers.IController#init(java.util.Observer)
	 */
	@Override
	public boolean init(Observer ob) {
		this.addObserver(ob);
		this.RunningPlugins = new ArrayList<PluginsRunningStatus>();
		workspaceView = new WorkspaceView();
		this.comController = new PluginCommunicationController();
		workspaceView.init(this);
		return true;
	}

	/*
	 * Launch a plugin in workspace
	 * 
	 * @see
	 * Controllers.IWorkspaceController#launchPlugin(plugincontract.APlugin)
	 */
	public void launchPlugin(APlugin plugin) {
		if (plugin != null) 
		{
			if (this.RunningPlugins.size() == NB_PLUGINS_MAX)
			{
				this.tooManyPlugins();
				return;
			}
			System.out.println("WORKSPACE LAUNCH PLUG " + plugin.getPath());
			PluginsRunningStatus newPlug = new PluginsRunningStatus(plugin);
			newPlug.setLanguage(this.currentLanguage);
			this.RunningPlugins.add(newPlug);
			System.out.println("WORKSPACE NEW PLUG : " + newPlug.getPluginName());
			workspaceView.addPlugin(newPlug);
		}
	}
	
	public void closePlugin(int id)
	{
		PluginsRunningStatus plugToClose = null;
		for (PluginsRunningStatus plug : this.RunningPlugins)
		{
			if (id == plug.getId())
			{
				plugToClose = plug;
				break;
			}
		}
		if (plugToClose == null)
		{
			System.out.println("WTF ??");
			return;
		}
		for (int plugIdInput : plugToClose.getInputPlugs())
		{
			for (PluginsRunningStatus plug : this.RunningPlugins)
			{
				if (plugIdInput == plug.getId())
				{
					plug.deleteCommunicationOutput(plugToClose);
					break;
				}
			}
		}
		
		for (int plugIdOutput : plugToClose.getOutputPlugs())
		{
			for (PluginsRunningStatus plug : this.RunningPlugins)
			{
				if (plugIdOutput == plug.getId())
				{
					plug.deleteCommunicationInput(plugToClose);
					break;
				}
			}
		}
		this.RunningPlugins.remove(plugToClose);
	}

	public void dropObjectToAPlugin(int id, Object[] arg)
	{
		for (PluginsRunningStatus plug : this.RunningPlugins)
		{
			if (id == plug.getId())
			{
				plug.dropObject(arg);
				break;
			}
		}
	}
	/*
	 * Sets observer
	 */
	public void setObserver(Observer o) {
		this.addObserver(o);
	}

	/*
	 * Notify main controller that a plugin is dropped
	 */
	public void getPluginDropped() {
		this.setChanged();
		this.notifyObservers("PluginDropped");
	}

	/*
	 * Calls popUp if too many plugins are executed
	 */
	public void tooManyPlugins() {
		this.setChanged();
		this.notifyObservers("PopupMessage."
				+ NB_PLUGINS_MAX
				+ " plugins sont déjà en cours d'exécution. Veuillez fermer au moins un plugin en cours pour en lancer un nouveau.");

	}

	@Override
	public void setLanguage(String language) {
		this.currentLanguage = language;
		System.out.println("WORKSPACE NEW LANG = " + language);
		this.comController.setLanguage(language);
		for (PluginsRunningStatus plug : this.RunningPlugins)
			plug.setLanguage(language);
	}


	@Override
	public List<IMenuBar> getMenuBar() {
		return (this.workspaceView.getMenuBarItems());
	}

	@Override
	public double getVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setShortCuts(ShortCutsListModel arg0) {
		this.workspaceView.setShortcuts(arg0);
	}

	public void setPluginsCommunication()
	{
		if (this.RunningPlugins.size() >= 2)
		{
			this.comController.setListRunningPlugins(RunningPlugins);
			this.comController.resetView();
			this.comController.launch();
			System.out.println("TEST");
			return;
		}
		this.setChanged();
		this.notifyObservers("PopupMessage."
				+ "Veuillez lancer au moins deux plugins pour acceder a cette fonctionalite");
	}
}
