package glproject;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLBase;
import javax.media.opengl.GLDrawable;
import javax.media.opengl.glu.GLU;

import solarsystem.Comet;

public class Particle {
    private int imageNumber = 0;
    private World world;
    private float scale;
    private Polygon polygon;
    public int lifetime;
    public Vector3f translation;
    public Vector3f rotation;
    public Vector3f scaling=new Vector3f(.1f);
    public Vector3f direction;
    
    private int counter=0;
    
    public Particle(World world,Vector3f inputTranslation, Vector3f inputRotation, Vector3f inputScaling) {
		this.world = world;
		this.translation=inputTranslation;
		this.rotation=inputRotation;
		//this.scaling=inputScaling;
		ArrayList<Vertex> verticies = new ArrayList<Vertex>();
		Vertex v = new Vertex();
		v.location = new Vector3f(-1.f, 0.0f, 0.0f);
		//v.location=v.location.multiply(.000001f);
		//v.textureCoordinate = new Vector2f(0.0f, 1.0f);
		verticies.add(v);
		v = new Vertex();
		v.location = new Vector3f(0.5f, 0.866f, 0.0f);
		//v.location=v.location.multiply(.000001f);
		//v.textureCoordinate = new Vector2f(0.0f, 0.0f);
		verticies.add(v);
		v = new Vertex();
		v.location = new Vector3f(0.5f, -0.866f, 0.0f);
		//v.location=v.location.multiply(.000001f);
		//v.textureCoordinate = new Vector2f(1.0f, 0.0f);
		verticies.add(v);
		this.polygon = new Polygon(verticies);
		this.lifetime=75;
		direction=null;
    }
    
    public Particle(World world,Vector3f inputTranslation, Vector3f inputRotation, Vector3f inputScaling, Vector4f color) {
    	this.world = world;
    	this.translation=inputTranslation;
    	this.rotation=inputRotation;
    	//this.scaling=inputScaling;
    	ArrayList<Vertex> verticies = new ArrayList<Vertex>();
    	Vertex v = new Vertex();
    	v.location = new Vector3f(-1.0f, 0.0f, 0.0f);
    	//v.textureCoordinate = new Vector2f(0.0f, 1.0f);
    	v.color=color;
    	verticies.add(v);
    	v = new Vertex();
    	v.location = new Vector3f(0.5f, 0.866f, 0.0f);
    	//v.textureCoordinate = new Vector2f(0.0f, 0.0f);
    	v.color=color;
    	verticies.add(v);
    	v = new Vertex();
    	v.location = new Vector3f(0.5f, -0.866f, 0.0f);
    	//v.textureCoordinate = new Vector2f(1.0f, 0.0f);
    	v.color=color;
    	verticies.add(v);
    	this.polygon = new Polygon(verticies);
    	this.lifetime=75;
    	direction=null;
        }
    
    public void render(GLDrawable drawable, GLU glu) {
    	//this.polygon.render((GLAutoDrawable)drawable, glu);
    	//System.out.println("I'm being printed");
    	GL2 gl = ((GLAutoDrawable) drawable).getGL().getGL2();
    	gl.glPushMatrix();
    	gl.glTranslatef(translation.x, translation.y, translation.z);
    	gl.glRotatef(rotation.z, 0.0f, 0.0f, 1.0f);
    	gl.glRotatef(rotation.y, 0.0f, 1.0f, 0.0f);
    	gl.glRotatef(rotation.x, 1.0f, 0.0f, 0.0f);
    	gl.glScalef(scaling.x, scaling.y, scaling.z);
    	
    	for(Vertex v: polygon.verticies)
    	{
    		//System.out.println(v.color.x+" "+v.color.y+" "+v.color.z);
    	}
	polygon.render(((GLAutoDrawable) drawable), glu);
    	
	gl.glPopMatrix();
	    
    	lifetime--;
    }
    
    public boolean dead()
    {
    	if(lifetime>0)
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    }
    
    public void step()
    {
    	scaling=new Vector3f(world.getActiveCamera().getLocation().distanceTo(translation));
    	scaling=scaling.multiply(.002f);
    	if(counter>20000 && direction!=null)
    	{
    		translation.x+=direction.x/100;
    		translation.y+=direction.y/100;
    		translation.z+=direction.z/100;
    	}
    	counter++;
    }
}
