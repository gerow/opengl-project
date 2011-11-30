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
    protected float step;
    
    public FunctionMover() {
	
    }
    
    public FunctionMover(RealToVector3fFunction function, Mesh mesh) {
	this.mesh = mesh;
	this.function = function;
    }

    public void step() {
	//System.out.println("Getting time " + System.currentTimeMillis());
	this.mesh.translation = this.function.eval(step);
	step += 0.1;
	this.mesh.rotation.y += rotationalVelocity;
    }

    public void initialize(World world) {
	this.world = world;
	this.world.addMesh(this.mesh);
    }
}
