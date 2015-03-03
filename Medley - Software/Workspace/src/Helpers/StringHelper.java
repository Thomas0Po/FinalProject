/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

/**
 *
 * @author favre
 */
public class StringHelper 
{
    public static String    getFileName(String path)
    {
        return  path.substring(path.lastIndexOf("\\") + 1, path.lastIndexOf("."));
    }
    
}
