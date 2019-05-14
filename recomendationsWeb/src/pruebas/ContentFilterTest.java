package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import packEuskoFlix.FilmCatalogue;
import packEuskoFlix.RatingCatalogue;

public class ContentFilterTest {

	@Test
	public void test() {
		System.out.println("------------------------------------------------------");
		System.out.println("ContentFilter sin normalización.");
		System.out.println("Resultados esperados:");
		System.out.println("Usuario: 2048, Película 2164, Valoración 0.1899");
		System.out.println("Usuario: 2048, Película 63, Valoración 0.2612");
		System.out.println("Usuario: 2048, Película 807, Valoración 0.2363");
		System.out.println("Usuario: 2048, Película 187, Valoración 0.2059");
		System.out.println("Usuario: 2048, Película 11, Valoración 0.3596");
		FilmCatalogue.getFilmCatalogue().initializeTitles();
		RatingCatalogue.getRatingCatalogue().changeValorationMode("ContentFilter");
		RatingCatalogue.getRatingCatalogue().recommendFilm(2048);
	}

}
