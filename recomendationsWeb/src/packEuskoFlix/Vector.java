package packEuskoFlix;

import java.util.ArrayList;

public class Vector {
	private ArrayList<Integer> vector;
	
	public Vector() {
		this.vector = new ArrayList<Integer>();
	}
	
	public void add(int pId) {
		vector.add(pId);
	}
}
