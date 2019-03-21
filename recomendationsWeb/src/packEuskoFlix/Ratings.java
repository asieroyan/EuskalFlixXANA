package packEuskoFlix;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Ratings{
	
	private HashMap<Integer,HashMap<Integer,Double>> ratingList;	//First key filmId
																//Second key userId
	private static Ratings myRatings = new Ratings();
	
	private Ratings() {
		ratingList = new HashMap<Integer,HashMap<Integer,Double>>();
	}
	
	public static Ratings getRatings() {return myRatings;}
	
	public void addReview(Integer pFilmId, Integer pUserId, Double pPuntuation) {
		if (pPuntuation>5||pPuntuation<0.5) {
			System.out.println("Puntuacion incorrecta");
		}
		else {
			HashMap<Integer,Double> aux = new HashMap<Integer,Double>();
			if(ratingList.containsKey(pFilmId)) {
				aux = ratingList.get(pFilmId);
				if(!aux.containsKey(pUserId)) {
					aux.put(pUserId,pPuntuation);
				}else {
					aux.remove(pUserId);
					aux.put(pUserId, pPuntuation);
				}	
			}else {
				aux.put(pUserId, pPuntuation);
				ratingList.put(pFilmId, aux);
			}
		}
	}
	public void printHashMap() {
		Iterator it = ratingList.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	        System.out.println("");
	    }
	}
	
	public String allRatings(int pId) {
		String text = "Rates \n";
		HashMap<Integer, Double> rates = ratingList.get(pId);
		Iterator it = rates.entrySet().iterator();
		
		while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        text += "    "+pair.getValue()+"\n";        
	    }
		
		return text;
	}
	public String allUsers(int pId) {
		String text = "User \n";
		HashMap<Integer, Double> rates = ratingList.get(pId);
		Iterator it = rates.entrySet().iterator();
		
		while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        text += pair.getKey()+"\n";        
	    }
		
		return text;
		}
	
}