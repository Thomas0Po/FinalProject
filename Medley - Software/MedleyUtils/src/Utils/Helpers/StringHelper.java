/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Helpers;

/**
 *
 * @author Persus
 */
public class StringHelper 
{
	/**
	 * this function return the name of a file from it file
	 * @param path : absolute path of the path
	 * @return name of the file
	 */
    public static String    getFileName(String path)
    {
        return  path.substring(path.lastIndexOf("\\") + 1, path.lastIndexOf("."));
    }
    
}
