package packEuskoFlix;

public class UserFilter {
	private Matrix filteredMatrix;
	private Matrix similitudeMatrix;
	public UserFilter() {
		this.filteredMatrix = new Matrix(); //USUARIO PELICULA VALORACION ESTIMADA
		this.similitudeMatrix = new Matrix(); //USUARIO1, USUARIO2, SIMILITUD (seguramente no sea atributo al final)
	}
	public Matrix normalize() {
		//TODO
		return null;
	}
	
	public Double calculateSimilitude( int pId1, int pId2) {
		Vector filmsInCommon= this.filmsInCommon(pId1, pId2);
		//TODO
		//Hay que aplicar la formula
		return 0.0;
	}
	
	public Vector filmsInCommon(int pId1, int pId2) {
		Vector films= new Vector();
		RatingCatalogue ratings= RatingCatalogue.getRatingCatalogue();
		Vector user1films=ratings.getFilmsFromUser(pId1); //obtengo las peliculas de usuario1
		Vector user2films=ratings.getFilmsFromUser(pId2); //obtengo las peliculas de usuario2
		return (user1films.valuesInCommon(user2films));
	}
}
