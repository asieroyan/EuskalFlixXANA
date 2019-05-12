package packEuskoFlix;

import view.LoginMenu;
import view.MainMenu;

public class Main {

	public static void main(String[] args) {
		LoginMenu logIn = new LoginMenu();
		MainMenu menu = new MainMenu();
		TagCatalogue.getTagCatalogue();//Hay que inicializarlo y con esto se inicializa
		logIn.setVisible(true);
	}

}
