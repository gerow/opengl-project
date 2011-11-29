package glproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
    public static ShaderProgram defaultShader = null;

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
	//gl.glShaderSource(out.v, vertexStrings.length, vertexStrings,
	//	lengthArray, 0);
	//gl.glCompileShader(out.v);
	String vertShaderString = ShaderProgram.fileToString("assets/shaders/" + vertexShader);
	String[] vertArray = new String[1];
	vertArray[0] = vertShaderString;
	int[] vertLengthArray = new int[1];
	vertLengthArray[0] = vertShaderString.length();
	gl.glShaderSource(out.v, 1, vertArray, vertLengthArray, 0);
	gl.glCompileShader(out.v);
	
	System.out.println("Compiled vertex shader: \n" + vertShaderString);

	GLErrorChecker.checkShader(out.v, "Vertex shader " + vertexShader);

	String fragShaderString = ShaderProgram.fileToString("assets/shaders/" + fragmentShader);
	String[] fragArray = new String[1];
	fragArray[0] = fragShaderString;
	int[] fragLengthArray = new int[1];
	fragLengthArray[0] = fragShaderString.length();
	gl.glShaderSource(out.f, 1, fragArray, fragLengthArray, 0);
	gl.glCompileShader(out.f);
	
	System.out.println("Compiled fragment shader: \n" + fragShaderString);

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

    public static String fileToString(String filename) throws IOException {
	BufferedReader reader = new BufferedReader(new FileReader(filename));
	String out = new String();
	String line = null;
	while ((line = reader.readLine()) != null)
	    out += line + "\n";
	return out;
    }

    public static void useDefaultShader() {
	if (ShaderProgram.defaultShader != null)
	    defaultShader.enable();
	else
	    gl.glUseProgram(0);
    }
    
    public int getShaderLocation() {
	return this.id;
    }
}
