package glproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

public class Mesh {
    ArrayList<Polygon> triangles = new ArrayList<Polygon>();
    ArrayList<Polygon> quads = new ArrayList<Polygon>();
    ArrayList<Polygon> polygons = new ArrayList<Polygon>();

    ArrayList<Polygon> allPolys = new ArrayList<Polygon>();
    Vector3d translation;
    // This is in DEGREES
    Vector3d rotation;
    Vector3d scaling;

    Integer textureIndex = null;

    public Mesh(ArrayList<Polygon> polys, Vector3d translation,
	    Vector3d rotation, Vector3d scaling, Integer textureIndex) {
	this.translation = translation;
	this.rotation = rotation;
	this.scaling = scaling;
	for (Polygon p : polys) {
	    this.allPolys.add(p);
	    if (p.type == Polygon.Type.TRIANGLE)
		this.triangles.add(p);
	    else if (p.type == Polygon.Type.QUAD)
		this.quads.add(p);
	    else
		this.polygons.add(p);
	}
	this.textureIndex = textureIndex;
    }

    public void render(GLAutoDrawable drawable, GLU glu) {
	GL2 gl = drawable.getGL().getGL2();
	if (this.textureIndex != null)
	    gl.glBindTexture(GL2.GL_TEXTURE_2D, this.textureIndex);
	gl.glTranslatef(translation.x, translation.y, translation.z);
	gl.glRotatef(rotation.z, 0.0f, 0.0f, 1.0f);
	gl.glRotatef(rotation.y, 0.0f, 1.0f, 0.0f);
	gl.glRotatef(rotation.x, 1.0f, 0.0f, 0.0f);
	gl.glScalef(scaling.x, scaling.y, scaling.z);
	// Start triangles
	gl.glBegin(GL2.GL_TRIANGLES);
	for (Polygon p : this.triangles)
	    p.render(drawable, glu);
	gl.glEnd();
	gl.glBegin(GL2.GL_QUADS);
	for (Polygon p : this.quads)
	    p.render(drawable, glu);
	gl.glEnd();
	for (Polygon p : this.polygons) {
	    gl.glBegin(GL2.GL_POLYGON);
	    p.render(drawable, glu);
	    gl.glEnd();
	}
	// Pop the scaling matrix
	gl.glPopMatrix();
	// Pop the rotx matrix
	gl.glPopMatrix();
	// Pop the roty matrix
	gl.glPopMatrix();
	// Pop the rotz matrix
	gl.glPopMatrix();
	// Pop the translation matrix
	gl.glPopMatrix();
    }

    public void calculateVertexNormals() {
	for (Polygon p : this.allPolys) {
	    for (Vertex v : p.verticies) {
		if (v.normal == null)
		    this.calculateNormalsForVertex(v);
	    }
	}
    }

    private void calculateNormalsForVertex(Vertex v) {
	ArrayList<Vertex> commonVerticies = new ArrayList<Vertex>();
	Vector3d sum = new Vector3d();
	commonVerticies.add(v);
	sum = sum.add(v.surfaceNormal);
	for (Polygon p : this.allPolys) {
	    for (Vertex vert : p.verticies) {
		if (vert.normal != null && vert.location.equals(v.location)) {
		    commonVerticies.add(vert);
		    sum = sum.add(vert.surfaceNormal);
		}
	    }
	}
	Vector3d normal = sum.divide(commonVerticies.size());
	for (Vertex vert : commonVerticies)
	    vert.normal = normal;
    }

    public static Mesh loadMeshFromObjFile(String filename) throws IOException {
	ArrayList<Vertex> verticies = new ArrayList<Vertex>();
	ArrayList<Polygon> polygons = new ArrayList<Polygon>();
	BufferedReader reader = null;
	reader = new BufferedReader(
		new FileReader("assets/objects/" + filename));
	String line;
	String splitLines[];
	int textureCount = 0;
	while ((line = reader.readLine()) != null) {
	    splitLines = line.split("\\s+");
	    if (splitLines[0].equals("v")) {
		Vertex v = new Vertex();
		v.location.x = Float.valueOf(splitLines[1]);
		v.location.y = Float.valueOf(splitLines[2]);
		v.location.z = Float.valueOf(splitLines[3]);
		v.color = new Vector3d(1, 0, 0);
		verticies.add(v);
		//System.out.println("Adding new vertex " + line);
	    } else if (splitLines[0].equals("vt")) {
		float u = Float.valueOf(splitLines[1]);
		float v = Float.valueOf(splitLines[2]);
		verticies.get(textureCount).textureCoordinate = new Vector2d(u,
			v);
		textureCount++;
	    } else if (splitLines[0].equals("f")) {
		ArrayList<Vertex> polygonVerticies = new ArrayList<Vertex>();
		for (int i = 1; i < splitLines.length; ++i) {
		    polygonVerticies.add(verticies.get(Integer
			    .valueOf(splitLines[i]) - 1));
		}
		Polygon p = new Polygon(polygonVerticies);
		polygons.add(p);
	    }
	}

	return new Mesh(polygons, new Vector3d(0, 0, 0), new Vector3d(0, 0, 0),
		new Vector3d(1, 1, 1), null);
    }
}
