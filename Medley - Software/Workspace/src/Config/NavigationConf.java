

package Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

public class NavigationConf extends AConfig
{
    private List<String>                                  _originalPaths;
    private List<String>                                  _modifiedPaths;
    private Map<String, Map<String, List<String> > >      _originalExtensions;
    private Map<String, Map<String, List<String> > >      _modifiedExtensions;
    
    public NavigationConf(String configpath, String fileName, String name)
    {
        super(configpath, fileName, name);
        this._originalPaths = new ArrayList<String>();
        this._originalExtensions = new HashMap<String, Map<String, List<String>>>();
        this._modifiedPaths = new ArrayList<String>();
        this._modifiedExtensions = new HashMap<String, Map<String, List<String>>>();
        this.fillIfEmpty();
        this.createPaths(_originalPaths);
        this.createPaths(_modifiedPaths);
        this.createExtensions(_originalExtensions);
        this.createExtensions(_modifiedExtensions);
    }
    
    @Override
    public void                         fillIfEmpty()
    {
        try
        {
            this._root.getChildren("path");
        }
        catch (Exception e)
        {
            this._root = new Element("navigation");
            this._document = new Document(this._root);
        }
    }
    
    
    private boolean                     createPaths(List<String> paths)
    {
        try
        {
            List                elements = this._root.getChildren("path");
            Iterator            i = elements.iterator();
            Element             path;
            List<Attribute>     attributeList;
            Iterator            j;
            Attribute           attribut;

            while (i.hasNext())
            {
                path = (Element)i.next();
                attributeList = path.getAttributes();
                j = attributeList.iterator();
                while (j.hasNext())
                {
                    attribut = (Attribute)j.next();
                    if (attribut.getName().equals("path"))
                    {
                        paths.add(path.getAttributeValue("path"));
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error during getting paths");
            System.out.println(e.getCause());
            return (false);
        }     
        return (true);
    }
    
    private List<String>                cutExtensions(String extensions)
    {
        List<String>                    list = new ArrayList<String>();
        String[]                        tab;
        int                             nb;
        
        nb = 0;
        tab = extensions.split("[|]");
        while (nb < tab.length)
        {
            list.add(tab[nb]);
            ++nb;
        }
        return (list);
    }
    
    private boolean                     createExtensions(Map<String, Map<String, List<String> >> map)
    {
        try
        {
            List                            elements = this._root.getChildren("extensions");
            Element                         extensions;
            List                            extension;
            Element                         ext;
            Iterator                        i = elements.iterator();
            Iterator                        j;
        
            if (i.hasNext())
            {
                extensions = (Element)i.next();
                extension = extensions.getChildren("extension");
                j = extension.iterator();
                while (j.hasNext())
                {
                    Map<String, List<String> > newMap = new HashMap<String, List<String>>();
                    ext = (Element)j.next();
                    newMap.put(ext.getAttributeValue("type"), cutExtensions(ext.getText()));
                    map.put(ext.getAttributeValue("description"), newMap);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error during getting extensions");
            return (false);
        }
        return (true);
    }
    
    public List<String>                 getPaths()
    {
        return this._modifiedPaths;
    }
    
    public void                         addPath(String path)
    {
        this._modifiedPaths.add(path);
        this._isModif = true;
    }
    
    public void                         delAllPath()
    {
        this._modifiedPaths.clear();
    }
    
    public boolean                      delPath(String pathName)
    {
        Iterator                        ite;
        String                          path;
        
        ite = this._modifiedPaths.iterator();
        while (ite.hasNext())
        {
            path = (String)ite.next();
            if (path.equals(pathName))
            {
                this._isModif = true;
                ite.remove();
                return (true);
            }
        }
        return (false);
    }
    
    public Map<String, Map<String, List<String>>>    getExtensions()
    {
        return this._modifiedExtensions;
    }
    
    public void                         addExtension(String description, String type, List<String> extensions)
    {
        Map<String, List<String>>   newMap = new HashMap<String, List<String>>();
        newMap.put(type, extensions);
        this._modifiedExtensions.put(description, newMap);
        this._isModif = true;
    }
    
    public void                         delExtension(String description)
    {
        this._modifiedExtensions.remove(description);
        this._isModif = true;
    }
    
    private String                      makeExtensions(List<String> list)
    {
        String                          str = new String();
        Iterator                        ite = list.iterator();
        
        while (ite.hasNext())
        {
            str += (String)ite.next();
            if (ite.hasNext() == true)
            {
                str += "|";
            }
        }
        return (str);
    }
    
    @Override
    public boolean                      saveConf()
    {
        try
        {
            Iterator	                ite = this._modifiedPaths.iterator();
            Element                     extensions = new Element("extensions");
            Map<String, List<String>>   map;
        
            this._root.removeContent();
            while (ite.hasNext())
            {
                Element path = new Element("path");
                path.setAttribute("path", (String)ite.next());
                this._root.addContent(path);
            }
            this._root.addContent(extensions);
            Set keys = this._modifiedExtensions.keySet();
            ite = keys.iterator();
            while (ite.hasNext())
            {
                Element extension = new Element("extension");
                String  description = (String)ite.next();
                extension.setAttribute("description", description);
                map = this._modifiedExtensions.get(description);
                Set keys2 = map.keySet();
                Iterator ite2 = keys2.iterator();
                String type = (String)ite2.next();
                extension.setAttribute("type", type);
                extension.setText(makeExtensions(map.get(type)));
                extensions.addContent(extension);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error during saving navigation changes");
            return (false);
        }
        this.writeXML();
        this.createPaths(this._originalPaths);
        this.createExtensions(this._originalExtensions);
        this._isModif = false;
        return (true);
    }
}
