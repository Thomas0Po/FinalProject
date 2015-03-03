package plugincontract;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import Communication.MedleyTempo;
import javafx.scene.layout.Pane;

public abstract class APlugin extends Observable implements IPlugin, Observer {
	protected 	Pane 		mainComponent = null;
	private   	String  	path;
    private   	String  	name;
    private   	String  	iconName;
    private		String		description;
    private		String		tipsForCommunication;
	private   	boolean 	toHide;
    private   	boolean 	toRun;
    private   	boolean 	toCharge;
    private   	boolean 	toSend;
    private		Map<String, Method> mapReceiver;
    private		Map<String, PluginInfo> mapPluginInfo;
    protected   Object[]    arguments;
    
    //
    // INIT
    //
    
    public APlugin()
    {
    	this.toSend = false;
     	this.initMapReceiver();
    }
    
	public APlugin(APlugin plug)    
    {
    	super();
    	this.toSend = false;
        this.setPath(plug.getPath());
        this.setName(plug.getName());
        this.setIconName(plug.getIconName());
        this.setToHide(plug.isToHide());
        this.setToRun(plug.isToRun());
        this.setToCharge(plug.isToCharge());
        this.initMapReceiver();
 //       this.setLanguage(DEFAULT_LANGUAGE);
    }

	private void initMapReceiver()
	{
		this.mapReceiver = new HashMap<String, Method>();
    	try {
			this.mapReceiver.put("Communication.MedleyTempo", this.getClass().getMethod("receiveATempo", Observable.class, MedleyTempo.class));
			this.mapReceiver.put("java.io.File", this.getClass().getMethod("receiveAFile", Observable.class, File.class));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public final void initInfoPlugin()
	{
		PluginInfo plugDef = new PluginInfo(this.path);
		
		this.mapPluginInfo = new HashMap<String, PluginInfo>();
		this.mapPluginInfo.put("DEFAULT", plugDef);
		this.setLanguage("DEFAULT");
		try {
			@SuppressWarnings("resource")
			JarFile jar = new JarFile(this.path);
			Enumeration<JarEntry> jarEntrys = jar.entries();
			while(jarEntrys.hasMoreElements())
			{
				JarEntry entry = jarEntrys.nextElement();
				String fileName = entry.getName();
				if (fileName.startsWith("PluginInfo/"))
				{		
					InputStream is = jar.getInputStream(entry);
					Reader reader = new InputStreamReader(is, "UTF8");
					PluginInfo plugInfo = new PluginInfo(reader);
					if (plugInfo.getKey() != "ERROR")
						this.mapPluginInfo.put(plugInfo.getKey(), plugInfo);
				}
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    //
    // DROP SOMETHING ON THE PLUGIN
    //
    
	public final void dropObject(Object[] arg)
	 {
		 this.arguments = arg;
	     this.eventDropObject();
	 }

	//
	// GET VIEW
	//
	
	@Override
	public final Pane getMainComponent() {
		return this.mainComponent;
	}
	
	//
	// COMMUNICATION INTER-PLUG 
	//
	
    @Override
    public final void update(Observable arg0, Object arg1) 
    {
    	if (!this.mapReceiver.containsKey(arg1.getClass().getName()))
    	{
    		this.receiveAnObject(arg0, arg1);
    		return;
    	}
    	
		try {
				this.mapReceiver.get(arg1.getClass().getName()).invoke(this, arg0, Class.forName(arg1.getClass().getName()).cast(arg1));
		} catch (IllegalAccessException e) {
				e.printStackTrace();
		} catch (IllegalArgumentException e) {
				e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
    };
    
	@Override
	public final void sendATempo(MedleyTempo tempoToSend) {
		this.setChanged();
		this.notifyObservers(tempoToSend);
	}

	@Override
	public final void sendAFile(File file) {
		this.setChanged();
		this.notifyObservers(file);
	}

	@Override
	public final void sendAnObject(Object obj) {
		this.setChanged();
		this.notifyObservers(obj);
	}
	
	//
	// Modify LANGUAGE
	//
	
	public final void setLanguage(String key)
	{
		PluginInfo plugInfo;
		if (this.mapPluginInfo.containsKey(key))
			plugInfo = this.mapPluginInfo.get(key);	
		else
			plugInfo = this.mapPluginInfo.get("DEFAULT");
			
		this.name = plugInfo.getName();
		this.description = plugInfo.getDescription();
		this.tipsForCommunication = plugInfo.getTipsForCom();
		this.modifyLanguage(key);	
	}
	
	//
	// GETTERS AND SETTERS
	//
	
	public	final String  	getPath()                       {return this.path;	          	}
	public	final String 	getDescription() 				{return description;			}
	public	final String	getTipsForCommunication() 		{return tipsForCommunication;	}
	public  final String  	getName()                       {return name;               	}
	public  final String  	getIconName()                   {return iconName;           	}
	public  final boolean 	isToHide()                      {return toHide;             	}
	public  final boolean 	isToRun()                       {return toRun;              	}
	public  final boolean 	isToCharge()                    {return toCharge;           	}
	public 	final boolean	isToSend()						{return toSend;					}
	
	public  final void    	setPath(String path)            	{this.path      = path;     }
    public  final void    	setName(String name)            	{this.name      = name;     }
    public  final void    	setIconName(String iconName)    	{this.iconName  = iconName; }
    public  final void    	setToHide(boolean toHide)       	{this.toHide    = toHide;   }
    public  final void    	setToRun(boolean toRun)         	{this.toRun     = toRun;    }
    public  final void    	setToCharge(boolean toCharge)   	{this.toCharge  = toCharge; }
    public	final void		setToSend(boolean toSend)			{this.toSend	= toSend;	}
}
