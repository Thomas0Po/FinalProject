package Abstractions.Views;

import Abstractions.Views.AMenuBarItem;
import Abstractions.Views.IMenuBar;

/**
 * 
 * @author Persus
 * this class correspond of a menu bar item. it contains the menu bar item and the menu bar name. it implements the IMenuBar interface.
 */
public class MenuBar implements IMenuBar{
	/**
	 *  name of the menu bar
	 */
	private String 			menuName;
	/**
	 * menuItem
	 */
	private AMenuBarItem	menuItem;
	
	@Override
	public String getMenuName() {
		return this.menuName;
	}

	@Override
	public AMenuBarItem getMenuItem() {
		return menuItem;
	}

	@Override
	public void setName(String MenuName) {
		this.menuName = MenuName;
	}

	@Override
	public void setMenuItem(AMenuBarItem item) {
		this.menuItem = item;
	}
	
}
