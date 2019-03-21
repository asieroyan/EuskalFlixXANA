package packEuskoFlix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
	public void add(Integer pID,User pUser) {
		this.list.add(pID,pUser);
	}
	public void printUsers() {
		this.list.printUsers();
	}
	public void loadFilmRatings() {
		File file = new File(System.getProperty("user.dir"),"movie-ratings.csv");
		if(!file.exists()) {
			System.out.println("File not found");
		}else {
			Scanner sc = null;
			try {
				sc = new Scanner(file);
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			String information;
			String[] v1 = new String[3];     //the information split in three parts
			while(sc.hasNext()) {
				information = sc.nextLine();
				v1 = information.split(",");
				Integer idUser=Integer.parseInt(v1[0]);
				Integer idFilm=Integer.parseInt(v1[1]);
				Double rating= Double.parseDouble(v1[2]);
				User user= new User(idUser);
				this.add(idUser, user);
				Ratings.getRatings().addReview(idFilm, idUser, rating);			
			}
		}
	}
}