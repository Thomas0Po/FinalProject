package Views;

import Abstractions.Views.AMenuBarItem;
import Abstractions.Views.IMenuBar;

public class MenuBar implements IMenuBar{

	private String 			menuName;
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
