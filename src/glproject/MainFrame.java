package glproject;

import glproject.World;
import glproject.scenes.SolarSystem;

import java.awt.AWTException;
import java.awt.Frame;
import java.io.IOException;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -4337985330972808940L;
    //public static final int TICKRATE = 33;
    //GLU glu = new GLU();
    //GLCanvas canvas = new GLCanvas();
    //Animator animator = new Animator(this.canvas);
    World world;
    //private Timer t = new Timer(1000 / MainFrame.TICKRATE, this);
    
    public MainFrame(World world) throws IOException, AWTException {
	this.world = world;
	this.setTitle("OpenGL Project version 1");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.add(world);
	this.setSize(1280, 1024);
	this.setUndecorated(true);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

    /*
    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);
        gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
    }


    @Override
    public void dispose(GLAutoDrawable drawable) {
	
    }

    @Override
    public void display(GLAutoDrawable drawable) {
	this.world.render(drawable, this.glu);
    }
    
    public void step() {
	this.world.step();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
	    int height) {
        GL2 gl = drawable.getGL().getGL2();
        if (height <= 0) {
            height = 1;
        }
        float h = (float) width / (float) height;
        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(50.0f, h, 1.0, 1000.0);
        //glu.gluLookAt(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ)
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    
    public void start() {
	this.t.start();
    }
    
    public void stop() {
	this.t.stop();
    }
    */
    
    public static void main(String args[]) throws IOException, AWTException {
	MainFrame mainFrame = new MainFrame(new SolarSystem());
	mainFrame.setVisible(true);

    }

    /*
    @Override
    public void actionPerformed(ActionEvent arg0) {
	this.step();
    }
    */
}
