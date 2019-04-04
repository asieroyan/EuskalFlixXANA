package packEuskoFlix;

import java.util.HashMap;

public class Matrix {
	private HashMap<Integer, HashMap<Integer, Double>> matrix;
	
	public Matrix() {
		this.matrix = new HashMap<Integer, HashMap<Integer, Double>>();
	}
	
	public void addData(int pUserId, int pFilm, double pRate) {
		if (matrix.containsKey(pUserId)) {
			matrix.get(pUserId).put(pFilm, pRate);
		}
		else {
			HashMap<Integer,Double> aux = new HashMap<Integer, Double>();
			aux.put(pFilm, pRate);
			matrix.put(pUserId, aux);
		}
	}
}


