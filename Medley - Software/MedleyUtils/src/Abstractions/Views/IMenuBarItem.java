package Abstractions.Views;

/**
 * 
 * @author Persus
 * this interface if for the menu bar. it describe all menu bar item
 */

public interface IMenuBarItem {
	/**
	 * this function set the text of the menu item
	 * @param text : the text of the menu item
	 */
	void setTextMenuItem(String text);
	/**
	 * get the text of the menu item
	 * @return text of the menu item
	 */
	String getTextMenuItem();
}
