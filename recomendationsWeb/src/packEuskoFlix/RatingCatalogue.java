package packEuskoFlix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class RatingCatalogue {
	private static RatingCatalogue mRatingCatalogue;
	private Matrix ratingMatrix;
	private UserFilter valorationMode; //posible strategy
	private RatingCatalogue() {
		this.ratingMatrix = new Matrix();
		this.valorationMode= new UserFilter();
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
		v1 = pInformation.split(",");
		Integer idUser=Integer.parseInt(v1[0]);
		Integer idFilm=Integer.parseInt(v1[1]);
		Double rate = Double.parseDouble(v1[2]);
		addOneLine(idUser, idFilm, rate);
	}
	
	private void addOneLine(int pUserId, int pFilmId,double pRank) {
		ratingMatrix.addData(pUserId, pFilmId, pRank);
	}
	
	public Vector recommendFilm(int pId) {
		return(this.valorationMode.recommendedFilm(pId));
	}
	
	public Vector getFilmsFromUser(int pIDUser) {
		return(this.ratingMatrix.getSecondKeyList(pIDUser));
	}
	
	private Double getMean (int pIdUser) {
		return 0.0;
	}
	public double getValoration(Integer pUser, Integer pFilm) {
		double valoration=0.0;
		if (this.ratingMatrix.containsKeys(pUser, pFilm)){
			valoration=this.ratingMatrix.getValue(pUser, pFilm);
		}
		return valoration;
	}
	public Vector getAllUsers() {
		return(this.ratingMatrix.getFirstKeyList());
	}
	public Vector getNonViewFilmsFor(int pIdUser) {
		Vector films= new Vector();
		Vector allfilms=FilmCatalogue.getFilmCatalogue().getAllFilms();
		Vector userFilms=ratingMatrix.getSecondKeyList(pIdUser);
		films= allfilms.getNonCommonValuesWith(userFilms);
		return films;
	}
	public boolean hasValorated(int pIdUser, int pIdFilm) {
		return (this.ratingMatrix.containsKeys(pIdUser, pIdFilm));
	}
}
