package packEuskoFlix;

public class UserFilter {
	private Matrix filteredMatrix;
	private Matrix similitudeMatrix;
	public UserFilter() {
		this.filteredMatrix = new Matrix();
		this.similitudeMatrix = new Matrix();
	}
	public Matrix normalize() {
		return filteredMatrix;
	}
	
	public Double calculateSimilitude( int pId1, int pId2) {
		return 1.0;
	}
	
	public Vector filmsInCommon() {
		return new Vector();
	}
}
