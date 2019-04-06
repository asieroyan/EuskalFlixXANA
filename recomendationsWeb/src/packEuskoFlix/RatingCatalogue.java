package packEuskoFlix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

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
	
	public void initialize() {
		File file = new File(System.getProperty("user.dir"),"movie-ratings.csv");
		String information;
		Scanner sc = null;
		
		if (correctFile(file)) {
		
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
			
			//the information split in two parts
			while(sc.hasNext()) {
				information = sc.nextLine();
				divideLineAdd(information);				
		}
			sc.close();
	}
	
	private boolean correctFile(File pFile) {
		if(!pFile.exists()) {
			System.out.println("File not found");
			return false;
		}
		else 
		{
			return true;			
		}
	}
	
	private void divideLineAdd(String pInformation) {
		String[] v1 = new String[3];
		v1 = pInformation.split(";");
		Integer idUser=Integer.parseInt(v1[0]);
		Integer idFilm=Integer.parseInt(v1[1]);
		Double rate = Double.parseDouble(v1[2]);
		addOneLine(idUser, idFilm, rate);
	}
	
	private void addOneLine(int pUserId, int pFilmId,double pRank) {
		ratingMatrix.addData(pUserId, pFilmId, pRank);
	}
	
	public Film recommendFilm(int pId) {
		Film a = new Film(1,"a");
		return a;
	}
	
	public Vector getFilmsFromUser(int pIDUser) {
		return(this.ratingMatrix.getSecondKeyList(pIDUser));
	}
	
	private Double getMean (int pIdUser) {
		return 0.0;
	}
}
