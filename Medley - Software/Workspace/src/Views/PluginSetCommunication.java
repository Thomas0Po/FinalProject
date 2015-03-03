package Views;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Controllers.PluginCommunicationController;
import Models.PluginsRunningStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

@SuppressWarnings("unused")
public class  PluginSetCommunication {

    @FXML private ComboBox<PluginsRunningStatus> PluginInputComboBox;
    @FXML private Label PluginOutputLabel;
    @FXML private ImageView IconPlugin;
    @FXML private Button PluginInputAddButton;
    @FXML private ListView<PluginsRunningStatus> PluginOutputListView;
    @FXML private Button OkButton;
    @FXML private Button PluginInputDeleteButton;
    @FXML private ListView<PluginsRunningStatus> PluginInputListView;
    @FXML private ComboBox<PluginsRunningStatus> PluginOutputComboBox;
    @FXML private Label PluginNameLabel;
    @FXML private Label PluginInputLabel;
    @FXML private Button resetButton;
    @FXML private Button PluginOutputAddButton;
    @FXML private Button PluginOutputDeleteButton;
    
    private ObservableList<PluginsRunningStatus> plugInputList;
    private ObservableList<PluginsRunningStatus> plugToAddInputList;
    private ObservableList<PluginsRunningStatus> plugOutputList;
    private ObservableList<PluginsRunningStatus> plugToAddOutputList;

    private PluginsRunningStatus pluginSelected;
    private List<PluginsRunningStatus> plugRunningList;
	private PluginCommunicationController controller;
    
    public void initView()
    {
    	this.initInputListView();
    	this.initOutputListView();
    	this.initInputComboBox();
    	this.initOutputComboBox();
    	this.initPopUp();
    }
    
    private void initPopUp() {
		// TODO Auto-generated method stub
	}

	private void initOutputComboBox() {
    	PluginOutputComboBox.setCellFactory(new Callback<ListView<PluginsRunningStatus>, ListCell<PluginsRunningStatus>>() {
			
			@Override
			public ListCell<PluginsRunningStatus> call(
					ListView<PluginsRunningStatus> param) {
				final PluginRunningCell rPlug = new PluginRunningCell();
				return rPlug;
			}
		});
    	
    	PluginOutputComboBox.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<PluginsRunningStatus>() {
			@Override
			public void changed(
					ObservableValue<? extends PluginsRunningStatus> observable,
					PluginsRunningStatus oldValue,
					PluginsRunningStatus newValue) {
				if (newValue != null)
					PluginOutputAddButton.setDisable(false);
				else
					PluginOutputAddButton.setDisable(true);
			}
        });
		
	}

	private void initInputComboBox() {
		PluginInputComboBox.setCellFactory(new Callback<ListView<PluginsRunningStatus>, ListCell<PluginsRunningStatus>>() {
			
			@Override
			public ListCell<PluginsRunningStatus> call(
					ListView<PluginsRunningStatus> param) {
				final PluginRunningCell rPlug = new PluginRunningCell();
				return rPlug;
			}
		});
    	
    	PluginInputComboBox.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<PluginsRunningStatus>() {
			@Override
			public void changed(
					ObservableValue<? extends PluginsRunningStatus> observable,
					PluginsRunningStatus oldValue,
					PluginsRunningStatus newValue) {
				if (newValue != null)
					PluginInputAddButton.setDisable(false);
				else
					PluginInputAddButton.setDisable(true);
			}
        });
	}

	private void initOutputListView() {
		PluginOutputListView.setCellFactory(new Callback<ListView<PluginsRunningStatus>, ListCell<PluginsRunningStatus>>() {
			
			@Override
			public ListCell<PluginsRunningStatus> call(
					ListView<PluginsRunningStatus> param) {
				final PluginRunningCell rPlug = new PluginRunningCell();
				return rPlug;
			}
		});
    	
    	PluginOutputListView.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<PluginsRunningStatus>() {
			@Override
			public void changed(
					ObservableValue<? extends PluginsRunningStatus> observable,
					PluginsRunningStatus oldValue,
					PluginsRunningStatus newValue) {
				if (newValue != null)
					PluginOutputDeleteButton.setDisable(false);
				else
					PluginOutputDeleteButton.setDisable(true);
			}
        });
		
	}

	private void initInputListView() {
		PluginInputListView.setCellFactory(new Callback<ListView<PluginsRunningStatus>, ListCell<PluginsRunningStatus>>() {
			
			@Override
			public ListCell<PluginsRunningStatus> call(
					ListView<PluginsRunningStatus> param) {
				final PluginRunningCell rPlug = new PluginRunningCell();
				return rPlug;
			}
		});
    	
    	PluginInputListView.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<PluginsRunningStatus>() {
			@Override
			public void changed(
					ObservableValue<? extends PluginsRunningStatus> observable,
					PluginsRunningStatus oldValue,
					PluginsRunningStatus newValue) {
				if (newValue != null)
					PluginInputDeleteButton.setDisable(false);
				else
					PluginInputDeleteButton.setDisable(true);
			}
        });
		
	}

	public void initNewPlugSelected(PluginsRunningStatus plugin, List<PluginsRunningStatus> runningPlugins)
    {
    	List<PluginsRunningStatus> 	plugInput = new ArrayList<PluginsRunningStatus>();
    	List<PluginsRunningStatus> 	plugOutput = new ArrayList<PluginsRunningStatus>();
    	List<PluginsRunningStatus> 	plugToAddInput = new ArrayList<PluginsRunningStatus>();
    	List<PluginsRunningStatus> 	plugToAddOutput = new ArrayList<PluginsRunningStatus>();
    	Image						imagePlug = new Image(plugin.getPluginIcon());
    	
    	this.pluginSelected = plugin;
    	this.plugRunningList = runningPlugins;
    	this.PluginNameLabel.setText(plugin != null ? plugin.getPluginName() : "Plugin");
    	
    	if (imagePlug.isError())
    	{
			File f = new File("src/Ressources/Images/notfound.jpg");
			imagePlug = new Image("file:///" + f.getAbsolutePath());//image = new Image("/Ressources/Images/notfound.jpg");
		}
    	this.IconPlugin.setImage(imagePlug);
    	this.PluginInputDeleteButton.setDisable(true);
    	this.PluginOutputDeleteButton.setDisable(true);
    	this.PluginInputAddButton.setDisable(true);
    	this.PluginOutputAddButton.setDisable(true);
    	
    	for (PluginsRunningStatus runPlug : runningPlugins)
    	{
    		if (pluginSelected.getId() != runPlug.getId())
    		{
    			if (pluginSelected.isInInputPlugs(runPlug.getId()))
    				plugInput.add(runPlug);
    			else
    				plugToAddInput.add(runPlug);
    			if (pluginSelected.isInOutputPlugs(runPlug.getId()))
    				plugOutput.add(runPlug);
    			else
    				plugToAddOutput.add(runPlug);		
    		}
    	}
    	this.plugInputList = FXCollections.observableArrayList(plugInput);
    	this.PluginInputListView.setItems(this.plugInputList);
    	this.plugOutputList = FXCollections.observableArrayList(plugOutput);
    	this.PluginOutputListView.setItems(this.plugOutputList);
    	this.plugToAddInputList = FXCollections.observableArrayList(plugToAddInput);
    	this.PluginInputComboBox.setItems(this.plugToAddInputList);
    	this.plugToAddOutputList = FXCollections.observableArrayList(plugToAddOutput);
    	this.PluginOutputComboBox.setItems(this.plugToAddOutputList);
    }
    
    public void setLanguage(String key)
    {
    	
    }
    
    @FXML
    void resetButtonClicked(ActionEvent event) {
    	//TODO POPUP
    }

    @FXML
    void okButtonClicked(ActionEvent event) {
    	
    	this.applyInputCom();
    	this.applyOutputCom();
    	System.out.println("##########################");
		System.out.println("PLUG SELECTED");
		pluginSelected.showCommunication();
		System.out.println("##########################");
		this.controller.comeBackToPluginChooser();
    }

    private void applyOutputCom() {
    	System.out.println("Apply Output");
    	boolean toDelete;
    	List<Integer> plugIdToDelOutputList = new ArrayList<Integer>();
    	
    	for (int oldPlugIdOutput : this.pluginSelected.getOutputPlugs())
    	{
    		toDelete = true;
    		for (PluginsRunningStatus newPlugOutput : this.plugOutputList)
    		{
    			if (oldPlugIdOutput == newPlugOutput.getId())
    			{
    				toDelete = false;
    				break;
    			}
    		}
    		if (toDelete == true)
    		{
    			plugIdToDelOutputList.add(oldPlugIdOutput);
    		}
    	}
    	
    	for (PluginsRunningStatus plugs : this.plugRunningList)
    	{
    		for (Integer plugIdToDel : plugIdToDelOutputList)
        	{
    			if (plugIdToDel == plugs.getId())
    			{
    				this.pluginSelected.deleteCommunicationOutput(plugs);
    				break;
    			}
        	}
    	}
    	System.out.println("PlugToADD");
    	for (PluginsRunningStatus plugOutput : this.plugOutputList)
    	{
    		System.out.println("NEW OUTPUT ID : " + plugOutput);
    		if (this.pluginSelected.isInOutputPlugs(plugOutput.getId()) == false)
    		{
    			System.out.println("ADD TO PLUG");
    			this.pluginSelected.addOutputPlug(plugOutput);
    		}
    	}
	}

	private void applyInputCom() {
		System.out.println("Apply Input");
		boolean toDelete;
    	List<Integer> plugIdToDelInputList = new ArrayList<Integer>();
    	
    	for (int oldPlugIdInput : this.pluginSelected.getInputPlugs())
    	{
    		toDelete = true;
    		for (PluginsRunningStatus newPlugInput : this.plugInputList)
    		{
    			if (oldPlugIdInput == newPlugInput.getId())
    			{
    				toDelete = false;
    				break;
    			}
    		}
    		if (toDelete == true)
    		{
    			plugIdToDelInputList.add(oldPlugIdInput);
    		}
    	}
    	
    	for (PluginsRunningStatus plugs : this.plugRunningList)
    	{
    		for (Integer plugIdToDel : plugIdToDelInputList)
        	{
    			if (plugIdToDel == plugs.getId())
    			{
    				this.pluginSelected.deleteCommunicationInput(plugs);
    				break;
    			}
        	}
    	}
    	
    	for (PluginsRunningStatus plugInput : this.plugInputList)
    	{
    		if (this.pluginSelected.isInInputPlugs(plugInput.getId()) == false)
    		{
    			this.pluginSelected.addInputPlug(plugInput);
    		}
    	}
	}

	@FXML
    void addInputButtonClicked(ActionEvent event) {
    	PluginsRunningStatus plugToAddInput = this.PluginInputComboBox.getSelectionModel().getSelectedItem();
    	this.plugInputList.add(plugToAddInput);
    	this.plugToAddInputList.remove(plugToAddInput);
    }

    @FXML
    void delInputButtonClicked(ActionEvent event) {
    	PluginsRunningStatus plugToDelInput = this.PluginInputListView.getSelectionModel().getSelectedItem();
    	this.plugToAddInputList.add(plugToDelInput);
    	this.plugInputList.remove(plugToDelInput);
    }

    @FXML
    void addOutputButtonClicked(ActionEvent event) {
    	System.out.println("GROS FILS DE PUTE");
    	PluginsRunningStatus plugToAddOutput = this.PluginOutputComboBox.getSelectionModel().getSelectedItem();
    	this.plugOutputList.add(plugToAddOutput);
    	System.out.println("Je Add Output List SIZE LIST = " + this.plugOutputList.size());
    	this.plugToAddOutputList.remove(plugToAddOutput);
    }

    @FXML
    void delOutputButtonClicked(ActionEvent event) {
    	PluginsRunningStatus plugToDelOutput = this.PluginOutputListView.getSelectionModel().getSelectedItem();
    	this.plugToAddOutputList.add(plugToDelOutput);
    	this.plugOutputList.remove(plugToDelOutput);
    }
    
    public void setControllerRef(PluginCommunicationController contro)
    {
    	this.controller = contro;
    }
}
