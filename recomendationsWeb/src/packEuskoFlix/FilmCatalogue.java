package packEuskoFlix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FilmCatalogue {
	private FilmList list;
	private static FilmCatalogue mFilmCatalogue;
	
	private FilmCatalogue() {
		list = new FilmList();
	}
	
	public static FilmCatalogue getFilmCatalogue() {
		if (mFilmCatalogue == null) {
			mFilmCatalogue = new FilmCatalogue();
		}
		return mFilmCatalogue;
	}
	
	//TODO
	public void order() {}
	public void add(Film pFilm) {	
		list.add(pFilm);
	}
	
	public void add(Integer pID, Film pFilm) {
		list.add(pID, pFilm);
	}
	
	public void printFilms() {
		System.out.println("Films Imported:"+this.list.size());
		System.out.println("--------------------------------------------------------------------------------------");
		list.printFilms();
	}
	
	public Film searchFilmByID(Integer pID) {
		//DEVUELVE LA PELICULA, EN CASO DE NO ESTAR, DEVUELVE NULL
		return(this.list.searchFilmByID(pID));
	}
	
	public FilmList getList() {
		return this.list;
	}
	
	public void loadFilmCatalogue() {
		File file = new File(System.getProperty("user.dir"),"movie-titles.csv");
		if(!file.exists()) {
			System.out.println("File not found");
		}else {
			Scanner sc = null;
			try {
				sc = new Scanner(file);
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			String information;
			String[] v1 = new String[2];     //the information split in two parts
			while(sc.hasNext()) {
				information = sc.nextLine();
				v1 = information.split(";");
				Integer idFilm=Integer.parseInt(v1[0]);
				Film film = new Film(v1[1],idFilm); //first id, second filmId
				this.add(idFilm,film);
			}
		}
	}
	public void loadFilmTags() {
		File file = new File(System.getProperty("User.dir"),"movie-tags.csv");
		if(!file.exists()) {
			System.out.println("File not found");
		}else {
			Scanner sc = null;
			try {
				sc = new Scanner(file);
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			String information;
			String[] v1 = new String[2];     //the information split in two parts (film id/Tag)
			while(sc.hasNext()) {
				information = sc.nextLine();
				v1 = information.split(";");
				Integer idFilm=Integer.parseInt(v1[0]);
				String tag= v1[1];
				this.searchFilmByID(idFilm).addTag(tag);				
			}
		}
	}
}
