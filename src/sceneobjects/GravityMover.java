package sceneobjects;

import glproject.Mesh;
import glproject.Vector3f;

import java.util.ArrayList;

/**
 * 
 * @author gerow
 * @deprecated
 */
public class GravityMover extends DynamicSceneObject {
    public static float G = (float) (6 * Math.pow(10, -11));
    private float mass = 0;
    private ArrayList<GravityMover> interactingObjects = new ArrayList<GravityMover>();

    public GravityMover(Mesh mesh, float mass) {
	super(mesh);
	this.mass = mass;
    }

    public void step() {
	this.gravityMove();
    }

    public void applyForce(Vector3f force) {
	Vector3f changeInAcceleration = force.divide(this.mass);
	this.setAcceleration(this.getAcceleration().add(changeInAcceleration));
    }

    public void gravityMove() {
	for (GravityMover g : this.interactingObjects) {
	    float m1, m2, r;

	    m1 = this.getMass();
	    m2 = g.getMass();
	    r = this.getLocation().vectorTo(g.getLocation()).magnitude();

	    float forceMagnitude = (float) (GravityMover.G * (m1 * m2) / Math
		    .pow(r, 2));

	    Vector3f force = this.getAcceleration()
		    .vectorTo(g.getAcceleration()).normalize()
		    .multiply(forceMagnitude);
	    this.applyForce(force);
	}
    }

    public float getMass() {
	return this.mass;
    }

    public void setMass(float mass) {
	this.mass = mass;
    }
}
