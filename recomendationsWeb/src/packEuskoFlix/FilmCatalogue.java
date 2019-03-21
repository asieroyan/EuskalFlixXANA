package packEuskoFlix;

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
}
