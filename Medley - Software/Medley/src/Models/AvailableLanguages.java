package Models;

public class AvailableLanguages {
	String key;
	String name;
	
	public AvailableLanguages(String key, String name){
		this.key = key;
		this.name = name;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
