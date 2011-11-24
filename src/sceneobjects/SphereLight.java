package sceneobjects;

import glproject.Light;
import glproject.Material;
import glproject.Mesh;
import glproject.SceneObject;
import glproject.Vector3f;
import glproject.Vector4f;
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
	Material mat = new Material();
	mat.ambient = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	mat.diffuse = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	mat.specular = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	this.light = new Light(this.location, mat);
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
