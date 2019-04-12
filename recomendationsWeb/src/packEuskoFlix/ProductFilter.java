package packEuskoFlix;

import java.util.HashMap;
import java.util.Iterator;

public class ProductFilter extends FilterMode {
	private HashMap<Integer,Vector> filmUsers;
	public ProductFilter() {
		super();
		filmUsers=new HashMap<Integer,Vector>();
	}
	public Double getEstimatedValorationForFilm(Integer pIdUser,Integer pFilm) {
		//ESTE METODO CALCULA LA VALORACION QUE ESTIMAMOS QUE DARIA UN USUARIO A UNA PELICULA EN CONCRETO
		Double estimatedValoration=0.0;
		//RatingCatalogue ratingList=RatingCatalogue.getRatingCatalogue();
		Matrix similitudeMatrix= this.calculateNSimilars(pIdUser, 20, pFilm); //valor 20 por defecto
		Vector similitudes=similitudeMatrix.getSecondKeyList(pFilm); //lista de similitudes del usuario con los demas
		Iterator<Integer> films=similitudes.iterator(); //lista de peliculas
		Double sumNum=0.0;
		Double sumDem=0.0;
		while (films.hasNext()) {
			Integer filmAct= films.next();
			Double similitudeAct=Math.abs(similitudeMatrix.getValue(pFilm, filmAct));
			Double ratingAct=this.getValoration(pIdUser, filmAct);
			sumNum+=(ratingAct*similitudeAct);
			sumDem+=similitudeAct;
			}
		estimatedValoration=sumNum/sumDem;	
		return estimatedValoration;
	}
	public Matrix calculateNSimilars(Integer pIdUser, Integer pN, Integer pFilm) {
		//ESTE METODO CALCULA LOS N PELICULAS MAS SIMILARES A LA PELICULA DE LA CUAL QUEREMOS ESTIMAR
		Matrix similitudes=this.calculateSimilitudeWithOtherFilms(pIdUser,pFilm);
		Matrix nSimilitude=similitudes.getMatrixWithSecondKeySortedByValues(pFilm, pN);
		return nSimilitude;
	}
	private Matrix calculateSimilitudeWithOtherFilms(int pIdUser, int pFilm) { 
		System.out.println(pFilm);
		//ESTE METODO CALCULA LA MATRIZ DE SIMILITUD DE UNA PELICULA CON EL RESTO DE PELICULAS DE UN USUARIO
		RatingCatalogue ratings=RatingCatalogue.getRatingCatalogue();
		Vector films=ratings.getFilmsFromUser(pIdUser); //consigo las peliculas de ese usuario
		Iterator<Integer> itr=films.iterator();
		Matrix similitudeMatrix= new Matrix();
		while (itr.hasNext()) {
			int filmAct=itr.next(); 
			//System.out.println("filmAct="+filmAct);
			double similitude=this.calculateSimilitude(pFilm, filmAct);
			//similitude=similitude;
			//if (similitude!=0) {
			//System.out.println(pFilm+" "+filmAct+" "+similitude);
			similitudeMatrix.addData(pFilm, filmAct, similitude);
			//}
		}
		return similitudeMatrix;
	}
	public Double calculateSimilitude( Integer pFilm1, Integer pFilm2) { 
		///ESTE METODO CALCULA LA SIMILITUD DE UNA PELICULA CON OTRA PELICULA
		//System.out.println("Usuario1-Usuario2"+pId1+"   "+pId2);
		Double similitud=0.0;
		Vector users=this.getUsersFromBothFilms(pFilm1, pFilm2);
		//RatingCatalogue ratings= RatingCatalogue.getRatingCatalogue();
		Double sumNum=0.0; //guarda el sumatorio del numerador
		Double sumDem=0.0; //guarda el sumatorio del denominador
		Double dem1=0.0; //guarda la primera raiz del denominador
		Double dem2=0.0; //guarda la segunda raiz del denominador
		Iterator<Integer> itr=users.iterator();
			while (itr.hasNext()) {
				Integer user= itr.next();
				Double valoration1=super.getValoration(user,pFilm1);		
				Double valoration2=super.getValoration(user,pFilm2);
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
	private Vector getUsersFromBothFilms(Integer pId1, Integer pId2) {
		Vector filmsUser1=this.obtainUserWhoHasValorated(pId1);
		Vector filmsUser2=this.obtainUserWhoHasValorated(pId2);
		filmsUser1.addvaluesFromVector(filmsUser2);
		return filmsUser1;
	}
	private Vector obtainUserWhoHasValorated(Integer pFilm) {
		Vector user= new Vector();
		if (!this.filmUsers.containsKey(pFilm)){
			RatingCatalogue ratings=RatingCatalogue.getRatingCatalogue();
			Vector users=ratings.getAllUsers();
			Iterator<Integer> itr=users.iterator();
			while (itr.hasNext()) {
				int useract=itr.next();
				if (ratings.hasValorated(useract, pFilm)) {
					user.add(useract);
				}
			}
			this.filmUsers.put(pFilm, user);
		}
		else {
			user=this.filmUsers.get(pFilm);
		}
		return user;
	}
}
