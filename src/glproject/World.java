package glproject;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;
import javax.swing.Timer;

import com.jogamp.opengl.util.Animator;

public class World extends GLCanvas implements GLEventListener, ActionListener {
    /**
     * 
     */
    private static final long serialVersionUID = -7996207185050352558L;
    public static final int TICKRATE = 66;
    
    private Camera activeCamera;
    private ArrayList<Camera> cameras = new ArrayList<Camera>();
    private ArrayList<Mesh> meshes = new ArrayList<Mesh>();
    private ArrayList<SceneObject> sceneObjects = new ArrayList<SceneObject>();
    private boolean displayFps = true;
    private GLU glu = new GLU();
    private Timer t = new Timer(1000 / World.TICKRATE, this);
    private Animator animator = new Animator(this);

    public World() throws AWTException {
	this.addGLEventListener(this);
	this.animator.setRunAsFastAsPossible(true);
	this.setActiveCamera(new Camera());
	this.activeCamera.location.z = -200;
	new Controller(this);
    }
    
    public void setActiveCamera(Camera camera) {
	//this.removeKeyListener(this.activeCamera);
	//this.removeMouseListener(this.activeCamera);
	//this.removeMouseMotionListener(this.activeCamera);
	this.activeCamera = camera;
	//this.addKeyListener(this.activeCamera);
	//this.addMouseMotionListener(this.activeCamera);
	//this.addMouseListener(this.activeCamera);
	this.requestFocus();
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
	sceneObject.initialize(this);
	this.sceneObjects.add(sceneObject);
    }
    
    public boolean removeSceneObject(SceneObject sceneObject) {
	return this.sceneObjects.remove(sceneObject);
    }
    
    public void step() {
	for (SceneObject o : this.sceneObjects)
	    o.step();
	this.activeCamera.step();
    }

    public void render(GLAutoDrawable drawable, GLU glu) {
	GL2 gl = drawable.getGL().getGL2();
	
	//TEST CODE
        //float[] lightPosition = {-75, 0, 0, 1};
        //gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPosition, 0);
        //TEST CODE
	
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        
        //camera.applyMatrix(drawable, glu);
        //this.activeCamera.step();
        
	Vector3d reference = this.activeCamera.getReferencePoint();
	glu.gluLookAt(this.activeCamera.location.x, this.activeCamera.location.y, this.activeCamera.location.z,
		reference.x, reference.y, reference.z, 0.0f, 1.0f, 0.0f);
	for (Mesh m : meshes) {
	    m.render(drawable, glu);
	}
	gl.glPopMatrix();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	this.step();
	
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        //gl.glEnable(GL2.GL_LIGHTING);
        //gl.glEnable(GL2.GL_LIGHT0);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void display(GLAutoDrawable drawable) {
	this.render(drawable, glu);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
	    int height) {
        GL2 gl = drawable.getGL().getGL2();
        if (height <= 0) {
            height = 1;
        }
        float h = (float) width / (float) height;
        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(50.0f, h, 1.0, 100000.0);
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    
    public void startLogic() {
	this.t.start();
    }
    
    public void stopLogic() {
	this.t.stop();
    }
    
    public void startRender() {
	this.animator.start();
    }
    
    public void stopRender() {
	this.animator.stop();
    }
}
