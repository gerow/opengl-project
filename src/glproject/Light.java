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
	
	System.out.println("Rendering light at " + this.location.x + " " + this.location.y + " " + this.location.z);
	
	if (this.enabled) {
	    //gl.glPushMatrix();
	    //gl.glTranslatef(location.x, location.y, location.z);
	    gl.glEnable(lightNumber);
	    //float[] loc = {0.0f, 0.0f, 0.0f, 0.0f};
	    gl.glLightfv(lightNumber, GL2.GL_POSITION, this.location.toLightComponent(), 0);
	    gl.glLightfv(lightNumber, GL2.GL_AMBIENT, material.ambient.toArray(), 0);
	    gl.glLightfv(lightNumber, GL2.GL_DIFFUSE, material.diffuse.toArray(), 0);
	    gl.glLightfv(lightNumber, GL2.GL_SPECULAR, material.specular.toArray(), 0);
	    //gl.glPopMatrix();
	}
	else {
	    gl.glDisable(lightNumber);
	}
    }
}
