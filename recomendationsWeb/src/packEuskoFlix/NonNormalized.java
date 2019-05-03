package packEuskoFlix;

public class NonNormalized implements NormalizeMode {
	public Matrix normalizeMatrix() {
		return(RatingCatalogue.getRatingCatalogue().getMatrix());
	}
	public Matrix unNormalizeMatrix(int pIdUser,Matrix pMatrix) {
		return pMatrix;
	}
}
