package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private Vector3f position = new Vector3f(0,0,0);
	private float pitch;//how high/low the caemra is aimed
	private float yaw;//how much left or right is the camera aimed
	private float roll;//how tilted to one side (180 degree roll means the camera is upside down
	
	public Camera(){}
	
	public void move(){
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.z -= 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.z += 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x += 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x -= 0.02f;
		}
		
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
}
