package packEuskoFlix;

public abstract class FilterMode {
	private Matrix NMatrix;
	public FilterMode() {
		this.NMatrix= new Matrix();
	}
	public abstract Vector recommendedFilm(Integer pIdUser);
	
	public void normalizeMatrix() {
		this.NMatrix=RatingCatalogue.getRatingCatalogue().normalizeMatrix();
	}
	public double getValoration(Integer pUser, Integer pFilm) {
		double valoration=0.0;
		if (this.NMatrix.containsKeys(pUser, pFilm)){
			valoration=this.NMatrix.getValue(pUser, pFilm);
		}
		return valoration;
	}
	public Matrix unNormalizeMatrix(int pIdUser,Matrix pMatrix) {
		return(RatingCatalogue.getRatingCatalogue().unNormalizeMatrix(pIdUser, pMatrix));
	}
}
