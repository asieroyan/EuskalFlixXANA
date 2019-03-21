package packEuskoFlix;

import view.Frame;

public class FileTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FilmCatalogue filmCatalogue = FilmCatalogue.getFilmCatalogue();
		filmCatalogue.loadFilmCatalogue();
		UserCatalogue userCatalogue = UserCatalogue.getUserCatalogue();
		userCatalogue.loadFilmRatings();
		filmCatalogue.loadFilmTags();
		//filmCatalogue.printFilms();
		//userCatalogue.printUsers();		
		Frame mainFrame = new Frame();
		mainFrame.setVisible(true);
	}

}
