package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import packEuskoFlix.FilmCatalogue;
import packEuskoFlix.RatingCatalogue;

public class ProductFilterTest {

	@Test
	public void test() {
		//Not normalized
		System.out.println("------------------------------------------------------");
		System.out.println("ProductFilter sin normalización.");
		System.out.println("Resultados esperados:");
		System.out.println("Usuario: 2048, Película 161, Valoración 4.4293");
		System.out.println("Usuario: 2048, Película 107, Valoración 4.3505");
		System.out.println("Usuario: 2048, Película 9741, Valoración 4.35");
		System.out.println("Usuario: 2048, Película 63, Valoración 4.4714");
		FilmCatalogue.getFilmCatalogue().initializeTitles();
		RatingCatalogue.getRatingCatalogue().changeValorationMode("ProductFilter");
		RatingCatalogue.getRatingCatalogue().recommendFilm(2048);
		
		//Normalized by median
		System.out.println("------------------------------------------------------");
		System.out.println("ProductFilter con normalización en base a la media.");
		System.out.println("Resultados esperados:");
		System.out.println("Usuario: 2048, Película 161, Valoración 4.6853");
		System.out.println("Usuario: 2048, Película 107, Valoración 4.4019");
		System.out.println("Usuario: 2048, Película 9741, Valoración 3.8283");
		System.out.println("Usuario: 2048, Película 63, Valoración 4.2583");
		FilmCatalogue.getFilmCatalogue().initializeTitles();
		RatingCatalogue.getRatingCatalogue().changeNormalizeMode();
		RatingCatalogue.getRatingCatalogue().recommendFilm(2048);
	}

}
