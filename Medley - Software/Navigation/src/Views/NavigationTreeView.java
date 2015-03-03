package Views;

import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NavigationTreeView extends TreeView<String>{
	String pathRoot;
	NavigationTreeItem ItemRoot;
	
	public NavigationTreeView(String pathRoot) {
		super();
		this.setPathRoot(pathRoot);
		this.getStyleClass().add("navigTree");
		//this.ItemRoot.setGraphic(new ImageView(new Image("Ressources/Images/medleyFolderRoot.png", 40, 40,true, true)));

//		this.ItemRoot.setGraphic(new ImageView(new Image("Ressources/Images/NavigationRootFolderIcon.png", 40, 40,true, true)));
	}
	
	public void setPathRoot(String newPathRoot)
	{
		this.pathRoot = newPathRoot;
		ItemRoot = new NavigationTreeItem(pathRoot);
		this.setRoot(ItemRoot);
	}
	
	public String getPathRoot()
	{
		return (pathRoot);
	}
}
