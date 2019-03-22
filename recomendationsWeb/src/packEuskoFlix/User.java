package packEuskoFlix;

import java.util.HashMap;
import java.util.Iterator;

public class User {
	//private String name; PUEDE QUE HAYA QUE QUITARLO
	private Integer id;
	private UserList mostLikeUsers;
	
	public User(Integer pId) {
		//this.name = pName;
		this.id = pId;
		this.mostLikeUsers = new UserList("2");
	}
	
	//TODO
	private UserList obtainMostLikeUsers() {
		return null;
	}
	
	//TODO
	public boolean doILike(String pFimlName) {
		return false;
	}
	public void printUser(){
		System.out.println("User ID: "+this.id);
	}
	public int compareTo(User pUser) {
		return (this.id.compareTo(pUser.id));
	}
	private double cosUsers(HashMap<Integer,Double[]> pHash) {
		double sumnum=0.0;
		double den=0.0;
		double sumvi=0.0;
		double sumwi=0.0;
		Iterator<Integer> itr=pHash.keySet().iterator();
		while (itr.hasNext()) {
			Integer idAct= itr.next();
			Double[] ratings=pHash.get(idAct);
			double vi=ratings[0].doubleValue();
			double wi=ratings[1].doubleValue();
			sumnum +=vi*wi;
			sumvi+=vi*vi;
			sumwi=wi*vi;
		}
		den=Math.sqrt(sumvi)*Math.sqrt(sumwi);
		return sumnum/den;
	}
	private HashMap<Integer,Double[]> RatingsInCommon(User pUser) {
		//SE OBTIENEN LAS PELICULAS EN COMUN ENTRE DOS USUARIOS CON SUS VALORACIONES
		//EL HASHMAP ES TEMPORAL
		return (Ratings.getRatings().RatingsInCommon(this.id, pUser.id));
	}
}