package Models;

import java.util.List;
import java.util.Arrays;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ShortCutBindModel {
	Property<Boolean> one;
	
	ObservableList<String> commands;
	ObservableList<String> letters;
	String description;
	String name;
	String value;
	Property<String> command;
	Property<String> letter;

	public ShortCutBindModel(boolean one, String description, String name, String com, String letter, String value){
		
		this.one = new SimpleBooleanProperty(one);
		this.description = description;
		this.command = new SimpleStringProperty(com);
		this.letter = new SimpleStringProperty(letter);
		this.value = value;
		List<String> commandlist = Arrays.asList("Ctrl", "Alt", "Shift");
		List<String> letterslist = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "w", "X", "Y", "Z");
		
		commands = FXCollections.observableArrayList(commandlist);
		letters = FXCollections.observableArrayList(letterslist);
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Property<Boolean> getOne() {
		return one;
	}
	public void setOne(Property<Boolean> one) {
		this.one = one;
	}
	public ObservableList<String> getCommands() {
		return commands;
	}
	public void setCommands(ObservableList<String> commands) {
		this.commands = commands;
	}
	public ObservableList<String> getLetters() {
		return letters;
	}
	public void setLetters(ObservableList<String> letters) {
		this.letters = letters;
	}

	public Property<String> getCommand() {
		return command;
	}

	public void setCommand(Property<String> command) {
		this.command = command;
	}

	public Property<String> getLetter() {
		return letter;
	}

	public void setLetter(Property<String> letter) {
		this.letter = letter;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
