package packEuskoFlix;

import java.util.Iterator;

public class UserFilter extends FilterMode {
	public UserFilter() {
	}
	public Double getEstimatedValorationForFilm(Integer pIdUser,Integer pFilm) {
		//ESTE METODO CALCULA LA VALORACION QUE ESTIMAMOS QUE DARIA UN USUARIO A UNA PELICULA EN CONCRETO
		Double estimatedValoration=0.0;
		Matrix similitudeMatrix= this.calculateNSimilars(pIdUser, 30, pFilm); //valor 30 por defecto
		VectorInteger similitudes=similitudeMatrix.getSecondKeyList(pIdUser); //lista de similitudes del usuario con los demas
		Iterator<Integer> users=similitudes.iterator(); //lista de usuarios
		Double sumNum=0.0;
		Double sumDem=0.0;
		while (users.hasNext()) {
			Integer userAct= users.next();
			Double similitudeAct=Math.abs(similitudeMatrix.getValue(pIdUser, userAct));
			Double ratingAct=this.getValoration(userAct, pFilm);
			sumNum+=(ratingAct*similitudeAct);
			sumDem+=similitudeAct;
			}
		estimatedValoration=sumNum/sumDem;	
		return estimatedValoration;
	}
	public Matrix calculateNSimilars(Integer pIdUser, Integer pN, Integer pFilm) {
		//ESTE METODO CALCULA LOS N USUARIOS MAS SIMILARES AL USUARIO DEL QUE QUEREMOS SABER LA VALORACION ESTIMADA, ADEMAS ESTOS USUARIOS HAN TENIDO QUE PUNTUAR LA PELICULA QUE QUEREMOS ESTIMAR
		Matrix similitudes=this.calculateSimilitudeWithOtherUsers(pIdUser,pFilm);
		Matrix nSimilitude=similitudes.getMatrixWithSecondKeySortedByValues(pIdUser, pN);
		return nSimilitude;
	}
	private VectorInteger obtainUsersWhoHasValuedFilm(int pIdUser, int pFilm) { //obtiene el vector con los usuarios que han visto la pelicula
		VectorInteger userVector= new VectorInteger();
		RatingCatalogue ratings=RatingCatalogue.getRatingCatalogue();
		VectorInteger listUsers=ratings.getAllUsers();
		Iterator<Integer> itr=listUsers.iterator(); //los recorro
		while (itr.hasNext()) {
			Integer userAct=itr.next();
			if (ratings.hasValorated(userAct, pFilm)) { //si la ha valorado
				userVector.add(userAct);
			}
		}
		return userVector;
	}
	private Matrix calculateSimilitudeWithOtherUsers(Integer pIdUser, Integer pFilm) { 
		//ESTE METODO CALCULA LA MATRIZ DE SIMILITUD DE UN USUARIO CON EL RESTO DE USUARIOS QUE HAN VISTO CIERTA PELICULA
		VectorInteger users=this.obtainUsersWhoHasValuedFilm(pIdUser, pFilm);
		Iterator<Integer> itr=users.iterator();
		Matrix similitudeMatrix= new Matrix();
		while (itr.hasNext()) {
			Integer userAct=itr.next(); //no hace comprobar que pIdUser sea igual a userAct porque sabemos que pIdUser no ha visto pFilm
			double similitude=this.calculateSimilitude(pIdUser, userAct);
			//similitude=Math.abs(similitude); //valor absoluto
			similitudeMatrix.addData(pIdUser, userAct, similitude);
			}
		return similitudeMatrix;
	}
	private Double calculateSimilitude( Integer pId1, Integer pId2) { 
		///ESTE METODO CALCULA LA SIMILITUD DE UN USUARIO CON OTRO USUARIO CONCRETO
		VectorInteger filmsVector=this.getFilmsFromBothUsers(pId1,pId2);
		Double sumNum=0.0; //guarda el sumatorio del numerador
		Double dem1=0.0; //guarda la primera raiz del denominador
		Double dem2=0.0; //guarda la segunda raiz del denominador
		Double sumDem=dem1*dem2; //guarda el sumatorio del denominador
		Double similitud=0.0;
		Iterator<Integer> itr=filmsVector.iterator();
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
		return similitud;
	}
	private VectorInteger getFilmsFromBothUsers(Integer pId1, Integer pId2) {
		RatingCatalogue ratings= RatingCatalogue.getRatingCatalogue();
		VectorInteger filmsUser1=ratings.getFilmsFromUser(pId1);
		VectorInteger filmsUser2=ratings.getFilmsFromUser(pId2);
		filmsUser1.addvaluesFromVector(filmsUser2);
		return filmsUser1;
	}
	public double getSimilitudeForTest(Integer pUser1, Integer pUser2) {
		//solo se utiliza para pruebas
		RatingCatalogue.getRatingCatalogue().getAllUsers();
		return(this.calculateSimilitude(pUser1, pUser2));
	}
}
