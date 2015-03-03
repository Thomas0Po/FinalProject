package update;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.print.DocFlavor.READER;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import fr.wedley.clientwebservice.SoftwareLastVersions;
import fr.wedley.clientwebservice.SoftwareUpdate;
import fr.wedley.clientwebservice.SoftwareUpdateService;

/**
 * @author Persus
 *
 */
public class MedleyUpdate {// extends Thread {
	private MedleyCurrentVersion currentVersion;
	private SoftwareLastVersions lastVersions;
	private boolean needUpdate;
	public boolean isNeedUpdate() {
		return needUpdate;
	}
	private boolean needNavigUpdate;
	private boolean needCoreUpdate;
	private boolean needPlugListUpdate;
	private boolean needWorkspaceUpdate;

	public MedleyUpdate()
	{
		this.currentVersion = new MedleyCurrentVersion();
		this.needUpdate = false;
		this.needCoreUpdate = false;
		this.needNavigUpdate = false;
		this.needWorkspaceUpdate = false;
		this.needPlugListUpdate = false;
	}	

	public boolean updateCore()
	{
		if (needCoreUpdate == true)
		{
			if (this.downloadUpdateModule("Medley.jar", this.lastVersions.getCoreURL()) == false)
			{
				System.out.println("Can't download Medley.jar");
				return false;
			}
		}
		return true;
	}
	
	public boolean updateNavigation()
	{
		if (needNavigUpdate == true)
		{
			if (this.downloadUpdateModule("Navigation.jar", this.lastVersions.getNavigationURL()) == false)
			{
				System.out.println("Can't download Navig.jar");
				return false;
			}
		}
		return true;
	}
	
	public boolean updatePluginList()
	{
		if (needPlugListUpdate == true)
		{
			if (this.downloadUpdateModule("PlugList.jar", this.lastVersions.getPluginsURL()) == false)
			{
				System.out.println("Can't download PlugList.jar");
				return false;
			}
		}
		return true;
	}
	
	public boolean updateWorkspace()
	{
		if (needWorkspaceUpdate == true)
		{
			if (this.downloadUpdateModule("Workspace.jar", this.lastVersions.getWorkspaceURL()) == false)
			{
				System.out.println("Can't download Workspace.jar");
				return false;
			}
		}
		return true;
	}
	
	public boolean patchUpdate()
	{
		File tmpDir = new File("./tmp");
		File patch[] = tmpDir.listFiles();
		for (File fileUpdated : patch)
		{
			fileUpdated.renameTo(new File("." + File.separator + fileUpdated.getName())); 
		}
		return true;
	}
	

	/**
	 * 
	 * @return false if can't connect to the web service
	 */
	public boolean checkNewSoftwareUpdate()
	{
		try
		{
			SoftwareUpdate service = new SoftwareUpdateService().getSoftwareUpdatePort();
			this.lastVersions = service.getLastSoftwareVersions();
			
		} catch (Exception e) {return false;}
		
		if (this.currentVersion.getCoreVersion() != this.lastVersions.getCoreVersion())
		{
			this.needUpdate = true;
			this.needCoreUpdate = true;
		}
		
		if (this.currentVersion.getNavigationVersion() != this.lastVersions.getNavigationVersion())
		{
			this.needUpdate = true;
			this.needNavigUpdate = true;
		}
		
		if (this.currentVersion.getPlugListVersion() != this.lastVersions.getPluginsVersion())
		{
			this.needUpdate = true;
			this.needPlugListUpdate = true;
		}
		
		if (this.currentVersion.getWorkspaceVersion() != this.lastVersions.getWorkspaceVersion())
		{
			this.needUpdate = true;
			this.needCoreUpdate = true;
		}
		return (true);
	}
	
	private boolean downloadUpdateModule(String fileName, String url)
	{
		File dirTmp = new File("./tmp");
		if (!dirTmp.exists())
		{
			if (dirTmp.mkdir() == false)
			{
				System.out.println("Can't create tmp directory for update");
				return false;
			}
		}
		System.out.println("Download Update for " + fileName);
		try 
		{
			URL urlDownload = new URL(url);
			InputStream in = urlDownload.openStream();
			FileOutputStream fos = new FileOutputStream(new File(fileName));

			System.out.println("reading file...");
			int length = -1;
			byte[] buffer = new byte[1024];// buffer for portion of data from
			                                // connection
			while ((length = in.read(buffer)) > -1)
				fos.write(buffer, 0, length);
			//TODO buffer tournant 
			fos.close();
			in.close();
			System.out.println("Download Succed for " + fileName);
		} 
		catch (Exception e) 
		{
			System.out.println("Can't connect to URL for update " + fileName);
			return false;
		}
		return true;
	}
}
