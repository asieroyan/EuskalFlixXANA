package packEuskoFlix;

import java.util.HashMap;

public class ContentFilter extends FilterMode {
	private HashMap<Integer, String> tags;
	private VectorString lista;
	public ContentFilter() {
		super();
		this.tags = FilmCatalogue.getFilmCatalogue().getTags();
	}
	
	public Double getEstimatedValorationForFilm(Integer pIdUser, Integer pFilm) {
		// TODO Auto-generated method stub
		return null;
	}

	public Matrix calculateNSimilars(Integer pIdUser, Integer pN, Integer pFilm) {
		// TODO Auto-generated method stub
		return null;
	}

	private Double tagImportanceInFilm(int pFilmId, String pTag) {
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