package glproject;

import java.util.ArrayList;

import javax.media.opengl.GL2;

public class Mesh {
    ArrayList<Polygon> triangles;
    ArrayList<Polygon> quads;
    ArrayList<Polygon> polygons;
    
    ArrayList<Polygon> allPolys;
    Vector3d translation;
    //This is in DEGREES
    Vector3d rotation;
    Vector3d scaling;
    
    public Mesh(ArrayList<Polygon> polys, Vector3d translation, Vector3d rotation, Vector3d scaling) {
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
    }
    
    public void render(GL2 gl) {
	gl.glTranslatef(translation.x, translation.y, translation.z);
	gl.glRotatef(rotation.z, 0.0f, 0.0f, 1.0f);
	gl.glRotatef(rotation.y, 0.0f, 1.0f, 0.0f);
	gl.glRotatef(rotation.x, 1.0f, 0.0f, 0.0f);
	gl.glScalef(scaling.x, scaling.y, scaling.z);
	//Start triangles
	gl.glBegin(GL2.GL_TRIANGLES);
	for (Polygon p : this.triangles)
	    p.render(gl);
	gl.glEnd();
	gl.glBegin(GL2.GL_QUADS);
	for (Polygon p : this.quads) 
	    p.render(gl);
	gl.glEnd();
	for (Polygon p : this.polygons) {
	    gl.glBegin(GL2.GL_POLYGON);
	    p.render(gl);
	    gl.glEnd();
	}
	//Pop the scaling matrix
	gl.glPopMatrix();
	//Pop the rotx matrix
	gl.glPopMatrix();
	//Pop the roty matrix
	gl.glPopMatrix();
	//Pop the rotz matrix
	gl.glPopMatrix();
	//Pop the translation matrix
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
		if (vert.normal != null && vert.location.equals(v.location)){
		    commonVerticies.add(vert);
		    sum = sum.add(vert.surfaceNormal);
		}
	    }
	}
	Vector3d normal = sum.divide(commonVerticies.size());
	for (Vertex vert : commonVerticies)
	    vert.normal = normal;
    }
}
