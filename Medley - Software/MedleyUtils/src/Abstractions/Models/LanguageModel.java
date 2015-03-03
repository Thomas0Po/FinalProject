package Abstractions.Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Attribute;
import org.jdom2.Element;

import Abstractions.Models.IModel;

public class LanguageModel extends IModel {
	Map<String, Map<String, String>> constants;
	Map<String, String> availableLanguages;

	public Map<String, String> getAvailableLanguages() {
		return availableLanguages;
	}

	public LanguageModel(Element elem) {
		constants = new HashMap<String, Map<String, String>>();
		availableLanguages = new HashMap<String, String>();
		Attribute keyAttr = elem.getAttribute("key");
		Attribute nameAttr = elem.getAttribute("name");
		if (!availableLanguages.containsKey(keyAttr.getValue()))
			availableLanguages.put(keyAttr.getValue(), nameAttr.getValue());
		List<Element> keys = elem.getChildren();
		for (Element child : keys) {

			String key = child.getName();
			Map<String, String> traductions = new HashMap<String, String>();
			List<Element> trads = child.getChildren();

			for (Element trad : trads) {
				traductions.put(trad.getName(), trad.getValue());
			}
			constants.put(key, traductions);
		}
	}

	public LanguageModel() {
		constants = new HashMap<String, Map<String, String>>();
		availableLanguages = new HashMap<String, String>();
	}

	public void addElem(Element elem) {
		Attribute keyAttr = elem.getAttribute("key");
		Attribute nameAttr = elem.getAttribute("name");
		if (!availableLanguages.containsKey(keyAttr.getValue()))
			availableLanguages.put(keyAttr.getValue(), nameAttr.getValue());
		List<Element> keys = elem.getChildren();
		for (Element child : keys) {

			String key = child.getName();
			Map<String, String> traductions = new HashMap<String, String>();
			List<Element> trads = child.getChildren();

			if (constants.containsKey(key)) {
				constants.get(key).put(trads.get(0).getName(),
						trads.get(0).getValue());
			} else {

				for (Element trad : trads) {
					traductions.put(trad.getName(), trad.getValue());
				}
				constants.put(key, traductions);
			}
		}
	}

	public String getText(String key, String language) {
		if (key == null)
			return ("ERROR KEY NULL");
		if (language == null)
			language = "EN";
		if (constants.get(key) != null)
			return constants.get(key).get(language);
		return ("ERROR" + key);
	}
}
