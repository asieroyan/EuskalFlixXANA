package pruebas;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packEuskoFlix.Film;

public class FilmTest {
	Film f1,f2,g,p,mismoNombre;
	@Before
	public void setUp() throws Exception {
		f1= new Film("Pelicula1",01);
		f2= new Film("Pelicula2",02);
		f2.addTag("amazing");
		g= new Film("AAA",03);
		p= new Film("ZZZ",04);
		mismoNombre= new Film("Pelicula1",01);
	}

	@After
	public void tearDown() throws Exception {
		f1=null;
		f2=null;
	}

	@Test
	public void testFilm() {
		assertNotNull(f1);
		assertNotNull(f2);
	}

	@Test
	public void testCompareTo() {
		//Mismo nombre
		assertEquals(f1.compareTo(mismoNombre),0);
		//NombreP1>NombreP2
		assertTrue(g.compareTo(f1)<0);
		//NombreP1<NombreP2
		assertTrue(p.compareTo(f1)>0);
	}

	@Test
	public void testGetID() {
		assertTrue(f1.getID().equals(01));
		assertTrue(f2.getID().equals(02));
	}

	@Test
	public void testCompareToByID() {
		//ID MENOR
		assertTrue(f1.compareToByID(f2)<0);
		//ID MAYOR
		assertTrue(p.compareToByID(f1)>0);
		//MISMO ID
		assertEquals(f1.compareToByID(mismoNombre),0);
	}

	@Test
	public void testPrintFilm() {
		System.out.println("El programa tiene que mostrar:");
		System.out.println("Film ID: 1---Film Name: Pelicula1");
		System.out.println("Y tu programa muestra:");
		f1.printFilm();
	}

	@Test
	public void testGetName() {
		assertEquals(f1.getName(),"Pelicula1");
		assertEquals(g.getName(),"AAA");
	}

	@Test
	public void testAllTags() {
		//NO TIENE TAGS
		assertEquals(f1.allTags(),"");
		//TIENE UN TAG
		assertEquals(f2.allTags(),"amazing\n");
		//TIENE VARIOS TAG
		f2.addTag("incredible");
		assertEquals(f2.allTags(),"amazing\nincredible\n");
	}

}
