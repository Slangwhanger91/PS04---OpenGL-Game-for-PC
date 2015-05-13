package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;

public class MainGameLoop {
	public static void main(String[] args) {
		DisplayManager.createDisplay();

		int n = 0;
		while(!Display.isCloseRequested()){
			DisplayManager.updateDisplay();

			System.out.println(n++);
		}
		
		DisplayManager.closeDisplay();
	}
}
