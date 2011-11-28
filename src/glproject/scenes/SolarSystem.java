package glproject.scenes;

import glproject.MainFrame;
import glproject.World;

import java.awt.AWTException;
import java.io.IOException;

import javax.media.opengl.GLAutoDrawable;

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
