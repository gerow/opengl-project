package sceneobjects;

import java.util.ArrayList;

import glproject.Mesh;
import glproject.Vector3f;

public class Orbiter extends DynamicSceneObject {

    private float attraction = 0.1f;
    private ArrayList<Orbiter> affectedByList = new ArrayList<Orbiter>();

    public Orbiter(Mesh mesh) {
	super(mesh);

    }

    public void orbitMove() {
	for (Orbiter o : this.affectedByList)
	    this.accountForAttraction(o);
	this.accelerationMove();
    }

    private void accountForAttraction(Orbiter other) {
	this.setAcceleration(new Vector3f(0, 0, 0));
	Vector3f accelerationVector = this.getLocation()
		.vectorTo(other.getLocation()).normalize()
		.multiply(other.getAttraction());
	this.setAcceleration(this.getAcceleration().add(accelerationVector));
    }

    public float getAttraction() {
	return this.attraction;
    }

    public void setAttraction(float attraction) {
	this.attraction = attraction;
    }

    public void addAffectedBy(Orbiter other) {
	this.affectedByList.add(other);
    }

    public boolean removeAffectedBy(Orbiter other) {
	return this.affectedByList.remove(other);
    }

    public void step() {
	this.orbitMove();
	this.rotationalVelocityMove();
    }

}
