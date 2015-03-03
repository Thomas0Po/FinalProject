package Helpers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.jar.JarFile;

import plugincontract.APlugin;
import Models.PluginConf;
import Models.PluginsConf;

public class PluginLoader {

	private PluginsConf plugConf;
	private Collection collection;
	private File file;

	/*
	 * Constructor
	 */
	public PluginLoader(PluginsConf pluginsConf){

		this.plugConf = pluginsConf;
	}

	/*
	 * Creates the list of plugins
	 */
	public Collection<APlugin> buildAll()
	{
		Collection<APlugin> Plugins  = new ArrayList<APlugin>();
		for(PluginConf plugConf : this.plugConf.getPluginsConf())
		{
			this.file = new File(plugConf.getPath() + plugConf.getName() + ".jar");
			Plugins.addAll(this.buildAllInFile(file));
		}

		return Plugins;
	}

	/*
	 * Load the jar Plugins
	 */
	private Collection<APlugin>   buildAllInFile(File file)
	{
		try
		{
			JarLoader jarLoader             = new JarLoader(new URL("jar","","file:"+file.getAbsolutePath()+"!/"));
			JarFile currentJarFile          = new JarFile(file);
			collection                     = new ArrayList<APlugin>();

			jarLoader.load();

			for(Enumeration elem = currentJarFile.entries(); elem.hasMoreElements();)
			{
				String elemName             = elem.nextElement().toString();

				if (elemName.endsWith(".class"))
				{       
					Class   myClass         = null;

					if (elemName.lastIndexOf("/") != -1)
					{
						String packageName  = elemName.substring(0, elemName.lastIndexOf("/"));
						String className    = elemName.substring(elemName.lastIndexOf("/") + 1);

						myClass             = jarLoader.getClass(packageName.replace("/", ".")+ "." + className.split("\\.")[0]);
					}
					else myClass            = jarLoader.getClass(elemName.split("\\.")[0]);

					if (isValid(myClass) == true)    addToCollection(myClass);
				}
			}

			return collection;
		}
		catch(MalformedURLException e)  {System.out.println("[Factory : buildAllInFile : MalformedURLException]\t"  + e.toString());}
		catch(Exception e)              {System.out.println("[Factory : buildAllInFile : Exception]\t"              + e.toString());}
		return new ArrayList<APlugin>();
	}

	/*
	 * Gets plugins from config
	 */
	public Collection<APlugin> buildAll(PluginsConf config)
	{
		this.plugConf = config;
		Collection<APlugin> Plugins  = new ArrayList<APlugin>();
		for(PluginConf plugConf : config.getPluginsConf())
		{
			file = new File(plugConf.getPath() + plugConf.getName() + ".jar");
			Plugins.addAll(this.buildAllInFile(file));
		}

		return Plugins;
	}

	/*
	 * Checks if the plugin is valid
	 */
	protected boolean   isValid(Class myClass)  
	{
		if (APlugin.class.isAssignableFrom(myClass) == true)
			try                 
		{
				return (this.plugConf).getPluginConf(StringHelper.getFileName(this.file.getName())).isToCharge(); 
		}
		catch(Exception e)  {System.out.println("[Factory : isValid : Exception]\t" + e.toString());                                                }
		return false;
	} 

	/*
	 * Loads the plugin
	 */
	private APlugin loadPlugin(Class myClass, PluginConf conf)
	{
		try
		{
			APlugin     plugin  = (APlugin)myClass.newInstance();
			
			plugin.setPath(conf.getPath() + conf.getName() + ".jar");
			plugin.setName(conf.getName());
			plugin.setIconName(conf.getIconName());
			plugin.setToHide(conf.isToHide());
			plugin.setToRun(conf.isToRun());
			plugin.initInfoPlugin();
			return plugin;
		}
		catch(Exception e) {System.out.println("[Factory : loadPlugin : Exception]\t" + e.toString());}

		return null;
	}

	/*
	 * Adds the plugin to list
	 */
	protected void addToCollection(Class myClass) 
	{
		this.collection.add(loadPlugin(myClass, 
				((PluginsConf)this.plugConf).getPluginConf(StringHelper.getFileName(this.file.getName()))));
	}

}
