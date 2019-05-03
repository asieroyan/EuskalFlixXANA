package packEuskoFlix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class ContentFilter extends FilterMode {
	private ModelMatrix productModel; //lo generamos
	private ModelMatrix userModel; //lo generamos
	//private VectorString lista;
	public ContentFilter() {
		super();
		this.productModel= new ModelMatrix();
	    this.userModel= new ModelMatrix();
	}
	public void divideLineAdd(String pInformation) { //posible optimizacion
		String[] v1 = new String[3];
		v1 = pInformation.split(",");
		Integer idUser=Integer.parseInt(v1[0]);
		Integer idFilm=Integer.parseInt(v1[1]);
		Double rate = Double.parseDouble(v1[2]);
		RatingCatalogue.getRatingCatalogue().addOneLine(idUser, idFilm, rate);
	}
	public Matrix getEstimatedRatings(Integer pIdUser) {
		this.calculateProductModel();
		this.calculateUserModel(pIdUser);
		//ESTE METODO CONSIGUE LAS VALORACIONES QUE ESTIMAMOS QUE DARIA UN USUARIO A LAS PELICULAS QUE NO HA VISTO
		RatingCatalogue ratingList=RatingCatalogue.getRatingCatalogue();
		Matrix estimatedRatings= new Matrix(); //USER FILM RATINGESTIMATED
		VectorInteger nonViewFilms= ratingList.getNonViewFilmsFor(pIdUser);
		Iterator<Integer> itr=nonViewFilms.iterator(); //lista de peliculas no vistas
		while (itr.hasNext()) {
			//recorro las peliculas no vistas
			Integer filmAct= itr.next();
			double estimatedRating=this.getEstimatedValorationForFilm(pIdUser, filmAct);
			estimatedRatings.addData(pIdUser, filmAct, estimatedRating);
			//i++;
		}
		estimatedRatings.print(pIdUser);
		return estimatedRatings;
	}
	public Double getEstimatedValorationForFilm(Integer pIdUser, Integer pFilm) {
		VectorString filmTags=this.getTagsForFilm(pFilm);
		VectorString userTags=this.userModel.getTagList(pIdUser);
		userTags.addvaluesFromVector(filmTags);
		Double sumNum=0.0; //guarda el sumatorio del numerador
		Double dem1=0.0; //guarda la primera raiz del denominador
		Double dem2=0.0; //guarda la segunda raiz del denominador
		Double sumDem=dem1*dem2; //guarda el sumatorio del denominador
		Double similitud=0.0;
		Iterator<String> itr=userTags.iterator();
		while (itr.hasNext()) {
			String tagAct= itr.next();
			Double valoration1=this.userModel.getValue(pIdUser, tagAct);
			Double valoration2=this.productModel.getValue(pFilm, tagAct);
			Double num=valoration1*valoration2;
			sumNum+=num;
			dem1+=Math.pow(valoration1, 2);
			dem2+=Math.pow(valoration2, 2);
			}
		sumDem= (Math.sqrt(dem1))*(Math.sqrt(dem2));
		similitud=sumNum/sumDem;	
		return similitud;
	}

	private void calculateProductModel() {
		//CALCULA EL MODELO DE PRODUCTO
		VectorInteger films=FilmCatalogue.getFilmCatalogue().getAllFilms();
		Iterator<Integer> itr= films.iterator();
		while (itr.hasNext()) {
			int filmAct=itr.next();
			this.addFilmToModel(filmAct);
			this.productModel.adaptImportancyOfFilm(filmAct);
		}
	}
	private void addFilmToModel(int pFilm) {
		VectorString filmtags= new VectorString();
		filmtags= this.getTagsForFilm(pFilm);
		Iterator<String> itr= filmtags.iterator();
		while (itr.hasNext()) {
			String tagAct= itr.next();
			double tfidf=this.tagImportanceInFilm(pFilm, tagAct);
			//System.out.println("Film= "+pFilm+"tag= "+tagAct+"tfid= "+tfidf);
			this.productModel.add(pFilm, tagAct, tfidf);
		}
	}
	private void calculateUserModel(int pIdUser) {
		//CALCULAMOS EL MODELO DE PERSONA
		RatingCatalogue ratings= RatingCatalogue.getRatingCatalogue();
		VectorInteger films=ratings.getFilmsWithMorePuntuation(pIdUser, 3.5); //coge las peliculas a las que les ha votado mas de 3.5
		Iterator<Integer> itr= films.iterator();
		//HACEMOS LAS SUMAS
		while (itr.hasNext()) { //recorremos las peliuculas
			int filmAct= itr.next();
			VectorString tags=this.getTagsForFilm(filmAct);
			this.sumTagForUser(pIdUser, filmAct, tags);
		}	
	}
	private void sumTagForUser(int pIdUser, int pFilm, VectorString pTags) {
		Iterator<String> itr=pTags.iterator();
		while (itr.hasNext()) {
			String tagAct= itr.next();
			double filmtf=this.productModel.getValue(pFilm, tagAct);
			this.userModel.sumValue(pIdUser, tagAct, filmtf);
		}
		
	}
	private Double tagImportanceInFilm(int pFilmId, String pTag) {
		//calcula tfidf
		int tagAparitions = this.tagAparitionsInFilm(pFilmId, pTag);
		int filmsNum = FilmCatalogue.getFilmCatalogue().getAllFilms().size();
		int filmsWithThisTag = this.getFilmNumWithTag(pTag);
		Double output = tagAparitions * Math.log((filmsNum)/(filmsWithThisTag));
		return output;
	}
	
	private int tagAparitionsInFilm(int pFilmId, String pTag) {
		TagCatalogue tags= TagCatalogue.getTagCatalogue();
		return(tags.getTagAparitions(pFilmId, pTag));
	}
	private VectorString getTagsForFilm(int pFilmId) {
		TagCatalogue tags= TagCatalogue.getTagCatalogue();
		return (tags.getTagsForFilm(pFilmId));
	}
	
	private VectorInteger getFilmsWithTag(String pTag) {
		TagCatalogue tags= TagCatalogue.getTagCatalogue();
		return(tags.getFilmsWithTag(pTag));
	}
	
	private int getFilmNumWithTag(String pTag) {
		//TagCatalogue tags= TagCatalogue.getTagCatalogue();
		return getFilmsWithTag(pTag).size();
	}

}