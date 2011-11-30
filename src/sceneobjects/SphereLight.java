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
    
    public float a = 40;
    public float b = 40;
    public float c = 40;
    public float dt = 0.2f;
    
    private float t = 0;
    
    public SphereLight(Vector3f location) {
	this.location = location;
	try {
	    this.mesh = Mesh.loadMeshFromObjFile("sphere.obj");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	this.mesh.setColor(new Vector4f(1.0f, 1.0f, 0.0f, 0.1f));
	this.mesh.translation = this.location;
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
    
    public void setLocaiton(Vector3f loc) {
	this.location = loc;
	this.light.location = loc;
	this.mesh.translation = loc;
    }
    
    public void sineMove() {
	float x = (float)Math.sin(t) * a;
	float y = (float)Math.cos(t) * b;
	float z = (float)Math.sin(t) * c;
	
	t += dt;
	
	this.setLocaiton(new Vector3f(x, z, y));
    }

    @Override
    public void step() {
	this.sineMove();
    }

}
