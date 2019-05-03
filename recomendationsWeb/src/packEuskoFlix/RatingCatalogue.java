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
		this.valorationMode= new ContentFilter();
		this.normalizeMode=new NonNormalized(); //por defecto no normaliza
	}
	public static RatingCatalogue getRatingCatalogue() {
		if (mRatingCatalogue == null) {
			mRatingCatalogue = new RatingCatalogue();
		}
		return mRatingCatalogue;
	}
	public void initialize() {
		this.ratingMatrix= new Matrix(); //resetea la matriz
		this.valorationMode.initialize(); //la inicializa
	}
	public void addOneLine(int pUserId, int pFilmId,double pRank) {
		ratingMatrix.addData(pUserId, pFilmId, pRank);
	}
	
	public VectorInteger recommendFilm(int pId) {
		return(this.valorationMode.recommendedFilm(pId));
	}
	
	public VectorInteger getFilmsFromUser(int pIDUser) {
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
	public VectorInteger getAllUsers() {
		return(this.ratingMatrix.getFirstKeyList());
	}
	public VectorInteger getNonViewFilmsFor(int pIdUser) {
		VectorInteger films= new VectorInteger();
		VectorInteger allfilms=FilmCatalogue.getFilmCatalogue().getAllFilms();
		VectorInteger userFilms=ratingMatrix.getSecondKeyList(pIdUser);
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
	public VectorInteger getFilmsWithMorePuntuation(int pIdUser, double pValoration) {
		//CONSIGUE LAS PELICULAS DE UN USUARIO QUE LES HA DADO UNA VALORACION MAYOR O IGUAL A pValoration
		return(this.ratingMatrix.getSecondKeyWithMoreValue(pIdUser, pValoration));
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
		this.initialize();
	}
}
