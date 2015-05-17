package engineTester;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;
import entities.Camera;
import entities.Entity;
import entities.Light;

public class MainGameLoop {
	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);

		//openGL expects vertices to be defined counter clockwise by default
		
		
		/**can load: stall*/
		RawModel model = OBJLoader.loadObjModel("dragon", loader);
		
		/**can load: stall_image, myImage*/
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));

		Entity entity = new Entity(staticModel, new Vector3f(0,0,-20), 0, 0, 0, 1);
		Light light = new Light(new Vector3f(0, 0, -10), new Vector3f(1,1,1));
		
		Camera camera = new Camera();

		while(!Display.isCloseRequested()){
			entity.increasePosition(0.0f, 0.0f, 0.0f);//move entity
			entity.increaseRotation(0.0f, 0.5f, 0.0f);//rotate entity
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadLight(light);
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			shader.stop();
			DisplayManager.updateDisplay();
		}

		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
