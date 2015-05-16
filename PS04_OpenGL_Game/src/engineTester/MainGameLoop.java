package engineTester;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		//openGL expects vertices to be defined counter clockwise by default
		float[] vertices = {
				-0.5f, 0.5f, 0,
				-0.5f, -0.5f, 0,
				0.5f, -0.5f, 0,
				0.5f, 0.5f, 0
		};

		int[] indices = {
				0, 1, 3,
				3, 1, 2
		};
		
		float[] textureCoords = {
			0,0,	//V0
			0,1,	//V1
			1,1,	//V2
			1,0		//V3
		};

		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("myImage")));

		Entity entity = new Entity(staticModel, new Vector3f(0,0,-1), 0, 0, 0, 1);
		
		while(!Display.isCloseRequested()){
			entity.increasePosition(0.0001f, 0.0f, 0.0002f);//move entity
			entity.increaseRotation(0.0f, 0.5f, 0.1f);//rotate entity
			renderer.prepare();
			shader.start();
			renderer.render(entity, shader);
			shader.stop();
			DisplayManager.updateDisplay();
		}

		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
