package packEuskoFlix;

public class FileTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFiles files = new MyFiles();
		files.loadFilmCatalogue();
		FilmCatalogue filmCatalogue = FilmCatalogue.getFilmCatalogue();
		filmCatalogue.printFilms();
	}

}
