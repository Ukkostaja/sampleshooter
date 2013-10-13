package com.sampleshooter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.sampleshooter.Screen;

/**
 * Main Game class
 * @author Petri Partanen
 */
class Game implements ApplicationListener {	
	/**
	 * 
	 */
	@Override
	public void create() {
		// Load General game stuff
		Art.load();
		Sound.load();

		// Create the game engine
		// We would create "MenuEngine" here if necessary
		engine = new MenuEngine();
		
		// Get the engine's drawing screen and set it as current
		setScreen(engine.getScreen());
		
		status = 0;

		this.running = true;
	}
	
	/**
	 * 
	 */
	@Override
	public void resize(int width, int height) {
		// Tell engine to resize the screen
		//engine.resize(width, height);
	}

	/**
	 * Called when game should render
	 */
	@Override
	public void render() {
		if(this.running) {
			// Clear the screen
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

			// Update engine
			engine.tick();

			// TODO: Show GAME END SCREEN if game is not running
			// this is done by simply switching the engine and screen
			if(!engine.isRunning()) {
				if(status == 0) {
					status = 1;
					engine = new GameEngine(1);
					setScreen(engine.getScreen());
				}
				else
					System.exit(0);
			}
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

		// Initialize if successful
		if(screen != null)
			screen.init(this);
	}

	// MAIN running flag
	private boolean running = false;

	// Main game engine
	private Engine engine;

	// Screen on which to draw
	private Screen screen;
	
	// Game status
	// 0 = Intro
	// 1 = Level menu
	// 2 = Game
	// 3 = Game Over
	private int status = 0;

	// Sets screen resolution for initialization
	public static final int GAME_HEIGHT = 720;
	public static final int GAME_WIDTH = (int)(GAME_HEIGHT * 16/9); // 720 for HD
}