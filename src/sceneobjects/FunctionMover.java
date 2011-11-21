package sceneobjects;

import glproject.Mesh;
import glproject.SceneObject;
import glproject.World;
import glproject.scenes.RealToVector3dFunction;

public class FunctionMover implements SceneObject {
    private Mesh mesh;
    private RealToVector3dFunction function;
    private World world;
    
    public FunctionMover(RealToVector3dFunction function, Mesh mesh) {
	this.mesh = mesh;
	this.function = function;
    }

    public void step() {
	this.mesh.translation = this.function.eval(System.currentTimeMillis());
    }

    public void initialize(World world) {
	this.world = world;
	this.world.addMesh(this.mesh);
    }
}
