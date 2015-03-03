package Views;

import java.io.IOException;
import java.util.List;

import Controllers.PluginCommunicationController;
import Models.PluginsRunningStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class PluginComChooser {
	    @FXML private Label PluginInfoLabel;
	    @FXML private TextArea TipsForCommunicationTextArea;
	    @FXML private Button editButton;
	    @FXML private Button ResetButton;
	    @FXML private Button OKButton;
	    @FXML private TextArea PluginInfoTextArea;
	    @FXML private Label PluginCommunicationTitleLabel;
	    @FXML private Label TipForCommunicationLabel;
	    @FXML private ListView<PluginsRunningStatus> RunningPluginsListView;
	    private ObservableList<PluginsRunningStatus> pluginRunningList;
	    
	    final Stage stagePopup = new Stage(StageStyle.TRANSPARENT);
	    private Scene	popupScene;
	    private Parent 	popupView;
	    @FXML private Label popupMessageLabel;
	    @FXML private Button YesButtonPopUp;
	    @FXML private Button noButton;

	    private PluginCommunicationController controller;
	    
	    public void initView(Stage stage)
	    {
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
					"popUp.fxml"));
	    	fxmlLoader.setController(this);
	    	try {
				popupView = fxmlLoader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	popupScene = new Scene(this.popupView);
	    	RunningPluginsListView.setCellFactory(new Callback<ListView<PluginsRunningStatus>, ListCell<PluginsRunningStatus>>() {
				
				@Override
				public ListCell<PluginsRunningStatus> call(
						ListView<PluginsRunningStatus> param) {
					final PluginRunningCell rPlug = new PluginRunningCell();
					return rPlug;
				}
			});
	    	
	    	RunningPluginsListView.getSelectionModel().selectedItemProperty()
	        .addListener(new ChangeListener<PluginsRunningStatus>() {
				@Override
				public void changed(
						ObservableValue<? extends PluginsRunningStatus> observable,
						PluginsRunningStatus oldValue,
						PluginsRunningStatus newValue) {
					System.out.println("Selection Change");
					if (newValue != null)
					{
						System.out.println("new value desc = " + newValue.getPluginDescription());
						System.out.println("new value tips = " + newValue.getPluginTips());
						editButton.setDisable(false);
						PluginInfoTextArea.setText(newValue.getPluginDescription());
						TipsForCommunicationTextArea.setText(newValue.getPluginTips());
					}
					else
						editButton.setDisable(true);
				}
	        });
	    	this.editButton.setDisable(true);
	    }
	    
	    public void initListPlug(List<PluginsRunningStatus> listRunningPlugins) {
			pluginRunningList = FXCollections.observableArrayList(listRunningPlugins);
	    	RunningPluginsListView.setItems(pluginRunningList);
	    	
	    	
		}
	    
	    public void setControllerRef(PluginCommunicationController contro)
	    {
	    	this.controller = contro;
	    }
	    
	    public void setLanguage(String key)
	    {
	    	//TODO !!! 
	    }
	    
	    @FXML
	    void ResetButtonClicked(ActionEvent event) {
			stagePopup.initModality(Modality.APPLICATION_MODAL);
			stagePopup.initStyle(StageStyle.UTILITY);
			stagePopup.setTitle("Are you sure ?");
			stagePopup.setScene(this.popupScene);
			stagePopup.setResizable(false);
			stagePopup.showAndWait();
	    }

	    @FXML
	    void editButtonClicked(ActionEvent event) {
	    	controller.setPluginCom(RunningPluginsListView.getSelectionModel().getSelectedItem());
	    }

	    @FXML
	    void OkButtonClicked(ActionEvent event) {
	    	controller.close();
	    }
	    
	    @FXML 
	    void yesButtonPopUpClicked(ActionEvent event) {
	    	controller.resetAllCommunication();
	    	stagePopup.close();
	    }

	    @FXML
	    void NoButtonPopupClicked(ActionEvent event) {
	    	stagePopup.close();
	    }
}
