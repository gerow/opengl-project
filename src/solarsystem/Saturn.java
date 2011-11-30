package solarsystem;

import glproject.Mesh;
import glproject.RealToVector3fFunction;
import glproject.Vector3f;

import java.io.IOException;

public class Saturn extends Planet {
    
    public Saturn() {
	try {
	    this.mesh = Mesh.loadMeshFromObjFile("saturnUV.obj");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	this.mesh.reverseVertexWinding();
	this.mesh.disableBackCulling();
	
	this.function = new RealToVector3fFunction() {
	    public Vector3f eval(float t) {
		return new Vector3f(800.0f, 0.0f, 0.0f);
	    }
	};
	
	//this.mesh.setShaderProgram(ShaderProgram.getFromShaderLibrary("phong"));
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
