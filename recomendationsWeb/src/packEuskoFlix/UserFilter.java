package packEuskoFlix;

import java.util.Iterator;

public class UserFilter extends FilterMode {
	public UserFilter() {
		super();
	}
	public Vector recommendedFilm(Integer pIdUser) {
		super.normalizeMatrix();
		//ESTE METODO DEVUELVE LA LISTA DE LAS 10 PELICULAS QUE MAS LE PUEDE GUSTAR A UN USUARIO
		Matrix films= this.getEstimatedRatings(pIdUser);
		return (films.getSecondKeySortedByValues(pIdUser, 10)); //obtiene las 10 peliculas que mas pueden gustar a un usuario
	}
	private Matrix getEstimatedRatings(Integer pIdUser) {
		//ESTE METODO CONSIGUE LAS VALORACIONES QUE ESTIMAMOS QUE DARIA UN USUARIO A LAS PELICULAS QUE NO HA VISTO
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
		estimatedRatings=super.unNormalizeMatrix(pIdUser, estimatedRatings);
		estimatedRatings.print(pIdUser);
		return estimatedRatings;
	}
	public Double getEstimatedValorationForFilm(Integer pIdUser,Integer pFilm) {
		//ESTE METODO CALCULA LA VALORACION QUE ESTIMAMOS QUE DARIA UN USUARIO A UNA PELICULA EN CONCRETO
		Double estimatedValoration=0.0;
		Matrix similitudeMatrix= this.calculateNSimilarUsers(pIdUser, 30, pFilm); //valor 30 por defecto (consigue los 30 usuarios mas parecidos al otro)
		Vector similitudes=similitudeMatrix.getSecondKeyList(pIdUser); //lista de similitudes del usuario con los demas
		Iterator<Integer> users=similitudes.iterator(); //lista de usuarios
		Double sumNum=0.0;
		Double sumDem=0.0;
		while (users.hasNext()) {
			Integer userAct= users.next();
			Double similitudeAct=Math.abs(similitudeMatrix.getValue(pIdUser, userAct));
			Double ratingAct=super.getValoration(userAct, pFilm);
			//System.out.println("User = "+userAct+"    Similitude= "+similitudeAct+ "    Rating ="+ratingAct);
			sumNum+=(ratingAct*similitudeAct);
			sumDem+=similitudeAct;
			}
		estimatedValoration=sumNum/sumDem;	
		//System.out.println(estimatedValoration);
		return estimatedValoration;
	}
	private Matrix calculateNSimilarUsers(Integer pIdUser, Integer pN, Integer pFilm) {
		//ESTE METODO CALCULA LOS N USUARIOS MAS SIMILARES AL USUARIO DEL QUE QUEREMOS SABER LA VALORACION ESTIMADA, ADEMAS ESTOS USUARIOS HAN TENIDO QUE PUNTUAR LA PELICULA QUE QUEREMOS ESTIMAR
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
		//ESTE METODO CALCULA LA MATRIZ DE SIMILITUD DE UN USUARIO CON EL RESTO DE USUARIOS
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
	public Double calculateSimilitude( Integer pId1, Integer pId2) { 
		///ESTE METODO CALCULA LA SIMILITUD DE UN USUARIO CON OTRO USUARIO CONCRETO
		//System.out.println("Usuario1-Usuario2"+pId1+"   "+pId2);
		Vector filmsInCommon= this.filmsInCommon(pId1, pId2); //consigo las peliculas en comun
		Double sumNum=0.0; //guarda el sumatorio del numerador
		Double sumDem=0.0; //guarda el sumatorio del denominador
		Double dem1=0.0; //guarda la primera raiz del denominador
		Double dem2=0.0; //guarda la segunda raiz del denominador
		Double similitud=0.0;
		Iterator<Integer> itr=filmsInCommon.iterator();
		while (itr.hasNext()) {
			Integer film= itr.next();
			Double valoration1=super.getValoration(pId1,film);
			Double valoration2=super.getValoration(pId2,film);
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
		//ESTE METODO CONSIGUE LAS PELICULAS QUE HAN VOTADO EN COMUN UN USUARIO CON OTRO CONCRETO
		Vector films= new Vector();
		RatingCatalogue ratings= RatingCatalogue.getRatingCatalogue();
		Vector user1films=ratings.getFilmsFromUser(pId1); //obtengo las peliculas de usuario1
		Vector user2films=ratings.getFilmsFromUser(pId2); //obtengo las peliculas de usuario2
		films=user1films.valuesInCommon(user2films);
		return films;
	}
}
