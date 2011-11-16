package sceneobjects;

import glproject.Mesh;
import glproject.SceneObject;
import glproject.Vector3d;
import glproject.World;

public abstract class DynamicSceneObject implements SceneObject {
    protected World world;
    protected Mesh mesh;
    
    private Vector3d location = new Vector3d(0, 0, 0);
    private Vector3d velocity = new Vector3d(0, 0, 0);
    private Vector3d acceleration = new Vector3d(0, 0, 0);
    
    private Vector3d rotation = new Vector3d(0, 0, 0);
    private Vector3d rotationalVelocity = new Vector3d(0, 0, 0);
    private Vector3d rotationalAcceleration = new Vector3d(0, 0, 0);
    
    private Vector3d scale = new Vector3d(1, 1, 1);
    private Vector3d scalingVelocity = new Vector3d(0, 0, 0);
    private Vector3d scalingAcceleration = new Vector3d(0, 0, 0);
    
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
    
    public void setLocation(Vector3d location) {
	this.location = location;
	this.mesh.translation = this.location;
    }
    
    public Vector3d getLocation() {
	return this.location;
    }
    
    public void setVelocity(Vector3d velocity) {
	this.velocity = velocity;
    }
    
    public Vector3d getVelocity() {
	return this.velocity;
    }
    
    public void setAcceleration(Vector3d acceleration) {
	this.acceleration = acceleration;
    }
    
    public Vector3d getAcceleration() {
	return this.acceleration;
    }
    
    public void setRotation(Vector3d rotation) {
	this.rotation = rotation;
	this.mesh.rotation = this.rotation;
    }
    
    public Vector3d getRotation() {
	return this.rotation;
    }
    
    public void setRotationalVelocity(Vector3d rotationalVelocity) {
	this.rotationalVelocity = rotationalVelocity;
    }
    
    public Vector3d getRotationalVelocity() {
	return this.rotationalVelocity;
    }
    
    public void setRotationalAcceleration(Vector3d rotationalAcceleration) {
	this.rotationalAcceleration = rotationalAcceleration;
    }
    
    public void setScale(Vector3d scale) {
	this.scale = scale;
	this.mesh.scaling = this.scale;
    }
    
    public Vector3d getScale() {
	return this.scale;
    }
    
    public void setScalingVelocity(Vector3d scalingVelocity) {
	this.scalingVelocity = scalingVelocity;
    }
    
    public Vector3d getScalingVelocity() {
	return this.scalingVelocity;
    }
    
    public void setScalingAcceleration(Vector3d scalingAcceleration) {
	this.scalingAcceleration = scalingAcceleration;
    }
    
    public Vector3d getScalingAcceleration() {
	return this.scalingAcceleration;
    }
}
