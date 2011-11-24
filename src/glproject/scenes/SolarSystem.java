package glproject.scenes;

import glproject.Light;
import glproject.Material;
import glproject.Mesh;
import glproject.ShaderProgram;
import glproject.Vector3f;
import glproject.Vector4f;
import glproject.World;

import java.awt.AWTException;
import java.io.IOException;

import javax.media.opengl.GLAutoDrawable;

import sceneobjects.Orbiter;
import sceneobjects.SphereLight;

public class SolarSystem extends World {

    public SolarSystem() throws AWTException {
	super();

	this.startRender();
	this.startLogic();
	// mainFrame.world.addMesh(m2);
    }

    public void init(GLAutoDrawable drawable) {
	super.init(drawable);

	Mesh m = null;
	try {
	    m = Mesh.loadMeshFromObjFile("mtdew.obj");
	} catch (IOException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	m.scaling = new Vector3f(20.0f, 20.0f, 20.0f);
	//m.scaling = new Vector3f(40.0f, 40.0f, 40.0f);
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
	this.addSceneObject(new SphereLight(new Vector3f(0.0f, 0.0f, -500.0f)));
	// orbiter2.setRotationalVelocity(new Vector3d(0, 1, 0));
	// Light l = new Light();

	// this.addLight(l);
	
	try {
	    ShaderProgram p = ShaderProgram.loadFromFile("example.vert", "example.frag");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
