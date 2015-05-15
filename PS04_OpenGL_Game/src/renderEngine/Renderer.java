package renderEngine;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**Renders the 3D model out of a vao*/
public class Renderer {
/**will be called once every frame to prepare OpenGL to render the game.*/
	public void prepare(){
		GL11.glClearColor(0, 0, 1, 1);//backgrounds: (red, green, blue, x)
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}
	
	public void render(TexturedModel texturedModel){
		RawModel model = texturedModel.getRawModel();
		GL30.glBindVertexArray(model.getVaoID());//open
		GL20.glEnableVertexAttribArray(0);//open
		GL20.glEnableVertexAttribArray(1);//enable
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getID());
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);//close
		GL20.glDisableVertexAttribArray(1);//disable
		GL30.glBindVertexArray(0);//close
	}
}
