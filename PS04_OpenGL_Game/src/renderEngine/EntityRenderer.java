package renderEngine;

import java.util.List;
import java.util.Map;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import shaders.StaticShader;
import textures.ModelTexture;
import toolbox.Maths;
import entities.Entity;

/**Renders the 3D model out of a vao*/
public class EntityRenderer {

	private StaticShader shader;

	public EntityRenderer(StaticShader shader, Matrix4f projectionMatrix){
		this.shader = shader;
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}


	public void render(Map<TexturedModel, List<Entity>> entities){
		for(TexturedModel model : entities.keySet()){
			prepareTexturedModel(model);
			List<Entity> batch = entities.get(model);
			for(Entity entity : batch){
				prepareInstance(entity);
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			}
			unbindTexturedModel();
		}	
	}

	private void prepareTexturedModel(TexturedModel model){
		RawModel rawModel = model.getRawModel();
		GL30.glBindVertexArray(rawModel.getVaoID());//open
		GL20.glEnableVertexAttribArray(0);//open
		GL20.glEnableVertexAttribArray(1);//enable
		GL20.glEnableVertexAttribArray(2);//enable
		ModelTexture texture = model.getTexture();
		shader.loadShineVarianbles(texture.getShineDamper(), texture.getReflectivity());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());

	}

	private void unbindTexturedModel(){
		GL20.glDisableVertexAttribArray(0);//close
		GL20.glDisableVertexAttribArray(1);//disable
		GL20.glDisableVertexAttribArray(2);//disable
		GL30.glBindVertexArray(0);//close
	}

	private void prepareInstance(Entity entity){
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), 
				entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
	}

}
