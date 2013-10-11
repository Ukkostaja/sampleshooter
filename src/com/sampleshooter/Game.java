package com.sampleshooter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.sampleshooter.GameScreen;

class Game implements ApplicationListener {

	@Override
	public void create() {
		// Create everything
		//Art.load();
		//Sound.Load();
		running = true;
		//setScreen(new GameScreen());
	}

	@Override
	public void resize(int width, int height) {
		// !!!
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
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

	private boolean running = false;
}
