package glproject;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

public interface Renderable {
    public void init(World world);
    public void render(GLAutoDrawable drawable, GLU glu);
}
