package Helpers;

import java.io.File;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import Abstractions.Models.LanguageModel;

public class LanguageLoader {
	
	static Document document;
	static Element racine;
	 
	/*
	 * Loads and returns LanguageModel
	 */
	public static LanguageModel LanguageLoader(){
		 
		
		SAXBuilder sxb = new SAXBuilder();
		try
		{
			//On crée un nouveau document JDOM avec en argument le fichier XML
			//Le parsing est terminé ;)
			document = sxb.build(new File("./src/Ressources/language.xml"));
		}	
		catch(Exception e){
			
		}

		//On initialise un nouvel élément racine avec l'élément racine du document.
		racine = document.getRootElement();

		List<Element> listPuginList = racine.getChildren();

		return new LanguageModel(listPuginList.get(0));
		
	}
}
