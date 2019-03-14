package packEuskoFlix;

public class Film implements Comparable<Film>{
	private String name;
	private String id;
	
	public Film(String pName, String pId) {
		this.name = pName;
		this.id = pId;
	}

	//TODO
	public int compareTo(Film pFilm) {}
}