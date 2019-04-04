package notUsed;

import java.util.HashMap;

public class UserCatalogue {
	private HashMap<Integer, User> list;
	private static UserCatalogue myUserCatalogue = new UserCatalogue();
	
	private UserCatalogue() {
		list = new HashMap<Integer, User>();
	}
	
	public static UserCatalogue getUserCatalogue() {
		return myUserCatalogue;
	}
	
}
