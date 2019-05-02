package packEuskoFlix;

import java.util.HashMap;

public class ModelMatrix {
	private HashMap<Integer, HashMap<String, Double>> matrix; //id, tag, tfidf
	
	public void add(int pId, String pTag, double pTfidf) {
		if (this.containsFirstKey(pId)) {
			matrix.get(pId).put(pTag, pTfidf);
		}
		else {
			HashMap<String,Double> aux = new HashMap<String, Double>();
			aux.put(pTag, pTfidf);
			matrix.put(pId, aux);
		}
	}
	public boolean containsFirstKey(int pFirstKey) {
		return (matrix.containsKey(pFirstKey));
	}
}
