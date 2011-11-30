package glproject.scenes;

import glproject.Light;
import glproject.MainFrame;
import glproject.Material;
import glproject.Mesh;
import glproject.ShaderProgram;
import glproject.Vector3f;
import glproject.Vector4f;
import glproject.World;

import java.awt.AWTException;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

import sceneobjects.SphereLight;

public class TeapotShowcase extends World {

    /**
     * 
     */
    private static final long serialVersionUID = 3171853764150449223L;
    public boolean throwComet = false;

    public TeapotShowcase() throws AWTException {
	super();

	this.startRender();
	this.startLogic();
	// mainFrame.world.addMesh(m2);
    }

    public void init(GLAutoDrawable drawable) {
	super.init(drawable);
	
	GL2 gl = drawable.getGL().getGL2();
	
	gl.glDisable(GL2.GL_CULL_FACE);

	//Material mat = new Material();
	//mat.ambient = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	//mat.diffuse = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	//mat.specular = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	//Light sunLight = new Light(new Vector3f(40.0f, 40.0f, 40.0f), mat);
	//this.addLight(sunLight);
	
	SphereLight light = new SphereLight(new Vector3f(0.0f, 0.0f, 0.0f));
	
	this.addSceneObject(light);
	
	Mesh m = null;
	try {
	    m = Mesh.loadMeshFromObjFile("teapot_2.obj");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	m.calculateVertexNormals();
	
	Material material = new Material();
	material.ambient = new Vector4f(0.1f);
	material.diffuse = new Vector4f(0.3f, 1.0f, 0.8f, 1.0f);
	material.specular = new Vector4f(1.0f);
	material.shininess = 20.0f;
	m.setShaderProgram(ShaderProgram.getFromShaderLibrary("phong_untextured"));
	
	m.setMaterial(material);
	this.addMesh(m);
    }

    public void render(GLAutoDrawable drawable, GLU glu) {
	super.render(drawable, glu);
    }

    public static void main(String args[]) {
	MainFrame mainFrame = null;
	try {
	    mainFrame = new MainFrame(new TeapotShowcase());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (AWTException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	mainFrame.setVisible(true);
    }

    public void setShaders(ShaderProgram shader) {

    }
}
