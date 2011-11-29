package glproject.scenes;

import glproject.Light;
import glproject.MainFrame;
import glproject.Material;
import glproject.ShaderProgram;
import glproject.Vector3f;
import glproject.Vector4f;
import glproject.World;

import java.awt.AWTException;
import java.io.IOException;

import javax.media.opengl.GLAutoDrawable;

import solarsystem.Mercury;
import solarsystem.Saturn;
import solarsystem.Sun;

public class SolarSystem extends World {

    /**
     * 
     */
    private static final long serialVersionUID = 3171853764150449223L;

    public SolarSystem() throws AWTException {
	super();

	this.startRender();
	this.startLogic();
	// mainFrame.world.addMesh(m2);
    }

    public void init(GLAutoDrawable drawable) {
	super.init(drawable);
	
	Material mat = new Material();
	mat.ambient = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	mat.diffuse = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	mat.specular = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	Light sunLight = new Light(new Vector3f(0.0f, 0.0f, 0.0f), mat);
	this.addLight(sunLight);
	
	this.addSceneObject(new Sun());
	this.addSceneObject(new Mercury());
	this.addSceneObject(new Saturn());
	
	//ShaderProgram.defaultShader = ShaderProgram.getFromShaderLibrary("phong");
    }
    
    public static void main(String args[])  {
	MainFrame mainFrame = null;
	try {
	    mainFrame = new MainFrame(new SolarSystem());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (AWTException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	mainFrame.setVisible(true);
    }
}
