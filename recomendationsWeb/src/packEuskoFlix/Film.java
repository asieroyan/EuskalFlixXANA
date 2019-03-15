package packEuskoFlix;

import java.util.ArrayList;
import java.util.Collection;

public class Film implements Comparable<Film>{
	private String name;
	private Integer id;
	private Collection<Review> reviewList;
	
	public Film(String pName, Integer pId) {
		this.name = pName;
		this.id = pId;
		reviewList = new ArrayList<Review>();
	}

	//TODO
	public int compareTo(Film pFilm) {
		return name.compareTo(pFilm.name);
	}
	public Integer getID() {
		return this.id;
	}
	public int compareToByID(Film pFilm) {
		return id.compareTo(pFilm.id);
	}
	public void printFilm(){
		System.out.println("Film ID: "+this.id+"---Film Name: "+this.name);
	}
}