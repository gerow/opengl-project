package glproject.scenes;

import java.awt.AWTException;
import java.io.IOException;

import javax.media.opengl.GLAutoDrawable;

import sceneobjects.Orbiter;

import glproject.Mesh;
import glproject.Vector3d;
import glproject.World;

public class SolarSystem extends World {

    public SolarSystem() throws AWTException {
	super();
	
	this.startRender();
	this.startLogic();
	//mainFrame.world.addMesh(m2);
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
	m.scaling = new Vector3d(1, 1, 1);
	Orbiter orbiter1 = new Orbiter(m);
	Mesh m2 = null;
	try {
	    m2 = Mesh.loadMeshFromObjFile("mtdew.obj");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	m2.scaling = new Vector3d(80, 80, 80);
	Orbiter orbiter2 = new Orbiter(m2);
	orbiter1.addAffectedBy(orbiter2);
	orbiter1.setLocation(new Vector3d(100, 100, 100));
	orbiter1.setVelocity(new Vector3d(0, 9, 1));
	//mainFrame.world.addMesh(m);
	this.addSceneObject(orbiter1);
	this.addSceneObject(orbiter2);
	orbiter1.setRotationalVelocity(new Vector3d(3, 1, 0));
	orbiter2.setRotationalVelocity(new Vector3d(0, 1, 0));
    }
}
