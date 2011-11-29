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
    private float colorG=.6f;
    private float colorB=.1f;
    private final int PARTICLEFREQUENCY=100000;
    private DynamicSceneObject object;
    private final int DISTANCERANGE=20;
    private boolean exploding;
    private boolean done=false;
    
    public ParticleEmitter(World inputworld, DynamicSceneObject inputObject, boolean state)
    {
    	super(inputObject.mesh);
    	this.world=inputworld;
    	this.object=inputObject;
    	particles= new CopyOnWriteArrayList<Particle>();
    	exploding=state;
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
    			if(Math.random()*PARTICLEFREQUENCY>97500 && !exploding && !done)
    			{
    				//System.out.println("Adding Particles");
    				Vector3f location=new Vector3f(object.getLocation().x+v.location.x+(int)(Math.random()*DISTANCERANGE),object.getLocation().y+v.location.y+(int)(Math.random()*DISTANCERANGE),object.getLocation().z+v.location.z+(int)(Math.random()*DISTANCERANGE));
    				Vector4f color=new Vector4f(colorR+(float)(Math.random()*colorRange),colorG+(float)(Math.random()*colorRange),colorB+(float)(Math.random()*colorRange),1.0f);
    				particles.add(new Particle(world,location,this.getRotation(),this.getScale(),color));
    			}
    			else if(exploding)
    			{
    				for(int i=0; i<2;i++)
    				{
    					Vector4f color=new Vector4f(0.75f+(float)(Math.random()),0.2f*i+(float)(Math.random()),0.0f,1.0f);
    					Vector3f location=new Vector3f(object.getLocation().x+v.location.x+(int)(Math.random()*DISTANCERANGE),object.getLocation().y+v.location.y+(int)(Math.random()*DISTANCERANGE),object.getLocation().z+v.location.z+(int)(Math.random()*DISTANCERANGE));
    					particles.add(new Particle(world,location,this.getRotation(),this.getScale(),color));
	    				//particles.get(particles.size()-1).direction= new Vector3f((float)Math.random(),(float)Math.random(),(float)Math.random());
    					particles.get(particles.size()-1).direction=v.normal;
    					particles.get(particles.size()-1).lifetime=30;
    				}
    			}
    		}
    		for(Particle part:particles)
    		{
    			part.step();
    		}
    	}
		if(exploding)
		{
			exploding=false;
			done=true;
		}
	}
}
