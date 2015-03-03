package Abstractions.Views;

/**
 * 
 * @author Persus
 * this interface is for describe all menu item with it menu bar name
 */
public interface IMenuBar {
	/**
	 * get the menu bar name of the menu item
	 * @return the menu bar name of the menu item
	 */
	public String getMenuName();
	/**
	 * get the menu bar item
	 * @return
	 */
	public AMenuBarItem getMenuItem();
	/**
	 * set name of the menu
	 * @param MenuName : name of the menu bar
	 */
	public void setName(String MenuName);
	/**
	 * set the menu bar item
	 * @param item new menu bar item
	 */
	public void setMenuItem(AMenuBarItem item);
}
