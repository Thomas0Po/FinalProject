
package Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

public class ShortcutsConf extends AConfig
{
    
    private Map<String, List<String> >   _shortcuts;
    
    public ShortcutsConf(String configpath, String fileName, String name)
    {
        super(configpath, fileName, name);
        this._shortcuts = new HashMap<String, List<String> >();
        this.fillIfEmpty();
        this.createShortcuts();
    }
    
    @Override
    public void                         fillIfEmpty()
    {
       try
        {
            this._root.getChildren("combination");
        }
        catch (Exception e)
        {
            this._root = new Element("shortcuts");
            this._document = new Document(this._root);
            this.initializeDefaultsShortcuts();
        } 
    }
    
    private void                        initializeDefaultsShortcuts()
    {
        this._shortcuts.put("delPlugin", Arrays.asList("ctrl", "d"));
        this._shortcuts.put("shortcuts", Arrays.asList("ctrl", "s"));
        this._shortcuts.put("openWindow", Arrays.asList("ctrl", "o"));
        this._shortcuts.put("openTab", Arrays.asList("ctrl", "t"));
        this._shortcuts.put("addPlugin", Arrays.asList("ctrl", "a"));
        this._shortcuts.put("closeTab", Arrays.asList("ctrl", "w"));
        this._shortcuts.put("quit", Arrays.asList("ctrl", "q"));
        this._shortcuts.put("preferences", Arrays.asList("ctrl", "p"));
    }
 
    public void                         createShortcuts()
    {
         try
        {
            List                    elements = this._root.getChildren("combination");
            Iterator                i = elements.iterator();
            Element                 combination;
            List<Attribute>         attributeList;
            Iterator                j;
            Attribute               attribut;
            Map<String, String>     map = new HashMap<String, String>();
            String                  action = null;
            String                  first = null;
            String                  second = null;

            while (i.hasNext())
            {
                List<String>    list = new ArrayList<String>();
                combination = (Element)i.next();
                attributeList = combination.getAttributes();
                j = attributeList.iterator();
                while (j.hasNext())
                {
                    attribut = (Attribute)j.next();
                    if (attribut.getName().equals("action")){action = attribut.getValue();}
                    else if (attribut.getName().equals("first")){first = attribut.getValue();}
                    else if (attribut.getName().equals("second")){second = attribut.getValue();}
                }
                list.add(first);
                list.add(second);
                this._shortcuts.put(action, list);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error during getting paths");
            System.out.println(e.getCause());
        }
    }
    
    public List<String>                 getShortcuts(String key)
    {
        return (this._shortcuts.get(key));
    }
    
    public Map<String, List<String> >   getMap()
    {
        return this._shortcuts;
    }
    
    public void                         setShorcuts(String key, String first, String second)
    {
        List<String>                    list = new ArrayList<String>();
        
        list.add(first);
        list.add(second);
        this._shortcuts.put(key, list);
        this._isModif = true;
    }
    
    @Override
    public boolean                      saveConf()
    {
        try
        {
            Set                         keys = this._shortcuts.keySet();
            Iterator                    ite = keys.iterator();
            String                      key;
            List<String>                list;
        
            this._root.removeContent();
            while (ite.hasNext())
            {
                key = (String)ite.next();
                Element elem = new Element("combination");
                elem.setAttribute("action", key);
                list = this._shortcuts.get(key);
                elem.setAttribute("first", list.get(0));
                elem.setAttribute("second", list.get(1));
                this._root.addContent(elem);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error during saving shortcuts changes");
            System.out.println(e.getCause());
            return (false);
        }
        this.writeXML();
        this._isModif = false;
        return (true);
    }  
}
