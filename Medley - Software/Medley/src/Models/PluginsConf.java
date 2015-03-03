
package Models;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import plugincontract.APlugin;
import Models.PluginConf;

public class PluginsConf
{
	private Document document;
	private Element root;
    private List<PluginConf>    pluginsConf;
    
    /*
     * Constructor
     */
    public                  PluginsConf()
    {
        this.pluginsConf = new ArrayList<PluginConf>();
        this.fillIfEmpty();

        File f = new File("src/Ressources/Configs/plugins.xml");
        SAXBuilder sxb = new SAXBuilder();
		try {
			
			if (f == null)
			{
				//TODO : throw error
			}
			if (!f.exists()){
				f.createNewFile();
			}
				
			document = sxb.build(f);

		} catch (Exception e) {
			System.out.println("FILE ERROR : " + e);
		}

		// On initialise un nouvel élément racine avec l'élément racine du
		// document.
		root = document.getRootElement();
        
        
        this.createListPlugins(this.pluginsConf);
        this.checkIfPluginExist(this.pluginsConf);

        //this.fillPluginsList();
    }
    
    /*
     * Creates default content
     */
	public void         fillIfEmpty()
    {
        try
        {
            this.root.getChildren("plugin");
        }
        catch (Exception e)
        {
            this.root = new Element("plugins");
            this.document = new Document(this.root);
        }
    }
    
	/*
	 * Checks if the plugin exists
	 */
    private void            checkIfPluginExist(List<PluginConf> list)
    {
        File                file;
        List<String>        str = new ArrayList<String>();
        Iterator            ite = list.iterator();
        PluginConf          plug;
        
        while (ite.hasNext())
        {
            plug = (PluginConf)ite.next();
            file = new File(plug.getPath() + plug.getName() + ".jar");
            if (file.exists() == false)
            {
                str.add(plug.getName());
            }
        }
        String  name;
        ite = str.iterator();
        while (ite.hasNext())
        {
            name = (String)ite.next();
            this.delPluginConf(name);
        }
        this.saveConf();
    }
    
    /*
     * Sets the attributes of the plugin
     */
    private void            pluginSetValue(PluginConf plugin, String key, String value)
    {
        if (key.equals("name")){plugin.setName(value);}
        else if (key.equals("description")){plugin.setDescription(value);}
        else if (key.equals("path")){plugin.setPath(value);}
        else if (key.equals("iconName")){plugin.setIconName(value);}
        else if (key.equals("toCharge")){plugin.setToCharge(value);}
        else if (key.equals("toHide")){plugin.setToHide(value);}
        else if (key.equals("toRun")){plugin.setToRun(value);}
        if (key.equals("iconName"))
        {
            try
            {
                URL url = new URL(value);
                Image img = ImageIO.read(url);
                plugin.setImage(img);
            }
            catch (Exception e){}
        }
    }
    
    /*
     * Creates the list og PluginConf
     */
    private boolean         createListPlugins(List<PluginConf> list)
    {
        List<Element>    elements = this.root.getChildren("plugin");
        Iterator        i = elements.iterator();
        Element         courant;
        List<Attribute> attributeList;
        Iterator        j;
        Attribute       attribut;
        try
        {
            while(i.hasNext())
            {
                PluginConf plugin = new PluginConf();
                courant = (Element)i.next();
                attributeList = courant.getAttributes();
                j = attributeList.iterator();
                while (j.hasNext())
                {
                    attribut = (Attribute)j.next();
                    this.pluginSetValue(plugin, attribut.getName(), courant.getAttributeValue(attribut.getName()));
                }
                list.add(plugin);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error during setting values");
            System.out.println(e.getCause());
            return (false);
        }
        return (true);
    }
    
    /*
     * Converts boolean to String
     */
    public static String    booleanToString(boolean bl)
    {
        if (bl == true)
        {
            return ("true");
        }
        return ("false");
    }
    
    /*
     * Returns PluginConf for name
     */
    public PluginConf       getPluginConf(String name)
    {    	
    	for (PluginConf plugin : this.pluginsConf)
    	{
    		if (plugin.getName().equals(name))
    		{
    			return (plugin);
    		}
    	}
    	return null;
    }
   
    /*
     * Deletes PluginConf for name
     */
    public boolean          delPluginConf(String name)
    {
        
        Iterator    ite;
        PluginConf  plugin;

        ite = this.pluginsConf.iterator();
        while (ite.hasNext())
        {
            plugin = (PluginConf)ite.next();
            if (plugin.getName().equals(name))
            {
                ite.remove();
                return (true);
            }
        }
        return (false);
    }
    
    /*
     * Deletes PluginConf plug
     */
    public boolean          delPluginConf(PluginConf plug)
    {   
        Iterator    ite;
        PluginConf  plugin;

        ite = this.pluginsConf.iterator();
        while (ite.hasNext())
        {
            plugin = (PluginConf)ite.next();
            if (plugin.getName().equals(plug.getName()))
            {
                ite.remove();
                return (true);
            }
        }
        return (false);
    }
    
    /*
     * Adds PuginConf plug
     */
    public void             addPluginConf(PluginConf plug)
    {
        this.pluginsConf.add(plug);
    }
    
    /*
     * Adds PluginCOnf for name
     */
    public void             addPluginConf(String name)
    {
        PluginConf  plugin = new PluginConf();
        plugin.setName(name);
        this.pluginsConf.add(plugin);
    }
    
    /*
     * Sets value for plugin name
     */
    public boolean          setValue(String name, String attributName, String value)
    {
        Iterator    ite;
        PluginConf  plugin;

        ite = this.pluginsConf.iterator();
        while (ite.hasNext())
        {
            plugin = (PluginConf)ite.next();
            if (plugin.getName().equals(name))
            {
                this.pluginSetValue(plugin, attributName, value);
                return (true);
            }
        }
        return (false);
    }
    
    /*
     * Sets value for plugin plug
     */
    public boolean          setValue(PluginConf plug, String attributName, String value)
    {
        Iterator    ite;
        PluginConf  plugin;

        ite = this.pluginsConf.iterator();
        while (ite.hasNext())
        {
            plugin = (PluginConf)ite.next();
            if (plugin.getName().equals(plug.getName()))
            {
                this.pluginSetValue(plugin, attributName, value);
                return (true);
            }
        }
        return (false);
    }
    
    /*
     * Fills and Writes elem on xml file with plugin
     */
    private void            fillElement(Element elem, PluginConf plugin)
    {
        elem.setAttribute("name", plugin.getName());
        elem.setAttribute("description", plugin.getDescription());
        elem.setAttribute("path", plugin.getPath());
        elem.setAttribute("iconName", plugin.getIconName());
        elem.setAttribute("toCharge", booleanToString(plugin.isToCharge()));
        elem.setAttribute("toHide", booleanToString(plugin.isToHide()));
        elem.setAttribute("toRun", booleanToString(plugin.isToRun()));
    }

    /*
     * Saves the configuration file
     */
    public boolean          saveConf()
    {
        try
        {
            Iterator    ite = this.pluginsConf.iterator();
            PluginConf      plugin;
        
            this.root.removeContent();
            while (ite.hasNext())
            {
                Element elem = new Element("plugin");
                plugin = (PluginConf)ite.next();
                this.fillElement(elem, plugin);
                this.root.addContent(elem);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error during saving plugins changes");
            System.out.println(e.getCause());
            return (false);
        }
        this.writeXML();
        return (true);
    }
    
    /*
     * Writes in the configuration file
     */
    private boolean  writeXML()
    {
        try
        {
            if (new File("src/Ressources/Configs/plugins.xml").exists() == false)
            {
                new File("src/Ressources/Configs/plugins.xml").createNewFile();
            }
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(this.document, new FileOutputStream("src/Ressources/Configs/plugins.xml"));
        }
        catch (Exception e)
        {
            System.out.println("Error during writing XML file, can't write '" + "src/Ressources/Configs/plugins.xml" + "'");
            System.out.println(e.getCause());
            return (false);
        }
        return (true);
    }
    
    /*
     * 
     */
    /*private APlugin loadPlugin(Class myClass, PluginConf conf)
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
    }*/
    
    /*
     * Returns List of PluginConf
     */
    public List<PluginConf> getPluginsConf() {
		return pluginsConf;
	}
}
