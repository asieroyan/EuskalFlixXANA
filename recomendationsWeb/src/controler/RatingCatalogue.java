package controler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class RatingCatalogue {
	private HashMap<Integer,HashMap<Integer, Double>> list;
	private static RatingCatalogue myRatingCatalogue = new RatingCatalogue();
	
	private RatingCatalogue() {
		list = new HashMap<Integer, HashMap<Integer,Double>>();
	}
	
	public static RatingCatalogue getUserCatalogue() {
		return myRatingCatalogue;
	}
	
	public void normalize() {
		//To do//
	}
	
	
	public void initialize() {
		File file = new File(System.getProperty("user.dir"),"movie-ratings.csv");
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
				divideLineAdd(information);				
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
	
	private void divideLineAdd(String pInformation) {
		String[] v1 = new String[3];
		v1 = pInformation.split(";");
		Integer idUser=Integer.parseInt(v1[0]);
		Integer idFilm=Integer.parseInt(v1[1]);
		Double rate = Double.parseDouble(v1[2]);
		addOneLine(idUser, idFilm, rate);
	}
	
	private void addOneLine(int pUserId, int pFilmId,double pRank) {
		
		if (list.containsKey(pUserId)) {
		list.get(pUserId).put(pFilmId, pRank);
		}
		else {
			HashMap<Integer,Double> hash= new HashMap<Integer,Double>();
			hash.put(pFilmId, pRank);
			list.put(pUserId, hash);
		}
	}
}
