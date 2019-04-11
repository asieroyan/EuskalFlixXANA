package packEuskoFlix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class VectorString implements Iterable<String>{
	private ArrayList<String> vector;
	
	public VectorString() {
		this.vector = new ArrayList<String>();
	}
	
	public void add(String pId) {
		if (!this.contains(pId)){
			vector.add(pId);
		}
	}
	public void addIntegerSet(Set<String> array) {
		Iterator<String> itr= array.iterator();
		while (itr.hasNext()) {
			String act= itr.next();
			this.add(act);
		}
	} //fin metodo
	public void delete(String pId) {
		if (this.contains(pId)) {
			this.vector.remove(pId);
		}
	}
	private Iterator<String> getIterador(){
		return this.vector.iterator();
	}
	private boolean contains(String pValue) {
		return vector.contains(pValue);
	}
	public void addvaluesFromVector(VectorString pVector2) { //ANADE LOS VALORES QUE NO TIENE DEL OTRO VECTOR
		VectorString common= new VectorString();
		//compruebo las peliculas comunes
		Iterator<String> itr=pVector2.getIterador();
		while (itr.hasNext()) {
			String act= itr.next();
			if (!this.contains(act)) {
				this.add(act);
			}
		} //fin while
	} //fin metodo
	public int size() {
		return this.vector.size();
	}
	public String getInPos(int pPos) {
		//DEVUELVE EL ELEMENTO EN LA POSICION pPos (si no existe devuelve null)
		String elem=null;
		if (pPos<this.size()) {
			elem=this.vector.get(pPos);
		}
		return elem;
	}
	public Iterator<String> iterator() {
		return this.vector.iterator();
	}
	public VectorString getNonCommonValuesWith(VectorString pVector) {
		VectorString nonCommon= new VectorString();
		Iterator<String> itr=this.getIterador();
		while (itr.hasNext()) {
			String act= itr.next();
			if (!pVector.contains(act)) { //valor distinto
				nonCommon.add(act);
			}
		}
		return nonCommon;
	}
	public void printVector() {
		Iterator<String> itr= this.getIterador();
		int i=0;
		while (itr.hasNext()) {
			System.out.println("Element "+i+" = "+itr.next());
			i++;
		}
	}
}