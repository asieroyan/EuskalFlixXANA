package packEuskoFlix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class VectorInteger{
	private ArrayList<Integer> vector;
	
	public VectorInteger() {
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
	public boolean contains(Integer pValue) {
		return vector.contains(pValue);
	}
	public void addvaluesFromVector(VectorInteger pVector2) { //ANADE LOS VALORES QUE NO TIENE DEL OTRO VECTOR
		VectorInteger common= new VectorInteger();
		//compruebo las peliculas comunes
		Iterator<Integer> itr=pVector2.getIterador();
		while (itr.hasNext()) {
			int act= itr.next();
			if (!this.contains(act)) {
				this.add(act);
			}
		} //fin while
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
		return this.vector.iterator();
	}
	public VectorInteger getNonCommonValuesWith(VectorInteger pVector) {
		VectorInteger nonCommon= new VectorInteger();
		Iterator<Integer> itr=this.getIterador();
		while (itr.hasNext()) {
			int act= itr.next();
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
