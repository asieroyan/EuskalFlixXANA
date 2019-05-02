package packEuskoFlix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ContentFilter extends FilterMode {
	private HashMap<Integer, String> tags;
	private VectorString lista;
	public ContentFilter() {
		super();
		this.tags = this.getTags();
	}
	//hacer su propia inicializacion
	public Double getEstimatedValorationForFilm(Integer pIdUser, Integer pFilm) {
		// TODO Auto-generated method stub
		return null;
	}

	public Matrix calculateNSimilars(Integer pIdUser, Integer pN, Integer pFilm) {
		// TODO Auto-generated method stub
		return null;
	}
	public HashMap<Integer, String> getTags() {
		File file = new File(System.getProperty("user.dir"),"movie-tags.csv");
		String information;
		Scanner sc = null;
		HashMap<Integer, String> output= new HashMap<Integer,String>();
		
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
				Integer filmId = this.getFilmIdFromLine(information);
				String tag = this.getTagFromLine(information);
				output.put(filmId, tag);		
		}
			sc.close();
			return output;
	}
	private Integer getFilmIdFromLine(String pLine){
		String[] v1 = new String[2];
		v1 = pLine.split(";");
		Integer idFilm=Integer.parseInt(v1[0]);
		return idFilm;
	}
	
	private String getTagFromLine(String pLine) {
		String[] v1 = new String[2];
		v1 = pLine.split(";");
		String tag = v1[1];
		return tag;
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
	private ModelMatrix calculateProductModel() {
		
	}
	private ModelMatrix calculateUserModel() {
		
	}
	private Double tagImportanceInFilm(int pFilmId, String pTag) {
		//calcula tfidf
		int tagAparitions = this.tagAparitionsInFilm(pFilmId, pTag);
		int filmsNum = FilmCatalogue.getFilmCatalogue().getAllFilms().size();
		int filmsWithThisTag = this.getFilmNumWithTag(pTag);
		Double output = tagAparitions * Math.log((filmsNum)/(filmsWithThisTag));
		System.out.println(output);
		return output;
	}
	
	private int tagAparitionsInFilm(int pFilmId, String pTag) {
		VectorString filmVector = this.getTagsForFilm(pFilmId);
		return this.tagAparitionsInVector(filmVector, pTag);
	}
	private VectorString getTagsForFilm(int pFilmId) {
		VectorString tagVector= new VectorString();
		tags.forEach((filmId,tag) ->{
			if (filmId == pFilmId) {
				tagVector.add(tag);
			}
		});
		return tagVector;
	}
	
	private int tagAparitionsInVector(VectorString pVector, String pTag) {
		return pVector.calculateStringAparitions(pTag);
	}
	private Vector getFilmsWithTag(String pTag) {
		Vector filmsWithTag = new Vector();
		tags.forEach((filmId,tag) ->{
			if (tag == pTag) {
				if(!filmsWithTag.contains(filmId)) {
					filmsWithTag.add(filmId);
				}
			}
		});
		return filmsWithTag;
	}
	
	private int getFilmNumWithTag(String pTag) {
		return getFilmsWithTag(pTag).size();
	}
}