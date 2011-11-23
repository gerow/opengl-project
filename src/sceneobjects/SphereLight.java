package sceneobjects;

import glproject.Light;
import glproject.Mesh;
import glproject.SceneObject;
import glproject.Vector3f;
import glproject.World;

import java.io.IOException;

public class SphereLight implements SceneObject {
    private Vector3f location = new Vector3f();
    private Mesh mesh;
    private World world;
    private Light light;
    
    public SphereLight(Vector3f location) {
	this.location = location;
	try {
	    this.mesh = Mesh.loadMeshFromObjFile("sphere.obj");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	this.light = new Light();
	this.light.location = this.location;
    }
    public void initialize(World world) {
	this.world = world;
	world.addMesh(this.mesh);
	world.addLight(this.light);
    }

    @Override
    public void step() {
	
    }

}
