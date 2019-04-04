package packEuskoFlix;

public class RatingCatalogue {
	private static RatingCatalogue mRatingCatalogue;
	private Matrix ratingMatrix;
	
	private RatingCatalogue() {
		this.ratingMatrix = new Matrix();
	}
	public static RatingCatalogue getRatingCatalogue() {
		if (mRatingCatalogue == null) {
			mRatingCatalogue = new RatingCatalogue();
		}
		return mRatingCatalogue;
	}
	
	public void initialize(String pFile) {
		
	}
	
	private void divideLineAdd(String pLine) {
		
	}
	
	private void addOneLineOfFile(int pUserId, int pFilm, double pRate) {
		this.ratingMatrix.addData(pUserId, pFilm, pRate);
	}
	
	public Film recommendFilm(int pId) {
		Film a = new Film(1,"a");
		return a;
	}
	
	private void filmInCommon(int pIdUser1, int pIdUser2, int pIdFilm) {
		
	}
	
	private Double getMean (int pIdUser) {
		return 0.0;
	}
}
