package glproject;

public class Vertex {
    public Vector3d location;
    public Vector3d normal = null;
    public Vector3d surfaceNormal = null;
    public Vector2d textureCoordinate = null;
    public Vector3d color = null;
    
    public Vertex(Vector3d location, Vector3d surfaceNormal, Vector2d textureCoordinate) {
	this.location = location;
	this.textureCoordinate = textureCoordinate;
    }
    
    public Vertex() {
	this.location = new Vector3d(0.0f, 0.0f, 0.0f);
    }
}
