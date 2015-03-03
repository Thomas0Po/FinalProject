/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Config.PluginConf;
import Config.PluginsConf;
import Helpers.StringHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.omg.CORBA.StringHolder;

import plugincontract.APlugin;

/**
 *
 * @author thoma_000
 * 
 *  Ajout du gestionnaire de config.
 *  la class APlugin est completé par les info contenu dans le plugInfo.xml
 *  La factory de plug possède un fichier xml qui lui permet de générer des objets de facon plus fine.
 */

public class FactoryPlug extends FactoryClass<APlugin>
{

    public  FactoryPlug()                                   {super();               }
    public  FactoryPlug(String fileName)                    {super(fileName);       }
    public  FactoryPlug(File file)                          {super(file);           }
    public  FactoryPlug(String fileName, PluginsConf conf)  {super(fileName, conf); }
    public  FactoryPlug(File file, PluginsConf conf)        {super(file, conf);     }
    
    public  FactoryPlug(PluginsConf conf)                   {super(); this.config = conf;}
    
    @Override
    public Collection<APlugin> buildAll()
    {
        Collection<APlugin> Plugins  = new ArrayList<APlugin>();
        for(PluginConf plugConf : ((PluginsConf)this.config).getModifiedList())
        {
            this.file = new File(plugConf.getPath() + plugConf.getName() + ".jar");
            Plugins.addAll(this.buildAllInFile(file));
        }
        
        return Plugins;
    }
    
    
    protected boolean   isValid(Class myClass)  
    {
        if (APlugin.class.isAssignableFrom(myClass) == true)
            try                 
            {
                return ((PluginsConf)this.config).getPluginConf(StringHelper.getFileName(this.file.getName())).isToCharge(); 
            }
            catch(Exception e)  {System.out.println("[Factory : isValid : Exception]\t" + e.toString());                                                }
        return false;
    } 

    @Override
    protected void addToCollection(Class myClass) 
    {
        this.collection.add(loadPlugin(myClass, 
                           ((PluginsConf)this.config).getPluginConf(StringHelper.getFileName(this.file.getName()))));
    }
    
    private APlugin loadPlugin(Class myClass, PluginConf conf)
    {
        try
        {
            APlugin     plugin  = (APlugin)myClass.newInstance();
         
            plugin.setPath(conf.getPath());
            plugin.setName(conf.getName());
            plugin.setIconName(conf.getIconName());
            plugin.setToHide(conf.isToHide());
            plugin.setToRun(conf.isToRun());
            return plugin;
        }
        catch(Exception e) {System.out.println("[Factory : loadPlugin : Exception]\t" + e.toString());}
        
        return null;
    }

}
