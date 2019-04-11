package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import packEuskoFlix.EuskoFlix;
import packEuskoFlix.RatingCatalogue;
import packEuskoFlix.VectorInteger;

public class RatingCatalogueTest {

	@Test
	public void testGetRatingCatalogue() {
		RatingCatalogue ratings1 = null;
		ratings1 = RatingCatalogue.getRatingCatalogue();
		assertNotNull(ratings1);
	}


	@Test
	public void testRecommendFilm() {
		System.out.println("--------------------------------------------");
		System.out.println("PRUEBA RECOMENDAR UNA PELICULA PARA EL USUARIO 2048");
		System.out.println("--------------------------------------------");
		EuskoFlix.getEuskoFlix().initialize();
		VectorInteger vector1 = RatingCatalogue.getRatingCatalogue().recommendFilm(2048);
		vector1.printVector();
	}

	@Test
	public void testGetFilmsFromUser() {
		System.out.println("--------------------------------------------");
		System.out.println("PRUEBA IMPRIMIR PELICULAS DE USUARIO1");
		System.out.println("--------------------------------------------");
		EuskoFlix.getEuskoFlix().initialize();
		VectorInteger vector1 = RatingCatalogue.getRatingCatalogue().getFilmsFromUser(1);
		vector1.printVector();
	}

	@Test
	public void testGetValoration() {
		EuskoFlix.getEuskoFlix().initialize();
		double valoration1 = RatingCatalogue.getRatingCatalogue().getValoration(1, 788);
		assertEquals(valoration1, 4.0,0);
		double valoration2 = RatingCatalogue.getRatingCatalogue().getValoration(5, 453);
		assertEquals(valoration2, 4.5,0);
	}

	@Test
	public void testGetAllUsers() {
		System.out.println("--------------------------------------------");
		System.out.println("PRUEBA IMPRIMIR LOS ID DE TODOS LOS USUARIOS");
		System.out.println("--------------------------------------------");
		EuskoFlix.getEuskoFlix().initialize();
		VectorInteger vector1 = RatingCatalogue.getRatingCatalogue().getAllUsers();
		vector1.printVector();
	}

	@Test
	public void testGetNonViewFilmsFor() {
		System.out.println("--------------------------------------------");
		System.out.println("PRUEBA MOSTRAR PELICULAS NO VISTAS POR USUARIO1");
		System.out.println("--------------------------------------------");
		EuskoFlix.getEuskoFlix().initialize();
		VectorInteger vector1 = RatingCatalogue.getRatingCatalogue().getNonViewFilmsFor(1);
		vector1.printVector();
	}

	@Test
	public void testHasValorated() {
		EuskoFlix.getEuskoFlix().initialize();
		assertTrue(RatingCatalogue.getRatingCatalogue().hasValorated(1, 1900));
		assertFalse(RatingCatalogue.getRatingCatalogue().hasValorated(1, 187));
	}

}
