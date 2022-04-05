package manam.kiran.models;

public class Movie {

	private String id;
	private String title;
	private String overview;
	private String original_title;

	public String getOriginal_title() {
		return original_title;
	}

	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}

	public Movie() {

	}

	public Movie(String id, String title, String overview) {
		this.id = id;
		this.title = title;
		this.overview = overview;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	
	
}
