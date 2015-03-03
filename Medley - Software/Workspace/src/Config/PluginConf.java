
package Config;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class PluginConf
{
    private String          _name;
    private String          _description;
    private String          _path;
    private String          _iconName;
    private boolean         _toCharge;
    private boolean         _toHide;
    private boolean         _toRun;
    private Image   _image; 
    
    public PluginConf()
    {
        _name = "";
        _description = "";
        _path = "";
        _iconName = "";
    }
    
    public Image getImage()
    {
        return _image;
    }
    
    public void     setImage(Image image)
    {
        _image = image;
    }
    
    public String   getDescription()
    {
        return _description;
    }
    
    public void     setDescription(String description)
    {
        _description = description;
    }
    
    public String   getPath()
    {
        return _path;
    }

    public void     setPath(String path)
    {
        this._path = path;
    }

    public String   getName()
    {
        return _name;
    }
  
    public void     setName(String name)
    {
        this._name = name;
    }

    public String   getIconName()
    {
        return _iconName;
    }

    public void     setIconName(String iconName)
    {
        this._iconName = iconName;
    }

    public boolean  isToCharge()
    {
        return _toCharge;
    }

    public void     setToCharge(String toCharge)
    {
        if (toCharge.equals("true"))
        {
            this._toCharge = true;
        }
        else if (toCharge.equals("false"))
        {
            this._toCharge = false;   
        }
    }

    public boolean  isToHide()
    {
        return _toHide;
    }

    public void     setToHide(String toHide)
    {    
        if (toHide.equals("true"))
        {
            this._toHide = true;
        }
        else if (toHide.equals("false"))
        {
            this._toHide = false;   
        }
    }

    public boolean isToRun()
    {
        return _toRun;
    }

    public void     setToRun(String toRun)
    {
        if (toRun.equals("true"))
        {
            this._toRun = true;
        }
        else if (toRun.equals("false"))
        {
            this._toRun = false;
        }
    }
    
    @Override
    public String   toString()
    {
        return (_name);
    }
}
