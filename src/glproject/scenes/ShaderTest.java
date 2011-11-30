package glproject.scenes;

import glproject.MainFrame;
import glproject.Mesh;
import glproject.ShaderProgram;
import glproject.SkySphere;
import glproject.Vector3f;
import glproject.World;

import java.awt.AWTException;
import java.io.IOException;

import javax.media.opengl.GLAutoDrawable;

import sceneobjects.Orbiter;
import sceneobjects.SphereLight;
import sceneobjects.SphereLightOrbiter;

public class ShaderTest extends World {

    /**
     * 
     */
    private static final long serialVersionUID = -8501533942596676773L;

    public ShaderTest() throws AWTException {
	super();

	this.startRender();
	this.startLogic();
	// mainFrame.world.addMesh(m2);
    }

    public void init(GLAutoDrawable drawable) {
	super.init(drawable);
	
	
    }

    public static void main(String args[]) {
	MainFrame mainFrame = null;
	try {
	    mainFrame = new MainFrame(new ShaderTest());
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
