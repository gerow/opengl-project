package sceneobjects;

import glproject.Mesh;
import glproject.SceneObject;
import glproject.World;

public class Teapot implements SceneObject {
    World world;
    Mesh teapot;
    public Teapot(Mesh teapot) {
	this.teapot = teapot;
    }
    public void step() {
	//this.teapot.rotation.x += 1;
	//this.teapot.scaling.x += 0.01;
    }
    public void initialize(World world) {
	this.world = world;
    }
}
