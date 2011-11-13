package glproject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Camera implements KeyListener, MouseMotionListener, MouseListener {
    public static final float SPEED = 5.0f;
    public static final int MOUSE_LOCATION_X = 320;
    public static final int MOUSE_LOCATION_Y = 240;

    Vector3d location = new Vector3d(0, 0, 0);
    Azimuth azimuth = new Azimuth();

    boolean forward = false;
    boolean back = false;
    boolean left = false;
    boolean right = false;

    boolean mouseListening = false;

    Robot robot;

    public Camera() throws AWTException {
	robot = new Robot();
    }

    public Vector3d getReferencePoint() {
	return this.location.add(this.azimuth.direction);
    }

    public void step() {
	if (forward)
	    this.location = this.location.add(this.azimuth.direction
		    .multiply(SPEED));
	if (back)
	    this.location = this.location.add(this.azimuth.direction
		    .multiply(-SPEED));
	//if (left)
	//    this.location = this.location.add(this.getUnitLeft().multiply(SPEED));
	//if (right)
	//    this.location = this.location.add(this.getUnitLeft().multiply(-SPEED));
    }
    
    public Vector3d getUnitLeft() {
	Vector3d out = new Vector3d(azimuth.direction);
	out.y = 0;
	out.x = -out.x;
	return out.normalize();
    }

    public void keyPressed(KeyEvent arg0) {
	System.out.println("Key pressed");
	System.out.println(this.azimuth.direction.x + " " + this.azimuth.direction.y + " " + this.azimuth.direction.z);
	switch (arg0.getKeyCode()) {
	case KeyEvent.VK_W:
	    this.forward = true;
	    break;
	case KeyEvent.VK_S:
	    this.back = true;
	    break;
	case KeyEvent.VK_A:
	    this.left = true;
	    break;
	case KeyEvent.VK_D:
	    this.right = true;
	    break;
	case KeyEvent.VK_ESCAPE:
	    this.mouseListening = false;
	    break;
	}
    }

    public void keyReleased(KeyEvent arg0) {
	switch (arg0.getKeyCode()) {
	case KeyEvent.VK_W:
	    this.forward = false;
	    break;
	case KeyEvent.VK_S:
	    this.back = false;
	    break;
	case KeyEvent.VK_A:
	    this.left = false;
	    break;
	case KeyEvent.VK_D:
	    this.right = true;
	    break;
	}
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
	if (this.mouseListening) {
	    this.azimuth.setHeading(this.azimuth.getHeading()
		    - (arg0.getXOnScreen() - MOUSE_LOCATION_X) * 4.0f);
	    this.azimuth.setAltitude(this.azimuth.getAltitude()
		    + (arg0.getYOnScreen() - MOUSE_LOCATION_Y) * 4.0f);
	    robot.mouseMove(MOUSE_LOCATION_X, MOUSE_LOCATION_Y);
	}
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
	robot.mouseMove(MOUSE_LOCATION_X, MOUSE_LOCATION_Y);
	this.mouseListening = true;
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }
}
