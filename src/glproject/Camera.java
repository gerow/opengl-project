package glproject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Camera implements KeyListener, MouseMotionListener {
    public static final float SPEED = 5.0f;
    
    Vector3d location = new Vector3d(0, 0, 0);
    Azimuth azimuth = new Azimuth();
    
    boolean forward = false;
    boolean back = false;
    boolean left = false;
    boolean right = false;
    
    public Camera() {
	
    }
    
    public Vector3d getReferencePoint() {
	return this.location.add(this.azimuth.direction);
    }
    
    public void step() {
	if (forward)
	    this.location = this.location.add(this.azimuth.direction.multiply(SPEED));
	if (back)
	    this.location = this.location.add(this.azimuth.direction.multiply(-SPEED));
	//azimuth.setAltitude(azimuth.getAltitude() + Mouse.getDY());
    }

    public void keyPressed(KeyEvent arg0) {
	switch (arg0.getKeyCode()) {
	case KeyEvent.VK_W:
	    this.forward = true;
	    break;
	case KeyEvent.VK_S:
	    this.back = true;
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
	this.azimuth.setHeading(this.azimuth.getHeading() + (arg0.getX() - 320) / 10.0f);
	this.azimuth.setAltitude(this.azimuth.getAltitude() + (arg0.getY() - 240) / 10.0f);
    }
}
