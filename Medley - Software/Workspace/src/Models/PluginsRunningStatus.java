package Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;

import Helpers.PluginHelper;
import javafx.scene.layout.Pane;
import plugincontract.APlugin;

public class PluginsRunningStatus {
	private APlugin		  		plugin;
	private List<Integer>		inputPlugs;
	private List<Integer>		outputPlugs;
	private static int			id = 0;
	private int					idPlugin;

	
	//TODO SET LANGUAGE
	public PluginsRunningStatus(APlugin plug) {
		this.plugin = PluginHelper.newInstanceOf(plug);
		System.out.println("Plugin Running Status " + this.plugin.getPath());
		this.plugin.initInfoPlugin();
		this.plugin.initialiseComponent();
		this.inputPlugs = new ArrayList<Integer>();
		this.outputPlugs = new ArrayList<Integer>();
		idPlugin = id;
		id += 1;
	}

	public int getId() {
		return this.idPlugin;
	}
	
	public Pane getPluginView()
	{
		return this.plugin.getMainComponent();
	}
	
	public List<Integer> getInputPlugs() {
		return inputPlugs;
	}

	public List<Integer> getOutputPlugs() {
		return outputPlugs;
	}	
	
	public void stopPlugin()
	{
		this.plugin.stop();
	}
	
	public void setLanguage(String key)
	{
		System.out.println("SET LANGUAGE PRS");
		this.plugin.setLanguage(key);
	}
	
	public String getPluginIcon()
	{
		return (this.plugin.getIconName());
	}
	
	public void dropObject(Object[] obj)
	{
		this.plugin.dropObject(obj);
	}
	
	//#############################//
	//		   Plugin INFO		   //
	//#############################//
	
	public String getPluginName()
	{
		return (this.plugin.getName());
	}
	
	public String getPluginDescription()
	{
		return (this.plugin.getDescription());
	}
	
	public String getPluginTips()
	{
		return (this.plugin.getTipsForCommunication());
	}
	
	//#############################//
	//	   Plugin Communication    //
	//#############################//
	
	/*
	 * Check if the plugin in on the list of input Plugin
	 */
	public boolean isInInputPlugs(int id)
	{
		System.out.println("IS IN INPUT ARG");
		for (Integer i : this.inputPlugs)
		{
			System.out.println("je compare l'id : " + id + " avec " + i);
			if (id == i)
			{
				System.out.println("CA CORRESPOND BORDEL");
				return (true);
			}
			else
				System.out.println("NON");
		}
		return (false);
	}
	
	/**
	 * Check if the plugin id is in the list of output plugins
	 */
	public boolean isInOutputPlugs(int id)
	{
		System.out.println("IS IN OUTPUT ARG");
		for (Integer i : this.outputPlugs)
		{
			System.out.println("je compare l'id : " + id + " avec " + i);
			if (id == i)
			{
				System.out.println("CA CORRESPOND BORDEL");
				return (true);
			}
			else
				System.out.println("NON");
			
		}
		return (false);
	}
	
	/**
	 * @return the observer of the plugin
	 */
	public Observer getPluginObserver()
	{
		return this.plugin;
	}
	
	public void addInputPlug(PluginsRunningStatus plug)
	{
		if (this.inputPlugs.size() == 0)
			this.plugin.setToSend(true);
		this.inputPlugs.add(plug.getId());
		plug.addOutputPlugForInputOfAnAnotherPlugin(this);
	}

	private void addInputPlugForOutputOfAnAnotherPlugin(PluginsRunningStatus plug)
	{
		this.inputPlugs.add(plug.getId());
	}
	
	public void addOutputPlug(PluginsRunningStatus plug)
	{
		this.outputPlugs.add(plug.getId());
		this.plugin.addObserver(plug.getPluginObserver());
		plug.addInputPlugForOutputOfAnAnotherPlugin(this);
	}
	
	private void addOutputPlugForInputOfAnAnotherPlugin(PluginsRunningStatus plug)
	{
		this.outputPlugs.add(plug.getId());
		this.plugin.addObserver(plug.getPluginObserver());
	}
	
	public void deleteCommunicationInput(PluginsRunningStatus plug)
	{
		for (Iterator<Integer> it = inputPlugs.listIterator(); it.hasNext(); ) {
		    Integer id = it.next();
		    if (id == plug.getId()) {
		        it.remove();
		        plug.deleteComOutputFromAnAnotherPlug(this);
		        break;
		    }
		}
	}
	
	private void deleteComOutputFromAnAnotherPlug(PluginsRunningStatus plug)
	{
		for (Iterator<Integer> it = outputPlugs.listIterator(); it.hasNext(); ) {
		    Integer id = it.next();
		    if (id == plug.getId()) {
		        it.remove();
		        this.plugin.deleteObserver(plug.getPluginObserver());
		    }
		}
	}
	
	public void deleteCommunicationOutput(PluginsRunningStatus plug)
	{
		for (Iterator<Integer> it = outputPlugs.listIterator(); it.hasNext(); ) {
		    Integer id = it.next();
		    if (id == plug.getId()) {
		        it.remove();
		        this.plugin.deleteObserver(plug.getPluginObserver());
		        plug.deleteComInputFromAnAnotherPlug(this);
		    }
		}
	}
	
	private void deleteComInputFromAnAnotherPlug(PluginsRunningStatus plug)
	{
		for (Iterator<Integer> it = inputPlugs.listIterator(); it.hasNext(); ) {
		    Integer id = it.next();
		    if (id == plug.getId()) {
		        it.remove();
		        break;
		    }
		}
	}
	
	public void resetPluginsCommunication()
	{
		this.plugin.deleteObservers();
		this.inputPlugs.clear();
		this.outputPlugs.clear();
		this.plugin.setToSend(false);
	}
	
	public void showCommunication()
	{
		System.out.println("---------------------------");
		System.out.println("PLUGIN : " + this.getPluginName() + " ID :" + this.getId());
		System.out.println("INPUT :");
		for (Integer in : this.inputPlugs)
			System.out.println(in);
		System.out.println("OUTPUT :");
		for (Integer out : this.outputPlugs)
			System.out.println(out);
		System.out.println("---------------------------");
	}
	
	@Override
	public String toString() {
		return this.getPluginName();
	}
}
//TODO Close Plugin virer le plug des autre
//TODO boolean isToSend a set

