package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import packEuskoFlix.Film;

public class FilmTest {

	@Test
	public void testFilm() {
		Film film1 = null;
		assertNull(film1);
		film1 = new Film(3, "Film1");
		assertNotNull(film1);
	}

	@Test
	public void testAddTag() {
		Film film1 = new Film(3, "Film1");
		film1.addTag("tag1");
		assertTrue(film1.containsTag("tag1"));
	}

}
