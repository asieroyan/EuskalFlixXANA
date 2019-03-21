package packEuskoFlix;

import view.Frame;

public class FileTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFiles files = MyFiles.getMyFiles();
		files.loadFilmCatalogue();
		FilmCatalogue filmCatalogue = FilmCatalogue.getFilmCatalogue();
		files.loadFilmRatings();
		files.loadFilmTags();
		UserCatalogue userCatalogue = UserCatalogue.getUserCatalogue();
		//filmCatalogue.printFilms();
		//userCatalogue.printUsers();		
		Frame mainFrame = new Frame();
		mainFrame.setVisible(true);
	}

}
