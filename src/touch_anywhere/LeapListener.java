package touch_anywhere;
import com.leapmotion.leap.*;

public class LeapListener extends Listener {
	
	public SurfaceChecker checker;
	public Main gui_method;
	public boolean readyToCheck = false;
	
	public LeapListener(SurfaceChecker c, Main main) {
		checker = c;
		gui_method = main;
	}
	
	public void onConnect(Controller controller) {
		System.out.println("Connected to Leap");
	}
	
	public void onFrame(Controller controller) {
		
		if (readyToCheck) {
			
			Frame frame = controller.frame();
			if (((frame.id()) % 20) == 0) { //only process once every 20 frames
				gui_method.lblTouchRespond.setText(checker.check(frame)); //print the button that is being pressed
			}
		}
			
	}
	
	public void onDisconnect(Controller controller) {
		System.out.println("Leap disconnected");
		controller.removeListener(this);
	}
		
}
