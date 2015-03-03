package Models;

import java.net.URL;

public class MainModel {
	public String description;
	public String name;
	public String absolutePath;
	public String iconPath;
	public boolean isAlreadyInList;
	public boolean checked;
	
	public MainModel(){
		description = "";
		name = "";
		absolutePath = "";
		iconPath = "";
		isAlreadyInList = false;
	}
	//public Image icon; 
}
