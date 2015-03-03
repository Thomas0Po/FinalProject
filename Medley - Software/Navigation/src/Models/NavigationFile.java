package Models;

import java.io.File;

public class NavigationFile {
	File file;

	public NavigationFile(File file)
	{
		this.file = file;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	@Override
	public String toString()
	{
		return this.file.getName();
	}
}
