package com.sampleshooter;

import com.badlogic.gdx.Gdx;

/**
 * @author Petri Partanen
*/
public class MenuEngine extends Engine {
	public MenuEngine() {
		// set input
		Gdx.input.setInputProcessor(iInput);
		
		isRunning = true;
	}
	
	protected void draw() {
		iScreen.render();
	}
	
	public Screen getScreen() {
		return iScreen;
	}
	
	protected void step(float delta) {
		if(Gdx.input.isTouched()) {
			isRunning = false;
		}
	}
	
	private IntroScreen iScreen = new IntroScreen();
	
	private IntroInput iInput = new IntroInput();
}