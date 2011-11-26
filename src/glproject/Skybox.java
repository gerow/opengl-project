package glproject;

import java.io.IOException;
import java.util.ArrayList;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;

public class Skybox implements Renderable {
    private Polygon north, south, east, west, top, bottom;
    public Skybox(float size, String allSides) throws GLException, IOException {
	this(size, allSides, allSides, allSides, allSides, allSides, allSides);
    }
    
    public Skybox(float size, String northTex, String southTex, String eastTex, String westTex, String topTex, String bottomTex) throws GLException, IOException {
	float factor = size / 2;
	
	//Building north
	ArrayList<Vertex> northVerticies = new ArrayList<Vertex>();
	Vertex v1 = new Vertex();
	v1.location = new Vector3f(-factor, -factor, factor);
	v1.textureCoordinate = new Vector2f(1.0f, 0.0f);
	Vertex v2 = new Vertex();
	v2.location = new Vector3f(-factor, factor, factor);
	v2.textureCoordinate = new Vector2f(1.0f, 1.0f);
	Vertex v3 = new Vertex();
	v3.location = new Vector3f(factor, factor, factor);
	v3.textureCoordinate = new Vector2f(0.0f, 1.0f);
	Vertex v4 = new Vertex();
	v4.location = new Vector3f(factor, -factor, factor);
	v4.textureCoordinate = new Vector2f(0.0f, 0.0f);
	this.north = new Polygon(northVerticies);
	this.north.material = Material.pureAmbient();
	this.north.material.texture = TextureLoader.loadTexture(northTex);
	
	//Building south
	ArrayList<Vertex> southVerticies = new ArrayList<Vertex>();
	v1 = new Vertex();
	v1.location = new Vector3f(factor, -factor, -factor);
	v1.textureCoordinate = new Vector2f(1.0f, 0.0f);
	v2 = new Vertex();
	v2.location = new Vector3f(factor, factor, -factor);
	v2.textureCoordinate = new Vector2f(1.0f, 1.0f);
	v3 = new Vertex();
	v3.location = new Vector3f(-factor, factor, -factor);
	v3.textureCoordinate = new Vector2f(0.0f, 1.0f);
	v4 = new Vertex();
	v4.location = new Vector3f(-factor, -factor, -factor);
	v4.textureCoordinate = new Vector2f(0.0f, 0.0f);
	this.south = new Polygon(southVerticies);
	this.south.material = Material.pureAmbient();
	this.south.material.texture = TextureLoader.loadTexture(southTex);
    }
    
    public void init(World world) {
	// TODO Auto-generated method stub

    }

    public void render(GLAutoDrawable drawable, GLU glu) {
	// TODO Auto-generated method stub

    }

}
