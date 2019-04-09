package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import packEuskoFlix.EuskoFlix;
import packEuskoFlix.RatingCatalogue;
import packEuskoFlix.Vector;

public class RatingCatalogueTest {

	@Test
	public void testGetRatingCatalogue() {
		RatingCatalogue ratings1 = null;
		ratings1 = RatingCatalogue.getRatingCatalogue();
		assertNotNull(ratings1);
	}


	@Test
	public void testRecommendFilm() {
		EuskoFlix.getEuskoFlix().initialize();
		Vector vector1 = RatingCatalogue.getRatingCatalogue().recommendFilm(2048);
		vector1.printVector();
	}

	@Test
	public void testGetFilmsFromUser() {
		EuskoFlix.getEuskoFlix().initialize();
		Vector vector1 = RatingCatalogue.getRatingCatalogue().getFilmsFromUser(1);
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
		EuskoFlix.getEuskoFlix().initialize();
		Vector vector1 = RatingCatalogue.getRatingCatalogue().getAllUsers();
		vector1.printVector();
	}

	@Test
	public void testGetNonViewFilmsFor() {
		EuskoFlix.getEuskoFlix().initialize();
		Vector vector1 = RatingCatalogue.getRatingCatalogue().getNonViewFilmsFor(1);
		vector1.printVector();
	}

	@Test
	public void testHasValorated() {
		EuskoFlix.getEuskoFlix().initialize();
		assertTrue(RatingCatalogue.getRatingCatalogue().hasValorated(1, 1900));
		assertFalse(RatingCatalogue.getRatingCatalogue().hasValorated(1, 187));
	}

}
