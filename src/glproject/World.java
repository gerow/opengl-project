package glproject;

import java.awt.AWTException;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

public class World {
    /**
     * 
     */
    private static final long serialVersionUID = -7996207185050352558L;
    private Camera activeCamera;
    private ArrayList<Camera> cameras = new ArrayList<Camera>();
    private ArrayList<Mesh> meshes = new ArrayList<Mesh>();
    private ArrayList<SceneObject> sceneObjects = new ArrayList<SceneObject>();
    private boolean displayFps = true;

    public World() throws AWTException {
	this.activeCamera = new Camera();
	this.activeCamera.location.z = -250;
    }
    
    public void setActiveCamera(Camera camera) {
	this.activeCamera = camera;
    }
    
    public Camera getActiveCamera() {
	return this.activeCamera;
    }
    
    public void setDisplayFps(boolean displayFps) {
	this.displayFps = displayFps;
    }
    
    public boolean isDisplayFps() {
	return this.displayFps;
    }
    
    public void addMesh(Mesh mesh) {
	this.meshes.add(mesh);
    }
    
    public boolean removeMesh(Mesh mesh) {
	return this.meshes.remove(mesh);
    }
    
    public void addSceneObject(SceneObject sceneObject) {
	this.sceneObjects.add(sceneObject);
    }
    
    public boolean removeSceneObject(SceneObject sceneObject) {
	return this.sceneObjects.remove(sceneObject);
    }
    
    public void step() {
	for (SceneObject o : this.sceneObjects)
	    o.step();
    }

    public void render(GLAutoDrawable drawable, GLU glu) {
	GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        
        //camera.applyMatrix(drawable, glu);
        this.activeCamera.step();
        
	Vector3d reference = this.activeCamera.getReferencePoint();
	glu.gluLookAt(this.activeCamera.location.x, this.activeCamera.location.y, this.activeCamera.location.z,
		reference.x, reference.y, reference.z, 0.0f, 1.0f, 0.0f);
	for (Mesh m : meshes) {
	    m.render(drawable, glu);
	}
	gl.glPopMatrix();
    }
}
