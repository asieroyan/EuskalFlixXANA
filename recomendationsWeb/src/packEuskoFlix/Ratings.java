package packEuskoFlix;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
	private Iterator<Integer> filmsIterator(){
		return (ratingList.keySet().iterator());
	}
	public void normalize() {
		double usercont=0;
		double desvcont=0;
		int contfilms=0;
		HashMap<Integer,HashMap<Integer,Double>> newRatingList=new HashMap<Integer,HashMap<Integer,Double>>();
		Iterator<Integer> idAllfilms=ratingList.keySet().iterator(); //recorre las peliculas
		while (idAllfilms.hasNext()) {
			int idFilm=idAllfilms.next();
			
				idMyFilms=ratingList.keySet().iterator(); //vuelvo a hacer el iterador de usuarios
				while (idMyFilms.hasNext()) {
					Integer idAct= idMyFilms.next();
					HashMap<Integer,Double> userList=ratingList.get(idAct);
					if (userList.containsKey(i)) {
						desvcont+=Math.pow(userList.get(i)-median, 2);
					}
				} //fin while
				double vardesv=Math.sqrt(desvcont/contfilms);
				//System.out.println("Media usuario "+i+"= "+median);*/
				usercont=0;
				contfilms=0;
			} //fin recorrer usuarios dentro de una pelicula
			
		} //fin recorrer peliculas
		/*
		for (int i=0;i<=6000;i++) {
			Iterator<Integer> idMyFilms=ratingList.keySet().iterator();
			while (idMyFilms.hasNext()) {
				Integer idAct= idMyFilms.next();
				HashMap<Integer,Double> userList=ratingList.get(idAct);
				if (userList.containsKey(i)) {
					usercont+=userList.get(i);
					contfilms++;
				}
			}
				double median= usercont/contfilms;
				System.out.println("Media usuario "+i+"= "+median);
				usercont=0;
				contfilms=0;
		} //fin del for
			//System.out.println("Usuario "+i+" ha aparecido "+cont+"veces");
		*/
		ratingList=newRatingList;
	}
	private double getMedia(int pIDFilm) {
		int usercont=0;
		int contfilms=0;
		HashMap<Integer,Double> filmHash=this.ratingList.get(pIDFilm);
		Iterator<Integer> itr=filmHash.keySet().iterator(); //recorre los usuarios dentro de la pelicula
		while (itr.hasNext()) {
			int i= itr.next();
			Iterator<Integer> idMyFilms=ratingList.keySet().iterator(); 
			while (idMyFilms.hasNext()) { //calcula la media
				Integer idAct= idMyFilms.next();
				HashMap<Integer,Double> userList=ratingList.get(idAct);
				if (userList.containsKey(i)) {
					usercont+=userList.get(i);
					contfilms++;
				}
			} //fin while
			double median= usercont/contfilms; //media de un usuario
			return median;
		}
	public HashMap<Integer,Double[]> RatingsInCommon(Integer pIDUser1, Integer pIDUser2) {
		HashMap<Integer,Double[]> ratingsInCommon= new HashMap<Integer,Double[]>();
		Iterator<Integer> itr= this.filmsIterator();
		while (itr.hasNext()) {
			Integer filmAct= itr.next();
			if (ratingList.get(filmAct).containsKey(pIDUser1) && ratingList.get(filmAct).containsKey(pIDUser2)){
				double valoracionUser1=ratingList.get(filmAct).get(pIDUser1);
				double valoracionUser2=ratingList.get(filmAct).get(pIDUser2);
				Double[] ratings= new Double[2];
				ratings[0]=valoracionUser1;
				ratings[1]=valoracionUser2;
				ratingsInCommon.put(filmAct, ratings);
			}
		} //end of while
		return ratingsInCommon;
	}
}