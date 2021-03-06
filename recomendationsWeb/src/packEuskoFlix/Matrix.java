package packEuskoFlix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Matrix {
	private HashMap<Integer, HashMap<Integer, Double>> matrix;
	
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
	public VectorInteger getFirstKeyList() {
		VectorInteger firstKeys= new VectorInteger();
		Set<Integer> myKeys=this.matrix.keySet(); //OBTENGO LAS KEYS
		firstKeys.addIntegerSet(myKeys);
		return firstKeys;
	}
	public VectorInteger getSecondKeyList(int pFirstKey) {
		VectorInteger secondKeys= new VectorInteger();
		if (this.matrix.containsKey(pFirstKey)){
			Set<Integer> myKeys=this.matrix.get(pFirstKey).keySet(); //OBTENGO LAS KEYS
			secondKeys.addIntegerSet(myKeys);//SE ANADE EL ARRAYLIST AL VECTOR
		}
		return secondKeys;
	}
	public Double getValue(int pFirstKey, int pSecondKey) {
		if (this.containsKeys(pFirstKey, pSecondKey)){
			return matrix.get(pFirstKey).get(pSecondKey);
		}
		else {
			return 0.0; //si no hay devuelve 0.0
		}
	}
	public void changeValue(Integer pUser, Integer pFilm, double pValue) {
		matrix.get(pUser).replace(pFilm, pValue);
	}
	public VectorInteger getSecondKeySortedByValues(int pFirstKey, int pNumKeys) {
		VectorInteger keys= new VectorInteger();
		int i=0;
		VectorInteger myKeys=this.getSecondKeyList(pFirstKey);
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
		if (this.containsFirstKey(pFirstKey)){
			VectorInteger myKeys=this.getSecondKeyList(pFirstKey);
			while (myKeys.size()>0 && i<pNumKeys) { 
				int keyId=this.getMaxValueKey(pFirstKey, myKeys);
				double value=this.getValue(pFirstKey, keyId);
				sortedMatrix.addData(pFirstKey, keyId, value);
				i++;
				myKeys.delete(keyId); //lo borro de la lista
				}
			}
		return sortedMatrix;
	}
	public int getMaxValueKey(int pFirstKey,VectorInteger pSecondKeyList) { //devuelve la segunda key del mayor valor
		Iterator<Integer> itr=pSecondKeyList.iterator();
		double maxvalue=0.0;
		int maxkey=-1;
		while (itr.hasNext()) {
			int useract= itr.next();
			double valueact=this.getValue(pFirstKey, useract);
			if (valueact>=maxvalue) {
				maxvalue=valueact;
				maxkey=useract;
			} 
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
	public VectorInteger getSecondKeyWithMoreValue(int pFirstKey, double pValue) {
		VectorInteger secondKeys= new VectorInteger();
		if (this.containsFirstKey(pFirstKey)){
			VectorInteger myKeys=this.getSecondKeyList(pFirstKey);
			Iterator<Integer> itr=myKeys.iterator(); //recorremos las segundas keys
			while (itr.hasNext()) {
				int secondKeyAct= itr.next();
				double value=this.getValue(pFirstKey, secondKeyAct);
				if (value>=pValue) {
					secondKeys.add(secondKeyAct);
				}
			} //fin while
		} //fin if
		return secondKeys;
	}
}


