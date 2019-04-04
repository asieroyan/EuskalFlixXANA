package packEuskoFlix;

import java.util.HashMap;

public class Film {
	private int id;
	private String title;
	private HashMap<String, Integer> tags;
	
	public Film(int pId, String pTitle) {
		this.id = pId;
		this.title = pTitle;
	}
	
	public void addTag(int pId, String pTag) {
		this.tags.put(pTag, pId);
	}
}
