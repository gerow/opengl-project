package glproject;

import javax.media.opengl.GL2;

public class Uniform {
    enum Type { t1f, t2f, t3f, t4f, t1i, t2i, t3i, t4i, t1fv, t2fv, t3fv, t4fv, t1iv, t2iv, t3iv, t4iv };
    public ShaderProgram shaderProgram;
    public int location;
    public String name;
    public Type type;
    public GL2 gl;
    
    public Uniform(GL2 gl, Type type, ShaderProgram shaderProgram, String name) {
	this.gl = gl;
	this.name = name;
	this.type = type;
	this.shaderProgram = shaderProgram;
	shaderProgram.enable();
	this.location = gl.glGetUniformLocation(shaderProgram.getShaderLocation(), name);
    }
    
    public void set(float value) {
	this.shaderProgram.enable();
	this.gl.glUniform1f(this.location, value);
    }
    
    public void set(int value) {
	this.shaderProgram.enable();
	this.gl.glUniform1i(this.location, value);
    }
}
