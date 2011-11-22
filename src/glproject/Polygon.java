package glproject;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

public class Polygon {
    public enum Type {
	TRIANGLE, QUAD, POLY
    };

    Type type;
    public ArrayList<Vertex> verticies;
    public Vector3d surfaceNormal;
    public Material material;

    public Polygon(ArrayList<Vertex> verticies) {
	this.verticies = verticies;
	if (this.verticies.size() < 3)
	    throw new RuntimeException(
		    "Tried to create a polygon with less than three verticies");
	if (this.verticies.size() == 3)
	    this.type = Type.TRIANGLE;
	else if (this.verticies.size() == 4)
	    this.type = Type.QUAD;
	else
	    this.type = Type.POLY;
	// this.surfaceNormal = Polygon.computeSurfaceNormal(
	// verticies.get(0).location, verticies.get(1).location,
	// verticies.get(2).location);
	// for (Vertex v : this.verticies)
	// v.surfaceNormal = this.surfaceNormal;
    }

    public static Vector3d computeSurfaceNormal(Vector3d vec1, Vector3d vec2,
	    Vector3d vec3) {
	Vector3d U = vec1.vectorTo(vec2);
	Vector3d V = vec1.vectorTo(vec3);
	return U.cross(V);
    }

    public void render(GLAutoDrawable drawable, GLU glu) {
	GL2 gl = drawable.getGL().getGL2();
	if (this.material != null)
	    this.material.enableMaterial(drawable, glu);
	for (Vertex v : this.verticies) {
	    if (v.textureCoordinate != null)
		gl.glTexCoord2f(v.textureCoordinate.x, v.textureCoordinate.y);
	    else
		gl.glColor3f(v.color.x, v.color.y, v.color.z);
	    if (v.normal != null)
		gl.glNormal3f(v.normal.x, v.normal.y, v.normal.z);
	    gl.glVertex3f(v.location.x, v.location.y, v.location.z);
	}
    }
}
