package sceneobjects;

import glproject.Mesh;
import glproject.Particle;
import glproject.Polygon;
import glproject.Vector3f;
import glproject.Vector4f;
import glproject.Vertex;
import glproject.World;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLDrawable;
import javax.media.opengl.glu.GLU;


public class ParticleEmitter extends DynamicSceneObject {
	private Vector3f location = new Vector3f(0, 0, 0);
    private int imageNumber = 0;
    private World world;
    private float scale;
    private CopyOnWriteArrayList<Particle> particles;
    private float colorRange=0.4f;
    private float colorR=.7f;
    private float colorG=.1f;
    private float colorB=.1f;
    private final int PARTICLEFREQUENCY=10000;
    private DynamicSceneObject object;
    private final int DISTANCERANGE=20;
    
    public ParticleEmitter(World inputworld, DynamicSceneObject inputObject)
    {
    	super(inputObject.mesh);
    	this.world=inputworld;
    	this.object=inputObject;
    	particles= new CopyOnWriteArrayList<Particle>();
    }
    
    public void render(GLDrawable drawable, GLU glu) {
    	for(Particle particle : particles)
	   	{
	   		particle.render(drawable, glu);
	   		if(particle.dead())
	   		{
    			particles.remove(particle);	    		
    		}
	    }
    }

	@Override
	public void step() {
		// TODO Auto-generated method stub
		this.setRotation(new Vector3f(-object.getRotation().x,-object.getRotation().y,-object.getRotation().z));
    	for(Polygon p : object.mesh.allPolys)
    	{
    		for(Vertex v : p.verticies)
    		{
    			if(Math.random()*PARTICLEFREQUENCY>9500)
    			{
    				//System.out.println("Adding Particles");
    				Vector3f location=new Vector3f(object.getLocation().x+v.location.x+(int)(Math.random()*DISTANCERANGE),object.getLocation().y+v.location.y+(int)(Math.random()*DISTANCERANGE),object.getLocation().z+v.location.z+(int)(Math.random()*DISTANCERANGE));
    				Vector4f color=new Vector4f(colorR+(float)(Math.random()*colorRange),colorG+(float)(Math.random()*colorRange),colorB+(float)(Math.random()*colorRange),1.0f);
    				particles.add(new Particle(world,location,this.getRotation(),this.getScale(),color));
    			}
    		}
    	}
	}
}
