package glproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

public class Mesh implements Renderable {
    public ArrayList<Polygon> triangles = new ArrayList<Polygon>();
    public ArrayList<Polygon> quads = new ArrayList<Polygon>();
    public ArrayList<Polygon> polygons = new ArrayList<Polygon>();

    public ArrayList<Polygon> allPolys = new ArrayList<Polygon>();
    public Vector3f translation;
    // This is in DEGREES
    public Vector3f rotation;
    public Vector3f scaling;
    public static final boolean USE_DISPLAY_LISTS = false;

    private Integer displayListId = null;

    public Mesh(ArrayList<Polygon> polys, Vector3f translation,
	    Vector3f rotation, Vector3f scaling) {
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

    public void render(GLAutoDrawable drawable, GLU glu) {
	GL2 gl = drawable.getGL().getGL2();
	gl.glPushMatrix();
	gl.glTranslatef(translation.x, translation.y, translation.z);
	gl.glRotatef(rotation.z, 0.0f, 0.0f, 1.0f);
	gl.glRotatef(rotation.y, 0.0f, 1.0f, 0.0f);
	gl.glRotatef(rotation.x, 1.0f, 0.0f, 0.0f);
	gl.glScalef(scaling.x, scaling.y, scaling.z);
	GLErrorChecker.check("After rotation, scale, translate and pushMatrix");
	// Start verticies
	// if (this.displayListId == null) {
	// this.displayListId = gl.glGenLists(1);
	// gl.glNewList(this.displayListId, GL2.GL_COMPILE);
	/*
	 * gl.glBegin(GL2.GL_TRIANGLES);
	 * GLErrorChecker.check("After glBegin() for triangles"); for (Polygon p
	 * : this.triangles) p.render(drawable, glu);
	 * GLErrorChecker.check("Before glEnd() for triangles"); gl.glEnd();
	 * GLErrorChecker.check("After glEnd() for triangles");
	 * gl.glBegin(GL2.GL_QUADS); for (Polygon p : this.quads)
	 * p.render(drawable, glu); gl.glEnd();
	 * GLErrorChecker.check("Ater QUADS"); for (Polygon p : this.polygons) {
	 * gl.glBegin(GL2.GL_POLYGON); p.render(drawable, glu); gl.glEnd(); }
	 * GLErrorChecker.check("After POLYGONS");
	 */
	if (this.displayListId == null) {
	    this.displayListId = gl.glGenLists(1);
	    gl.glNewList(this.displayListId, GL2.GL_COMPILE);
	    for (Polygon p : this.allPolys) {
		p.render(drawable, glu);
	    }
	    gl.glEndList();
	    // gl.glEndList();
	    // }
	    // gl.glCallList(this.displayListId);
	}
	gl.glCallList(this.displayListId);
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
	System.out.println("Calculating normals");
	ArrayList<Vertex> commonVerticies = new ArrayList<Vertex>();
	Vector3f sum = new Vector3f();
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
	Vector3f normal = sum.divide(commonVerticies.size());
	for (Vertex vert : commonVerticies)
	    vert.normal = normal.multiply(-1);
    }

    public static Mesh loadMeshFromObjFile(String filename) throws IOException {
	ArrayList<Vertex> verticies = new ArrayList<Vertex>();
	ArrayList<Vector2f> textureCoordinates = new ArrayList<Vector2f>();
	ArrayList<Vector3f> vertexNormals = new ArrayList<Vector3f>();
	ArrayList<Polygon> polygons = new ArrayList<Polygon>();
	BufferedReader reader = null;
	reader = new BufferedReader(
		new FileReader("assets/objects/" + filename));
	String line;
	String splitLines[];
	Material currentMaterial = null;
	String mtlFile = null;
	while ((line = reader.readLine()) != null) {
	    splitLines = line.split("\\s+");
	    if (splitLines[0].equals("v")) {
		Vertex v = new Vertex();
		v.location.x = Float.valueOf(splitLines[1]);
		v.location.y = Float.valueOf(splitLines[2]);
		v.location.z = Float.valueOf(splitLines[3]);
		verticies.add(v);
		//System.out.println("Adding new vertex " + line);
	    } else if (splitLines[0].equals("vt")) {
		float u = Float.valueOf(splitLines[1]);
		float v = Float.valueOf(splitLines[2]);
		textureCoordinates.add(new Vector2f(u, v));
	    } else if (splitLines[0].equals("vn")) {
		float x = Float.valueOf(splitLines[1]);
		float y = Float.valueOf(splitLines[2]);
		float z = Float.valueOf(splitLines[3]);
		vertexNormals.add(new Vector3f(x, y, z));
	    } else if (splitLines[0].equals("f")) {
		//System.out.println("Adding new face " + line);
		ArrayList<Vertex> polygonVerticies = new ArrayList<Vertex>();
		for (int i = 1; i < splitLines.length; ++i) {
		    String[] slashSplitLines = splitLines[i].split("/");
		    Vertex tempVertex = null;
		    tempVertex = new Vertex(verticies.get(Integer
			    .valueOf(slashSplitLines[0]) - 1));
		    if (slashSplitLines.length >= 2) {
			tempVertex.textureCoordinate = new Vector2f(
				textureCoordinates.get(Integer
					.valueOf(slashSplitLines[1]) - 1));
		    }
		    if (slashSplitLines.length >= 3) {
			tempVertex.normal = new Vector3f(
				vertexNormals.get(Integer
					.valueOf(slashSplitLines[1]) - 1));
		    }
		    polygonVerticies.add(tempVertex);
		}
		Polygon p = new Polygon(polygonVerticies);
		p.material = currentMaterial;
		polygons.add(p);
	    } else if (splitLines[0].equals("mtllib")) {
		mtlFile = splitLines[1];
	    } else if (splitLines[0].equals("usemtl")) {
		currentMaterial = Material.loadFromMtlFile(mtlFile,
			splitLines[1]);
	    }
	}

	Mesh out = new Mesh(polygons, new Vector3f(0, 0, 0), new Vector3f(0, 0,
		0), new Vector3f(1, 1, 1));
	// out.calculateVertexNormals();
	return out;
    }
    
    public void setShaderProgram(ShaderProgram shaderProgram) {
	for (Polygon p : this.allPolys) {
	    p.shaderProgram = shaderProgram;
	}
    }
    
    public void useFixedShader() {
	for (Polygon p : this.allPolys) {
	    p.forceFixedShader = true;
	}
    }
    
    public void setColor(Vector3f color) {
	Vector4f fcolor = new Vector4f(color.x, color.y, color.z, 1.0f);
	for (Polygon p : this.allPolys)
	    for (Vertex v : p.verticies)
		v.color = fcolor;
    }
    
    public void setColor(Vector4f color) {
	for (Polygon p : this.allPolys)
	    for (Vertex v : p.verticies)
		v.color = color;
    }
    
    public void setMaterial(Material mat) {
	for (Polygon p : this.allPolys)
	    p.material = mat;
    }

    @Override
    public void init(World world) {
	// TODO Auto-generated method stub
	
    }
}
