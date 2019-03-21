package packEuskoFlix;

import java.util.HashMap;

public class FilmList {
	private HashMap<Integer,Film> list;
	
	public FilmList() {
		this.list = new HashMap<Integer,Film>();
	}
	
	public void add(Film pFilm) {
		list.put(pFilm.getID(), pFilm);
	}
	public void add(Integer pID,Film pFilm) {
		//METODO PARA EVITAR UTILIZAR EL GETTER
		if (!this.contains(pFilm)){
			list.put(pID,pFilm);
		}
	}
	
	//TODO
	public void remove(Film pFilm) {
		if (this.contains(pFilm)) {
			list.remove(pFilm.getID());
		}
	}
	
	//TODO
	public boolean contains(Film pFilm) {
		return list.containsKey(pFilm.getID());
	}
	
	public int size() {
		return list.size();
	}
	public void printFilms() {
		Film[] myFilms=this.list.values().toArray(new Film[this.list.size()]);
		this.sortByID(myFilms);
		int size=myFilms.length;
		for (int i=0;i<size;i++) {
			myFilms[i].printFilm();
		}
		
	}
	public Film searchFilmByID(Integer pID) {
		Film myFilm=null;
		myFilm=this.list.get(pID);
		return myFilm;
	}
	private void sortByID(Film[] myFilms) {
		//METODO TEMPORAL PARA COMPROBAR QUE SE IMPORTAN BIEN LAS PELICULAS
		sortByID(myFilms,0,myFilms.length-1);
	}
	private void sortByID(Film[] myFilms, int start, int end) {
		if (end-start>0) {
			int iSwap= swap(myFilms,start,end);
			sortByID(myFilms,start,iSwap-1);
			sortByID(myFilms,iSwap+1,end);
		}
	}
	private int swap(Film[] myFilms, int start,int end) {
		Film pivot= myFilms[start];
		int left= start;
		int right= end;
		while (left<right) {
			while(myFilms[left].compareToByID(pivot)<=0 && left<right)
				left++;
			while(myFilms[right].compareToByID(pivot)>0)
				right--;
			if(left<right)
				swap(myFilms,left,right);
		}
		myFilms[start]=myFilms[right];
		myFilms[right]=pivot;
		return right;
	}
	
	public HashMap<Integer,Film> getHashMap() {
		return list;
	}
	
	public Film getByName(String pName) {		
		Film oneFilm;
		Film[] myFilms=this.list.values().toArray(new Film[this.list.size()]);
		int size=myFilms.length;
		for (int i=0;i<size;i++) { 
			oneFilm = myFilms[i];		
			if (oneFilm.getName().equals(pName)) {
				return oneFilm;
			}
		}
		return null;		
	}
}