package packEuskoFlix;

public class User {
	private String name;
	private String id;
	private UserList mostLikeUsers;
	
	public User(String pName, String pId) {
		this.name = pName;
		this.id = pId;
		this.mostLikeUsers = new UserList();
	}
	
	//TODO
	private UserList obtainMostLikeUsers() {}
	
	//TODO
	public boolean doILike(String pFimlName) {}
}