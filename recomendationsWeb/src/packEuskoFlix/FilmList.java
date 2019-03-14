package packEuskoFlix;

import java.util.HashMap;

public class FilmList {
	private HashMap<String,Film> list;
	
	public FilmList() {
		this.list = new HashMap<String,Film>();
	}
	
	//TODO
	public void add(Film pFilm) {
		list.put(pFilm.getName(), pFilm);
	}
	
	//TODO
	public void remove(Film pFilm) {
		list.remove(pFilm.getName());
	}
	
	//TODO
	public boolean contains(Film pFilm) {
		return list.containsKey(pFilm.getName());
	}
	
	public int print() {
		return list.size();
	}
}