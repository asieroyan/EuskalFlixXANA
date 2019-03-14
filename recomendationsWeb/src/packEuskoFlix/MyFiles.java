package packEuskoFlix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyFiles {
	
	private FilmCatalogue films = FilmCatalogue.getFilmCatalogue();

	public MyFiles() {}
	
	public void loadFilmCatalogue() {
		File file = new File(System.getProperty("user.dir"),"movie-titles.csv");
		if(!file.exists()) {
			System.out.println("File not found");
		}else {
			Scanner sc = null;
			try {
				sc = new Scanner(file);
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			String information;
			String[] v1 = new String[2];     //the information split in two parts
			while(sc.hasNext()) {
				information = sc.nextLine();
				v1 = information.split(";");
				Film film = new Film(v1[1],v1[0]); //first name, second filmId
				films.add(film);
			}
		}
	}
	
}
