package solarsystem;

import java.io.IOException;

import javax.media.opengl.GLException;

import glproject.Material;
import glproject.Mesh;
import glproject.RealToVector3fFunction;
import glproject.ShaderProgram;
import glproject.TextureLoader;
import glproject.Vector3f;
import glproject.Vector4f;

public class Mercury extends Planet {
    public static final float SCALING_FACTOR = Sun.SCALE_FACTOR * 0.2f;
    public static final float ROTATIONAL_VELOCITY = 0.7f;
    public Mercury() {
	
	try {
	    this.mesh = Mesh.loadMeshFromObjFile("solarplanet.obj");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	Material mat = new Material();
	mat.ambient = new Vector4f(0.0f, 0.0f, 0.0f, 1.0f);
	mat.diffuse = new Vector4f(0.75f, 0.75f, 0.75f, 1.0f);
	mat.specular = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	mat.shininess = 20;
	try {
	    mat.texture = TextureLoader.loadTexture("mars.jpg");
	} catch (GLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	this.function = new RealToVector3fFunction() {
	    public Vector3f eval(float t) {
		return new Vector3f(400.0f, 0.0f, 0.0f);
	    }
	};
	
	this.rotationalVelocity = ROTATIONAL_VELOCITY;
	
	this.mesh.setMaterial(mat);
	this.mesh.scaling = new Vector3f(SCALING_FACTOR);
	this.mesh.setShaderProgram(ShaderProgram.getFromShaderLibrary("phong"));
    }

    @Override
    public float getAttractionValue() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public float getRadius() {
	// TODO Auto-generated method stub
	return 0;
    }

}
