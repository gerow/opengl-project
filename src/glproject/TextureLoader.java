package glproject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class TextureLoader {
    public class ImageIntPair {
	public BufferedImage image;
	public int integer;
    }
    public static HashMap<String, Integer> images = new HashMap<String, Integer>();
    public static GLAutoDrawable drawable;
    public static GLU glu;
    public static GL2 gl;
    
    //public class TextureWithIdentifier;
    
    public static void init(GLAutoDrawable drawable, GLU glu) {
	TextureLoader.drawable = drawable;
	TextureLoader.glu = glu;
	TextureLoader.gl = drawable.getGL().getGL2();
    }
    
    public static int loadTexture(String name) throws GLException, IOException {
	if (TextureLoader.images.containsKey(name))
	    return images.get(name);
	//InputStream stream = new InputStream(new File("assets/textures/" + name)));
	//BufferedImage im = ImageIO.read(new File("assets/texture/" + name));
	Texture texture = TextureIO.newTexture(new File("assets/texture/" + name), true);
	texture.bind(gl);
	//texture.getTextureObject(gl);
	TextureLoader.images.put(name, texture.getTextureObject(gl));
	return texture.getTarget();
    }
}
