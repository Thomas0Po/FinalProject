package Abstractions.Controllers;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Abstractions.Views.IMenuBar;

/**
 * 
 * @author Persus
 * this class is the Abstract class for each module controller.
 * it extends java.util.Observable for the communication with the main controller of the application and implement IController interface
 */

public abstract class AController extends Observable implements IController {	
	/**
	 * link the observer with the class
	 */
	public void addObserver(Observer ob){
		super.addObserver(ob);
	}
}
