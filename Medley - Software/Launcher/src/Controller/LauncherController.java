package Controller;

import javafx.stage.Stage;
import View.MedleyLauncherView;
import update.MedleyUpdate;

import java.lang.Thread;
public class LauncherController {
	private MedleyUpdate updater;
	private MedleyLauncherView view;
	
	public LauncherController(Stage stage) {
		this.updater = new MedleyUpdate();
		this.view = new MedleyLauncherView(stage);
		this.view.initView();
//		this.view.addProgress();
//		this.updater.updateSoftware();
		//this.updater.start();
		//Thread t = this.updater;
		//t.start();
		//try {
		//	t.join();
		//} catch (InterruptedException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
//		this.updater.getProgressBar().setProgress(1.0);
		try {
		    Thread.sleep(3000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	//	this.view.exit();
	}
	
	//TODO PopUpErreur + exit
	
	public boolean updateSoftware()
	{
		if (this.updater.checkNewSoftwareUpdate() == false)
		{
			System.out.println("Can't connect to the server");
		}
		
		if (this.updater.isNeedUpdate() == false)
		{
			this.view.setState(new String ("There is no update"));
			return true;
		}
		
		this.view.setState(new String("Need New Update"));
		this.view.addProgress();
		
		this.view.setState(new String("Start Core Update"));
		if (this.updater.updateCore() == false)
		{
			return false;
		}
		this.view.setState(new String("Core Update Done"));
		this.view.addProgress();
		
		this.view.setState(new String("Start Navigation Update"));
		if (this.updater.updateNavigation() == false)
		{
			return false;
			
		}
		this.view.addProgress();
		this.view.setState(new String("Navigation Update Done"));
		
		this.view.setState(new String("Start Plugins List Update"));
		if (this.updater.updatePluginList() == false)
		{
			return false;
		}
		this.view.addProgress();
		this.view.setState(new String("Plugins List Update Done"));
		this.view.setState(new String("Start Workspace Update"));
		if (this.updater.updateWorkspace() == false)
		{
			return false;
		}
		
		this.view.addProgress();
		this.view.setState(new String("Workspace Update Done"));
		this.view.setState(new String("Installing Update"));
		if (this.updater.patchUpdate() == false)
		{
			return false;
		}
		this.view.setState(new String("Medley Update Done"));
		this.view.addProgress();
		this.view.showPopUpSucces();
		return true;
	}
}
