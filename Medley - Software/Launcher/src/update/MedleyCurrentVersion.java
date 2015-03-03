package update;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MedleyCurrentVersion {
	private String navigationVersion;
	private String coreVersion;
	private String plugListVersion;
	private String workspaceVersion;

	public MedleyCurrentVersion()
	{		
		this.navigationVersion = findCurrentModuleVersion("./Navigation.jar");
		this.coreVersion = findCurrentModuleVersion("./Medley.jar");
		this.plugListVersion = findCurrentModuleVersion("./PlugList.jar");
		this.workspaceVersion = findCurrentModuleVersion("./Workspace.jar");
		System.out.println("Current Version of Medley");
		System.out.println("Core : " + coreVersion);
		System.out.println("Navig : " + navigationVersion);
		System.out.println("PlugList : " + plugListVersion);
		System.out.println("Workspace : " + workspaceVersion);
	}
	
	public String getNavigationVersion() {
		return navigationVersion;
	}

	public String getCoreVersion() {
		return coreVersion;
	}

	public String getPlugListVersion() {
		return plugListVersion;
	}

	public String getWorkspaceVersion() {
		return workspaceVersion;
	}

	private String findCurrentModuleVersion(String modulePath)
	{	
		InputStream in = null;
		URL inputURL = null;
		String result = new String("0.0");
		File jarFile = new File(modulePath);
		String inputFile = "jar:file:/" + jarFile.getAbsolutePath() + "!/version/version";
		  try {
		    inputURL = new URL(inputFile);
		    JarURLConnection conn = (JarURLConnection)inputURL.openConnection();
		    in = conn.getInputStream();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		    if ((result = reader.readLine()) == null)
		    	result = new String("0.0");
		  } catch (MalformedURLException e1) {
		    System.err.println("Malformed input URL: "+ inputURL);
		  } catch (IOException e1) {
		    System.err.println("IO error open connection");
		}
		return (result);
	}
}
