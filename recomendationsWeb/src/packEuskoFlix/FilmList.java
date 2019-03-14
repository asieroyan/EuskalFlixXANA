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
}