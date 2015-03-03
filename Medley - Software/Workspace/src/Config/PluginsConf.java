
package Config;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

public class PluginsConf extends AConfig
{
    private List<PluginConf>    _originalPluginsConf;
    private List<PluginConf>    _modifiedPluginsConf;
    
    public                  PluginsConf(String configpath, String fileName, String name)
    {
        super(configpath, fileName, name);
        this._originalPluginsConf = new ArrayList<PluginConf>();
        this._modifiedPluginsConf = new ArrayList<PluginConf>();
        this.fillIfEmpty();
        this.createListPlugins(this._originalPluginsConf);
        this.createListPlugins(this._modifiedPluginsConf);
        this.checkIfPluginExist(this._modifiedPluginsConf);
        this.checkIfPluginExist(this._originalPluginsConf);
    }
    
    @Override
    public void         fillIfEmpty()
    {
        try
        {
            this._root.getChildren("plugin");
        }
        catch (Exception e)
        {
            this._root = new Element("plugins");
            this._document = new Document(this._root);
        }
    }
    
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
    
    private boolean         createListPlugins(List<PluginConf> list)
    {
        List    elements = this._root.getChildren("plugin");
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
    
    public static String    booleanToString(boolean bl)
    {
        if (bl == true)
        {
            return ("true");
        }
        return ("false");
    }
       
    public List<PluginConf> getModifiedList()
    {
        return this._modifiedPluginsConf;
    }
    
    public PluginConf       getPluginConf(String name)
    {    	
    	for (PluginConf plugin : this._modifiedPluginsConf)
    	{
    		if (plugin.getName().equals(name))
    		{
    			return (plugin);
    		}
    	}
    	return null;
    }
   
    public boolean          delPluginConf(String name)
    {
        
        Iterator    ite;
        PluginConf  plugin;

        ite = this._modifiedPluginsConf.iterator();
        while (ite.hasNext())
        {
            plugin = (PluginConf)ite.next();
            if (plugin.getName().equals(name))
            {
                ite.remove();
                this._isModif = true;
                return (true);
            }
        }
        return (false);
    }
    
    public boolean          delPluginConf(PluginConf plug)
    {   
        Iterator    ite;
        PluginConf  plugin;

        ite = this._modifiedPluginsConf.iterator();
        while (ite.hasNext())
        {
            plugin = (PluginConf)ite.next();
            if (plugin.getName().equals(plug.getName()))
            {
                ite.remove();
                this._isModif = true;
                return (true);
            }
        }
        return (false);
    }
    
    public void             addPluginConf(PluginConf plug)
    {
        this._modifiedPluginsConf.add(plug);
        this._isModif = true;
    }
    
    public void             addPluginConf(String name)
    {
        PluginConf  plugin = new PluginConf();
        plugin.setName(name);
        this._modifiedPluginsConf.add(plugin);
        this._isModif = true;
    }
    
    public boolean          setValue(String name, String attributName, String value)
    {
        Iterator    ite;
        PluginConf  plugin;

        ite = this._modifiedPluginsConf.iterator();
        while (ite.hasNext())
        {
            plugin = (PluginConf)ite.next();
            if (plugin.getName().equals(name))
            {
                this.pluginSetValue(plugin, attributName, value);
                this._isModif = true;
                return (true);
            }
        }
        return (false);
    }
    
    public boolean          setValue(PluginConf plug, String attributName, String value)
    {
        Iterator    ite;
        PluginConf  plugin;

        ite = this._modifiedPluginsConf.iterator();
        while (ite.hasNext())
        {
            plugin = (PluginConf)ite.next();
            if (plugin.getName().equals(plug.getName()))
            {
                this.pluginSetValue(plugin, attributName, value);
                this._isModif = true;
                return (true);
            }
        }
        return (false);
    }
    
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
    
    @Override
    public boolean          saveConf()
    {
        try
        {
            Iterator    ite = this._modifiedPluginsConf.iterator();
            PluginConf      plugin;
        
            this._root.removeContent();
            while (ite.hasNext())
            {
                Element elem = new Element("plugin");
                plugin = (PluginConf)ite.next();
                this.fillElement(elem, plugin);
                this._root.addContent(elem);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error during saving plugins changes");
            System.out.println(e.getCause());
            return (false);
        }
        this.writeXML();
        this.createListPlugins(this._originalPluginsConf);
        this._isModif = false;
        return (true);
    }
}
