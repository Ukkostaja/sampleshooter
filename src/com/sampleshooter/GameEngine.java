package com.sampleshooter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;

/**
 * Main game engine
 * @author Petri Partanen
 */
public class GameEngine extends Engine {
	/**
	 * Creates game engine
	 * @param lvl	Initial level to play
	 */
	public GameEngine(int lvl) {
		// Load game stuff
		Art.load();
		Sound.load();
		
		// Set level number
		loadLevel(lvl);
		
		// set input
		Gdx.input.setInputProcessor(input);
		
		isRunning = true;
	}
	
	/**
	 * Draws the screen
	 */
	protected void draw() {
		screen.startOfDraw();
		//------------------------------
		screen.drawPelaaja(player);
		screen.drawKakat(kakat);
		screen.drawPommit(pommit);
		//------------------------------
		screen.endOfDraw();
	}
	
	/**
	 * 
	 */
	protected void loadLevel(int lvl)
	{
		level = new Level("../sampleshooter/assets/", "level" + lvl + ".txt");
		level_number = lvl;
	}
	
	public void pasko(char merkki) {
		Kakka uusi;
		switch (merkki) {
		case '+':
			uusi = new Kakka(1,screen.homepoint);
			break;
		case '-':
			uusi = new Kakka(-1,screen.homepoint);
			break;
		default:
			uusi = new Kakka(0,screen.homepoint); 
			break;
		}
		kakat.add(uusi);

	}
	
	
	
	/**
	 * Resizes the game world
	 */
	public void resize(int width, int height) {
		
	}
	
	/**
	 * Updates game logic
	 */
	protected void step(float delta) {
		// Deduce time for the next tempo update
		nextTempo -= delta;
		
		if(nextTempo <= 0.0F) {
			// Set to next tempo step
			tempoSignal = true;
			nextTempo += level.getTempoDelay();
			level.step();
		}
		
		// Calculate current tempo number
		int tempoNumber = position / 4;
		
		// Tempo!
		if(tempoSignal) {
			pasko(levelkakat.charAt((int)position));
			
			// Check for game ending condition
			if(position >= levelkakat.length())
				isRunning = false;
		}
		
		for (Kakka kk : kakat) {
			kk.sijainti.x += kk.suunta.x;
			kk.sijainti.y += kk.suunta.y;
		}
		
		for (Pommi pom : pommit) {
			pom.sijainti.x += pom.speed;
		}
		
		tempoSignal = false; // Set next step as no tempo
	}
	
	// Main elements
	private GameScreen screen = new GameScreen();
	private Input input = new Input();
	private int level_number = 1;
	
	// Game objects
	private Level level;
	private Player player = new Player();
	private ArrayList<Kakka> kakat = new ArrayList<Kakka>();
	private ArrayList<Pommi> pommit = new ArrayList<Pommi>();
	
	// Level objects
	private String levelkakat;
	private int position = -1;		// current level tempo position
	
	// Tempo object
	float nextTempo = 0.0F; // time for next tempo
	boolean tempoSignal = false;
}
