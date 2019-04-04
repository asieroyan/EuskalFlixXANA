package packEuskoFlix;

import java.util.HashMap;

public class FilmCatalogue {
	private static FilmCatalogue mFilmCatalogue;
	private HashMap<Integer,Film> list;
	
	private FilmCatalogue() {
		this.list = new HashMap<Integer,Film>();
	}
	
	public static FilmCatalogue getFilmCatalogue() {
		if (mFilmCatalogue == null) {
			mFilmCatalogue = new FilmCatalogue();
		}
		return mFilmCatalogue;
	}
	
	public void initializeTitles(String pfile) {
		
	}
	
	public void initializeTags(String pFile) {
		
	}
	
	private void divideLineAddTags(String pLine) {
		
	}
	
	private void divideLineAddTitle(String pLine) {
		
	}
	
	private boolean correctFile(File pFile) {
		return true;
	}
	
	private int getLength() {
		return list.size();
	}
}
