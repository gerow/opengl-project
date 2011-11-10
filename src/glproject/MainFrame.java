package glproject;

import glproject.World;

import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Robot;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import com.jogamp.opengl.util.Animator;

public class MainFrame extends JFrame implements GLEventListener {
    GLU glu = new GLU();
    GLCanvas canvas = new GLCanvas();
    Animator animator = new Animator(this.canvas);
    World world;
    
    public MainFrame() throws IOException, AWTException {
	world = new World();
	this.setTitle("OpenGL Project version 1");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.add(canvas);
	this.setSize(640, 480);
	this.setUndecorated(true);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        canvas.addGLEventListener(this);
        this.world.meshes.add(Mesh.loadMeshFromObjFile("teapot.obj"));
    }

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
    
    public static void main(String args[]) throws IOException, AWTException {
	MainFrame mainFrame = new MainFrame();
	mainFrame.setVisible(true);
	mainFrame.animator.start();
	mainFrame.canvas.addKeyListener(mainFrame.world.camera);
	mainFrame.canvas.addMouseMotionListener(mainFrame.world.camera);
	mainFrame.canvas.addMouseListener(mainFrame.world.camera);
	mainFrame.canvas.requestFocus();
    }
}
