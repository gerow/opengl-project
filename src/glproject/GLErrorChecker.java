package glproject;

import java.io.LineNumberReader;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.common.nio.Buffers;

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
    
    public static void checkShader(int shaderId, String message) {
	System.out.println("For " + message);
	IntBuffer tReturnValue = Buffers.newDirectIntBuffer(1);
        gl.glGetObjectParameterivARB(shaderId, GL2.GL_OBJECT_INFO_LOG_LENGTH_ARB, tReturnValue);
        int tLogLength = tReturnValue.get();
        if (tLogLength <= 1) {
            return;
        }
        ByteBuffer tShaderLog = Buffers.newDirectByteBuffer(tLogLength);
        tReturnValue.flip();
        gl.glGetInfoLogARB(shaderId, tLogLength, tReturnValue, tShaderLog);
        byte[] tShaderLogBytes = new byte[tLogLength];
        tShaderLog.get(tShaderLogBytes);
        String tShaderValidationLog = new String(tShaderLogBytes);
        StringReader tStringReader = new StringReader(tShaderValidationLog);
        LineNumberReader tLineNumberReader = new LineNumberReader(tStringReader);
        String tCurrentLine;
        try {
            while ((tCurrentLine = tLineNumberReader.readLine()) != null) {
                if (tCurrentLine.trim().length()>0) {
                    System.out.println("GLSL VALIDATION: "+tCurrentLine.trim());
                }
            }
        } catch (Exception e) {
            
        }
    }

    public static void checkShaderProgram(int shaderId, String message) {
	int[] status = new int[1];
	gl.glGetProgramiv(shaderId, GL2.GL_VALIDATE_STATUS, status, 0);
	if (status[0] == GL2.GL_FALSE) {
	    System.out.println("Shader did not validate.  Message: " + message);
	    System.exit(status[0]);
	}
    }
}
