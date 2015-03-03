
package Config;

import java.io.File;
import java.io.FileOutputStream;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public abstract class AConfig
{
   protected String                 _configPath;
   protected String                 _fileName;
   protected String                 _name;
   protected Element                _root;
   protected org.jdom2.Document     _document;
   protected boolean                _isModif;
   
    public AConfig(String configPath, String fileName, String name)
    {
       this._configPath = configPath;
       this._fileName = fileName;
       this._name = name;
       this._isModif = false;
       this.buildXML();
    }
    
    private boolean  buildXML()
    {
      try
      {
          File file = new File(_configPath + _fileName);
          SAXBuilder sxb = new SAXBuilder();
          if (file.exists() == true)
          {  
            this._document = sxb.build(file);
            this._root = this._document.getRootElement();
          }
      }
      catch(Exception e)
      {
            System.out.println("Error during build XML file, can't read '" + this._configPath + this._fileName + "'");
            System.out.println(e.getCause());
            return (false);
      }
      return (true);
    }
    
    protected boolean  writeXML()
    {
        try
        {
            if (new File(this._configPath + this._fileName).exists() == false)
            {
                new File(this._configPath + this._fileName).createNewFile();
            }
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(this._document, new FileOutputStream(this._configPath + this._fileName));
        }
        catch (Exception e)
        {
            System.out.println("Error during writing XML file, can't write '" + this._configPath + this._fileName + "'");
            System.out.println(e.getCause());
            return (false);
        }
        return (true);
    }
    
    protected boolean  isModif()
    {
        return this._isModif;
    }
    
    protected String   getName()
    {
        return this._name;
    }
    
    protected abstract boolean saveConf();
    protected abstract void fillIfEmpty();
}
