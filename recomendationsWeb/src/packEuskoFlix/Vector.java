package packEuskoFlix;

import java.util.ArrayList;
import java.util.Iterator;

public class Vector {
	private ArrayList<Integer> vector;
	
	public Vector() {
		this.vector = new ArrayList<Integer>();
	}
	
	public void add(Integer pId) {
		if (!this.contains(pId)){
			vector.add(pId);
		}
	}
	public void addIntegerArrayList(ArrayList<Integer> array) {
		Iterator<Integer> itr= array.iterator();
		while (itr.hasNext()) {
			Integer act= itr.next();
			this.add(act);
		}
	} //fin metodo
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
}
