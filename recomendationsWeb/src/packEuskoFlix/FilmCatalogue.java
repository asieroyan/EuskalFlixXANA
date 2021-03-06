package packEuskoFlix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;

public class FilmCatalogue {
	private HashMap<Integer, Film> list;
	private static FilmCatalogue myFilmCatalogue = new FilmCatalogue();
	
	private FilmCatalogue() {
		list = new HashMap<Integer, Film>();
	}
	
	public static FilmCatalogue getFilmCatalogue() {
		return myFilmCatalogue;
	}
	public void initializeTitles() {
		File file = new File(System.getProperty("user.dir"),"movie-titles.csv");
		String information;
		Scanner sc = null;
		
		if (correctFile(file)) {
		
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
			//the information split in two parts
			while(sc.hasNext()) {
				information = sc.nextLine();
				divideLineTitles(information);				
		}
			sc.close();
	}
	
	
	private boolean correctFile(File pFile) {
		if(!pFile.exists()) {
			System.out.println("File not found");
			return false;
		}
		else 
		{
			return true;			
		}
	}
	
	private void divideLineTitles(String pInformation) {
		String[] v1 = new String[2];
		v1 = pInformation.split(";");
		Integer idFilm=Integer.parseInt(v1[0]);
		String filmTitle = v1[1];
		addOneLine(idFilm, filmTitle, "title");
	}
	
	private void addOneLine(int pId, String pText,String pLineType) {
		if (pLineType.equals("title")) {
			Film film = new Film(pId,pText); //first id, second filmId
			list.put(pId, film);
		}		
	}
	public VectorInteger getAllFilms() {	
		VectorInteger allfilms= new VectorInteger();
		Set<Integer> films=this.list.keySet();
		//ArrayList<Integer> films=(ArrayList<Integer>) this.list.keySet();
		allfilms.addIntegerSet(films);
		return allfilms;
	}
	public String getFilmTitle(int pFilmId) {
		return(this.list.get(pFilmId).getTitle());
	}
	public String obtainFilmTitlesFromVector(VectorInteger pFilmVector) {
		String films="";
		Iterator<Integer> itr=pFilmVector.iterator();
		while (itr.hasNext()) {
			int filmAct=itr.next();
			films= films+filmAct+":"+this.getFilmTitle(filmAct)+"\n";
		}
		return films;
	}
	public String getAllFilmsTitles() {
		return(this.obtainFilmTitlesFromVector(this.getAllFilms()));
	}
}
