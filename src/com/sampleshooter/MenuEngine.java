package com.sampleshooter;

import com.badlogic.gdx.Gdx;

/**
 * @author Petri Partanen
*/
public class MenuEngine extends Engine {
	private CreditScreen cScreen = new CreditScreen();
	private IntroInput iInput = new IntroInput();
	Screen myScreen;
	public MenuEngine(int status) {
		isRunning = true;
	
		switch (status) {
		case 0:
			myScreen = new IntroScreen();
			
			break;
		case 3:
			myScreen = new CreditScreen();
			
			break;
		default:
			isRunning = false;
			break;
		}

		
		// set input
		Gdx.input.setInputProcessor(iInput);
		
		
	}
	
	protected void draw() {
		myScreen.render();
	}
	
	public Screen getScreen() {
		return myScreen;
	}
	
	protected void step(float delta) {
		if(Gdx.input.isTouched()) {
			isRunning = false;
		}
	}
	

}