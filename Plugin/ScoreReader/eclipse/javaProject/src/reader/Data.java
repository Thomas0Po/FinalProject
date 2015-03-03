package reader;

import java.io.File;

import reader.controller.plugScoreController;
import Communication.MedleyTempo;

public class Data
{		
	
	public File 				file;
	public MedleyTempo 			tempo;
	public plugScoreController 	controller;
	
	/** Constructeur privé */	
	private Data()
	{}
 
	/** Holder */
	private static class DataHolder
	{		
		/** Instance unique non préinitialisée */
		private final static Data instance = new Data();
	}
 
	/** Point d'accès pour l'instance unique du singleton */
	public static Data getInstance()
	{
		return DataHolder.instance;
	}
}
