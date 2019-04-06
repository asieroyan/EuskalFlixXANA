package packEuskoFlix;

import view.Frame;

public class FileTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stopwatch cronometro= new Stopwatch();
		System.out.println("-------------------------------_");
		System.out.println("Carga de datos");
		FilmCatalogue filmCatalogue = FilmCatalogue.getFilmCatalogue();
		filmCatalogue.loadFilmCatalogue();
		UserCatalogue userCatalogue = UserCatalogue.getUserCatalogue();
		userCatalogue.loadFilmRatings();
		filmCatalogue.loadFilmTags();
		double tiempocarga=cronometro.elapsedTime();
		cronometro= new Stopwatch();
		System.out.println("-------------------------------_");
		System.out.println("Normalizar");
		//filmCatalogue.printFilms();
		//userCatalogue.printUsers();		
		//Frame mainFrame = new Frame();
		//mainFrame.setVisible(true);
		Ratings.getRatings().normalize();
		double tiemponormalizar=cronometro.elapsedTime();
		System.out.println("-------------------------------_");
		System.out.println("Tiempo en cargar:"+tiempocarga);
		System.out.println("Tienpo en normalizar:"+tiemponormalizar);
		double tiempototal=tiempocarga+tiemponormalizar;
		System.out.println("Tiempo total:"+tiempototal);
	}

}
