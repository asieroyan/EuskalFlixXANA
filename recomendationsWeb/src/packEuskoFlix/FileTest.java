package packEuskoFlix;

//import view.Frame;

public class FileTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stopwatch cronometro= new Stopwatch();
		System.out.println("-------------------------------_");
		System.out.println("Carga de datos");
		FilmCatalogue filmCatalogue = FilmCatalogue.getFilmCatalogue();
		filmCatalogue.initializeTitles();
		RatingCatalogue ratings=RatingCatalogue.getRatingCatalogue();
		ratings.initialize();
		TagCatalogue tags=TagCatalogue.getTagCatalogue();
		tags.initialize();
		double tiempocarga=cronometro.elapsedTime();
		System.out.println("Tiempo en cargar= "+tiempocarga);
		cronometro= new Stopwatch();
		//ratings.changeNormalizeMode(); //Cambia a normalizar
		//ratings.changeValorationMode(); //Cambia a ProductFilter
		VectorInteger recommends=ratings.recommendFilm(4045);
		double tiemporecomendacion=cronometro.elapsedTime();
		System.out.println("Tiempo en recomendar= "+tiemporecomendacion);	
	}

}
