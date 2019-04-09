package packEuskoFlix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Matrix {
	private HashMap<Integer, HashMap<Integer, Double>> matrix; //UserId, FilmId, rating
	
	public Matrix() {
		this.matrix = new HashMap<Integer, HashMap<Integer, Double>>();
	}
	
	public void addData(int pFirstKey, int pSecondKey, double pValue) {
		if (this.containsFirstKey(pFirstKey)) {
			matrix.get(pFirstKey).put(pSecondKey, pValue);
		}
		else {
			HashMap<Integer,Double> aux = new HashMap<Integer, Double>();
			aux.put(pSecondKey, pValue);
			matrix.put(pFirstKey, aux);
		}
	}
	public boolean containsFirstKey(int pFirstKey) {
		return (matrix.containsKey(pFirstKey));
	}
	public boolean containsKeys(int pFirstKey, int pSecondKey) {
		boolean contains=false;
		if (this.containsFirstKey(pFirstKey)) {
			if (this.matrix.get(pFirstKey).containsKey(pSecondKey)){
				contains=true;
			}
		}
		return contains;
	}
	public Vector getFirstKeyList() {
		Vector firstKeys= new Vector();
		Set<Integer> myKeys=this.matrix.keySet(); //OBTENGO LAS KEYS
		firstKeys.addIntegerSet(myKeys);
		return firstKeys;
	}
	public Vector getSecondKeyList(int pFirstKey) {
		Vector secondKeys= new Vector();
		Set<Integer> myKeys=this.matrix.get(pFirstKey).keySet(); //OBTENGO LAS KEYS
		secondKeys.addIntegerSet(myKeys);//SE A�ADE EL ARRAYLIST AL VECTOR
		return secondKeys;
	}
	public Double getValue(int pFirstKey, int pSecondKey) {
		return matrix.get(pFirstKey).get(pSecondKey);
	}
	public Vector getSecondKeySortedByValues(int pFirstKey, int pNumKeys) {
		Vector keys= new Vector();
		int i=0;
		Vector myKeys=this.getSecondKeyList(pFirstKey);
		while (myKeys.size()>0 && i<pNumKeys) { //mientras la lista no sea vacia y no se alcancen el numero de keys
			int keyId=this.getMaxValueKey(pFirstKey, myKeys); //obtiene la id de la key con mayor value
			keys.add(keyId);
			i++;
			myKeys.delete(keyId); //lo borro de la lista
		}
		return keys;
	}
	public Matrix getMatrixWithSecondKeySortedByValues(int pFirstKey,int pNumKeys) {
		Matrix sortedMatrix= new Matrix();
		int i=0;
		Vector myKeys=this.getSecondKeyList(pFirstKey);
		while (myKeys.size()>0 && i<pNumKeys) { 
			int keyId=this.getMaxValueKey(pFirstKey, myKeys);
			double value=this.getValue(pFirstKey, keyId);
			sortedMatrix.addData(pFirstKey, keyId, value);
			i++;
			myKeys.delete(keyId); //lo borro de la lista
		}
		return sortedMatrix;
	}
	public int getMaxValueKey(int pFirstKey,Vector pSecondKeyList) { //devuelve la segunda key del mayor valor
		Iterator<Integer> itr=pSecondKeyList.iterator();
		double maxvalue=0.0;
		int maxkey=0;
		while (itr.hasNext()) {
			int useract= itr.next();
			double valueact=this.getValue(pFirstKey, useract);
			if (valueact>maxvalue) {
				maxvalue=valueact;
				maxkey=useract;
			} //
		}
		return maxkey;
	}
	public void print(int pIdUser) {
		Iterator<Integer> itr=this.matrix.get(pIdUser).keySet().iterator();
		while (itr.hasNext()) {
			Integer act= itr.next();
			System.out.println("User: "+act+"    Value"+this.getValue(pIdUser, act));
		}
	}
	
	public double getMean(int pUserId) {
		HashMap<Integer, Double> aux = this.matrix.get(pUserId);
		Double suma = 0.0;
		int cont = 0;
		for (Map.Entry<Integer, Double> entry : aux.entrySet()) {
			suma = suma + entry.getValue();
			cont = cont+1;
		}
		return suma/cont;
	}
	
}


