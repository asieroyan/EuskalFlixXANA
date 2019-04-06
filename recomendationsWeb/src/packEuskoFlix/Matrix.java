package packEuskoFlix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
	public Vector getFirstKeyList() {
		Vector firstKeys= new Vector();
		ArrayList<Integer> myKeys= (ArrayList<Integer>) this.matrix.keySet(); //OBTENGO LAS KEYS
		firstKeys.addIntegerArrayList(myKeys);
		return firstKeys;
	}
	public Vector getSecondKeyList(int pFirstKey) {
		Vector secondKeys= new Vector();
		ArrayList<Integer> myKeys=(ArrayList<Integer>) this.matrix.get(pFirstKey).keySet(); //OBTENGO LAS KEYS
		secondKeys.addIntegerArrayList(myKeys);//SE AÑADE EL ARRAYLIST AL VECTOR
		return secondKeys;
	}
}


