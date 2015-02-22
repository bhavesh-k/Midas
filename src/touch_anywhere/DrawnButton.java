package touch_anywhere;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.leapmotion.leap.*;

public class DrawnButton {

	public String buttonName;
	public Vector topLeft;
	public Vector bottomRight;
	public float averageZ;
	
	public UserMethods usermethods;
	
	
	public DrawnButton(Vector tl, Vector br, String name) {
		buttonName = name;
		
		averageZ = (tl.getZ() + br.getZ()) / ((float) 2);
		
		topLeft = new Vector(tl.getX(), tl.getY(), averageZ);
		bottomRight = new Vector(br.getX(), br.getY(), averageZ);
		
		usermethods = new UserMethods();
	}
	
	
	//Do method by name
	public void doMethod(String methodName) {
		try {
			Class c = Class.forName("touch_anywhere.UserMethods");
			Method method = c.getDeclaredMethod(methodName);
			method.invoke(usermethods);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
				
	}
	
}
