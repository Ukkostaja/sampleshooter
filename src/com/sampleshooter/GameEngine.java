package com.sampleshooter;

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
	 * @param lvl	Initial level number to play
	 */
	public GameEngine(int lvl) {
		// Load level
		loadLevel(lvl);
		
		// set input
		Gdx.input.setInputProcessor(input);
		input.setEngine(this);
		
		isRunning = true;
	}
	
	/**
	 * 
	 * @param rawinput
	 */
	public void confirmInput(int rawinput) {
		player.setPositio(rawinput);
		
		if(rawinput == 0)
			balls.add(new Kakka(rdom.nextInt(3)-1,screen.homepoint));
	}
	
	/**
	 * Draws the screen
	 */
	protected void draw() {
		screen.startOfDraw();
		//------------------------------
		screen.drawPelaaja(player);
		screen.drawKakat(balls);
		screen.drawPommit(pommit);
		screen.drawLines(lines.getLines());
		//------------------------------
		screen.endOfDraw();
	}
	
	/**
	 * Returns the screen where the engine draws
	 */
	public Screen getScreen() {
		return screen;
	}
	
	/**
	 * 
	 */
	protected void loadLevel(int lvl)
	{
		level = new Level("../sampleshooter/assets/", "level" + lvl + ".txt");
		level_number = lvl;
	}
	
	/**
	 * 
	 * @param sho
	 */
	private void spawn(Level.PatternShoot sho) {
		if(sho.left)
			balls.add(new Kakka(1,screen.homepoint));
	
		if(sho.center)
			balls.add(new Kakka(0,screen.homepoint));

		if(sho.right)
			balls.add(new Kakka(-1,screen.homepoint));
	}
	
	/**
	 * Quick hack for note spawning
	 */
	private void spawnNote(Level.Note note) {		
		// TODO: FIX!
		if(note.note == 'A')
			pommit.add(new Pommi(50, screen.lanes[note.position],0));
		else if(note.note == 'B')
			pommit.add(new Pommi(50, screen.lanes[note.position],1));
		else if(note.note == 'C')
			pommit.add(new Pommi(50, screen.lanes[note.position],0));
		else if(note.note == 'D')
			pommit.add(new Pommi(50, screen.lanes[note.position],1));
		else if(note.note == 'E')
			pommit.add(new Pommi(50, screen.lanes[note.position],0));
	}
	
	/**
	 * Updates game logic
	 */
	protected void step(float delta) {
		// Deduce time for the next tempo update
		nextTempo -= delta;
		
		if(nextTempo <= 0.0f) {
			// Set to next tempo step
			tempoSignal = true;
			nextTempo += level.getTempoDelay();
			level.step();
		}
		
		// Tempo!
		if(tempoSignal) {
			// Spawn under
			spawn(level.getPattern());
			
			// Spawn notes
			for(Level.Note note : level.getNotes()) {
				spawnNote(note);
			}
			
			// Play sound
			
			// Check for a game ending condition
			if(level.end())
				isRunning = false;
		}
		
		for (Kakka kk : balls) {
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
	private Lines lines = new Lines();
	private Player player = new Player();
	private ArrayList<Kakka> balls = new ArrayList<Kakka>();
	public ArrayList<Pommi> pommit = new ArrayList<Pommi>();	// TODO: CHANGE TO PRIVATE!
	
	// Level objects
	private int position = -1;		// current level tempo position
	
	// Tempo object
	float nextTempo = 0.0f; // time for next tempo
	boolean tempoSignal = false;
	
	private Random rdom = new Random();
}
