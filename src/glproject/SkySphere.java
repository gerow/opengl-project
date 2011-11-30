package glproject;

import java.io.IOException;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;

public class SkySphere implements Renderable {
    private Mesh mesh;
    public SkySphere(float size, String texture) {
	try {
	    this.mesh = Mesh.loadMeshFromObjFile("solarplanet.obj");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	//this.mesh.reverseVertexWinding();
	Material mat = Material.pureAmbient();
	try {
	    mat.texture = TextureLoader.loadTexture(texture);
	} catch (GLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	mat.face = Material.Face.FRONT;
	this.mesh.setMaterial(mat);
	this.mesh.scaling = new Vector3f(size, size, size);
	
	this.mesh.useFixedShader();
	this.mesh.enableOptimization();
	
	//this.mesh.setShaderProgram(ShaderProgram.getFromShaderLibrary("phong_textured"));
    }
    
    @Override
    public void init(World world) {
	
    }

    @Override
    public void render(GLAutoDrawable drawable, GLU glu) {
	this.mesh.render(drawable, glu);
    }

}
