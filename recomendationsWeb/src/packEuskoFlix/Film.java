package packEuskoFlix;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Film implements Comparable<Film>{
	private String name;
	private Integer id;
	private ArrayList<String> tagList;
	
	public Film(String pName, Integer pId) {
		this.name = pName;
		this.id = pId;
		tagList = new ArrayList<String>();
	}

	//TODO
	public int compareTo(Film pFilm) {
		return this.name.compareTo(pFilm.name);
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
	public void addTag(String pTag) {
		tagList.add(pTag);
	}	

	public String getName() {
		return name;
	}
	
	public String allTags() {
		String text = "";
		for (int i = 0; i < tagList.size(); i++) {
			text += tagList.get(i)+"\n";			
		}
		return text;
	}
	
}