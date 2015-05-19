package renderEngine;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.TexturedModel;
import shaders.StaticShader;
import entities.Camera;
import entities.Entity;
import entities.Light;

public class MasterRenderer {

	private StaticShader shader = new StaticShader();
	private Renderer renderer = new Renderer(shader);

	private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();

	public void render(Light sun, Camera camera){
		renderer.prepare();
		shader.start();
		shader.loadLight(sun);
		shader.loadViewMatrix(camera);
		renderer.render(entities);//hashmapping entities
		shader.stop();
		entities.clear();
	}

	/**
	 * checks if the model is already there:
	 * <br>If it's then add another entity which's using the same model
	 * <br>If not then create new space for this model in the hashmap
	 */
	public void processEntity(Entity entity){
		TexturedModel entityModel = entity.getModel();
		List<Entity> batch = entities.get(entityModel);
		if(batch != null){
			batch.add(entity);
		}else{
			List<Entity> newBatch = new ArrayList<Entity>();
			entities.put(entityModel, newBatch);
		}
	}

	public void cleanUp(){
		shader.cleanUp();
	}

}
