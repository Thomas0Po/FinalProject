package Abstractions.Models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.input.KeyCode;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ShortCutsListModel {
	Map<String, ShortCutBindModel> shortcuts;
	public Map<String, ShortCutBindModel> getShortcuts() {
		return shortcuts;
	}

	Map<String, KeyCode> keycodes;
	
	
	static Document document;
	static Element racine;
	File file = new File("src/Ressources/Configs/shortcuts.xml");
	
	public ShortCutsListModel() {
		keycodes = new HashMap<String, KeyCode>();
		fillkeyCodes();
		shortcuts = new HashMap<String, ShortCutBindModel>();
		
		
		
		
		SAXBuilder sxb = new SAXBuilder();
		try {

			document = sxb.build(file);

		} catch (Exception e) {
			System.out.println("FILE ERROR : " + e);
			
		}
		racine = document.getRootElement();
		List<Element> t = racine.getChildren();
		for (Element element : t) {
			ShortCutBindModel shortcut;
			String value = element.getValue();
			String com = (value.contains("+") ? value.substring(0, value.lastIndexOf("+")): "");
			String letter = value.substring(value.lastIndexOf("+") + 1, value.length());
			
			shortcut = new ShortCutBindModel(!element.getValue().contains("+"), element.getAttributeValue("description"), element.getName(), com, letter, value);
			shortcuts.put(element.getName(), shortcut);
		}
	}

	private void fillkeyCodes() {
		keycodes.put("ctrl", KeyCode.CONTROL);
		keycodes.put("alt", KeyCode.ALT);
		keycodes.put("shift", KeyCode.SHIFT);
		keycodes.put("a", KeyCode.A);
		keycodes.put("b", KeyCode.B);
		keycodes.put("c", KeyCode.C);
		keycodes.put("d", KeyCode.D);
		keycodes.put("e", KeyCode.E);
		keycodes.put("f", KeyCode.F);
		keycodes.put("g", KeyCode.G);
		keycodes.put("h", KeyCode.H);
		keycodes.put("i", KeyCode.I);
		keycodes.put("j", KeyCode.J);
		keycodes.put("k", KeyCode.K);
		keycodes.put("l", KeyCode.L);
		keycodes.put("m", KeyCode.M);
		keycodes.put("n", KeyCode.N);
		keycodes.put("o", KeyCode.O);
		keycodes.put("p", KeyCode.P);
		keycodes.put("q", KeyCode.Q);
		keycodes.put("r", KeyCode.R);
		keycodes.put("s", KeyCode.S);
		keycodes.put("t", KeyCode.T);
		keycodes.put("u", KeyCode.U);
		keycodes.put("v", KeyCode.V);
		keycodes.put("w", KeyCode.W);
		keycodes.put("x", KeyCode.X);
		keycodes.put("y", KeyCode.Y);
		keycodes.put("z", KeyCode.Z);
	}
	
	public KeyCode getKeyCode(String key){
		return (this.keycodes.get(key));
	}
	
	public boolean save(/*List<ShortCutBindModel> newModel*/){
		List<Element> t = racine.getChildren();
		//this.shortcuts. = newModel.getShortcuts();
		
		for (Element elem : t){
			ShortCutBindModel scut = this.shortcuts.get(elem.getName());
			String val = (scut.getOne().getValue() == Boolean.FALSE ? scut.getCommand().getValue() + "+" + scut.getLetter().getValue() : scut.getLetter().getValue());
			if (!val.equals(elem.getValue())){
				elem.setText(val);
				System.out.println("VAL = " + val);
				scut.setValue(val);
			}
		}
		
		
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
        try {
			sortie.output(document, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		return true;
	}
	
}
