package Views;

import java.io.File;
import java.io.FilenameFilter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NavigationTreeItem extends TreeItem<String> {
	File directoryRoot;
	Image directoryIcon;

	public NavigationTreeItem(String pathRoot) {
		super();
		this.directoryRoot = new File(pathRoot);
		this.init();
	}

	private void init() {
		this.setValue(this.directoryRoot.getName());
		this.searchSubDirectory();
		this.setGraphic(new ImageView(new Image("Ressources/Images/medleyFolder.png", 30, 30,true, true)));
		this.expandedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if (newValue){
					NavigationTreeItem.this.setGraphic(new ImageView(new Image("Ressources/Images/medleyFolderClosed.png", 30, 30,true, true)));
				}
				else {
					NavigationTreeItem.this.setGraphic(new ImageView(new Image("Ressources/Images/medleyFolder.png", 30, 30,true, true)));
				}
			}
		});
//		this.setGraphic(new ImageView(new Image("Ressources/Images/NavigationFolderIcon.png", 30, 30,true, true)));
	}

	private void searchSubDirectory() {
		String[] SubDirectory = this.directoryRoot.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				File child = new File(dir.getAbsolutePath() + File.separator
						+ name);
				if (child.isDirectory() && !child.isHidden())
					return true;
				return false;
			}
		});

		if (SubDirectory != null)
			for (String subDirectory : SubDirectory) {
				final TreeItem<String> childDirectory = new TreeItem<String>(
						subDirectory, new ImageView(new Image("Ressources/Images/medleyFolder.png", 30, 30,true, true)));
				String subDirectoryPath = this.directoryRoot.getAbsolutePath()
						+ File.separator + subDirectory;
				
				childDirectory.expandedProperty().addListener(new ChangeListener<Boolean>() {

					@Override
					public void changed(ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {
						if (newValue){
							childDirectory.setGraphic(new ImageView(new Image("Ressources/Images/medleyFolderClosed.png", 30, 30,true, true)));
						}
						else {
							childDirectory.setGraphic(new ImageView(new Image("Ressources/Images/medleyFolder.png", 30, 30,true, true)));
						}
					}
				});
				
				this.addSubDirOfChildren(childDirectory, subDirectoryPath);
				this.getChildren().add(childDirectory);
			}
	}

	private void addSubDirOfChildren(TreeItem<String> child, String path) {
		File childDir = new File(path);
		String[] childSubDirectory = childDir.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				File child = new File(dir.getAbsolutePath() + File.separator
						+ name);
				if (child.isDirectory() && !child.isHidden() && child.canRead()
						&& child.exists())
					return true;
				return false;
			}
		});

		if (childSubDirectory != null)
			for (String childSubDir : childSubDirectory) {
				final TreeItem<String> childDirectory = new TreeItem<String>(
						childSubDir, new ImageView(new Image("Ressources/Images/medleyFolder.png", 30, 30,true, true)));
				
				childDirectory.expandedProperty().addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {
						
						if (newValue){
							childDirectory.setGraphic(new ImageView(new Image("Ressources/Images/medleyFolderClosed.png", 30, 30,true, true)));
						}
						else {
							childDirectory.setGraphic(new ImageView(new Image("Ressources/Images/medleyFolder.png", 30, 30,true, true)));
						}
						
					}
				});
				child.getChildren().add(childDirectory);
			}
	}
}
