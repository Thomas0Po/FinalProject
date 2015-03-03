package Models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class NavigationConf {
	String confFile = new String("./src/Ressources/Configs/navigation.xml");
	List<String> navigPathList;
	List<String> musicExt;
	List<String> tablatureExt;
	List<String> scoreMusicExt;
	
	public NavigationConf() {
		this.init();
	}
	
	public List<String> getNavigationPathList()
	{
		return navigPathList;
	}
	
	public void 		 setNavigationPathList(List<String> pathList)
	{
		this.navigPathList = pathList;
	}
	
	public List<String> getListMusicExt()
	{
		return this.musicExt;
	}
	
	public List<String> getListTablatureExt()
	{
		return this.tablatureExt;
	}
	
	public List<String> getListScoreMusicExt()
	{
		return this.scoreMusicExt;
	}
	
	public void setListMusicExt(List<String> musicExt)
	{
		this.musicExt = musicExt;
	}
	
	public void setListScoreMusicExt(List<String> scoreMusicExt)
	{
		this.scoreMusicExt = scoreMusicExt;
	}
	
	public void setListTablatureExt(List<String> tablatureExt)
	{
		this.tablatureExt = tablatureExt;
	}
	
	private void createDefaultValue()
	{
		this.musicExt.clear();
		this.navigPathList.clear();
		this.scoreMusicExt.clear();
		this.tablatureExt.clear();
		this.musicExt.add("ogg");
		this.musicExt.add("mp3");
		this.musicExt.add("wave");
		this.tablatureExt.add("tab");
		this.scoreMusicExt.add("part");
	}
	
	private void addPath(String path)
	{
		File file = new File(path);
		if (file.exists() && file.isDirectory())
			this.navigPathList.add(path);
	}
	
	private void addExtension(Element elem)
	{
		if (elem.getAttribute("type").getValue().equals("music"))
			this.musicExt.add(elem.getValue());
		else if (elem.getAttribute("type").getValue().equals("tab"))
			this.tablatureExt.add(elem.getValue());
		else if (elem.getAttribute("type").getValue().equals("score"))
			this.scoreMusicExt.add(elem.getValue());
	}
	
	private void fillNavigationConfig(Element root)
	{
		for (Element elem : root.getChildren())
		{
			if (elem.getName().equals("path") && elem.getValue() != null)
				addPath(elem.getValue());
			else if (elem.getName().equals("extension") && elem.hasAttributes()
					&& elem.getAttribute("type") != null && elem.getAttribute("type").getValue() != null 
					&& elem.getValue() != null)
			{
				addExtension(elem);
			}
			else
			{
				createDefaultValue();
			}
		}
		
	}
	
	private void loadConf()
	{
		System.out.println("LOAD CONF ?? ");
		File file = new File(this.confFile);
		System.out.println("NAVIG CONF " + file.getAbsolutePath());
        SAXBuilder sxb = new SAXBuilder();
        Document document;
        if (file.exists() == true)
        {
        	try 
        	{
        		document = sxb.build(file);
        		Element  root = document.getRootElement();
        		if (root.getName().equals("navigation"))
        		{
        			fillNavigationConfig(root);
        		}
        		else
        		{
        			createDefaultValue();
        		}
        	}
        	catch (JDOMException e) {
        		createDefaultValue();
        	}catch (IOException e){
        		createDefaultValue();
        	}
        }
        else
        {
        	createDefaultValue();
        }
	}
	
	public void init()
	{
		this.navigPathList = new ArrayList<String>();
		this.scoreMusicExt = new ArrayList<String>();
		this.tablatureExt = new ArrayList<String>();
		this.musicExt = new ArrayList<String>();
		this.loadConf();
	}
	
	private boolean writeConfigInFile()
	{
		Element root = new Element("navigation");
		Document document = new Document(root);

		for (String path : this.navigPathList)
		{
			Element pathElem = new Element("path");
			pathElem.setText(path);
			root.addContent(pathElem);
		}
		for (String musicExt : this.musicExt)
		{
			Element musicExtElement = new Element ("extension");
			org.jdom2.Attribute extAttr = new org.jdom2.Attribute("type", "music");
			musicExtElement.setAttribute(extAttr);
			musicExtElement.setText(musicExt);
			root.addContent(musicExtElement);
		}
		for (String tabExt : this.tablatureExt)
		{
			Element tabExtElement = new Element ("extension");
			org.jdom2.Attribute extAttr = new org.jdom2.Attribute("type", "tab");
			tabExtElement.setAttribute(extAttr);
			tabExtElement.setText(tabExt);
			root.addContent(tabExtElement);
		}
		for (String scoreExt : this.scoreMusicExt)
		{
			Element scoreExtElement = new Element ("extension");
			org.jdom2.Attribute extAttr = new org.jdom2.Attribute("type", "score");
			scoreExtElement.setAttribute(extAttr);
			scoreExtElement.setText(scoreExt);
			root.addContent(scoreExtElement);
		}
		
		XMLOutputter XMLOutput = new XMLOutputter(Format.getPrettyFormat());
		try {
			XMLOutput.output(document, new FileOutputStream(confFile));
			return true;
		} 
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		System.out.println("CANT SAVE");
		return false;
	}
	
	public boolean save()
	{
		System.out.println("SAVE");
		File file = new File(this.confFile);
		try 
		{
			if (!file.exists() && !file.createNewFile())
			{
				System.out.println("NAVIGATION CAN'T SAVE CONF");
				return false;
			}
			if (!file.canWrite())
			{
				System.out.println("NAVIGATION CAN'T SAVE CONF");
				return false;
			}
			return (this.writeConfigInFile());
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
}
