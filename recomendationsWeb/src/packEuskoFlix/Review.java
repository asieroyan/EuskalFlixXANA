package packEuskoFlix;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Review{
	
	private HashMap<Integer,HashMap<Integer,Double>> reviewList;	//First key filmId
																//Second key userId
	private static Review myReview = new Review();
	
	private Review() {
		reviewList = new HashMap<Integer,HashMap<Integer,Double>>();
	}
	
	public static Review getReview() {return myReview;}
	
	public void addReview(Integer pFilmId, Integer pUserId, Double puntuation) {
		HashMap<Integer,Double> aux = new HashMap<Integer,Double>();
		if(reviewList.containsKey(pFilmId)) {
			aux = reviewList.get(pFilmId);
			if(!aux.containsKey(pUserId)) {
				aux.put(pUserId,puntuation);
			}else {
				aux.remove(pUserId);
				aux.put(pUserId, puntuation);
			}	
		}else {
			aux.put(pUserId, puntuation);
			reviewList.put(pFilmId, aux);
		}
	}
	public void printHashMap() {
		Iterator it = reviewList.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	        System.out.println("");
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	}
	
}