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
		screen.drawLines(notelines);
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
			kk.update();
		}
		
		for (Pommi pom : pommit) {
			pom.update();
		}
		
		tempoSignal = false; // Set next step as no tempo
	}
	
	// Main elements
	public GameScreen screen = new GameScreen();	// TODO: CHANGE TO PRIVATE!!
	private Input input = new Input();
	private int level_number = 1;
	
	// Game objects
	private Level level;
	private Player player = new Player();
	private ArrayList<Kakka> kakat = new ArrayList<Kakka>();
	private ArrayList<Pommi> pommit = new ArrayList<Pommi>();
	private int[] notelines = new int[5];
	
	// Level objects
	private String levelkakat;
	private int position = -1;		// current level tempo position
	
	// Tempo object
	float nextTempo = 0.0F; // time for next tempo
	boolean tempoSignal = false;
}
