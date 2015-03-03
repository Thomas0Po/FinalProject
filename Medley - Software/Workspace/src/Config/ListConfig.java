
package Config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListConfig
{
   private List<AConfig>        _listConfig;
   private static ListConfig    _instance;
   
    public static ListConfig getInstance()
    {
        if (_instance == null)
        {
            _instance = new ListConfig();
        }
        return _instance;
    }
   
   private           ListConfig()
   {
       this._listConfig = new ArrayList<AConfig>();
       //this._listConfig.add(new SystemConf("./src/ressources/config/", "system.xml", "System"));
       //this._listConfig.add(new ShortcutsConf("./src/ressources/config/", "shortcuts.xml", "Shortcuts"));
       //this._listConfig.add(new NavigationConf("./src/ressources/config/", "navigation.xml", "Navigation"));
       this._listConfig.add(new PluginsConf("./src/Ressources/Configs", "plugins.xml", "Plugins"));
   }
   
   public AConfig   getConfig(String name)
   {
       Iterator     ite;
       AConfig      config;
       
       ite = this._listConfig.iterator();
       while (ite.hasNext())
       {
           config = (AConfig)ite.next();
           if (config.getName().equals(name))
           {
               return (config);
           }
       }
       return (null);
   }
   
   public boolean   checkModif()
   {
       Iterator     ite;
       AConfig      config;
       
       ite = this._listConfig.iterator();
       while (ite.hasNext())
       {
           config = (AConfig)ite.next();
           if (config.isModif() == true)
           {
               return (true);
           }
       }
       return (false);
   }
   
   public void      saveModif()
   {
       Iterator     ite;
       AConfig      config;
       
       ite = this._listConfig.iterator();
       while (ite.hasNext())
       {
           config = (AConfig)ite.next();
           config.saveConf();
       }
   }
}
