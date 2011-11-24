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
    public Vector3f surfaceNormal;
    public Material material;
    public ShaderProgram shaderProgram = null;

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

    public static Vector3f computeSurfaceNormal(Vector3f vec1, Vector3f vec2,
	    Vector3f vec3) {
	Vector3f U = vec1.vectorTo(vec2);
	Vector3f V = vec1.vectorTo(vec3);
	return U.cross(V);
    }

    // Heh... you can't make a call to glError within a glBegin()/glEnd() block.
    // It creates another error...
    public void render(GLAutoDrawable drawable, GLU glu) {
	GL2 gl = drawable.getGL().getGL2();
	if (this.shaderProgram != null)
	    this.shaderProgram.enable();
	else
	    ShaderProgram.useDefaultShader();
	if (this.material != null) {
	    this.material.enableMaterial(drawable, glu);
	    GLErrorChecker.check("Ater enabling material");
	}
	if (this.verticies.size() == 3)
	    gl.glBegin(GL2.GL_TRIANGLES);
	else if (this.verticies.size() == 4)
	    gl.glBegin(GL2.GL_QUADS);
	else
	    gl.glBegin(GL2.GL_POLYGON);
	for (Vertex v : this.verticies) {
	    if (v.textureCoordinate != null) {
		gl.glTexCoord2f(v.textureCoordinate.x, v.textureCoordinate.y);
		// GLErrorChecker.check("Ater enabling textureCoordinate. "
		// + v.textureCoordinate.x + ", " + v.textureCoordinate.y);
	    } else {
		gl.glColor3f(v.color.x, v.color.y, v.color.z);
		// GLErrorChecker.check("Ater enabling color. (" + v.color.x
		// + ", " + v.color.y + ", " + v.color.z + ")");
	    }
	    if (v.normal != null) {
		gl.glNormal3f(v.normal.x, v.normal.y, v.normal.z);
		// GLErrorChecker.check("After drawing vertex (" + v.normal.x
		// + ", " + v.normal.y + ", " + v.normal.z + ")");
	    }
	    gl.glVertex3f(v.location.x, v.location.y, v.location.z);
	    // GLErrorChecker.check("Ater creating vertex at (" + v.location.x +
	    // ", " + v.location.y + ", " + v.location.z + ")");
	}
	gl.glEnd();
	GLErrorChecker.check("Ater drawing polygon");
    }
}
