package glproject;

import java.util.ArrayList;

import javax.media.opengl.GLDrawable;
import javax.media.opengl.glu.GLU;

public class Sprite {
    private Vector3f location = new Vector3f(0, 0, 0);
    private int imageNumber = 0;
    private World world;
    private float scale;
    private Polygon polygon;
    
    public Sprite(World world) {
	this.world = world;
	ArrayList<Vertex> verticies = new ArrayList<Vertex>();
	Vertex v = new Vertex();
	v.location = new Vector3f(-1.0f, -1.0f, 0.0f);
	v.textureCoordinate = new Vector2f(0.0f, 1.0f);
	verticies.add(v);
	v = new Vertex();
	v.location = new Vector3f(-1.0f, 1.0f, 0.0f);
	v.textureCoordinate = new Vector2f(0.0f, 0.0f);
	verticies.add(v);
	v = new Vertex();
	v.location = new Vector3f(1.0f, 1.0f, 0.0f);
	v.textureCoordinate = new Vector2f(1.0f, 0.0f);
	verticies.add(v);
	v = new Vertex();
	v.location = new Vector3f(1.0f, -1.0f, 0.0f);
	v.textureCoordinate = new Vector2f(1.0f, 1.0f);
	verticies.add(v);
	this.polygon = new Polygon(verticies);
    }
    
    public void render(GLDrawable drawable, GLU glu) {
    }
}
