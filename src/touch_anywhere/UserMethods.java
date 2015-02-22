package touch_anywhere;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class UserMethods {
	
	//useless CONSTRUCTOR
	public UserMethods() {
		
	}
	
	
	//Open a website
	public void openWebpage() {
		try {
			java.awt.Desktop.getDesktop().browse(new URI("https://www.leapmotion.com"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
