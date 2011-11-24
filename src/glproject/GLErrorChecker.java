package glproject;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public class GLErrorChecker {
    public static GL2 gl;
    public static GLU glu;

    public static void check(String message) {
	int error = gl.glGetError();
	if (error != GL2.GL_NO_ERROR) {
	    System.out.println("GL Error detected.  Message: " + message
		    + ".  GLErrorString: " + glu.gluErrorString(error));
	    System.exit(error);
	}
    }
}
