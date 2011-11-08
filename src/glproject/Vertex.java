package glproject;

public class Vertex {
    public Vector3d location;
    public Vector3d normal = null;
    public Vector3d surfaceNormal;
    public Vector3d color;
    
    public Vertex(Vector3d location, Vector3d surfaceNormal, Vector3d color) {
	this.location = location;
	this.color = color;
    }
}
