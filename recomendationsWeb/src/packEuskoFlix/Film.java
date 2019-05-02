package packEuskoFlix;

import java.util.HashMap;

public class Film {
	private int  id;
	private String title;
	private HashMap<String, Integer> tags;
	
	public Film(int pId,String pTitle) {
		id = pId;
		title = pTitle;
		tags = new HashMap<String, Integer>();
	}
	
	public void addTag(String pTagString) {
		if (tags.containsKey(pTagString)){
			tags.put(pTagString, tags.get(pTagString)+1);
		}
		else {
			tags.put(pTagString,1);
		}
	}
	
	public boolean containsTag(String tag) {
		return this.tags.containsKey(tag);
	}

}
