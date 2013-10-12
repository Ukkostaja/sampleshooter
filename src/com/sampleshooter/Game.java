package com.sampleshooter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.sampleshooter.Screen;
import com.sampleshooter.GameScreen;


class Game implements ApplicationListener {
	// Set screen resolution for initialization
	public static final int GAME_HEIGHT = 720;
	public static final int GAME_WIDTH = (int)(GAME_HEIGHT * 16/9); // 720 for HD
	

	private boolean running = false;
	// The main Screen, this can be anything like
	// GameScreen or MenuScreen
	
	long a=0;


	private final boolean started = false;
	private GameEngine gEngine;
	
	@Override
	public void create() {
		// Create everything

		this.gEngine = new GameEngine(this);
		gEngine.start();
	}

	@Override
	public void resize(int width, int height) {
		// !!!
	}

	@Override
	public void render() {
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//System.out.println(a++ +" ");
		try {
			gEngine.tick();
		} catch (InterruptedException e) {
			System.out.println("Sitä on nyt jo myöhäistä surra");
			e.printStackTrace();
		}
		
		

	}

	@Override
	public void pause() {
		running = false;
		gEngine.stop();
	}

	@Override
	public void resume() {
		running = true;
		gEngine.resume();
	}

	@Override
	public void dispose() {
		// !!!
	}
	



	


}
