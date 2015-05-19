package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import textures.ModelTexture;
import entities.Camera;
import entities.Entity;
import entities.Light;

public class MainGameLoop {
	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();

		//openGL expects vertices to be defined counter clockwise by default

		//test1
		/*		RawModel model = OBJLoader.loadObjModel("stall", loader);

		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("myImage")));
		ModelTexture texture = staticModel.getTexture();
		texture.setShineDamper(50);//glasslike(shinier)
		texture.setReflectivity(1);//amount of light reflected (can absorb light as well with negative values!)

		Entity entity = new Entity(staticModel, new Vector3f(0,-5,-20), 0, 0, 0, 1);//entity position, preset color and scale

		Light light = new Light(new Vector3f(200, 200, 100), new Vector3f(1,1,1));//light position(x,y,z) and color(RGB)
		 */
		//test1

		//test2
		RawModel model = OBJLoader.loadObjModel("cube", loader);
		TexturedModel[] all_textures = { 
				new TexturedModel(model, new ModelTexture(loader.loadTexture("blue"))),
				new TexturedModel(model, new ModelTexture(loader.loadTexture("blue"))),
				new TexturedModel(model, new ModelTexture(loader.loadTexture("blue"))),
				new TexturedModel(model, new ModelTexture(loader.loadTexture("grey"))),
				new TexturedModel(model, new ModelTexture(loader.loadTexture("red"))),
				new TexturedModel(model, new ModelTexture(loader.loadTexture("purple"))),
				new TexturedModel(model, new ModelTexture(loader.loadTexture("green"))),
				new TexturedModel(model, new ModelTexture(loader.loadTexture("red"))),
				new TexturedModel(model, new ModelTexture(loader.loadTexture("green")))
		};
		int[] lights = {-10, 1, 10, 100};
		int[] shines = {1, 10, 100};
		for (int i = 0; i < 9; i++) {
			all_textures[i].getTexture().setShineDamper(shines[i % shines.length]);
			all_textures[i].getTexture().setReflectivity(lights[i % lights.length]);
		}


		Light light = new Light(new Vector3f(3000, 2000, 3000), new Vector3f(1, 1, 1));
		List<Entity> allEntities = new ArrayList<Entity>();
		Random random = new Random();
		for (int i = 0; i < 200; i++) {
			float x = random.nextFloat() * 100 - 50;
			float y = random.nextFloat() * 100 - 50;
			float z = random.nextFloat() * -300;
			allEntities.add(new Entity(all_textures[i % all_textures.length], new Vector3f(x, y, z), random.nextFloat() * 180f,
					random.nextFloat() * 180f, 0f, 1f));
			//f.e: new Entity(staticModel, new Vector3f(0,-5,-20), 0, 0, 0, 1);//entity position, preset color and scale
		}
		//test2

		Camera camera = new Camera();
		MasterRenderer renderer = new MasterRenderer();
		while(!Display.isCloseRequested()){
			camera.move();
			for(Entity entity:allEntities){
				entity.increasePosition(0.0f, 0.0f, 0.0f);//move entity on x,y,z
				entity.increaseRotation(0.0f, 0.6f, 0.0f);//rotate entity around x,y,z
				renderer.processEntity(entity);
			}
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
