package plugincontract;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class PluginInfo {
	private String name;
	private String description;
	private String tipsForCom;
	private String key;
	
	public PluginInfo(Reader fileReader) 
	{
		this.getInfoFromFile(fileReader);
	}
	
	public PluginInfo(String path)
	{
		this.key = "DEFAULT";
		this.name = path.substring(path.lastIndexOf(File.separator) + 1, path.length() - 4);
		this.description = "no description for this language";
		this.tipsForCom = "no tips for this language";
	}
	
	private void getInfoFromFile(Reader languageInfoReader)
	{
		if (languageInfoReader == null)
		{
			this.key = "ERROR";
			return;
		}
		
		SAXBuilder builder = new SAXBuilder();
		try {
			Document document = (Document) builder.build(languageInfoReader);
			Element rootNode = document.getRootElement();
			
			if (rootNode.getName() != "PluginInfo")
			{
				System.out.println("root pas pluginInfo");
				
				this.key = "ERROR";
				return;
			}
			System.out.println("I FIND THE ROOT PLUGIN INFO");
			Attribute key = rootNode.getAttribute("key");
			if (key == null)
			{
				System.out.println("key null");
				this.key = "ERROR";
				return;
			}
			this.key = key.getValue();
			System.out.println("KEY = " + this.key);
			
			Element Name = rootNode.getChild("Name");
			if (Name == null)
			{
				System.out.println("name null");
				this.key = "ERROR";
				return;
			}
			this.name = Name.getValue();
			System.out.println("Name = " + this.name);
			
			Element Desc = rootNode.getChild("Description");
			if (Desc == null)
			{
				System.out.println("desc null");
				this.key = "ERROR";
				return;
			}
			this.description = Desc.getValue();
			System.out.println("DESC = " + this.description);
			
			Element Tips = rootNode.getChild("Tips");
			if (Tips == null)
			{
				System.out.println("tips null");
				this.key = "ERROR";
				return;
			}		
			this.tipsForCom = Tips.getValue();
			System.out.println("TIPS = " + this.tipsForCom);
			
		} catch (JDOMException e) {
			this.key = "ERROR";
			System.out.println("JDOM EX");
			e.printStackTrace();
		} catch (IOException e) {
			this.key = "ERROR";
			e.printStackTrace();
		}
	}
	
	public String getKey(){
		return key;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getTipsForCom() {
		return tipsForCom;
	}
}
