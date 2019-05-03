package packEuskoFlix;

import java.util.HashMap;
import java.util.Set;

public class ModelMatrix {
	private HashMap<Integer, HashMap<String, Double>> matrix; //id, tag, tfidf
	
	public ModelMatrix() {
		this.matrix= new HashMap<Integer, HashMap<String, Double>>();
	}
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
	public boolean containsKeys(int pFirstKey, String pSecondKey) {
		boolean contains=false;
		if (this.containsFirstKey(pFirstKey)) {
			if (this.matrix.get(pFirstKey).containsKey(pSecondKey)){
				contains=true;
			}
		}
		return contains;
	}
	public Double getValue(int pId, String pTag) {
		if (this.containsKeys(pId, pTag)){
			return matrix.get(pId).get(pTag);
		}
		else {
			return 0.0; //si no hay devuelve 0.0
		}
	}
	public void sumValue(int pId, String pTag, double pValue) {
		if (this.containsFirstKey(pId)) {
			double tfidfAct=this.getValue(pId,pTag);
			tfidfAct+=pValue;
			matrix.get(pId).put(pTag, tfidfAct);
			}
		else {
			HashMap<String,Double> aux = new HashMap<String, Double>();
			aux.put(pTag, pValue);
			matrix.put(pId, aux);
		}
	}
	public VectorString getTagList(int pId) {
		VectorString tags= new VectorString();
		if (this.matrix.containsKey(pId)){
			Set<String> myKeys=this.matrix.get(pId).keySet(); //OBTENGO LAS KEYS
			tags.addStringSet(myKeys);//SE ANADE EL ARRAYLIST AL VECTOR
		}
		return tags;
	}
}
