package Abstractions.Views;

import java.util.List;

import Abstractions.Models.LanguageModel;
import javafx.scene.Node;

/**
 * @author Persus
 * this interface is for all module view. 
 */
public interface IView
	{
	/**
	 * initialize the view
	 */
		public void init();
		/**
		 * get the main component of the view
		 * @return the main component of the view
		 */
		public Node getMainComponent();
		/**
		 * get the list of menu items
		 * @return the list of menu items
		 */
		List<IMenuBar> getMenuBarItems();
		/**
		 * set all texts of view in function of the language
		 * @param languages : language texts
		 * @param language : language name
		 */
		public void setTexts(LanguageModel languages, String language);
	}
