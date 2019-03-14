package packEuskoFlix;

public class Review implements Comparable<Review>{
	private String userId;
	private String description;
	private double valoration;
	
	public Review(String pUserId, String pDescription, double pValoration) {
		this.userId = pUserId;
		this.description = pDescription;
		this.valoration = pValoration;
	}
	
	//TODO
	public int compareTo(Review pReview) {}
}