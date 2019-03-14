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
}
