package glproject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLException;
import javax.media.opengl.GLProfile;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.awt.ImageUtil;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class TextureLoader {
    public class ImageIntPair {
	public BufferedImage image;
	public int integer;
    }

    public static HashMap<String, Texture> images = new HashMap<String, Texture>();
    public static GLAutoDrawable drawable;
    public static GLU glu;
    public static GL2 gl;

    // public class TextureWithIdentifier;

    public static void init(GLAutoDrawable drawable, GLU glu) {
	TextureLoader.drawable = drawable;
	TextureLoader.glu = glu;
	TextureLoader.gl = drawable.getGL().getGL2();
    }

    public static Texture loadTexture(String name) throws GLException,
	    IOException {
	if (TextureLoader.images.containsKey(name))
	    return images.get(name);
	// InputStream stream = new InputStream(new File("assets/textures/" +
	// name)));
	// BufferedImage im = ImageIO.read(new File("assets/texture/" + name));
	//GLContext.getCurrent().setGL(gl);
	Texture texture = TextureIO.newTexture(new File("assets/textures/"
		+ name), true);
	if (texture.getMustFlipVertically()) {
	    texture.destroy(gl);
	    BufferedImage img = ImageIO.read(new File("assets/textures/" + name)); 
	    ImageUtil.flipImageVertically(img);
	    texture = AWTTextureIO.newTexture(GLProfile.getDefault(), img, true);
	}
	//texture.bind(gl);
	//texture.
	// texture.getTextureObject(gl);
	//TextureIO.
	TextureLoader.images.put(name, texture);
	System.out.println(images.size() + " textures loaded");
	System.out.println("Adding texture named " + texture);
	return texture;
    }
}
