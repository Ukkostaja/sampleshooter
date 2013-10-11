package com.sampleshooter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.sampleshooter.Screen;
import com.sampleshooter.GameScreen;

class Game implements ApplicationListener {

	@Override
	public void create() {
		// Create everything
		Art.load();
		Sound.load();
		running = true;
		setScreen(new GameScreen());
	}

	@Override
	public void resize(int width, int height) {
		// !!!
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		screen.render();
	}

	@Override
	public void pause() {
		running = false;
	}

	@Override
	public void resume() {
		running = true;
	}

	@Override
	public void dispose() {
		// !!!
	}
	
	/**
	 * Changes the active screen which renders.
	 * @param newScreen	new renderable screen
	 */
	private void setScreen(Screen newScreen)
	{
		if(screen != null)
			screen.removed();
		
		screen = newScreen;
		
		if(screen != null)
			screen.init(this);
	}

	public static final int GAME_HEIGHT = 240;
	public static final int GAME_WIDTH = 320;
	
	private boolean running = false;
	
	// The main Screen, this can be anything like
	// GameScreen or MenuScreen
	private Screen screen;
}
