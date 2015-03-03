package Views;

import javafx.scene.control.TextField;

public class TextFieldSetExt extends TextField {
	
	@SuppressWarnings("unused")
	private String value;
	
	public TextFieldSetExt()
	{
		value = new String();
	}
		
	public TextFieldSetExt(String text) {
		this.setValue(text);
	}
	
	@Override 
	public void replaceText(int start, int end, String text) {               
		if (text.matches("[a-z]") || text.matches("[A-Z]") || text.matches(";") || text.hashCode() == 0) {
			super.replaceText(start, end, text);
			}
	}

	public void setValue(String value) {
		System.out.println("value = " + value);
		this.setText(value);
	}
}
