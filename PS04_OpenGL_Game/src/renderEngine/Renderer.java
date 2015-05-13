package renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**Renders the 3D model out of a vao*/
public class Renderer {
/**will be called once every frame to prepare OpenGL to render the game.*/
	public void prepare(){
		GL11.glClearColor(0, 0, 1, 1);//backgrounds: (red, green, blue, x)
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}
	
	public void render(RawModel model){
		GL30.glBindVertexArray(model.getVaoID());//open
		GL20.glEnableVertexAttribArray(0);//open
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);//close
		GL30.glBindVertexArray(0);//close
	}
}
