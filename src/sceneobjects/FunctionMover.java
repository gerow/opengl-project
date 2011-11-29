package sceneobjects;

import glproject.Mesh;
import glproject.RealToVector3fFunction;
import glproject.SceneObject;
import glproject.World;

public class FunctionMover implements SceneObject {
    protected Mesh mesh;
    protected RealToVector3fFunction function;
    protected World world;
    protected float rotationalVelocity = 0.0f;
    
    public FunctionMover() {
	
    }
    
    public FunctionMover(RealToVector3fFunction function, Mesh mesh) {
	this.mesh = mesh;
	this.function = function;
    }

    public void step() {
	this.mesh.translation = this.function.eval(System.currentTimeMillis() / 1000.0f);
	this.mesh.rotation.y += rotationalVelocity;
    }

    public void initialize(World world) {
	this.world = world;
	this.world.addMesh(this.mesh);
    }
}
