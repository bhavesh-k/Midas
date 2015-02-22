package touch_anywhere;

import java.util.ArrayList;

import com.leapmotion.leap.*;

public class SurfaceChecker {
	
	public ArrayList<DrawnButton> buttonsList;
	
	
	//CONSTRUCTOR populates with list of buttons
	public SurfaceChecker(SurfaceCalibrator calibrator) {
		this.buttonsList = calibrator.buttonsList;
	}
	
	
	//Check which button is being pressed
	public String check(Frame frame) {
		Vector position = frame.hands().rightmost().fingers().get(1).tipPosition();
		
		for (DrawnButton element : buttonsList) {
			float min_x = element.topLeft.getX();			
			float max_y = element.topLeft.getY();
			float max_x = element.bottomRight.getX();
			float min_y = element.bottomRight.getY();
			
			float curr_x = position.getX(); float curr_y = position.getY();
			
			if ((curr_x < max_x) && (curr_x > min_x) && (curr_y < max_y) && (curr_y > min_y)) { //check x-y position
				
				if ((position.getZ() - element.averageZ) < 5.0) {//check z position within bounds	
					element.doMethod(element.buttonName);
					return (element.buttonName);
				}
				
			}
		}
		
		return ("None");
	}
	
	//everytime calibrator has new button in it
	public void update(SurfaceCalibrator calibrator) {
		this.buttonsList = calibrator.buttonsList;
	}

}
