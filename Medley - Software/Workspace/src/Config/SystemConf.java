
package Config;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;


public class SystemConf extends AConfig
{

    private String      _path;
    private String      _version;
    private String      _discoveryActivated;
    
    public SystemConf(String configpath, String fileName, String name)
    {
        super(configpath, fileName, name);
        this.fillIfEmpty();
        this.createPath();
        this.createVersion();
        this.getDiscovery();
    }
    
    @Override
    public void                         fillIfEmpty()
    {
       try
        {
            this._root.getChildren("install");
        }
        catch (Exception e)
        {
            this._root = new Element("system");
            this._document = new Document(this._root);
            this.initializeSystemProperties();
        } 
    }
    
    private void                        initializeSystemProperties()
    {
        this._path = new File(".").getAbsolutePath();
        this._version = "1.0";
        this._discoveryActivated = "true";
    }
    
    private void                        createPath()
    {
        try
        {
            List                elements = this._root.getChildren("install");
            Iterator            i = elements.iterator();
            Element             elem;
            List<Attribute>     attributeList;
            Attribute           attribut;
            Iterator            j;

            while (i.hasNext())
            {
                elem = (Element)i.next();
                attributeList = elem.getAttributes();
                j = attributeList.iterator();
                while (j.hasNext())
                {
                    attribut = (Attribute)j.next();
                    if (attribut.getName().equals("path"))
                    {
                        this._path = attribut.getValue();
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error during getting install path");
            System.out.println(e.getCause());
        }
    }
    
    private void                        createVersion()
    {
        try
        {
            List                elements = this._root.getChildren("version");
            Iterator            i = elements.iterator();
            Element             elem;
            List<Attribute>     attributeList;
            Attribute           attribut;
            Iterator            j;

            while (i.hasNext())
            {
                elem = (Element)i.next();
                attributeList = elem.getAttributes();
                j = attributeList.iterator();
                while (j.hasNext())
                {
                    attribut = (Attribute)j.next();
                    if (attribut.getName().equals("numero"))
                    {
                        this._version = attribut.getValue();
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error during getting paths");
            System.out.println(e.getCause());
        }
    }
    
    private void                            getDiscovery()
    {
        try
        {
            List                elements = this._root.getChildren("discovery");
            Iterator            i = elements.iterator();
            Element             elem;
            List<Attribute>     attributeList;
            Attribute           attribut;
            Iterator            j;

            while (i.hasNext())
            {
                elem = (Element)i.next();
                attributeList = elem.getAttributes();
                j = attributeList.iterator();
                while (j.hasNext())
                {
                    attribut = (Attribute)j.next();
                    if (attribut.getName().equals("activated"))
                    {
                        this._discoveryActivated = attribut.getValue();
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error during getting paths");
            System.out.println(e.getCause());
        }
    }
    
    public void                                 setPath(String path)
    {
        this._path = path;
    }
    
    public void                                 setVersion(String version)
    {
        this._version = version;
    }
    
    public void                                 setDiscoveryActivated(String str)
    {
        this._discoveryActivated = str;
    }
    
    public boolean                              getDiscoveryActivated()
    {
        if (this._discoveryActivated.equals("true"))
        {
            return (true);
        }
        return (false);
    }
    
    public String                               getPath()
    {
        return (this._path);
    }
    
    public String                               getVersion()
    {
        return (this._version);
    }
    
    @Override
    public boolean                              saveConf()
    {
       try
        {
            Element                     extensions = new Element("system");
            Element                     install = new Element("install");
            Element                     version = new Element("version");
            Element                     discovery = new Element("discovery");
                    
            this._root.removeContent();
            install.setAttribute("path", this._path);
            version.setAttribute("numero", this._version);
            discovery.setAttribute("activated", this._discoveryActivated);
            this._root.addContent(install);
            this._root.addContent(version);
            this._root.addContent(discovery);
        }
        catch (Exception e)
        {
            System.out.println("Error during saving system changes");
            return (false);
        }
        this.writeXML();
        this.createPath();
        this.createVersion();
        this._isModif = false;
        return (true);
    }
}
