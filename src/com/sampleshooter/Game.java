package com.sampleshooter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.sampleshooter.Screen;

/**
 * @author Petri Partanen
 *
 */
class Game implements ApplicationListener {	
	@Override
	public void create() {
		// Create the game engine
		// We would create "MenuEngine" here if necessary
		engine = new GameEngine(1);
		
		// Get the engine's drawing screen and set it as current
		setScreen(engine.getScreen());
		
		this.running = true;
	}

	
	@Override
	public void resize(int width, int height) {
		// Tell engine to resize the screen
		//engine.resize(width, height);
	}

	@Override
	public void render() {
		if(this.running) {
			// Clear the screen
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
				
			// Update engine
			engine.tick();
			
			// TODO: Show GAME END SCREEN if game is not running
			// this is done by simply switching the engine and screen
			if(!engine.isRunning())
				System.exit(0);
		}
	}

	@Override
	public void pause() {
		// Pause engine
		this.running = false;
	}

	@Override
	public void resume() {
		// Run engine
		this.running = true;
	}

	@Override
	public void dispose() {
		// TODO: Destroy
	}
	
	private void setScreen(Screen newScreen) {
		if(screen != null)
			screen.removed();
		
		// Get the engine's screen
		screen = newScreen;
		
		if(screen != null)
			screen.init(this);
	}
	
	// MAIN running flag
	private boolean running = false;
	
	// Main game engine
	private Engine engine;
	
	// Screen on which to draw
	private Screen screen;
	
	// Sets screen resolution for initialization
	public static final int GAME_WIDTH = 720; // 720 for HD
	public static final int GAME_HEIGHT = (int)(GAME_WIDTH * 16/9);
}
