package sceneobjects;

import glproject.Mesh;
import glproject.SceneObject;
import glproject.Vector3f;
import glproject.World;

public abstract class DynamicSceneObject implements SceneObject {
    protected World world;
    protected Mesh mesh;
    
    private Vector3f location = new Vector3f(0, 0, 0);
    private Vector3f velocity = new Vector3f(0, 0, 0);
    private Vector3f acceleration = new Vector3f(0, 0, 0);
    
    private Vector3f rotation = new Vector3f(0, 0, 0);
    private Vector3f rotationalVelocity = new Vector3f(0, 0, 0);
    private Vector3f rotationalAcceleration = new Vector3f(0, 0, 0);
    
    private Vector3f scale = new Vector3f(1, 1, 1);
    private Vector3f scalingVelocity = new Vector3f(0, 0, 0);
    private Vector3f scalingAcceleration = new Vector3f(0, 0, 0);
    
    public DynamicSceneObject(Mesh mesh) {
	this.mesh = mesh;
    }
    
    public void setMesh(Mesh mesh) {
	this.mesh = mesh;
	this.mesh.translation = this.location;
    }
    
    public void initialize(World world) {
	this.world = world;
	this.world.addMesh(this.mesh);
    }
    
    public void velocityMove() {
	this.location = this.location.add(this.velocity);
	this.mesh.translation = this.location;
    }
    
    public void accelerationMove() {
	this.velocity = this.velocity.add(this.acceleration);
	this.velocityMove();
    }
    
    public void setLocation(Vector3f location) {
	this.location = location;
	this.mesh.translation = this.location;
    }
    
    public Vector3f getLocation() {
	return this.location;
    }
    
    public void setVelocity(Vector3f velocity) {
	this.velocity = velocity;
    }
    
    public Vector3f getVelocity() {
	return this.velocity;
    }
    
    public void setAcceleration(Vector3f acceleration) {
	this.acceleration = acceleration;
    }
    
    public Vector3f getAcceleration() {
	return this.acceleration;
    }
    
    public void setRotation(Vector3f rotation) {
	this.rotation = rotation;
	this.mesh.rotation = this.rotation;
    }
    
    public Vector3f getRotation() {
	return this.rotation;
    }
    
    public void setRotationalVelocity(Vector3f rotationalVelocity) {
	this.rotationalVelocity = rotationalVelocity;
    }
    
    public Vector3f getRotationalVelocity() {
	return this.rotationalVelocity;
    }
    
    public void setRotationalAcceleration(Vector3f rotationalAcceleration) {
	this.rotationalAcceleration = rotationalAcceleration;
    }
    
    public void setScale(Vector3f scale) {
	this.scale = scale;
	this.mesh.scaling = this.scale;
    }
    
    public Vector3f getScale() {
	return this.scale;
    }
    
    public void setScalingVelocity(Vector3f scalingVelocity) {
	this.scalingVelocity = scalingVelocity;
    }
    
    public Vector3f getScalingVelocity() {
	return this.scalingVelocity;
    }
    
    public void setScalingAcceleration(Vector3f scalingAcceleration) {
	this.scalingAcceleration = scalingAcceleration;
    }
    
    public Vector3f getScalingAcceleration() {
	return this.scalingAcceleration;
    }
    
    public void scalingVelocityMove() {
	this.setScale(this.scale.add(this.scalingVelocity));
    }
    
    public void scalingAccelerationMove() {
	this.scalingVelocity = this.scalingVelocity.add(this.scalingAcceleration);
	this.scalingVelocityMove();
    }
    
    public void rotationalVelocityMove() {
	this.setRotation(this.rotation.add(this.rotationalVelocity));
    }
    
    public void rotationalAccelerationMove() {
	this.rotationalVelocity = this.rotationalVelocity.add(this.rotationalAcceleration);
    }
}
