package packEuskoFlix;

public class UserCatalogue {
	private UserList list;
	private static UserCatalogue mUserCatalogue;
	
	private UserCatalogue() {
		list = new UserList("1");
	}
	
	public static UserCatalogue getUserCatalogue() {
		if (mUserCatalogue == null) {
			mUserCatalogue = new UserCatalogue();
		}
		return mUserCatalogue;
	}
	
	//TODO
	public void initialize() {}
}