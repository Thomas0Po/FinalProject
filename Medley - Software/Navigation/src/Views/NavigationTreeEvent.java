package Views;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NavigationTreeEvent implements ChangeListener<TreeItem<String>>  {
	NavigationTab navigTab;
	
	public NavigationTreeEvent(NavigationTab navigTab)
	{
		this.navigTab = navigTab;
	}
	
	@Override
	public void changed(
			ObservableValue<? extends TreeItem<String>> observable,
			TreeItem<String> oldValue, TreeItem<String> newValue) 
	{
		if (newValue == null)
			return;
		String newPath = navigTab.tree.getPathRoot() + File.separator + generatePath(newValue);
		navigTab.navigFolder.setPath(newPath);
		newValue = new NavigationTreeItem(newPath);
		TreeItem<String> selectedItem = navigTab.tree.getSelectionModel().getSelectedItem();
		selectedItem.setValue(newValue.getValue());
		selectedItem.getChildren().clear();
		for (TreeItem<String> child : newValue.getChildren())
		{
			selectedItem.getChildren().add(child);
		}
		
		navigTab.update();
		navigTab.setText(navigTab.navigFolder.getPath());
	}
	
	public String generatePath(TreeItem<String> itemSelected)
	{
		List<String> directories = new ArrayList<String>();
		String result = new String();
		
		for (TreeItem<String> item = itemSelected; item.getParent() != null; item = item.getParent())
			directories.add(item.getValue());
		 
		for (int index = directories.size(); index > 0; index = index - 1)
		{
			result += directories.get(index - 1);
			if (index != 0)
				result += File.separator;
		}
		return result;
	}

	
}
