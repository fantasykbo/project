package real.dto;

public class NewsDTO {
	
	String title;
	String link;
	String[] pubdate;
	String description;

	public NewsDTO(String title, String link, String[] pubdate, String description) {
		this.title = title;
		this.link = link;
		this.pubdate = pubdate;
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String[] getPubdate() {
		return pubdate;
	}
	public void setPubdate(String[] pubdate) {
		this.pubdate = pubdate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
