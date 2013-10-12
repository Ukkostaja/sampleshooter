package com.sampleshooter;

import com.badlogic.gdx.Gdx;

/**
 * Base class for all engines
 * @author Petri Partanen
 */
public abstract class Engine {
	/**
	 * Drawing method.
	 */
	protected abstract void draw();
	
	/**
	 * Returns the screen on which this engine draws
	 * @return Drawing Screen
	 */
	public Screen getScreen() {
		return screen;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isRunning() {
		return isRunning;
	}
	
	/**
	 * Resizes screen
	 * @param width
	 * @param height
	 */
	//public abstract void resize(int width, int height);
	
	/**
	 * Steps the engine for updating physics
	 * @param delta		Time which has passed
	 */
	protected abstract void step(float delta);
	
	public void tick() {
		// Step the engine
		step(Gdx.graphics.getDeltaTime()*1000);
		
		// Draw the screen
		draw();
	}
	
	// Flag for if the engine has stopped or is running
	protected boolean isRunning = false;
	
	// Screen on which to draw
	private Screen screen;
}
