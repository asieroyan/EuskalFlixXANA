package packEuskoFlix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class RatingCatalogue {
	private static RatingCatalogue mRatingCatalogue;
	private Matrix ratingMatrix;
	private FilterMode valorationMode; //posible strategy
	private NormalizeMode normalizeMode; //PATRON STRATEGY
	
	private RatingCatalogue() {
		this.ratingMatrix = new Matrix();
		this.valorationMode= new UserFilter();
		this.normalizeMode=new NonNormalized(); //por defecto no normaliza
		this.initialize();
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
		//CALCULOS EXTRA PARA EL PRODUCTFILTER
		if (this.valorationMode instanceof ProductFilter) {
			((ProductFilter) this.valorationMode).addUsersWhoHasValorated(idFilm, idUser);
		}
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
	public double getValoration(Integer pUser, Integer pFilm) {
		double valoration=0.0;
		if (this.ratingMatrix.containsKeys(pUser, pFilm)){
			valoration=this.ratingMatrix.getValue(pUser, pFilm);
		}
		return valoration;
	}
	public Matrix getMatrix() {
		return this.ratingMatrix;
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
	public Matrix normalizeMatrix() {
		return(this.normalizeMode.normalizeMatrix());
	}
	public Matrix unNormalizeMatrix(int pIdUser,Matrix pMatrix) {
		return(this.normalizeMode.unNormalizeMatrix(pIdUser, pMatrix));
	}
	public void changeNormalizeMode() { //Cambia el modo de normalizar
		//SI ESTA EN NORMALIZACION POR MEDIA CAMBIA A SIN NORMALIZAR
		//SI ESTA EN SIN NORMALIZAR CAMBIA A NORMALIZACION POR MEDIA
		if (this.normalizeMode instanceof NonNormalized) {
			this.normalizeMode=new NormalizeByMedian();
		}
		else if (this.normalizeMode instanceof NormalizeByMedian) {
			this.normalizeMode=new NonNormalized();
		}
	}
	public void changeValorationMode() { //Cambia el modo de valorar
		if (this.valorationMode instanceof UserFilter) {
			this.valorationMode=new ProductFilter();
		}
		else if (this.valorationMode instanceof ProductFilter) {
			this.valorationMode=new UserFilter();
		}
	}
}
