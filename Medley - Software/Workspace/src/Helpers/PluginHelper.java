/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Helpers.JarLoader;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarFile;
import plugincontract.APlugin;

/**
 *
 * @author favre
 */

public class PluginHelper 
{
    public static APlugin newInstanceOf(APlugin plug)
    {
        try                 {
        APlugin newPlugin = plug.getClass().newInstance();
        newPlugin.setName(plug.getName());
        newPlugin.setIconName(plug.getIconName());
        System.out.println("Plug Helper + " + plug.getPath());
        newPlugin.setPath(plug.getPath());
        	return newPlugin;
        	
        }
        catch(Exception e)  {System.out.println("[PluginHelper : Exception]\t" + e.toString());}
        return null;
    }
    
    public static boolean isAPlugin(File file, String iconName)
    {
      
       try
        {
            JarLoader jarLoader             = new JarLoader(new URL("jar","","file:"+file.getAbsolutePath()+"!/"));

            jarLoader.load();

            URLClassLoader  urlClassLoader  = URLClassLoader.newInstance(new URL[]{new URL("file://" + file.getCanonicalPath() + file.getCanonicalFile())});
            JarFile currentJarFile = new JarFile(file);
            for(Enumeration elem = currentJarFile.entries(); elem.hasMoreElements();)
            {
                String elemName             = elem.nextElement().toString();
                if (elemName.endsWith(".class"))
                {
                    String className;
                    String packageName = new String();
                    Class myClass;
                    
                    className = elemName.substring(elemName.lastIndexOf("/") + 1);
                    if (elemName.lastIndexOf("/") != -1)
                    {
                        packageName = elemName.substring(0, elemName.lastIndexOf("/"));
                        myClass = urlClassLoader.loadClass(packageName.replace("/", ".") + "." + className.split("\\.")[0]);
                    }
                    else
                    {
                        myClass = urlClassLoader.loadClass(className.split("\\.")[0]);
                    }
                                         
                    if (APlugin.class.isAssignableFrom(myClass) == true)
                    {   
                       return true;
                    }
                }
            }
            return false;
        }
        catch(Exception e) {System.out.println("[PluginHelper : isAPlugin : Exception]\t" + e.toString());}
        return false;
    }
}
