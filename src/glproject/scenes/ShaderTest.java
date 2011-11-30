package glproject.scenes;

import glproject.MainFrame;
import glproject.Mesh;
import glproject.ShaderProgram;
import glproject.SkySphere;
import glproject.Vector3f;
import glproject.World;

import java.awt.AWTException;
import java.io.IOException;

import javax.media.opengl.GLAutoDrawable;

import sceneobjects.Orbiter;
import sceneobjects.SphereLight;
import sceneobjects.SphereLightOrbiter;

public class ShaderTest extends World {

    /**
     * 
     */
    private static final long serialVersionUID = -8501533942596676773L;

    public ShaderTest() throws AWTException {
	super();

	this.startRender();
	this.startLogic();
	// mainFrame.world.addMesh(m2);
    }

    public void init(GLAutoDrawable drawable) {
	super.init(drawable);

	Mesh m = null;
	try {
	    m = Mesh.loadMeshFromObjFile("saturnUV.obj");
	} catch (IOException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	// m.scaling = new Vector3f(20.0f, 20.0f, 20.0f);
	// m.scaling = new Vector3f(40.0f, 40.0f, 40.0f);
	Orbiter orbiter1 = new Orbiter(m);
	// Mesh m2 = null;
	// try {
	// m2 = Mesh.loadMeshFromObjFile("mtdew.obj");
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// m2.scaling = new Vector3f(80, 80, 80);
	// Orbiter orbiter2 = new Orbiter(m2);
	// orbiter1.addAffectedBy(orbiter2);
	// orbiter1.setLocation(new Vector3f(100, 100, 100));
	// orbiter1.setVelocity(new Vector3d(0, 9, 1));
	// mainFrame.world.addMesh(m);
	this.addSceneObject(orbiter1);
	// this.addSceneObject(orbiter2);
	orbiter1.setRotationalVelocity(new Vector3f(0, 1, 0));
	SphereLightOrbiter sphereLight = new SphereLightOrbiter(new Vector3f(
		0.0f, 100.0f, -100000.0f));
	sphereLight.addAffectedBy(orbiter1);
	sphereLight.setVelocity(new Vector3f(5.0f, 5.0f, 0.0f));

	SphereLightOrbiter sphereLightTwo = new SphereLightOrbiter(
		new Vector3f(100.0f, 400.0f, 0.0f));
	sphereLightTwo.addAffectedBy(orbiter1);
	sphereLightTwo.setVelocity(new Vector3f(5.0f, 3.0f, 1.0f));

	SphereLight sLight = new SphereLight(new Vector3f(0.0f, 0.0f, -500.0f));
	this.addSceneObject(sLight);
	this.addSceneObject(sphereLightTwo);
	this.addSceneObject(sphereLight);
	// orbiter2.setRotationalVelocity(new Vector3d(0, 1, 0));
	// Light l = new Light();

	// this.addLight(l);
	SkySphere ssphere = new SkySphere(10.0f, "space2.jpg");
	this.addRenderable(ssphere);
	// m.setShaderProgram(redShader);
	ShaderProgram.defaultShader = ShaderProgram
		.getFromShaderLibrary("phong");
    }

    public static void main(String args[]) {
	MainFrame mainFrame = null;
	try {
	    mainFrame = new MainFrame(new ShaderTest());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (AWTException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	mainFrame.setVisible(true);
    }
}
