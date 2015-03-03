package Models;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.apache.commons.io.FileUtils;

public class NavigationFolder {
	File directory;
	File selectedFile;
	ObservableList<NavigationFile> scoreMusicFileList;
	ObservableList<NavigationFile> tablatureFileList;
	ObservableList<NavigationFile> musicFileList;
	List<String> scoreMusicFileExt;
	List<String> tablatureFileExt;
	List<String> musicFileExt;
	
	
	public NavigationFolder(List<String> scoreMusicExt, List<String> tablatureExt, List<String> musicExt)
	{
		this.init(scoreMusicExt, tablatureExt, musicExt);
		this.setPath(System.getProperty("user.home"));
	}
	
	public NavigationFolder(String path, List<String> scoreMusicExt, List<String> tablatureExt, List<String> musicExt)
	{
		this.init(scoreMusicExt, tablatureExt, musicExt);
		this.setPath(path);
	}

	void init(List<String> scoreMusicExt, List<String> tablatureExt, List<String> musicExt)
	{
		this.scoreMusicFileList = FXCollections.observableArrayList();
		this.tablatureFileList = FXCollections.observableArrayList();
		this.musicFileList = FXCollections.observableArrayList();
		this.scoreMusicFileExt = scoreMusicExt;
		this.tablatureFileExt = tablatureExt;
		this.musicFileExt = musicExt;
		this.selectedFile = null;
	}
	
	private void fillFilesLists()
	{
		this.scoreMusicFileList.clear();
		this.tablatureFileList.clear();
		this.musicFileList.clear();
		
		String [] scoreExt = this.scoreMusicFileExt.toArray(new String[this.scoreMusicFileExt.size()]);
		String [] tabExt = this.tablatureFileExt.toArray(new String[this.tablatureFileExt.size()]);
		String [] musicExt = this.musicFileExt.toArray(new String[this.musicFileExt.size()]);
		
		
		Collection<File> scoreList = FileUtils.listFiles(directory, scoreExt, false);
		Collection<File> tabList = FileUtils.listFiles(directory, tabExt, false);
		Collection<File> musicList = FileUtils.listFiles(directory, musicExt, false);
		
		Collection<NavigationFile> ScoreNavigFile = new ArrayList<NavigationFile>();
		Collection<NavigationFile> TabNavigFile = new ArrayList<NavigationFile>();
		Collection<NavigationFile> MusicNavigFile = new ArrayList<NavigationFile>();
		
		for (File file : scoreList)
		{
			ScoreNavigFile.add(new NavigationFile(file));
		}
		
		for (File file : tabList)
		{
			TabNavigFile.add(new NavigationFile(file));
		}
		
		for (File file : musicList)
		{
			MusicNavigFile.add(new NavigationFile(file));
		}
		
		this.scoreMusicFileList.addAll(ScoreNavigFile);
		this.tablatureFileList.addAll(TabNavigFile);
		this.musicFileList.addAll(MusicNavigFile);
	}
	
	public void setPath(String path)
	{
		this.directory = new File(path);
		this.fillFilesLists();
	}
	
	public String getPath() {
		return this.directory.getName();
	}
	
	public String getAbsolutePath()
	{
		return this.directory.getAbsolutePath();
	}
	
	public void setMusicFileExt(List<String> musicFileExt) {
		this.musicFileExt = musicFileExt;
		this.fillFilesLists();
	}
	
	public void setTablatureFileExt(List<String> tabFileExt) {
		this.tablatureFileExt = tabFileExt;
	}
	
	public void setScoreMusicFileExt(List<String> scoreMusicFileExt) {
		this.scoreMusicFileExt = scoreMusicFileExt;
	}
	
	public ObservableList<NavigationFile> getScoreMusicFileList() {
		return scoreMusicFileList;
	}

	public ObservableList<NavigationFile> getTablatureFileList() {
		return tablatureFileList;
	}

	public ObservableList<NavigationFile> getMusicFileList() {
		return musicFileList;
	}

	public File getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(File selectedFile) {
		this.selectedFile = selectedFile;
	}
}
