package packEuskoFlix;

public class EuskoFlix {
	private static EuskoFlix mEuskoFlix;
	private EuskoFlix() {}
	public static EuskoFlix getEuskoFlix() {
		if (mEuskoFlix == null) {
			mEuskoFlix = new EuskoFlix();
		}
		return mEuskoFlix;
	}
	
	public void initialize() {
		FilmCatalogue.getFilmCatalogue().initializeTitles();
		FilmCatalogue.getFilmCatalogue().initializeTags();
		RatingCatalogue.getRatingCatalogue().initialize();
	}
}