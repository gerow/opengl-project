package glproject;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

public class Material {
    public enum Face {
	FRONT, BACK, BOTH
    };

    public Vector4d ambient = new Vector4d();
    public Vector4d diffuse = new Vector4d();
    public Vector4d specular = new Vector4d();
    public float shininess = 0.0f;
    public Face face = Face.FRONT;

    public Material() {

    }

    public Material(Vector4d ambient, Vector4d diffuse, Vector4d specular,
	    float shininess) {
	this.ambient = ambient;
	this.diffuse = diffuse;
	this.specular = specular;
	this.shininess = shininess;
    }
    
    public Material(Vector4d ambient, Vector4d diffuse, Vector4d specular, float shininess, Face face) {
	this(ambient, diffuse, specular, shininess);
	
	this.face = face;
    }

    public void enableMaterial(GLAutoDrawable drawable, GLU glu) {
	GL2 gl = drawable.getGL().getGL2();
	
	int faceInt = 0;
	
	switch (face) {
	case FRONT:
	    faceInt = GL.GL_FRONT;
	    break;
	case BACK:
	    faceInt = GL.GL_BACK;
	    break;
	case BOTH:
	    faceInt = GL.GL_FRONT_AND_BACK;
	    break;
	}

	gl.glMaterialfv(faceInt, GL2.GL_AMBIENT, this.ambient.toArray(), 0);
	gl.glMaterialfv(faceInt, GL2.GL_DIFFUSE, this.diffuse.toArray(), 0);
	gl.glMaterialfv(faceInt, GL2.GL_SPECULAR, this.specular.toArray(),
		0);
	gl.glMaterialf(faceInt, GL2.GL_SHININESS, this.shininess);
    }
}
