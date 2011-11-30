package solarsystem;

import java.io.IOException;

import glproject.Attractor;
import glproject.Collider;
import glproject.Mesh;
import glproject.Vector3f;
import sceneobjects.FunctionMover;

public abstract class Planet extends FunctionMover implements Attractor,
	Collider {
    public Mesh smaller;
    public Mesh bigger;

    private boolean usingSmaller;
    public final static float PLANET_SCALE = 11.37f;

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
	this.smaller.calculateVertexNormals();
	this.smaller.reverseVertexWinding();
	this.smaller.negateNormals();
	this.bigger = this.mesh;
    }

    public void step() {
	float distToCamera = this.getLocation().distanceTo(
		this.world.getActiveCamera().getLocation());
	if ((distToCamera > 1000) && !this.usingSmaller) {
	    this.world.removeMesh(this.mesh);
	    this.mesh = this.smaller;
	    this.world.addMesh(this.mesh);
	    this.usingSmaller = true;
	} if ((distToCamera <= 1000) && this.usingSmaller) {
	    this.world.removeMesh(this.mesh);
	    this.mesh = this.bigger;
	    this.world.addMesh(this.mesh);
	    this.usingSmaller = false;
	}
	super.step();
    }

    public Vector3f getLocation() {
	return this.location;
    }
}
