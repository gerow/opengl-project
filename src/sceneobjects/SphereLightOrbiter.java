package sceneobjects;

import java.io.IOException;

import glproject.Light;
import glproject.Material;
import glproject.Mesh;
import glproject.Vector3f;
import glproject.Vector4f;
import glproject.World;

public class SphereLightOrbiter extends Orbiter {
    private Light light;

    public SphereLightOrbiter(Vector3f location) {
	SphereLight light = new SphereLight(location);
	try {
	    this.mesh = Mesh.loadMeshFromObjFile("sphere.obj");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	this.setLocation(location);
	this.mesh.setColor(new Vector4f(1.0f, 1.0f, 0.0f, 0.2f));
	Material mat = new Material();
	mat.ambient = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	mat.diffuse = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	mat.specular = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	this.light = new Light(this.getLocation(), mat);
    }

    public void initialize(World world) {
	super.initialize(world);
	world.addLight(light);
    }
    
    public void step() {
	super.step();
	//System.out.println("Sphere light orbiting at " + this.getLocation().x + " " + this.getLocation().y + " " + this.getLocation().z);
	this.light.location = this.getLocation();
    }
}
