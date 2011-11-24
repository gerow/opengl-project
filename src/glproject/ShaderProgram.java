package glproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;
import javax.media.opengl.GL3;
import javax.media.opengl.glu.GLU;

public class ShaderProgram {
    public static GL2 gl;
    public static GLU glu;

    private int id = 0;
    private int v = 0;
    private int f = 0;

    public void enable() {
	gl.glUseProgram(this.id);
    }

    public static ShaderProgram loadFromFile(String vertexShader,
	    String fragmentShader) throws IOException {
	ShaderProgram out = new ShaderProgram();
	out.v = gl.glCreateShader(GL2.GL_VERTEX_SHADER);
	out.f = gl.glCreateShader(GL2.GL_FRAGMENT_SHADER);
	String[] vertexStrings = fileToStringArray("assets/shaders/"
		+ vertexShader);
	String[] fragmentStrings = fileToStringArray("assets/shaders/"
		+ fragmentShader);
	for (int i = 0; i < vertexStrings.length; ++i) {
	    System.out.println(vertexStrings[i]);
	}
	for (int j = 0; j < fragmentStrings.length; ++j) {
	    System.out.println(fragmentStrings[j]);
	}
	int[] lengthArray = new int[vertexStrings.length];
	for (int i = 0; i < vertexStrings.length; ++i) {
	    lengthArray[i] = vertexStrings[i].length();
	}
	gl.glShaderSource(out.v, vertexStrings.length, vertexStrings, lengthArray, 0);
	gl.glCompileShader(out.v);
	
	GLErrorChecker.checkShader(out.v, "Vertex shader " + vertexShader);
	
	int[] lengthArraytwo = new int[fragmentStrings.length];
	for (int i = 0; i < fragmentStrings.length; ++i) {
	    lengthArraytwo[i] = fragmentStrings[i].length();
	}
	gl.glShaderSource(out.f, fragmentStrings.length, fragmentStrings, lengthArraytwo, 0);
	gl.glCompileShader(out.f);
	
	GLErrorChecker.checkShader(out.f, "Fragment shader " + fragmentShader);

	out.id = gl.glCreateProgram();
	gl.glAttachShader(out.id, out.v);
	gl.glAttachShader(out.id, out.f);
	gl.glLinkProgram(out.id);
	gl.glValidateProgram(out.id);
	
	GLErrorChecker.checkShader(out.id, "Shader program");

	GLErrorChecker.check("After compiling shaders " + vertexShader
		+ " and " + fragmentShader);
	System.out.println(out.id);

	GLErrorChecker.checkShaderProgram(out.id, "After compiling shaders "
		+ vertexShader + " and " + fragmentShader);
	
	gl.glUseProgram(out.id);
	return out;
    }

    public static String[] fileToStringArray(String filename)
	    throws IOException {
	BufferedReader reader = new BufferedReader(new FileReader(filename));
	List<String> out = new ArrayList<String>();
	String line = null;
	while ((line = reader.readLine()) != null)
	    out.add(line);
	String[] outString = new String[out.size()];
	out.toArray(outString);
	return outString;
    }
    
    public static void useDefaultShader() {
	gl.glUseProgram(0);
    }
}
