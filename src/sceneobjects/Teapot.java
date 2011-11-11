package sceneobjects;

import glproject.Mesh;
import glproject.SceneObject;

public class Teapot implements SceneObject {
    Mesh teapot;
    public Teapot(Mesh teapot) {
	this.teapot = teapot;
    }
    public void step() {
	this.teapot.rotation.x += 1;
	this.teapot.scaling.x += 0.01;
    }

}
