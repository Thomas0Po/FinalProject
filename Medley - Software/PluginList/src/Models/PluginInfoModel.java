package Models;

/*
 * Model used bu PluginListController to manage the plugins list
 */
public class PluginInfoModel {
		public String description;
		public String name;
		public String absolutePath;
		public String iconPath;
		public boolean isAlreadyInList;
		public boolean checked;
		public boolean isToRun;
		
		public PluginInfoModel(){
			description = "";
			name = "";
			absolutePath = "";
			iconPath = "";
			isAlreadyInList = false;
			isToRun = false;
		}
}
