package sample.model.object;

public class ScoreInfos {
	private String title;
	private String author;
	private String date;
	
	public ScoreInfos(String title, String author, String date)
	{
		this.title = title;
		this.author = author;
		this.date = date;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		String ret = new String();
		ret += "titre : ";
		ret += this.title;
		ret += " auteur : ";
		ret += this.author;
		ret += " date : ";
		ret += this.date;
		
		
		
		return ret;
	}
}
