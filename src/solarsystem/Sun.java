package solarsystem;

import glproject.Material;
import glproject.Mesh;
import glproject.RealToVector3fFunction;
import glproject.TextureLoader;
import glproject.Vector3f;
import glproject.Vector4f;

import java.io.IOException;

import javax.media.opengl.GLException;

public class Sun extends Planet {
    public final static float SCALE_FACTOR = 1.0f;
    public final static float ROTATIONAL_VELOCITY = 0.2f;
    public final static float SUN_ATTRACTION_VALUE = 1.0f;
    public final static float SUN_RADIUS = 136.68f;

    public Sun() {
	/*
	try {
	    this.mesh = Mesh.loadMeshFromObjFile("solarplanet.obj");
	} catch (IOException e) {
	    e.printStackTrace();
	}
	this.mesh.reverseVertexWinding();
	*/
	this.mesh.enableOptimization();

	System.out.println(mesh);
	Material mat = new Material();
	mat.ambient = new Vector4f(0.75f, 0.75f, 0.75f, 1.0f);
	mat.diffuse = new Vector4f(0.75f, 0.75f, 0.75f, 1.0f);
	mat.specular = new Vector4f(0.0f, 0.0f, 0.0f, 1.0f);
	try {
	    mat.texture = TextureLoader.loadTexture("sun.jpg");
	} catch (GLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	// this.mesh.negateNormals();
	this.mesh.setMaterial(mat);
	this.mesh.useFixedShader();
	this.function = new RealToVector3fFunction() {
	    public Vector3f eval(float t) {
		return new Vector3f(0.0f, 0.0f, 0.0f);
	    }
	};

	// this.rotationalVelocity = ROTATIONAL_VELOCITY;
	// this.mesh.scaling = new Vector3f(SCALE_FACTOR, SCALE_FACTOR,
	// SCALE_FACTOR);
    }

    public float getRadius() {
	// TODO Auto-generated method stub
	return Sun.SUN_RADIUS;
    }

    @Override
    public float getAttractionValue() {
	// TODO Auto-generated method stub
	return Sun.SUN_ATTRACTION_VALUE;
    }
}
