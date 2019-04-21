package packEuskoFlix;

import java.util.Iterator;

public class VectorIterator implements Iterator<Integer> {
	private int posicion;
	private Vector vector;
	
	public VectorIterator(Vector pVector) {
		posicion=0;
		this.vector=pVector;
	}
	public boolean hasNext() {
		boolean hasnext=false;
		if (posicion<this.vector.size()) {
			hasnext=true;
		}
		return hasnext;
	}

	public Integer next() {
		Integer elem=this.vector.getInPos(posicion);
		this.posicion++;
		return elem;
	}


}
