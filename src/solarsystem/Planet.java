package solarsystem;

import java.io.IOException;

import glproject.Attractor;
import glproject.Collider;
import glproject.Mesh;
import glproject.Vector3f;
import sceneobjects.FunctionMover;

public abstract class Planet extends FunctionMover implements Attractor, Collider {
    public Mesh smaller;
    public final static float PLANET_SCALE = 10.37f;
    public Planet() {
	try {
	    this.mesh = Mesh.loadMeshFromObjFile("solarplanet.obj");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	try {
	    this.smaller = Mesh.loadMeshFromObjFile("solarplanet_small.obj");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	this.mesh.reverseVertexWinding();
	this.mesh.enableOptimization();
	this.smaller.enableOptimization();
	//this.smaller.calculateVertexNormals();
    }
    
    public Vector3f getLocation() {
	return this.getLocation();
    }
}
