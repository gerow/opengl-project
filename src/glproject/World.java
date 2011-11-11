package glproject;

import java.awt.AWTException;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

public class World {
    Camera camera;
    ArrayList<Mesh> meshes = new ArrayList<Mesh>();

    public World() throws AWTException {
	camera = new Camera();
	camera.location.z = -250;
    }

    public void render(GLAutoDrawable drawable, GLU glu) {
	camera.step();
	
	GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        
        //camera.applyMatrix(drawable, glu);
        
	Vector3d reference = this.camera.getReferencePoint();
	glu.gluLookAt(camera.location.x, camera.location.y, camera.location.z,
		reference.x, reference.y, reference.z, 0.0f, 1.0f, 0.0f);
	for (Mesh m : meshes) {
	    m.step();
	    m.render(drawable, glu);
	}
	gl.glPopMatrix();
    }
}
