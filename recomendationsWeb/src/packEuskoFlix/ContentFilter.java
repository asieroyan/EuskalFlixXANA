package packEuskoFlix;

import java.util.HashMap;

public class ContentFilter extends FilterMode {
	private HashMap<Integer, String> tags;
	private VectorString lista;
	public ContentFilter() {
		super();
		this.tags = new HashMap<Integer, String>();
	}
	
	public Double getEstimatedValorationForFilm(Integer pIdUser, Integer pFilm) {
		// TODO Auto-generated method stub
		return null;
	}

	public Matrix calculateNSimilars(Integer pIdUser, Integer pN, Integer pFilm) {
		// TODO Auto-generated method stub
		return null;
	}

}