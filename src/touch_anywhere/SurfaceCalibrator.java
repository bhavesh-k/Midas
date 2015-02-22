package touch_anywhere;
import java.util.ArrayList;

import com.leapmotion.leap.*;


public class SurfaceCalibrator {
	
	public ArrayList<DrawnButton> buttonsList;
	
	
	//CONSTRUCTOR does nothing
	public SurfaceCalibrator() {
		buttonsList = new ArrayList<DrawnButton>(); //initialize buttonsList
	}
	
	
	//Get index finger tip position vector for this corner
	public Vector calibratePos(Frame frame) {
		return frame.hands().rightmost().fingers().get(1).tipPosition(); 
	}
	
	//Add a hand-drawn "button" to the list of buttons
	public void addButton(Vector topLeft, Vector bottomRight, String buttonName) {
		buttonsList.add(new DrawnButton(topLeft,bottomRight,buttonName));
		System.out.println("New button " + buttonName + " added");
	}


}
