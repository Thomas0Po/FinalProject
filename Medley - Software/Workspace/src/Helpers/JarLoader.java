/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;



/**
 * The JarLoader is use to load a jarFile in a current context.
 * @author favre
 * @see URL
 * @see URLClassLoader
 */
public class JarLoader
{
 
    private URL             url;
    private URLClassLoader  classLoader;
    
    public JarLoader(URL url) {this.url = url;}
    
    public void load()
    {
        try
        {
            URLClassLoader  sysLoader   = (URLClassLoader)ClassLoader.getSystemClassLoader();                   
            Class           sysClass    = URLClassLoader.class;
            Method          sysMethod   = sysClass.getDeclaredMethod("addURL", new Class[] {URL.class});
            
            sysMethod.setAccessible(true);
            sysMethod.invoke(sysLoader, new Object[]{this.url});   
            
            this.classLoader            = URLClassLoader.newInstance(new URL[] {this.url});
        }
        catch(Exception e) { System.out.println("[JarLoader : load]\t" + e.toString());}
    }    
    
    public Class getClass(String strClass) 
    {
        try                 {return this.classLoader.loadClass(strClass);                   }
        catch(Exception e)  {System.out.println("[JarLoader : getClass]\t" + e.toString()); }
        
        return null;
    }
}

