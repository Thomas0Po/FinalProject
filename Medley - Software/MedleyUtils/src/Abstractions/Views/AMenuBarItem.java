package Abstractions.Views;

import javafx.scene.control.MenuItem;

/**
 * Abstract class for MenuItem
 */
public abstract class AMenuBarItem extends MenuItem implements IMenuBarItem {

	protected AMenuBarItem(String text) {
		super(text);
		this.setTextMenuItem(text);
	}
	
	@Override
	public void setTextMenuItem(String text)
	{
		this.setText(text);
	}
	
	@Override
	public String getTextMenuItem()
	{
		return (this.getText());
	}
}
