
package Dao;

import Helpers.ConvertHelper;
import Condition.ICondition;
import Config.PluginConf;
import Config.PluginsConf;
import Factory.FactoryPlug;

import java.io.File;
import java.util.Collection;

import plugincontract.APlugin;


public class DaoPlug extends ADao<APlugin>
{
    protected PluginsConf   config;
    protected PluginConf    plugin = new PluginConf();
    
    public DaoPlug()                                        {super(new FactoryPlug());                  }
    public DaoPlug(String fileName)                         {super(new FactoryPlug(fileName));          }
    public DaoPlug(File file)                               {super(new FactoryPlug(file));              }
    public DaoPlug(String fileName, PluginsConf config)     {super(new FactoryPlug(fileName, config));  }
    public DaoPlug(File file, PluginsConf config)           {super(new FactoryPlug(file, config));      }

    public DaoPlug(PluginsConf config)                      {super(new FactoryPlug(config));
    this.config = config;}
    
    private void loadPluginConf(APlugin obj, PluginConf plugin)
    {
        plugin.setName(obj.getName());
        plugin.setIconName(obj.getIconName());
        plugin.setPath(obj.getPath());
        plugin.setToHide(ConvertHelper.booleanToString(obj.isToHide()));
        plugin.setToRun(ConvertHelper.booleanToString(obj.isToRun()));
        plugin.setToCharge(ConvertHelper.booleanToString(obj.isToCharge()));
    }
    
    @Override
    public void add(APlugin obj) 
    {
        super.add(obj);
        loadPluginConf(obj, plugin);
        if (config != null) config.addPluginConf(plugin);
    }

    @Override
    public void delete(APlugin obj) 
    {
        super.delete(obj);
        loadPluginConf(obj, plugin);
        if (config != null) config.delPluginConf(plugin);
    }

    @Override
    public void update(APlugin oldObj, APlugin newObj) 
    {
        super.update(oldObj, newObj);
        loadPluginConf(oldObj, plugin);
        if (config != null) 
        {
            config.setValue(plugin, "path",     newObj.getPath());
            config.setValue(plugin, "name",     newObj.getName());
            config.setValue(plugin, "iconName", newObj.getIconName());
            config.setValue(plugin, "toCharge", PluginsConf.booleanToString(newObj.isToCharge()));
            config.setValue(plugin, "toHide",   PluginsConf.booleanToString(newObj.isToHide()));
            config.setValue(plugin, "toRun",    PluginsConf.booleanToString(newObj.isToRun()));              
        }
    }
	@Override
	public APlugin single(ICondition<APlugin> condition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public APlugin first(ICondition<APlugin> condition) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
    
}
