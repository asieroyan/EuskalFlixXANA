package packEuskoFlix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class ContentFilter extends FilterMode {
	private HashMap<Integer, String> tags;
	private VectorString lista;
	public ContentFilter() {
		super();
		this.tags= new HashMap<Integer,String>();
	}
	public void divideLineAdd(String pInformation) { //posible optimizacion
		String[] v1 = new String[3];
		v1 = pInformation.split(",");
		Integer idUser=Integer.parseInt(v1[0]);
		Integer idFilm=Integer.parseInt(v1[1]);
		Double rate = Double.parseDouble(v1[2]);
		RatingCatalogue.getRatingCatalogue().addOneLine(idUser, idFilm, rate);
		//consigo los tags
		this.getTags();
	}
	public Double getEstimatedValorationForFilm(Integer pIdUser, Integer pFilm) {
		// TODO Auto-generated method stub
		return null;
	}

	public Matrix calculateNSimilars(Integer pIdUser, Integer pN, Integer pFilm) {
		// TODO Auto-generated method stub
		return null;
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
				Integer filmId = this.getFilmIdFromLine(information);
				String tag = this.getTagFromLine(information);
				tags.put(filmId, tag);		
		}
			sc.close();
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
		ModelMatrix productModel= new ModelMatrix();
		VectorInteger films=FilmCatalogue.getFilmCatalogue().getAllFilms();
		Iterator<Integer> itr= films.iterator();
		while (itr.hasNext()) {
			int filmAct=itr.next();
			this.addFilmModel(filmAct, productModel);
		}
		return productModel;
		
	}
	private ModelMatrix addFilmModel(int pFilm, ModelMatrix pProductModel) {
		VectorString filmtags= this.getTagsForFilm(pFilm);
		Iterator<String> itr= filmtags.iterator();
		while (itr.hasNext()) {
			String tagAct= itr.next();
			double tfidf=this.tagImportanceInFilm(pFilm, tagAct);
			pProductModel.add(pFilm, tagAct, tfidf);
		}
		return pProductModel;
	}
	private ModelMatrix calculateUserModel() {
		//todo
		return null;
		
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
	private VectorInteger getFilmsWithTag(String pTag) {
		VectorInteger filmsWithTag = new VectorInteger();
		tags.forEach((filmId,tag) ->{
			if (tag == pTag) {
				filmsWithTag.add(filmId);
			}
		});
		return filmsWithTag;
	}
	
	private int getFilmNumWithTag(String pTag) {
		return getFilmsWithTag(pTag).size();
	}

}