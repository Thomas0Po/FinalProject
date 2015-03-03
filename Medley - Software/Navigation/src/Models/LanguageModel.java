package Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Element;

public class LanguageModel {
	Map<String, Map<String, String>> constants;
	
	public LanguageModel(Element elem){
		constants = new HashMap<String, Map<String,String>>();
		List<Element> keys = elem.getChildren();
		for(Element child : keys){
			String key = child.getName();
			Map<String, String> traductions = new HashMap<String, String>();
			List<Element> trads = child.getChildren();
			
			for(Element trad : trads) {
				traductions.put(trad.getName(), trad.getValue());
			}
			constants.put(key, traductions);
		}
	}
	
	public String getText(String key, String language){
		if (constants.get(key) != null)
			return constants.get(key).get(language);
		return ("ERROR" + key);
	}
}
