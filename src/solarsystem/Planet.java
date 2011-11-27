package solarsystem;

import glproject.Attractor;
import glproject.Collider;
import glproject.Vector3f;
import sceneobjects.FunctionMover;

public abstract class Planet extends FunctionMover implements Attractor, Collider {
    public Vector3f getLocation() {
	return this.getLocation();
    }
}
