package solarsystem;

import glproject.Light;
import glproject.Material;
import glproject.Mesh;
import glproject.TextureLoader;
import glproject.Vector3f;
import glproject.Vector4f;
import glproject.World;

import java.io.IOException;
import java.util.ArrayList;

import javax.media.opengl.GLException;

import sceneobjects.DynamicSceneObject;
import sceneobjects.ParticleEmitter;

public class Comet extends DynamicSceneObject {
    public static final float ATTRACTION_CONSTANT = 1000.0f;
    public static final float INITIAL_VELOCITY = 5.0f;
    public static final float SCALE_FACTOR = Sun.SCALE_FACTOR * 0.01f;
    public static boolean firstLight = true;

    public ArrayList<Planet> affectingPlanets = new ArrayList<Planet>();
    private World world;
    private Light light = null;
    private ParticleEmitter pe;
    
    public Comet(Vector3f location, Vector3f velocity, World world) {
	this.world = world;
	try {
	    this.mesh = Mesh.loadMeshFromObjFile("solarplanet.obj");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	this.mesh.reverseVertexWinding();
	this.mesh.enableOptimization();

	this.setLocation(location);
	this.setVelocity(velocity);

	Material mat = new Material();
	mat.ambient = new Vector4f(0.0f);
	mat.diffuse = new Vector4f(0.75f, 0.75f, 0.0f, 1.0f);
	mat.specular = new Vector4f(0.0f);
	try {
	    mat.texture = TextureLoader.loadTexture("sun.jpg");
	} catch (GLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	this.mesh.setMaterial(mat);
	this.mesh.scaling = new Vector3f(Comet.SCALE_FACTOR);

	mat = new Material();
	mat.ambient = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	mat.diffuse = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	mat.specular = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	this.light = new Light(this.getLocation(), mat);

	if (Comet.firstLight) {
	    this.world.addLight(this.light);
	    Comet.firstLight = false;
	}
	this.world.addMesh(this.mesh);
    }

    public void step() {
	for (Planet p : affectingPlanets) {
	    this.accountForAttraction(p);
	}

	//System.out.println("Comet acceleration: " + this.getAcceleration());
	this.accelerationMove();
	if (this.light != null)
	    this.light.location = this.getLocation();
    }

    public void accountForAttraction(Planet planet) {
	float attractionValue = planet.getAttractionValue();
	float distanceTo = planet.getLocation().distanceTo(this.getLocation());

	float acc = ATTRACTION_CONSTANT
		* (attractionValue / (float) Math.pow(distanceTo, 2));

	Vector3f accelerationDelta = this.getLocation()
		.vectorTo(planet.getLocation()).normalize().multiply(acc);

	this.setAcceleration(this.getAcceleration().add(accelerationDelta));
    }

    public void explode() {

    	world.removeParticleEmitter(pe);
    	world.addParticleEmitter(new ParticleEmitter(world,this,true));
    }
    
    public void setParticleEmitter(ParticleEmitter pe)
    {
    	this.pe=pe;
    }
}
