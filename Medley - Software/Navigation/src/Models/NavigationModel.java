package Models;

import java.util.ArrayList;
import java.util.List;

public class NavigationModel {
	NavigationConf navigationConf;
	List<NavigationFolder> listNavigFolder;
	List<String> listScoreMusicExt;
	List<String> listMusicExt;
	List<String> listTablatureExt;
	
	public NavigationModel() {
	
		this.navigationConf = new NavigationConf();
		
		this.listNavigFolder = new ArrayList<NavigationFolder>();
		
		List<String> listPath = navigationConf.getNavigationPathList();
		this.listMusicExt = navigationConf.getListMusicExt();
		this.listScoreMusicExt = navigationConf.getListScoreMusicExt();
		this.listTablatureExt = navigationConf.getListTablatureExt();
		
		if (listPath.isEmpty())
			this.addNavigationFolder();
		for (String path : listPath)
		{
			this.addNavigationFolder(path);
		}
	}
	
	public void addNavigationFolder()
	{
		System.out.println("add NavigationFolder sans path");
		this.listNavigFolder.add(new NavigationFolder(this.listScoreMusicExt, this.listTablatureExt, this.listMusicExt));
	}
	
	public void addNavigationFolder(String path)
	{
		System.out.println("Add NavigFolder avec path");
		this.listNavigFolder.add(new NavigationFolder(path, this.listScoreMusicExt, this.listTablatureExt, this.listMusicExt));
	}
	
	public void deleteNavigationFolderAt(int index)
	{
		this.listNavigFolder.remove(index);
	}
	
	public NavigationFolder getNavigationFolderAt(int index)
	{
		return this.listNavigFolder.get(index);
	}

	
	public List<String> getListScoreMusicExt() {
		return listScoreMusicExt;
	}

	public void setListScoreMusicExt(List<String> listScoreMusicExt) {
		this.listScoreMusicExt = listScoreMusicExt;
		
		for (NavigationFolder navig : this.listNavigFolder)
		{
			navig.setScoreMusicFileExt(listScoreMusicExt);
		}
	}

	public List<String> getListMusicExt() {
		return listMusicExt;
	}

	public void setListMusicExt(List<String> listMusicExt) {
		this.listMusicExt = listMusicExt;
		
		for (NavigationFolder navig : this.listNavigFolder)
		{
			navig.setMusicFileExt(listMusicExt);
		}
	}
	
	public void setExts(List<String> newScoreExt, List<String> newTabExt, List<String> newMusicExt)
	{
		this.listScoreMusicExt = newScoreExt;
		this.listTablatureExt = newTabExt;
		this.listMusicExt = newMusicExt;
		for (NavigationFolder navig : this.listNavigFolder)
		{
			navig.setScoreMusicFileExt(listScoreMusicExt);
			navig.setTablatureFileExt(listTablatureExt);
			navig.setMusicFileExt(listMusicExt);
			navig.setPath(navig.getAbsolutePath());
		}
	}
	
	public List<String> getListTablatureExt() {
		return listTablatureExt;
	}

	public void setListTablatureExt(List<String> listTablatureExt) {
		this.listTablatureExt = listTablatureExt;
		
		for (NavigationFolder navig : this.listNavigFolder)
		{
			navig.setTablatureFileExt(listTablatureExt);
		}
	}
	
	public void save()
	{
		List<String> listPath = new ArrayList<String>(); 
		
		for (NavigationFolder navig : this.listNavigFolder)
		{
			listPath.add(navig.getAbsolutePath());
		}
		this.navigationConf.setNavigationPathList(listPath);
		this.navigationConf.setListMusicExt(this.listMusicExt);
		this.navigationConf.setListTablatureExt(this.listTablatureExt);
		this.navigationConf.setListScoreMusicExt(this.listScoreMusicExt);
		this.navigationConf.save();
	}
	
	public List<NavigationFolder> getListNavigFolder()
	{
		return this.listNavigFolder;
	}
	
	public NavigationFolder getLastNavigFolder()
	{
		int indexLast = this.listNavigFolder.size() - 1;
		
		return this.listNavigFolder.get(indexLast);
	}
}
