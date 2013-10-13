package com.sampleshooter;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;


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
		 	luodit.add(new Luoti(new Vector2(screen.home_line - this.bullet_start, screen.lanes[player.ship_positio]), new Vector2(-8, 0)));
	
	
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
		screen.drawLuodit(luodit);
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
		
		nextTempo = level.getTempoDelay();
		
		System.out.println("First tempo: " + nextTempo);
	}
	
	public void spawn(Level.PatternShoot sho) {
		Kakka uusi;
		
		// FIXME!! KAKKA SPAWN DOES NOT WORK FOR SOME REASON. CHECK IF THEY ARE LOADED FROM LEVEL-FILE CORRECTLY ! :/
		
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
	
	public void spawn(Vector<Level.Note> notes) {
		
		for (int i=0;i<notes.size();i++) {
			
			if (notes.get(i) != null) {
				
				Level.Note note = notes.get(i);
				
				int noteType = (int)(note.note - 'A'); // 1; // FIXME!! Change this to take value from the note-type
				int notePos = screen.lanes[note.position];
				
				pommit.add(new Pommi(50, notePos, noteType));
				
			}
			
		}
		
	}
	
	int checkCollision(Pommi pommi, ArrayList<Luoti> luodit) {
		
		if (pommi.death > 0) return -1;
		
		// check luoti collisions
		for (int i=0;i<luodit.size();i++) if (luodit.get(i).death <= 0) {
			
			float xDist = pommi.sijainti.x - luodit.get(i).piste.x;
			float yDist = pommi.sijainti.y - luodit.get(i).piste.y;
			
			if (Math.abs(xDist) < horBulletColRange) {
				
				if (Math.abs(yDist) < verBulletColRange) {
					
					pommi.collide();
					luodit.get(i).collide();
					
				}
				
			}
		
		}
		
		// check player collision
		float xDist = pommi.sijainti.x - screen.home_line;
		float yDist = pommi.sijainti.y - screen.lanes[player.ship_positio];
		
		if (Math.abs(xDist) < horPlayerColRange) {
			
			if (Math.abs(yDist) < verPlayerColRange) {
				
				player.collide();
				pommi.collide();
				
			}
		}
		
		
		return 0;
	}	
	
	/**
	 * Updates game logic
	 */
	protected void step(float delta) {
		
		// Deduce time for the next tempo update
		nextTempo -= delta;
		
		// System.out.println("Delta: " + delta + ", Tempo: " + nextTempo);
		
		if(nextTempo <= 0.0F) {
			// Set to next tempo step
			tempoSignal = true;
			nextTempo += level.getTempoDelay();
			level.step();
			
			// System.out.println("Step!");
		}
		
		// Tempo!
		if(tempoSignal) {
			
			spawn(level.getPattern());
			
			spawn(level.getNotes());
			
			// Check for a game ending condition
			if(level.end())
				isRunning = false;
		}
		
		for (Kakka kk : kakat) {
			kk.update();
		}
		
		for (Pommi pom : pommit) {
			pom.update();
			
			checkCollision(pom, luodit);
		}
		
		for (Luoti luoti : luodit) {
			luoti.update();
		}		
		
		tempoSignal = false; // Set next step as no tempo
	}
	
	// Main elements
	public GameScreen screen = new GameScreen();	// TODO: CHANGE TO PRIVATE!!
	private Input input = new Input();
	private int level_number = 1;
	private int bullet_start = 30;
	private int verBulletColRange = 10;
	private int horBulletColRange = 48;
	private int verPlayerColRange = 45;
	private int horPlayerColRange = 64;
	
	// Game objects
	private Level level;
	private Player player = new Player();
	private ArrayList<Kakka> kakat = new ArrayList<Kakka>();
	public ArrayList<Pommi> pommit = new ArrayList<Pommi>();	// TODO: CHANGE TO PRIVATE!
	private int[] notelines = new int[5];
	private ArrayList<Luoti> luodit = new ArrayList<Luoti>();
	
	// Level objects
	private int position = -1;		// current level tempo position
	
	// Tempo object
	float nextTempo = 0.0F; // time for next tempo
	boolean tempoSignal = false;
	
	private Random rdom = new Random();
	
	private final int nuottivali = 64;
}
