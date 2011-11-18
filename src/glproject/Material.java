package glproject;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

public class Material {
    public Vector4d ambient = new Vector4d();
    public Vector4d diffuse = new Vector4d();
    public Vector4d specular = new Vector4d();
    public float shininess = 10.0f;

    public Material() {

    }
    
    public Material(Vector4d ambient, Vector4d diffuse, Vector4d specular, float shininess) {
	this.ambient = ambient;
	this.diffuse = diffuse;
	this.specular = specular;
	this.shininess = shininess;
    }
    
    public void enableMaterial(GLAutoDrawable drawable, GLU glu) {
	GL2 gl = drawable.getGL().getGL2();
	
	gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT, this.ambient.toArray(), 0);
	gl.glMaterialfv(GL.GL_FRONT, GL2.GL_DIFFUSE, this.diffuse.toArray(), 0);
	gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SPECULAR, this.specular.toArray(), 0);
	gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, this.shininess);
    }
}
