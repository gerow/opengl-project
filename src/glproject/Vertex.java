package glproject;

public class Vertex {
    public Vector3f location;
    public Vector3f normal = null;
    public Vector3f surfaceNormal = null;
    public Vector2f textureCoordinate = null;
    public Vector3f color = null;
    
    public Vertex(Vector3f location, Vector3f surfaceNormal, Vector2f textureCoordinate) {
	this.location = location;
	this.textureCoordinate = textureCoordinate;
    }
    
    public Vertex(Vertex copy) {
	this.location = new Vector3f(copy.location);
	if (copy.normal != null)
	    this.normal = new Vector3f(copy.normal);
	if (copy.surfaceNormal != null)
	    this.surfaceNormal = new Vector3f(copy.surfaceNormal);
	if (copy.textureCoordinate != null)
	    this.textureCoordinate = new Vector2f(copy.textureCoordinate);
	if (copy.color != null)
	    this.color = new Vector3f(copy.color);
    }
    
    public Vertex() {
	this.location = new Vector3f(0.0f, 0.0f, 0.0f);
    }
}
