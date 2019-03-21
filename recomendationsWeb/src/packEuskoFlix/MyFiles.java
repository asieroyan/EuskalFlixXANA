package packEuskoFlix;

import java.io.File;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyFiles {
	private static MyFiles mFiles= new MyFiles();
	private MyFiles() {}
	
	public static MyFiles getMyFiles() {
		return mFiles;
	}
	public void loadFilmCatalogue() {
		FilmCatalogue films = FilmCatalogue.getFilmCatalogue();
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
				Integer idFilm=Integer.parseInt(v1[0]);
				Film film = new Film(v1[1],idFilm); //first id, second filmId
				films.add(idFilm,film);
			}
		}
	}
	
	public void loadFilmRatings() {
		UserCatalogue users= UserCatalogue.getUserCatalogue();
		File file = new File(System.getProperty("user.dir"),"movie-ratings.csv");
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
			String[] v1 = new String[3];     //the information split in three parts
			while(sc.hasNext()) {
				information = sc.nextLine();
				v1 = information.split(",");
				Integer idUser=Integer.parseInt(v1[0]);
				Integer idFilm=Integer.parseInt(v1[1]);
				Double rating= Double.parseDouble(v1[2]);
				User user= new User(idUser);
				users.add(idUser, user);
				Ratings.getRatings().addReview(idFilm, idUser, rating);			
			}
		}
	}
	
	public void loadFilmTags() {
		FilmCatalogue films = FilmCatalogue.getFilmCatalogue();
		File file = new File(System.getProperty("User.dir"),"movie-tags.csv");
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
			String[] v1 = new String[2];     //the information split in two parts (film id/Tag)
			while(sc.hasNext()) {
				information = sc.nextLine();
				v1 = information.split(";");
				Integer idFilm=Integer.parseInt(v1[0]);
				String tag= v1[1];
				films.searchFilmByID(idFilm).addTag(tag);				
			}
		}
	}
}
