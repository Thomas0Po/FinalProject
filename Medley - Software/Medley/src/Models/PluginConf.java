
package Models;

import java.awt.Image;

public class PluginConf
{
    private String          _name;
    private String          _description;
    private String          _path;
    private String          _iconName;
    private boolean         _toCharge;
    private boolean         _toHide;
    private boolean         _toRun;
    private Image   		_image; 
    
    /*
     * Constructor
     */
    public PluginConf()
    {
        _name = "";
        _description = "";
        _path = "";
        _iconName = "";
    }
    
    /*
     * Returns image
     */
    public Image getImage()
    {
        return _image;
    }
    
    /*
     * Sets image
     */
    public void     setImage(Image image)
    {
        _image = image;
    }
    
    /*
     * Returns plugin description
     */
    public String   getDescription()
    {
        return _description;
    }
    
    /*
     * Sets plugin description
     */
    public void     setDescription(String description)
    {
        _description = description;
    }
    
    /*
     * Returs path
     */
    public String   getPath()
    {
        return _path;
    }

    /*
     * Sets path
     */
    public void     setPath(String path)
    {
        this._path = path;
    }

    /*
     * Returns plugin name
     */
    public String   getName()
    {
        return _name;
    }
  
    /*
     * Sets plugin name
     */
    public void     setName(String name)
    {
        this._name = name;
    }

    /*
     * Returns icon name
     */
    public String   getIconName()
    {
        return _iconName;
    }

    /*
     * Sets icon name
     */
    public void     setIconName(String iconName)
    {
        this._iconName = iconName;
    }

    /*
     * Return true if plugin must be charged
     */
    public boolean  isToCharge()
    {
        return _toCharge;
    }

    /*
     * Sets boolean to charge
     */
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

    /*
     * Returns true if plugin is to hide
     */
    public boolean  isToHide()
    {
        return _toHide;
    }

    /*
     * Sets to hide
     */
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

    /*
     * Returns true if plugin is to run
     */
    public boolean isToRun()
    {
        return _toRun;
    }

    /*
     * Sets to run
     */
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
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String   toString()
    {
        return (_name);
    }

}
