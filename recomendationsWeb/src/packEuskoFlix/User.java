package packEuskoFlix;

public class User {
	//private String name; PUEDE QUE HAYA QUE QUITARLO
	private Integer id;
	private UserList mostLikeUsers;
	
	public User(Integer pId) {
		//this.name = pName;
		this.id = pId;
		this.mostLikeUsers = new UserList("2");
	}
	
	//TODO
	private UserList obtainMostLikeUsers() {
		return null;
	}
	
	//TODO
	public boolean doILike(String pFimlName) {
		return false;
	}
	public void printUser(){
		System.out.println("User ID: "+this.id);
	}
	public int compareTo(User pUser) {
		return (this.id.compareTo(pUser.id));
	}
}