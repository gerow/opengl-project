package solarsystem;

import java.io.IOException;

import glproject.Mesh;
import glproject.RealToVector3fFunction;
import glproject.ShaderProgram;
import glproject.Vector3f;

public class SaturnWithRings extends Planet {
    
    public SaturnWithRings() {
	try {
	    this.bigger = Mesh.loadMeshFromObjFile("saturnUV.obj");
	    this.smaller = this.bigger;
	    this.mesh = this.bigger;
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	this.function = new RealToVector3fFunction() {
	    public Vector3f eval(float t) {
		
		float a = 1050;
		float b = 1050;
		
		t = t / 5.0f;
		
		float x = (float) (a * Math.cos(1.9*t));
		float y = (float) (b * Math.sin(1.9*t));
		return new Vector3f(x, -800.0f, y);
	    }
	};
	
	this.rotationalVelocity = 0.7f;
	
	this.mesh.setShaderProgram(ShaderProgram.getFromShaderLibrary("phong_textured"));
	
	this.mesh.reverseVertexWinding();
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
