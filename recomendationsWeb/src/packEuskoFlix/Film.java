package packEuskoFlix;

import java.util.ArrayList;
import java.util.Collection;

public class Film implements Comparable<Film>{
	private String name;
	private String id;
	private Collection<Review> reviewList;
	
	public Film(String pName, String pId) {
		this.name = pName;
		this.id = pId;
		reviewList = new ArrayList<Review>();
	}

	//TODO
	public int compareTo(Film pFilm) {
		return name.compareTo(pFilm.getName());
	}
	public String getName() {
		return name;
	}
}