package glproject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Controller implements KeyListener, MouseMotionListener,
	MouseListener {
    public static final int MOUSE_LOCATION_X = 320;
    public static final int MOUSE_LOCATION_Y = 240;

    private World world;
    private boolean mouseGrabbed = false;
    private Robot robot;

    public Controller(World world) {
	this.world = world;
	try {
	    this.robot = new Robot();
	} catch (AWTException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	world.addKeyListener(this);
	world.addMouseListener(this);
	world.addMouseMotionListener(this);
    }

    public void mouseClicked(MouseEvent arg0) {
	if (!this.mouseGrabbed) {
	    this.mouseGrabbed = true;
	    this.robot.mouseMove(Controller.MOUSE_LOCATION_X,
		    Controller.MOUSE_LOCATION_Y);
	    return;
	}
    }

    public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    public void mouseDragged(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    public void mouseMoved(MouseEvent arg0) {
	Camera activeCamera = this.world.getActiveCamera();
	if (this.mouseGrabbed) {
	    activeCamera.azimuth.setHeading(activeCamera.azimuth.getHeading()
		    - (arg0.getXOnScreen() - MOUSE_LOCATION_X) * 4.0f);
	    activeCamera.azimuth.setAltitude(activeCamera.azimuth.getAltitude()
		    + (arg0.getYOnScreen() - MOUSE_LOCATION_Y) * 4.0f);
	    robot.mouseMove(MOUSE_LOCATION_X, MOUSE_LOCATION_Y);
	}
    }

    public void keyPressed(KeyEvent arg0) {
	Camera activeCamera = this.world.getActiveCamera();
	switch (arg0.getKeyCode()) {
	case KeyEvent.VK_W:
	    activeCamera.forward = true;
	    break;
	case KeyEvent.VK_S:
	    activeCamera.back = true;
	    break;
	case KeyEvent.VK_A:
	    activeCamera.left = true;
	    break;
	case KeyEvent.VK_D:
	    activeCamera.right = true;
	    break;
	case KeyEvent.VK_ESCAPE:
	    this.mouseGrabbed = false;
	    break;
	}
    }

    public void keyReleased(KeyEvent arg0) {
	Camera activeCamera = this.world.getActiveCamera();
	switch (arg0.getKeyCode()) {
	case KeyEvent.VK_W:
	    activeCamera.forward = false;
	    break;
	case KeyEvent.VK_S:
	    activeCamera.back = false;
	    break;
	case KeyEvent.VK_A:
	    activeCamera.left = false;
	    break;
	case KeyEvent.VK_D:
	    activeCamera.right = false;
	    break;
	}
    }

    public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub

    }

}
