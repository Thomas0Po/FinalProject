package Factory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import plugincontract.APlugin;
import Abstractions.Controllers.ANavigationController;
import Abstractions.Controllers.APluginListController;
import Abstractions.Controllers.AWorkspaceController;
import Abstractions.Controllers.INavigationController;
import Abstractions.Controllers.IPluginListController;
import Abstractions.Controllers.IWorkspaceController;
import Helpers.JarLoader;

/**
 * 
 * This class is the module loader of Medley
 *
 */
public class FactoryMedleyPart {
	INavigationController navig = null;
	IWorkspaceController workspace = null;
	IPluginListController plugList = null;
	
	public void init()
	{
		this.loadNavigationController();
		this.loadPlugListController();
		this.loadWorkspaceController();
	}
	
	/**
	 * load the navigation part
	 * @return true on succeed or false
	 */
	private boolean loadNavigationController()
	{
		try
        {
		File file = new File("./Navigation.jar");
		System.out.println("LOAD NAVIGATION");
		JarLoader jarLoader             = new JarLoader(new URL("jar","","file:"+ file.getAbsolutePath()+"!/"));
        JarFile currentJarFile          = new JarFile(file);

        jarLoader.load();

        for(Enumeration<JarEntry> elem = currentJarFile.entries(); elem.hasMoreElements();)
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
                    //System.out.println("CLASS FIND 1: " + packageName.replace("/", ".")+ "." + className.split("\\.")[0] );
                }
                else
                {
                	myClass            = jarLoader.getClass(elemName.split("\\.")[0]);
                	//System.out.println("CLASS FIND 2: " + elemName.split("\\.")[0]);
                }
                
                if (ANavigationController.class.isAssignableFrom(myClass) == true)
                	{
                		try
                		{
                			this.navig = (ANavigationController)myClass.newInstance();
                			return true;
                		}
                		catch (Exception e)
                		{
                			System.out.println("Class load failed : " + myClass.getName());
                		}
                	}
            }
        }
        
       }
    catch(MalformedURLException e)  {System.out.println("[Factory : buildAllInFile : MalformedURLException]\t"  + e.toString());}
    catch(Exception e)              {System.out.println("[Factory : buildAllInFile : Exception]\t"              + e.toString());}
    return false;
	
}
	/**
	 * load the workspace part
	 * @return true on succeed or false
	 */
	private boolean loadWorkspaceController()
	{
		try
        {
		File file = new File("./Workspace.jar");
		System.out.println("LOAD WORKSPACE");
		JarLoader jarLoader             = new JarLoader(new URL("jar","","file:"+ file.getAbsolutePath()+"!/"));
        JarFile currentJarFile          = new JarFile(file);

        jarLoader.load();

        for(Enumeration<JarEntry> elem = currentJarFile.entries(); elem.hasMoreElements();)
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
                    //System.out.println("CLASS FIND 1: " + packageName.replace("/", ".")+ "." + className.split("\\.")[0] );
                }
                else
                {
                	myClass            = jarLoader.getClass(elemName.split("\\.")[0]);
                	//System.out.println("CLASS FIND 2: " + elemName.split("\\.")[0]);
                }
                
                if (AWorkspaceController.class.isAssignableFrom(myClass) == true)
                	{
                		try
                		{
                			this.workspace = (AWorkspaceController)myClass.newInstance();
                			return true;
                		}
                		catch (Exception e)
                		{
                			System.out.println("Class load failed : " + myClass.getName());
                		}
                	}
            }
        }
        
       }
    catch(MalformedURLException e)  {System.out.println("[Factory : buildAllInFile : MalformedURLException]\t"  + e.toString());}
    catch(Exception e)              {System.out.println("[Factory : buildAllInFile : Exception]\t"              + e.toString());}
    return false;
	
}
	/**
	 * load the plugin list part
	 * @return true on succeed or false
	 */
	private boolean loadPlugListController()
	{
		try
        {
		File file = new File("./PlugList.jar");
		System.out.println("LOAD PLUGIN LIST");
		JarLoader jarLoader             = new JarLoader(new URL("jar","","file:"+ file.getAbsolutePath()+"!/"));
        JarFile currentJarFile          = new JarFile(file);

        jarLoader.load();

        for(Enumeration<JarEntry> elem = currentJarFile.entries(); elem.hasMoreElements();)
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
                    //System.out.println("CLASS FIND 1: " + packageName.replace("/", ".")+ "." + className.split("\\.")[0] );
                }
                else
                {
                	myClass            = jarLoader.getClass(elemName.split("\\.")[0]);
                	//System.out.println("CLASS FIND 2: " + elemName.split("\\.")[0]);
                }
                
                if (APluginListController.class.isAssignableFrom(myClass) == true)
                	{
                		try
                		{
                			this.plugList = (APluginListController)myClass.newInstance();
                			return true;
                		}
                		catch (Exception e)
                		{
                			System.out.println("Class load failed : " + myClass.getName());
                		}
                	}
            }
        }
        
       }
    catch(MalformedURLException e)  {System.out.println("[Factory : buildAllInFile : MalformedURLException]\t"  + e.toString());}
    catch(Exception e)              {System.out.println("[Factory : buildAllInFile : Exception]\t"              + e.toString());}
    return false;
	
}
	
	public INavigationController getNavigationController()
	{
		return this.navig;
	}

	public IWorkspaceController getWorkspaceController()
	{
		return this.workspace;
	}

	public IPluginListController getPluginListController()
	{
		return this.plugList;
	}

}
