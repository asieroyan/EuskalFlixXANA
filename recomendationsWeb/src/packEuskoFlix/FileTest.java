package packEuskoFlix;

//import view.Frame;

public class FileTest {

	public static void main(String[] args) {
		// CARGAMOS TODOS LOS DATOS
		Stopwatch cronometro= new Stopwatch();
		System.out.println("-------------------------------");
		System.out.println("Carga de datos");
		FilmCatalogue filmCatalogue = FilmCatalogue.getFilmCatalogue();
		filmCatalogue.initializeTitles();
		RatingCatalogue ratings=RatingCatalogue.getRatingCatalogue();
		TagCatalogue tags=TagCatalogue.getTagCatalogue();
		double tiempocarga=cronometro.elapsedTime();
		System.out.println("Tiempo en cargar= "+tiempocarga);
		
		//CALCULAMOS LAS PELICULAS RECOMENDADAS (POR DEFECTO USERFILTER)
		cronometro= new Stopwatch();
		//ratings.changeNormalizeMode(); //Cambia a normalizar
		ratings.changeValorationMode("contentfilter"); //Cambia el modo de valorar
		int user=4045; //guarda el user
		VectorInteger recommends=ratings.recommendFilm(user); //pide peliculas recomendadas para el user
		System.out.println("-------------------------------");
		System.out.println("Las peliculas recomendadas para el usuario "+user +" son:");
		recommends.printVector();
		double tiemporecomendacion=cronometro.elapsedTime();
		System.out.println("Tiempo en recomendar= "+tiemporecomendacion);	
	}

}
