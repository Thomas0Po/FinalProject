/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Config.AConfig;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.jar.JarFile;

import Helpers.JarLoader;

public abstract class FactoryClass <T> implements IFactory <T>
{
    protected   String          lastError;
    protected   JarFile         currentJarFile;
    protected   File            file;
    protected   AConfig         config;
    protected   Class<T>        classIWant;
    protected   Collection<T>   collection;
    
    //////////////////////////////////////
    /////// Constructor
    
    public FactoryClass()                                   {                                                       }
    public FactoryClass(String fileName)                    {this.file = new File(fileName);                        }
    public FactoryClass(File file)                          {this.file = file;                                      }
    public FactoryClass(String fileName, AConfig conf)      {this.file = new File(fileName);    this.config = conf; }
    public FactoryClass(File file, AConfig conf)            {this.file = file;                  this.config = conf; }
    
    //////////////////////////////////////
    /////// Public Methods
    
    public T               buildOne()
    {
        /*idée : permetre de bouclé sur buildOne, un peux comme un iterator*/
        return null;
    }
    
    public Collection<T>   buildAll()
    {
        if (this.file == null)                  this.lastError = NO_FILE;
        else if (this.file.exists() == false)   this.lastError = FILE_DOES_NOT_EXIST;
        else
        {
            if (this.file.isDirectory())        return this.buildAllInFolder();
            else if (this.file.isFile())        return this.buildAllInFile();
            else                                this.lastError = FILE_UNKNOWN;
        }
        
        return null;
    }
    /////////////////////////////////////
    /////// Private methods
    
    protected Collection<T>   buildAllInFile()    {return this.buildAllInFile(this.file);}
    
    protected Collection<T>   buildAllInFile(File file)
    {
        try
        {
            JarLoader jarLoader             = new JarLoader(new URL("jar","","file:"+file.getAbsolutePath()+"!/"));
            currentJarFile                  = new JarFile(file);
            collection                      = new ArrayList<T>();

            jarLoader.load();

            for(Enumeration elem = currentJarFile.entries(); elem.hasMoreElements();)
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
                    }
                    else myClass            = jarLoader.getClass(elemName.split("\\.")[0]);

                    if (isValid(myClass) == true)    addToCollection(myClass);
                }
            }
            
            return collection;
        }
        catch(MalformedURLException e)  {System.out.println("[Factory : buildAllInFile : MalformedURLException]\t"  + e.toString());}
        catch(Exception e)              {System.out.println("[Factory : buildAllInFile : Exception]\t"              + e.toString());}
        return new ArrayList<T>();
    }
    
    protected Collection<T>   buildAllInFolder()
    {
       Collection<T>    collection = new ArrayList<T>();

       for (File f : this.file.listFiles())
          if (f.getName().contains(".jar"))collection.addAll(this.buildAllInFile(f));
       
       return collection;
    }
   
    protected void     addToCollection(Class myClass)
    {
        System.out.println("[Factory]\tBuild : " + myClass.getName());
        try                 {collection.add((T)myClass.newInstance());                                      }
        catch(Exception e)  {System.out.println("[Factory : addToCollection : Exception]\t" + e.toString());}
    }
    
    protected abstract boolean  isValid(Class myClass);

    /////////////////////////////////////
    /////// Getters & Setters

    public String   getLastError()          {return this.lastError;             }
    public File     getPackageName()        {return this.file;                  }
    public Class<T> getCompare()            {return this.classIWant;            }
    
    public void     setFile(String p)       {this.file          = new File(p);  }
    public void     setFile(File f)         {this.file          = f;            }
    public void     setCompare(Class<T> t)  {this.classIWant    = t;            }
}
