package glproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public class ShaderProgram {
    public static GL2 gl;
    public static GLU glu;
    public static ShaderProgram defaultShader = null;

    private int id = 0;
    private int v = 0;
    private int f = 0;

    private HashMap<String, Uniform> uniforms = new HashMap<String, Uniform>();

    private static HashMap<String, ShaderProgram> shaderLibrary = new HashMap<String, ShaderProgram>();

    public void enable() {
	gl.glUseProgram(this.id);
    }

    public static ShaderProgram loadFromFile(String vertexShader,
	    String fragmentShader) throws IOException {
	ShaderProgram out = new ShaderProgram();
	out.v = gl.glCreateShader(GL2.GL_VERTEX_SHADER);
	out.f = gl.glCreateShader(GL2.GL_FRAGMENT_SHADER);
	// gl.glShaderSource(out.v, vertexStrings.length, vertexStrings,
	// lengthArray, 0);
	// gl.glCompileShader(out.v);
	String vertShaderString = ShaderProgram.fileToString("assets/shaders/"
		+ vertexShader);
	String[] vertArray = new String[1];
	vertArray[0] = vertShaderString;
	int[] vertLengthArray = new int[1];
	vertLengthArray[0] = vertShaderString.length();
	gl.glShaderSource(out.v, 1, vertArray, vertLengthArray, 0);
	gl.glCompileShader(out.v);

	System.out.println("Compiled vertex shader: \n" + vertShaderString);

	GLErrorChecker.checkShader(out.v, "Vertex shader " + vertexShader);

	String fragShaderString = ShaderProgram.fileToString("assets/shaders/"
		+ fragmentShader);
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

    public void addUniform(Uniform uniform) {
	System.out.println("Adding uniform " + uniform);
	this.uniforms.put(uniform.name, uniform);
    }

    public Uniform getUniform(String name) {
	return this.uniforms.get(name);
    }

    public static void addToShaderLibrary(String name,
	    ShaderProgram shaderProgram) {
	ShaderProgram.shaderLibrary.put(name, shaderProgram);
    }

    public static ShaderProgram getFromShaderLibrary(String name) {
	return ShaderProgram.shaderLibrary.get(name);
    }

    public static void buildShaderLibrary() throws IOException {
	ShaderProgram phong = ShaderProgram.loadFromFile("phong_textured.vert",
		"phong_textured.frag");
	Uniform numLights = new Uniform(ShaderProgram.gl, Uniform.Type.t1i,
		phong, "num_lights");
	Uniform cameraPosition = new Uniform(ShaderProgram.gl,
		Uniform.Type.t3f, phong, "camera_position");
	phong.addUniform(numLights);
	phong.addUniform(cameraPosition);
	phong.getUniform("num_lights").set(1);
	phong.getUniform("camera_position").set(new Vector3f(0.0f, 0.0f, 0.0f));
	ShaderProgram.shaderLibrary.put("phong_textured", phong);

	ShaderProgram phongUntextured = ShaderProgram.loadFromFile(
		"phong_untextured.vert", "phong_untextured.frag");
	numLights = new Uniform(ShaderProgram.gl, Uniform.Type.t1i,
		phongUntextured, "num_lights");
	numLights.set(1);
	phongUntextured.addUniform(numLights);
	ShaderProgram.shaderLibrary.put("phong_untextured", phongUntextured);

	ShaderProgram cel = ShaderProgram.loadFromFile("cel_textured.vert",
		"cel_textured.frag");
	numLights = new Uniform(ShaderProgram.gl, Uniform.Type.t1i, cel,
		"num_lights");
	cel.addUniform(numLights);
	cel.getUniform("num_lights").set(3);
	ShaderProgram.shaderLibrary.put("cel_textured", cel);

	ShaderProgram celUntextured = ShaderProgram.loadFromFile(
		"cel_untextured.vert", "cel_untextured.frag");
	numLights = new Uniform(ShaderProgram.gl, Uniform.Type.t1i,
		celUntextured, "num_lights");
	celUntextured.addUniform(numLights);
	celUntextured.getUniform("num_lights").set(1);
	Uniform celFactor = new Uniform(ShaderProgram.gl, Uniform.Type.t1f,
		celUntextured, "cel_factor");
	celUntextured.addUniform(celFactor);
	celUntextured.getUniform("cel_factor").set(3.0f);
	ShaderProgram.shaderLibrary.put("cel_untextured", celUntextured);
    }
}
