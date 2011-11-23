package glproject;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

public class Light {
    public Vector3f location = new Vector3f(-50, 0, 0);
    public Material material = new Material();
    public boolean enabled = true;
    public int lightNumber;
    
    public Light() {
	
    }
    
    public Light(Vector3f location, Material material) {
	this.location = location;
	this.material = material;
    }
    
    public void render(GLAutoDrawable drawable, GLU glu) {
	GL2 gl = drawable.getGL().getGL2();
	
	if (this.enabled) {
	    gl.glEnable(lightNumber);
	    gl.glLightfv(lightNumber, GL2.GL_POSITION, this.location.toLightLocation(), 0);
	}
	else {
	    gl.glDisable(lightNumber);
	}
    }
}
