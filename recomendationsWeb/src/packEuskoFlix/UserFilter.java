package packEuskoFlix;

import java.util.Iterator;

public class UserFilter {
	public UserFilter() {
	}
	public Vector recommendedFilm(Integer pIdUser) {
		Matrix films= this.getEstimatedRatings(pIdUser);
		return (films.getSecondKeySortedByValues(pIdUser, 10)); //obtiene las 10 peliculas que mas pueden gustar a un usuario
	}
	private Matrix getEstimatedRatings(Integer pIdUser) {
		RatingCatalogue ratingList=RatingCatalogue.getRatingCatalogue();
		Matrix estimatedRatings= new Matrix(); //USER FILM RATINGESTIMATED
		Vector nonViewFilms= ratingList.getNonViewFilmsFor(pIdUser);
		Iterator<Integer> itr=nonViewFilms.iterator(); //lista de peliculas no vistas
		while (itr.hasNext()) {
			//recorro las peliculas no vistas
			Integer filmAct= itr.next();
			Double estimatedRating=this.getEstimatedValorationForFilm(pIdUser, filmAct);
			estimatedRatings.addData(pIdUser, filmAct, estimatedRating);
		}
		estimatedRatings.print(pIdUser);
		return estimatedRatings;
	}
	private Double getEstimatedValorationForFilm(Integer pIdUser,Integer pFilm) {
		Double estimatedValoration=0.0;
		RatingCatalogue ratingList=RatingCatalogue.getRatingCatalogue();
		Matrix similitudeMatrix= this.calculateNSimilarUsers(pIdUser, 30, pFilm); //valor 30 por defecto
		Vector similitudes=similitudeMatrix.getSecondKeyList(pIdUser); //lista de similitudes del usuario con los demas
		Iterator<Integer> users=similitudes.iterator(); //lista de usuarios
		Double sumNum=0.0;
		Double sumDem=0.0;
		while (users.hasNext()) {
			Integer userAct= users.next();
			Double similitudeAct=Math.abs(similitudeMatrix.getValue(pIdUser, userAct));
			Double ratingAct=ratingList.getValoration(userAct, pFilm);
			//System.out.println("User = "+userAct+"    Similitude= "+similitudeAct+ "    Rating ="+ratingAct);
			sumNum+=(ratingAct*similitudeAct);
			sumDem+=similitudeAct;
			}
		estimatedValoration=sumNum/sumDem;	
		return estimatedValoration;
	}
	private Matrix calculateNSimilarUsers(Integer pIdUser, Integer pN, Integer pFilm) {
		Matrix similitudes=this.calculateSimilitudeWithOtherUsers(pIdUser);
		Matrix similitudesFiltered= this.obtainMatrixWhoHasValuedFilm(pIdUser, pFilm, similitudes);
		Matrix nSimilitude=similitudesFiltered.getMatrixWithSecondKeySortedByValues(pIdUser, pN);
		return nSimilitude;
	}
	private Matrix obtainMatrixWhoHasValuedFilm(int pIdUser, int pFilm, Matrix pMatrix) { //obtiene la matriz de similitud con los usuarios que han votado a esa pelicula
		Matrix newMatrix= new Matrix();
		Vector listUsers=pMatrix.getSecondKeyList(pIdUser); //lista de todos los usuarios
		RatingCatalogue ratings=RatingCatalogue.getRatingCatalogue();
		Iterator<Integer> itr=listUsers.iterator(); //los recorro
		while (itr.hasNext()) {
			Integer userAct=itr.next();
			if (ratings.hasValorated(userAct, pFilm)) { //si la ha valorado
				Double similitude=pMatrix.getValue(pIdUser, userAct);
				newMatrix.addData(pIdUser,userAct,similitude);
			}
		}
		return newMatrix;
	}
	private Matrix calculateSimilitudeWithOtherUsers(Integer pIdUser) {
		RatingCatalogue ratings= RatingCatalogue.getRatingCatalogue();
		Vector users=ratings.getAllUsers();
		Iterator<Integer> itr=users.iterator();
		Matrix similitudeMatrix= new Matrix();
		while (itr.hasNext()) {
			Integer userAct=itr.next();
			if (!userAct.equals(pIdUser)){ //no es el mismo usuario
				Double similitude=this.calculateSimilitude(pIdUser, userAct);
				similitudeMatrix.addData(pIdUser, userAct, similitude);
			}
		}
		return similitudeMatrix;
	}
	public Double calculateSimilitude( Integer pId1, Integer pId2) { //calcula la similitud entre dos usuarios
		//System.out.println("Usuario1-Usuario2"+pId1+"   "+pId2);
		Vector filmsInCommon= this.filmsInCommon(pId1, pId2); //consigo las peliculas en comun
		RatingCatalogue ratings= RatingCatalogue.getRatingCatalogue();
		Double sumNum=0.0; //guarda el sumatorio del numerador
		Double sumDem=0.0; //guarda el sumatorio del denominador
		Double dem1=0.0; //guarda la primera raiz del denominador
		Double dem2=0.0; //guarda la segunda raiz del denominador
		Double similitud=0.0;
		Iterator<Integer> itr=filmsInCommon.iterator();
		while (itr.hasNext()) {
			Integer film= itr.next();
			Double valoration1=ratings.getValoration(pId1,film);
			Double valoration2=ratings.getValoration(pId2,film);
			Double num=valoration1*valoration2;
			sumNum+=num;
			dem1+=Math.pow(valoration1, 2);
			dem2+=Math.pow(valoration2, 2);
			}
		sumDem= (Math.sqrt(dem1))*(Math.sqrt(dem2));
		similitud=sumNum/sumDem;	
		//System.out.println("Similitud="+similitud);
		return similitud;
	}
	
	private Vector filmsInCommon(Integer pId1, Integer pId2) {
		Vector films= new Vector();
		RatingCatalogue ratings= RatingCatalogue.getRatingCatalogue();
		Vector user1films=ratings.getFilmsFromUser(pId1); //obtengo las peliculas de usuario1
		Vector user2films=ratings.getFilmsFromUser(pId2); //obtengo las peliculas de usuario2
		films=user1films.valuesInCommon(user2films);
		return films;
	}
}
