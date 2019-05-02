package packEuskoFlix;

import java.util.HashMap;
import java.util.Iterator;

public class ProductFilter extends FilterMode {
	private HashMap<Integer,VectorInteger> filmUsers;
	public ProductFilter() {
		super();
		filmUsers=new HashMap<Integer,VectorInteger>();
	}
	public void divideLineAdd(String pInformation) {
		String[] v1 = new String[3];
		v1 = pInformation.split(",");
		Integer idUser=Integer.parseInt(v1[0]);
		Integer idFilm=Integer.parseInt(v1[1]);
		Double rate = Double.parseDouble(v1[2]);
		RatingCatalogue.getRatingCatalogue().addOneLine(idUser, idFilm, rate);
		this.addUsersWhoHasValorated(idFilm, idUser);
	}
	public Double getEstimatedValorationForFilm(Integer pIdUser,Integer pFilm) {
		//ESTE METODO CALCULA LA VALORACION QUE ESTIMAMOS QUE DARIA UN USUARIO A UNA PELICULA EN CONCRETO
		Double estimatedValoration=0.0;
		//RatingCatalogue ratingList=RatingCatalogue.getRatingCatalogue();
		Matrix similitudeMatrix= this.calculateNSimilars(pIdUser, 20, pFilm); //valor 20 por defecto
		VectorInteger similitudes=similitudeMatrix.getSecondKeyList(pFilm); //lista de similitudes del usuario con los demas
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
		//ESTE METODO CALCULA LA MATRIZ DE SIMILITUD DE UNA PELICULA CON EL RESTO DE PELICULAS DE UN USUARIO
		RatingCatalogue ratings=RatingCatalogue.getRatingCatalogue();
		VectorInteger films=ratings.getFilmsFromUser(pIdUser); //consigo las peliculas de ese usuario
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
		VectorInteger users=this.getUsersFromBothFilms(pFilm1, pFilm2);
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
	private VectorInteger getUsersFromBothFilms(Integer pId1, Integer pId2) {
		VectorInteger filmsUser1=this.obtainUserWhoHasValorated(pId1);
		VectorInteger filmsUser2=this.obtainUserWhoHasValorated(pId2);
		filmsUser1.addvaluesFromVector(filmsUser2);
		return filmsUser1;
	}
	private VectorInteger obtainUserWhoHasValorated(Integer pFilm) {
		VectorInteger user= new VectorInteger();
		if (this.filmUsers.containsKey(pFilm)) {
			user=this.filmUsers.get(pFilm);
		}
		return user;
	}
	public void addUsersWhoHasValorated(int pFilm, int pUser) {
		if (this.filmUsers.containsKey(pFilm)) {
			this.filmUsers.get(pFilm).add(pUser);
		}
		else {
			VectorInteger filmUser= new VectorInteger();
			filmUser.add(pUser);
			this.filmUsers.put(pFilm, filmUser);
		}
		
	}
}
