package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import packEuskoFlix.FilmCatalogue;
import packEuskoFlix.RatingCatalogue;

public class UserFilterTest {

	@Test
	public void test() {
		//Not normalized
		System.out.println("------------------------------------------------------");
		System.out.println("UserFilter sin normalización.");
		System.out.println("Resultados esperados:");
		System.out.println("Usuario: 2048, Película 161, Valoración 3.9347");
		System.out.println("Usuario: 2048, Película 640, Valoración 3.6838");
		System.out.println("Usuario: 2048, Película 9331, Valoración 3.5990");
		System.out.println("Usuario: 2048, Película 1422, Valoración 3.5163");
		System.out.println("Usuario: 2048, Película 462, Valoración 3.1505");
		FilmCatalogue.getFilmCatalogue().initializeTitles();
		RatingCatalogue.getRatingCatalogue().recommendFilm(2048);
		
		//Normalized by median
		System.out.println("------------------------------------------------------");
		System.out.println("UserFilter con normalización en base a la media.");
		System.out.println("Resultados esperados:");
		System.out.println("Usuario: 2048, Película 550, Valoración 4.7789");
		System.out.println("Usuario: 2048, Película 1422, Valoración 4.6649");
		System.out.println("Usuario: 2048, Película 194, Valoración 4.6501");
		System.out.println("Usuario: 2048, Película 807, Valoración 4.5705");
		System.out.println("Usuario: 2048, Película 107, Valoración 4.5077");
		FilmCatalogue.getFilmCatalogue().initializeTitles();
		RatingCatalogue.getRatingCatalogue().changeNormalizeMode();
		RatingCatalogue.getRatingCatalogue().recommendFilm(2048);
	}

}
