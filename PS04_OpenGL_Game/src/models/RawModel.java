package models;

/**Represents a 3D model stored in memory*/
public class RawModel {

	private int vaoID;
	private int vertexCount;


	public RawModel(int vaoID, int vertexCount){
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}


	public int getVaoID() {
		return vaoID;
	}


	public int getVertexCount() {
		return vertexCount;
	}
}