package packEuskoFlix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class TagCatalogue {
	private HashMap<Integer, HashMap<String, Integer>> tags; //id pelicula, tag, num apariciones
	private static TagCatalogue mTagCatalogue;
	
	private TagCatalogue() {
		this.tags = new HashMap<Integer, HashMap<String, Integer>>();
	}
	public static TagCatalogue getTagCatalogue() {
		if (mTagCatalogue == null) {
			mTagCatalogue = new TagCatalogue();
		}
		return mTagCatalogue;
	}
	public void initialize() {
		this.getTags();
	}
	private void getTags() {
		File file = new File(System.getProperty("user.dir"),"movie-tags.csv");
		String information;
		Scanner sc = null;	
		if (correctFile(file)) {
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		//the information split in two parts
			while(sc.hasNext()) {
				information = sc.nextLine();
				this.divideLinesAdd(information);
		}
			sc.close();
	}
	private void divideLinesAdd(String pInformation) {
		String[] v1 = new String[2];
		v1 = pInformation.split(";");
		Integer idFilm=Integer.parseInt(v1[0]);
		String filmTag = v1[1];
		this.addData(idFilm, filmTag);
	}
	private boolean correctFile(File pFile) {
		if(!pFile.exists()) {
			System.out.println("File not found");
			return false;
		}
		else 
		{
			return true;			
		}
	}
	public void addData(int pId, String pTag) {
		if (this.containsFirstKey(pId)) {
			if (this.containsKeys(pId, pTag)) {
				int numApar=tags.get(pId).get(pTag);
				numApar++;
				tags.get(pId).put(pTag, numApar);
			}
			else {
				tags.get(pId).put(pTag, 1);
			}
		}
		else {
			HashMap<String,Integer> aux = new HashMap<String, Integer>();
			aux.put(pTag, 1);
			tags.put(pId, aux);
		}
	}
	public boolean containsFirstKey(int pFirstKey) {
		return (tags.containsKey(pFirstKey));
	}
	public boolean containsKeys(int pFirstKey, String pTag) {
		boolean contains=false;
		if (this.containsFirstKey(pFirstKey)) {
			if (this.tags.get(pFirstKey).containsKey(pTag)){
				contains=true;
			}
		}
		return contains;
	}
	public VectorString getTagsForFilm(int pFilmId) {
		VectorString tagVector= new VectorString();
		if (this.tags.containsKey(pFilmId)) {
			Set<String> myKeys=this.tags.get(pFilmId).keySet(); //OBTENGO LAS KEYS
			tagVector.addStringSet(myKeys);//SE ANADE EL ARRAYLIST AL VECTOR
		}
		return tagVector;
	}
	public int getTagAparitions(int pId, String pTag) {
		if (this.containsKeys(pId, pTag)){
			return tags.get(pId).get(pTag);
		}
		else {
			return 0; //si no hay devuelve 0.0
		}
	}
	public VectorInteger getFilmsWithTag(String pTag) {
		VectorInteger filmsWithTag= new VectorInteger();
		VectorInteger filmList=FilmCatalogue.getFilmCatalogue().getAllFilms();
		Iterator<Integer> itr= filmList.iterator();
		while (itr.hasNext()) {
			int filmAct= itr.next();
			if (this.containsKeys(filmAct, pTag)) {
				filmsWithTag.add(filmAct);
			}
		}
		return filmsWithTag;
	}
	public int getFilmSize() {
		VectorInteger firstKeys= new VectorInteger();
		Set<Integer> myKeys=this.tags.keySet(); //OBTENGO LAS KEYS
		firstKeys.addIntegerSet(myKeys);
		return firstKeys.size();
	}
}
