package packEuskoFlix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public abstract class FilterMode {
	private Matrix NMatrix;
	public FilterMode() {
		this.NMatrix= new Matrix();
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
	public abstract void divideLineAdd(String pInformation);
	
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
	public VectorInteger recommendedFilm(Integer pIdUser) {
		//ESTE METODO DEVUELVE LA LISTA DE LAS 10 PELICULAS QUE MAS LE PUEDE GUSTAR A UN USUARIO
		this.normalizeMatrix();
		Matrix films= this.getEstimatedRatings(pIdUser);
		VectorInteger filmsRecommended= films.getSecondKeySortedByValues(pIdUser, 10); //obtiene las 10 peliculas que mas pueden gustar a un usuario
		filmsRecommended.printVector();
		return filmsRecommended; 
	}
	
	public void normalizeMatrix() {
		this.NMatrix=RatingCatalogue.getRatingCatalogue().normalizeMatrix();
	}
	public double getValoration(Integer pUser, Integer pFilm) {
		double valoration=0.0;
		if (this.NMatrix.containsKeys(pUser, pFilm)){
			valoration=this.NMatrix.getValue(pUser, pFilm);
		}
		return valoration;
	}
	public Matrix unNormalizeMatrix(int pIdUser,Matrix pMatrix) {
		return(RatingCatalogue.getRatingCatalogue().unNormalizeMatrix(pIdUser, pMatrix));
	}
	public Matrix getEstimatedRatings(Integer pIdUser) {
		//ESTE METODO CONSIGUE LAS VALORACIONES QUE ESTIMAMOS QUE DARIA UN USUARIO A LAS PELICULAS QUE NO HA VISTO
		RatingCatalogue ratingList=RatingCatalogue.getRatingCatalogue();
		Matrix estimatedRatings= new Matrix(); //USER FILM RATINGESTIMATED
		VectorInteger nonViewFilms= ratingList.getNonViewFilmsFor(pIdUser);
		Iterator<Integer> itr=nonViewFilms.iterator(); //lista de peliculas no vistas
		while (itr.hasNext()) {
			//recorro las peliculas no vistas
			Integer filmAct= itr.next();
			Double estimatedRating=this.getEstimatedValorationForFilm(pIdUser, filmAct);
			estimatedRatings.addData(pIdUser, filmAct, estimatedRating);
		}
		estimatedRatings=this.unNormalizeMatrix(pIdUser, estimatedRatings);
		estimatedRatings.print(pIdUser);
		return estimatedRatings;
	}
	public abstract Double getEstimatedValorationForFilm(Integer pIdUser,Integer pFilm);
	public abstract Matrix calculateNSimilars(Integer pIdUser, Integer pN, Integer pFilm);
}
