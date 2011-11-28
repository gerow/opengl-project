package test;


import glproject.*;
import sceneobjects.*;

import java.awt.AWTException;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.media.opengl.GLAutoDrawable;

import glproject.World;
import glproject.scenes.SolarSystem;

public class ParticleTest extends World {

	public ParticleTest() throws AWTException {
		super();
		this.startRender();
		this.startLogic();
	}
	
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		
		Mesh m = null;
		try {
		    m = Mesh.loadMeshFromObjFile("teapot.obj");
		} catch (IOException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
		m.scaling = new Vector3f(1, 1, 1);
		m.setColor(new Vector3f(.5f,.3f,.7f));
		Orbiter orbiter1 = new Orbiter(m);
		Mesh m2 = null;
		try {
		    m2 = Mesh.loadMeshFromObjFile("mtdew.obj");
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		m2.scaling = new Vector3f(80, 80, 80);
		m2.setColor(new Vector3f(.9f,.3f,.7f));
		Orbiter orbiter2 = new Orbiter(m2);
		orbiter1.addAffectedBy(orbiter2);
		orbiter1.setLocation(new Vector3f(100, 100, 100));
		orbiter1.setVelocity(new Vector3f(0, 9, 1));
		
		ParticleEmitter pe=new ParticleEmitter(this,orbiter1);
		
		/**
		 * THIS IS WHERE YOU ADD THE PARTICLE EMITTER
		 * Steps to add:
		 * 1. make a particle emitter for an object
		 * 2. add that particle emitter to the world class
		 */
		
		this.addParticleEmitter(pe);
		
		//mainFrame.world.addMesh(m);
		this.addSceneObject(orbiter1);
		this.addSceneObject(orbiter2);
		
		orbiter1.setRotationalVelocity(new Vector3f(3, 1, 0));
		orbiter2.setRotationalVelocity(new Vector3f(0, 1, 0));
	    }
	
	public static void main(String args[])
	{
		try {
			ParticleTest pe= new ParticleTest();
			MainFrame mainFrame = null;
			try {
			    mainFrame = new MainFrame(pe);
			} catch (IOException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			} catch (AWTException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
			mainFrame.setVisible(true);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}