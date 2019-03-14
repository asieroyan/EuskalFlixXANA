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
	public void add(Film film) {	
		list.add(film);
	}
	
	public void printFilms() {
		System.out.println("Films Imported:"+this.list.size());
		System.out.println("--------------------------------------------------------------------------------------");
		list.printFilms();
	}
}
