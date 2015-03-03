package application;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

import javafx.beans.value.ObservableValue;

public class ColumnModel {
	private String name;
	private String type;
	private String date;
	static private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
	
	public ColumnModel(String name, String type)
	{
		this.name = name;
		this.type = type;
		this.date = dateFormat.format(new Date());
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return this.date;
	}
	public void setDate(Date date) {
		this.date = dateFormat.format(date);
	}
}
