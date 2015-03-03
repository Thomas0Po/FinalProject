package Controllers;

import java.io.IOException;
import java.util.List;

import Views.PluginComChooser;
import Views.PluginSetCommunication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Models.PluginsRunningStatus;

public class PluginCommunicationController {
	private PluginComChooser 			chooserController;
	private PluginSetCommunication 		setComController;
	private Parent			 			chooserView;
	private Parent			 			setComView;
	private BorderPane					pluginComunicationView;
	private Scene 						scene;
	private List<PluginsRunningStatus>	listRunningPlugins;
	final Stage stage = new Stage(StageStyle.TRANSPARENT);
	
	
	public PluginCommunicationController() {
		this.chooserController = new PluginComChooser();
		this.setComController = new PluginSetCommunication();
		this.loadViews();
		this.initView();
		this.pluginComunicationView = new BorderPane();
		this.pluginComunicationView.getChildren().setAll(this.chooserView);
		this.scene = new Scene(this.pluginComunicationView, 600, 400);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.DECORATED);
		stage.setTitle("Plugin Communication");
		stage.setScene(scene);
		stage.setResizable(false);
	}
	
	private boolean loadViews()
	{
			FXMLLoader fxmlLoaderChooser = new FXMLLoader(getClass().getResource(
					"../Views/PluginCommunicationList.fxml"));
			FXMLLoader fxmlLoaderSetCom = new FXMLLoader(getClass().getResource(
					"../Views/SetPluginCommunication.fxml"));
			
			fxmlLoaderChooser.setController(this.chooserController);
			fxmlLoaderSetCom.setController(this.setComController);
			try {
				this.chooserView = fxmlLoaderChooser.load();
				this.setComView = fxmlLoaderSetCom.load();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		return true;
	}

	private void initView()
	{
		this.chooserController.initView(this.stage);
		this.chooserController.setControllerRef(this);
		this.setComController.initView();
		this.setComController.setControllerRef(this);
	}
	
	public void resetView()
	{
		this.chooserController.initListPlug(this.listRunningPlugins);
	}
	
	public void launch()
	{
		this.pluginComunicationView.getChildren().setAll(this.chooserView);
		stage.show();
	}
	
	public void close()
	{
		stage.close();
	}
	
	public void setListRunningPlugins(List<PluginsRunningStatus> listRunningPlugins) {
		this.listRunningPlugins = listRunningPlugins;
	}
	
	public void setLanguage(String key)
	{
		this.chooserController.setLanguage(key);
	}
	
	public void resetAllCommunication()
	{
		for (PluginsRunningStatus plug : this.listRunningPlugins)
		{
			plug.resetPluginsCommunication();
		}
	}
	
	public void setPluginCom(PluginsRunningStatus plugSelected)
	{
		System.out.println("##########################");
		System.out.println("BEFORE");
		for (PluginsRunningStatus plug : this.listRunningPlugins)
			plug.showCommunication();
		System.out.println("##########################");
		this.setComController.initNewPlugSelected(plugSelected, this.listRunningPlugins);
		this.pluginComunicationView.getChildren().setAll(this.setComView);
	}
	
	public void comeBackToPluginChooser()
	{
		System.out.println("##########################");
		System.out.println("AFTER");
		for (PluginsRunningStatus plug : this.listRunningPlugins)
			plug.showCommunication();
		System.out.println("##########################");
		this.chooserController.initListPlug(this.listRunningPlugins);
		this.pluginComunicationView.getChildren().setAll(this.chooserView);
	}
}

