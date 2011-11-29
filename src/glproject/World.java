package glproject;

import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.media.opengl.DebugGL2;
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

import sceneobjects.ParticleEmitter;

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
    private ArrayList<Renderable> renderables = new ArrayList<Renderable>();
    private ArrayList<Light> lights = new ArrayList<Light>();
    private ArrayList<SceneObject> sceneObjects = new ArrayList<SceneObject>();
    private ArrayList<ParticleEmitter> particleEmitters = new ArrayList<ParticleEmitter>();
    private boolean displayFps = true;
    private GLU glu = new GLU();
    private Timer t = new Timer(1000 / World.TICKRATE, this);
    private Animator animator = new Animator(this);
    private Uniform numLightsUniform;
    private int numLights = 0;

    private boolean[] activeLights = { false, false, false, false, false,
	    false, false, false };

    public World() throws AWTException {
	this.addGLEventListener(this);
	this.animator.setRunAsFastAsPossible(true);
	this.setActiveCamera(new Camera());
	this.activeCamera.location.z = -200;
	new Controller(this);
    }

    public void setActiveCamera(Camera camera) {
	// this.removeKeyListener(this.activeCamera);
	// this.removeMouseListener(this.activeCamera);
	// this.removeMouseMotionListener(this.activeCamera);
	this.activeCamera = camera;
	// this.addKeyListener(this.activeCamera);
	// this.addMouseMotionListener(this.activeCamera);
	// this.addMouseListener(this.activeCamera);
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

    private void returnUnusedLight(int light) {
	switch (light) {
	case GL2.GL_LIGHT0:
	    this.activeLights[0] = false;
	    break;
	case GL2.GL_LIGHT1:
	    this.activeLights[1] = false;
	    break;
	case GL2.GL_LIGHT2:
	    this.activeLights[2] = false;
	    break;
	case GL2.GL_LIGHT3:
	    this.activeLights[3] = false;
	    break;
	case GL2.GL_LIGHT4:
	    this.activeLights[4] = false;
	    break;
	case GL2.GL_LIGHT5:
	    this.activeLights[5] = false;
	    break;
	case GL2.GL_LIGHT6:
	    this.activeLights[6] = false;
	    break;
	case GL2.GL_LIGHT7:
	    this.activeLights[7] = false;
	    break;
	}
    }

    private int findAndTakeNextAvailableLight() {
	int i;
	boolean found = false;
	for (i = 0; i < 8; ++i) {
	    if (!this.activeLights[i]) {
		found = true;
		break;
	    }
	}

	if (!found)
	    throw new RuntimeException("Tried to create too many lights");

	this.activeLights[i] = true;
	switch (i) {
	case 0:
	    return GL2.GL_LIGHT0;
	case 1:
	    return GL2.GL_LIGHT1;
	case 2:
	    return GL2.GL_LIGHT2;
	case 3:
	    return GL2.GL_LIGHT3;
	case 4:
	    return GL2.GL_LIGHT4;
	case 5:
	    return GL2.GL_LIGHT5;
	case 6:
	    return GL2.GL_LIGHT6;
	default:
	    return GL2.GL_LIGHT7;
	}
    }

    public void addLight(Light light) {
	light.lightNumber = this.findAndTakeNextAvailableLight();
	++this.numLights;
	this.numLightsUniform.set(this.numLights);
	this.lights.add(light);
    }

    public boolean removeLight(Light light) {
	this.returnUnusedLight(light.lightNumber);
	--this.numLights;
	this.numLightsUniform.set(this.numLights);
	return this.lights.remove(light);
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

    public void addParticleEmitter(ParticleEmitter particleEmitter) {
	this.particleEmitters.add(particleEmitter);
	this.sceneObjects.add(particleEmitter);
    }

    public boolean removeParticleEmitter(ParticleEmitter particleEmitter) {
	return (this.sceneObjects.remove(particleEmitter) && this.particleEmitters.remove(particleEmitter));
    }

    public void step() {
	for (SceneObject o : this.sceneObjects)
	    o.step();
	this.activeCamera.step();
    }

    public void render(GLAutoDrawable drawable, GLU glu) {
	GL2 gl = drawable.getGL().getGL2();

	// TEST CODE
	// Random r = new Random();
	// float[] lightPosition = { this.activeCamera.location.x,
	// this.activeCamera.location.y, this.activeCamera.location.z, 1 };
	// gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPosition, 0);
	// gl.glEnable(GL2.GL_LIGHT0);
	// GLUquadric glq = glu.gluNewQuadric();
	// glu.gluQuadricDrawStyle(glq, GLU.GLU_FILL); /* smooth shaded */
	// glu.gluQuadricNormals(glq, GLU.GLU_SMOOTH);
	// glu.gluSphere(glq, 12, 20, 20);
	// TEST CODE

	// gl.glEnable(GL2.GL_LIGHTING);

	gl.glClear(GL.GL_COLOR_BUFFER_BIT);
	gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
	gl.glEnable(GL2.GL_NORMALIZE);
	gl.glEnable(GL2.GL_BLEND);
	// gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
	// gl.glBlendFunc(GL2.GL_ONE_MINUS_DST_ALPHA,GL2.GL_DST_ALPHA);
	// gl.glBlendFunc(GL2.GL_ONE, GL2.GL_ONE);
	gl.glLoadIdentity();

	GLErrorChecker.check("After loading identity");

	// camera.applyMatrix(drawable, glu);
	// this.activeCamera.step();

	Vector3f reference = this.activeCamera.getReferencePoint();
	glu.gluLookAt(this.activeCamera.location.x,
		this.activeCamera.location.y, this.activeCamera.location.z,
		reference.x, reference.y, reference.z, 0.0f, 1.0f, 0.0f);

	GLErrorChecker.check("After applying camera matrix");

	for (Light l : this.lights) {
	    l.render(drawable, glu);
	}
	GLErrorChecker.check("After doing lights");

	for (Mesh m : meshes) {
	    m.render(drawable, glu);
	}

	for (ParticleEmitter pe : this.particleEmitters) {
	    pe.render(drawable, glu);
	}
	

	for (Renderable r : renderables) {
	    r.render(drawable, glu);
	}
	GLErrorChecker.check("At end of render");
	// gl.glPopMatrix();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	this.step();

    }

    @Override
    public void init(GLAutoDrawable drawable) {
	DebugGL2 gl = new DebugGL2(drawable.getGL().getGL2());
	// GL2 gl = drawable.getGL().getGL2();
	GLErrorChecker.gl = gl;
	GLErrorChecker.glu = glu;
	TextureLoader.gl = gl;
	TextureLoader.glu = glu;
	ShaderProgram.gl = gl;
	ShaderProgram.glu = glu;
	try {
	    ShaderProgram.buildShaderLibrary();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	this.numLightsUniform = ShaderProgram.getFromShaderLibrary("phong").getUniform("num_lights");
	this.numLightsUniform.set(3);
	gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
	gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
	gl.glClearDepth(1.0f);
	gl.glEnable(GL2.GL_DEPTH_TEST);
	gl.glDepthFunc(GL2.GL_LEQUAL);
	gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
	gl.glEnable(GL.GL_TEXTURE_2D);

	gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE,
		GL2.GL_MODULATE);
	gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER,
		GL2.GL_LINEAR);

	gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S,
		GL2.GL_REPEAT);
	gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T,
		GL2.GL_REPEAT);
	gl.glEnable(GL2.GL_BLEND);
	gl.glEnable(GL2.GL_LIGHTING);
	// gl.glEnable(GL2.GL_LIGHT0);
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
	glu.gluPerspective(50.0f, h, 1.0, 10000.0);
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

    public void addRenderable(Renderable r) {
	r.init(this);
	this.renderables.add(r);
    }

    public boolean removeRenderable(Renderable r) {
	return this.renderables.remove(r);
    }

    public void setMouseVisible(boolean visible) {
	if (visible)
	    this.setCursor(Cursor.getDefaultCursor());
	else {
	    this.setCursor(this.getToolkit().createCustomCursor(
		    new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
		    new Point(0, 0), "null"));
	}
    }
}
