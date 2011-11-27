package glproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.texture.Texture;

public class Material {
    public enum Face {
	FRONT, BACK, BOTH
    };

    public Vector4f ambient = new Vector4f();
    public Vector4f diffuse = new Vector4f();
    public Vector4f specular = new Vector4f();
    public float shininess = 0.0f;
    public Face face = Face.FRONT;
    public Texture texture;

    // public String texture = null;

    public Material() {
	Vector4f vec = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	this.ambient = this.diffuse = this.specular = vec;
    }

    public Material(Vector4f ambient, Vector4f diffuse, Vector4f specular,
	    float shininess) {
	this.ambient = ambient;
	this.diffuse = diffuse;
	this.specular = specular;
	this.shininess = shininess;
    }

    public Material(Vector4f ambient, Vector4f diffuse, Vector4f specular,
	    float shininess, String texture) throws GLException, IOException {
	this(ambient, diffuse, specular, shininess);

	// this.texture = texture;
	if (texture != null)
	    this.texture = TextureLoader.loadTexture(texture);
    }

    public Material(Vector4f ambient, Vector4f diffuse, Vector4f specular,
	    float shininess, String texture, Face face) throws GLException,
	    IOException {
	this(ambient, diffuse, specular, shininess, texture);

	this.face = face;
    }

    public void enableMaterial(GLAutoDrawable drawable, GLU glu) {
	// Still need to do texture mapping.
	GL2 gl = drawable.getGL().getGL2();
	gl.glEnable(GL2.GL_LIGHTING);
	int faceInt = 0;

	switch (face) {
	case FRONT:
	    faceInt = GL.GL_FRONT;
	    break;
	case BACK:
	    faceInt = GL.GL_BACK;
	    break;
	case BOTH:
	    faceInt = GL.GL_FRONT_AND_BACK;
	    break;
	}

	gl.glMaterialfv(faceInt, GL2.GL_AMBIENT, this.ambient.toArray(), 0);
	gl.glMaterialfv(faceInt, GL2.GL_DIFFUSE, this.diffuse.toArray(), 0);
	gl.glMaterialfv(faceInt, GL2.GL_SPECULAR, this.specular.toArray(), 0);
	gl.glMaterialf(faceInt, GL2.GL_SHININESS, this.shininess);
	// gl.glBindTexture(GL2.GL_TEXTURE_2D, this.textureInt);
	// gl.glScalef(1.0f, 1.0f, 1.0f);
	// System.out.println("Using texture " + this.texture);
	// System.out.println("Texture has width " + this.texture.getWidth()
	// + " and height " + this.texture.getHeight() + " and id " +
	// this.texture.getTextureObject(gl));
	// System.out.println(gl);
	// gl.glEnable(this.texture.getTarget());
	if (this.texture != null) {
	    
	    this.texture.enable(gl);
	    this.texture.bind(gl);
	}
	else {
	    gl.glDisable(GL2.GL_TEXTURE_2D);
	    gl.glColorMaterial(faceInt, GL2.GL_AMBIENT_AND_DIFFUSE);
	}
    }

    public void disableTexture(GL2 gl) {

    }

    public static Material loadFromMtlFile(String mtlFile, String mtlName)
	    throws GLException, IOException {
	Vector4f ka = new Vector4f();
	Vector4f kd = new Vector4f();
	Vector4f ks = new Vector4f();
	float ns = 1.0f;
	String textureName = null;

	// String map_kd;
	BufferedReader reader = null;
	reader = new BufferedReader(new FileReader("assets/materials/"
		+ mtlFile));
	String line;
	String[] splitLines;
	boolean foundName = false;
	while ((line = reader.readLine()) != null) {
	    splitLines = line.split("\\s+");
	    if (splitLines[0].equals("newmtl") && splitLines[1].equals(mtlName)) {
		foundName = true;
		continue;
	    }
	    if (foundName) {
		if (splitLines[0].equals("newmtl"))
		    break;
		else if (splitLines[0].equals("Ka")) {
		    ka.x = Float.valueOf(splitLines[1]);
		    ka.y = Float.valueOf(splitLines[2]);
		    ka.z = Float.valueOf(splitLines[3]);
		} else if (splitLines[0].equals("Kd")) {
		    kd.x = Float.valueOf(splitLines[1]);
		    kd.y = Float.valueOf(splitLines[2]);
		    kd.z = Float.valueOf(splitLines[3]);
		} else if (splitLines[0].equals("Ks")) {
		    ks.x = Float.valueOf(splitLines[1]);
		    ks.y = Float.valueOf(splitLines[2]);
		    ks.z = Float.valueOf(splitLines[3]);
		} else if (splitLines[0].equals("Ns")) {
		    ns = Float.valueOf(splitLines[1]);
		} else if (splitLines[0].equals("d")) {
		    ka.w = kd.w = ks.w = Float.valueOf(splitLines[1]);
		} else if (splitLines[0].equals("map_Kd")) {
		    textureName = splitLines[1];
		}
	    }
	}
	if (textureName == null)
	    return new Material(ka, kd, ks, ns);
	else
	    return new Material(ka, kd, ks, ns, textureName);
    }
    
    public static Material pureAmbient() {
	Material out = new Material();
	out.ambient = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	
	return out;
    }
}
