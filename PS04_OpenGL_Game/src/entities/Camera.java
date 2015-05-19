package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private Vector3f position = new Vector3f(0,1,0);
	private float pitch;//how high/low the caemra is aimed
	private float yaw;//how much left or right is the camera aimed
	private float roll;//how tilted to one side (180 degree roll means the camera is upside down

	//public Camera(){}

	/*public void move(){
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			yaw -= 0.02f;
			System.out.println("OI!");
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			yaw += 0.02f;
			System.out.println("OI!");
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			pitch += 0.02f;
			System.out.println("OI!");
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			pitch += 0.02f;
			System.out.println("OI!");
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.z -= 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.z += 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			position.y -= 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			position.y += 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x += 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x -= 0.02f;
		}

	}*/

	private float speed;
	private boolean enableMouse;
	public Camera()
	{
		this.speed = 0.5f;
		this.enableMouse = false;
	}

	public void move()
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_M)){//enable or disable mouse
			enableMouse = !enableMouse;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_P)){//increase camera movement speed
			speed += 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_O)){//decrease camera movement speed
			speed -= 0.1f;
		}
		if(enableMouse){
			yaw = -(Display.getWidth() - Mouse.getX() / 2) / 2;
			pitch = (Display.getHeight() / 2) - Mouse.getY();

			/*if (pitch >= 90)
			{
				pitch = 90;
			}
			else if (pitch <= -90)
			{
				pitch = -90;
			}*/
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			yaw += 1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			yaw -= 1f;
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			pitch -= 0.5f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			pitch += 0.5f;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			position.z += -(float)Math.cos(Math.toRadians(yaw)) * speed;
			position.x += (float)Math.sin(Math.toRadians(yaw)) * speed;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			position.z -= -(float)Math.cos(Math.toRadians(yaw)) * speed;
			position.x -= (float)Math.sin(Math.toRadians(yaw)) * speed;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			position.z += (float)Math.sin(Math.toRadians(yaw)) * speed;
			position.x += (float)Math.cos(Math.toRadians(yaw)) * speed;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			position.z -= (float)Math.sin(Math.toRadians(yaw)) * speed;
			position.x -= (float)Math.cos(Math.toRadians(yaw)) * speed;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			position.y -= 0.1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			position.y += 0.1f;
		}
		System.out.println(position);
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
