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
	 * @param lvl	Initial level to play
	 */
	public GameEngine(int lvl) {
		// Set level number
		loadLevel(lvl);

		// set input
		Gdx.input.setInputProcessor(input);
		input.setEngine(this);

		isRunning = true;

		// Lines
		int nuottialku = Game.GAME_WIDTH / 6;
		for(int i = 0; i < 5; i++) {
			notelines[i] = nuottialku + i*nuottivali;
		}
	}

	public void confirmInput(int rawinput) {
		player.setPositio(rawinput);

		if(rawinput == 0)
			kakat.add(new Kakka(rdom.nextInt(3)-1,screen.homepoint));
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

	public void spawn(Level.PatternShoot sho) {
		Kakka uusi;

		if(sho.left)
			uusi = new Kakka(1,screen.homepoint);
		else if(sho.center)
			uusi = new Kakka(1,screen.homepoint);
		else if(sho.right)
			uusi = new Kakka(1,screen.homepoint);
		else
			return;

		kakat.add(uusi);
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

		// Tempo!
		if(tempoSignal) {
			spawn(level.getPattern());

			// Check for a game ending condition
			if(level.end())
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
	public ArrayList<Pommi> pommit = new ArrayList<Pommi>();	// TODO: CHANGE TO PRIVATE!
	private int[] notelines = new int[5];

	// Level objects
	private int position = -1;		// current level tempo position

	// Tempo object
	float nextTempo = 0.0F; // time for next tempo
	boolean tempoSignal = false;

	private Random rdom = new Random();

	private final int nuottivali = 64;
}