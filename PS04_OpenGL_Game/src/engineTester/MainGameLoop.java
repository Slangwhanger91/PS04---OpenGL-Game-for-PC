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
import terrains.Terrain;
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
		/*RawModel model = OBJLoader.loadObjModel("dragon", loader);

		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("blue")));
		ModelTexture texture = staticModel.getTexture();
		texture.setShineDamper(50);//glasslike(shinier)
		texture.setReflectivity(1);//amount of light reflected (can absorb light as well with negative values!)

		Entity entity = new Entity(staticModel, new Vector3f(0,0,-20), 0, 0, 0, 0.5f);//entity position, preset color and scale

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
		//sun obj
		RawModel sun_model = OBJLoader.loadObjModel("sun", loader);

		TexturedModel staticModel = new TexturedModel(sun_model, new ModelTexture(loader.loadTexture("yellow")));
		ModelTexture texture = staticModel.getTexture();
		texture.setShineDamper(500);//glasslike(shinier)
		texture.setReflectivity(1000);//amount of light reflected (can absorb light as well with negative values!)
		Entity sun = new Entity(staticModel, new Vector3f(0, 70, -100), 0, 0, 0, 70f);//entity position, preset color and scale
		Light light = new Light(new Vector3f(0, 69, -100), new Vector3f(1, 1, 1));
		//sun obj
		
		//test2

		Terrain terrain = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("terrain")));
		Terrain terrain2 = new Terrain(-1, -1, loader, new ModelTexture(loader.loadTexture("terrain")));

		Camera camera = new Camera();
		MasterRenderer renderer = new MasterRenderer();


		while(!Display.isCloseRequested()){
			camera.move();
			renderer.processEntity(sun);
			renderer.processEntity(sun);
			sun.increaseRotation(0.01f, 0.01f, 0);
			
			for(Entity entity:allEntities){
			entity.increasePosition(0.0f, 0.0f, 0.0f);//move entity on x,y,z
			entity.increaseRotation(0.0f, 0.6f, 0.0f);//rotate entity around x,y,z
			renderer.processEntity(entity);
			renderer.processEntity(entity);//bug?
			}
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);

			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
