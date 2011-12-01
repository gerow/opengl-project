package glproject.scenes;

import glproject.Light;
import glproject.MainFrame;
import glproject.Material;
import glproject.Vector3f;
import glproject.Vector4f;

import java.awt.AWTException;
import java.io.IOException;

import javax.media.opengl.GLAutoDrawable;

import solarsystem.Planet;
import solarsystem.SaturnWithRings;

public class SolarSystemWithSaturn extends SolarSystem {
    
    public boolean throwComet = false;

    public SolarSystemWithSaturn() throws AWTException {
	this.startRender();
	this.startLogic();
    }
    
    public void init(GLAutoDrawable drawable) {
	super.init(drawable);
	/*
	Material mat = new Material();
	mat.ambient = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	mat.diffuse = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	mat.specular = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	Light sunLight = new Light(new Vector3f(0.0f, 0.0f, 0.0f), mat);
	this.addLight(sunLight);
	*/
	
	for (Planet p : this.planets) {
	    this.removeMesh(p.bigger);
	    this.removeMesh(p.smaller);
	    this.removeSceneObject(p);
	}
	
	this.addSceneObject(new SaturnWithRings());
    }
    
    public static void main(String args[]) {
	MainFrame mainFrame = null;
	try {
	    mainFrame = new MainFrame(new SolarSystemWithSaturn());
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
