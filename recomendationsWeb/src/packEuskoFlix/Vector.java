package packEuskoFlix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Vector implements Iterable<Integer>{
	private ArrayList<Integer> vector;
	
	public Vector() {
		this.vector = new ArrayList<Integer>();
	}
	
	public void add(Integer pId) {
		if (!this.contains(pId)){
			vector.add(pId);
		}
	}
	public void addIntegerSet(Set<Integer> array) {
		Iterator<Integer> itr= array.iterator();
		while (itr.hasNext()) {
			Integer act= itr.next();
			this.add(act);
		}
	} //fin metodo
	public void delete(Integer pId) {
		if (this.contains(pId)) {
			this.vector.remove(pId);
		}
	}
	private Iterator<Integer> getIterador(){
		return this.vector.iterator();
	}
	private boolean contains(Integer pValue) {
		return vector.contains(pValue);
	}
	public Vector valuesInCommon(Vector pVector2) {
		Vector common= new Vector();
		//compruebo las peliculas comunes
		Iterator<Integer> itr=this.getIterador();
		while (itr.hasNext()) {
			Integer act= itr.next();
			if (pVector2.contains(act)) {
				common.add(act);
			}
		} //fin while
		return common;
	} //fin metodo
	public int size() {
		return this.vector.size();
	}
	public Integer getInPos(int pPos) {
		//DEVUELVE EL ELEMENTO EN LA POSICION pPos (si no existe devuelve null)
		Integer elem=null;
		if (pPos<this.size()) {
			elem=this.vector.get(pPos);
		}
		return elem;
	}
	public Iterator<Integer> iterator() {
		VectorIterator itr= new VectorIterator(this);
		return itr;
	}
	public Vector getNonCommonValuesWith(Vector pVector) {
		Vector nonCommon= new Vector();
		Iterator<Integer> itr=this.getIterador();
		while (itr.hasNext()) {
			Integer act= itr.next();
			if (!pVector.contains(act)) { //valor distinto
				nonCommon.add(act);
			}
		}
		return nonCommon;
	}
	public void printVector() {
		Iterator<Integer> itr= this.getIterador();
		int i=0;
		while (itr.hasNext()) {
			System.out.println("Element "+i+" = "+itr.next());
			i++;
		}
	}
}
