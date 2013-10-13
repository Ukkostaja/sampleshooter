package com.sampleshooter;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

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
		int targetHeight = Game.GAME_HEIGHT /3;
		int targetFix = Game.GAME_HEIGHT /7;
		for(int i = 0;i< 3;i++) {
			maalit.add(new TargetArea((Game.GAME_WIDTH - screen.home_line )*2 /3 + screen.home_line,targetFix+i* targetHeight));
		}
	}
	
	/**
	 * 
	 * @param rawinput
	 */
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
		screen.drawLines(lines.getLines());
		screen.drawMaalit(maalit);
		screen.drawPelaaja(player);
		screen.drawKakat(balls);
		screen.drawPommit(pommit);
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
		
		//nextTempo = level.getTempoDelay(); // TODO: ?
	}
	
	/*
	public void spawn(Vector<Level.Note> notes) {
		
		for (int i=0;i<notes.size();i++) {
			
			if (notes.get(i) != null) {
				
				Level.Note note = notes.get(i);
				
				int noteType = (int)(note.note - 'A'); // 1; // FIXME!! Change this to take value from the note-type
				int notePos = screen.lanes[note.position];
				
				pommit.add(new Pommi(50, notePos, noteType));
				
			}
			
		}
		
>>>>>>> refs/remotes/origin/sec
	}*/

	void checkPress(int key) {
		switch (key) {
		case 20: // alas
			luodit.add(new Luoti(new Vector2(screen.home_line - this.bullet_start, screen.lanes[player.ship_positio]), new Vector2(-8, 0)));
			break;
		case 21: //vasen
			player.setPositio(1);
			break;
		case 22: //oikea
			player.setPositio(-1);
			break;
		default:
			break;
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
	private void spawnNotes(Vector<Level.Note> notes) {		
		// TODO: FIX!
		for(Level.Note note : notes) {
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
			
			spawnNotes(level.getNotes());
			
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
	
	// TODO: 
	private int bullet_start = 30;
	private int verBulletColRange = 10;
	private int horBulletColRange = 48;
	private int verPlayerColRange = 45;
	private int horPlayerColRange = 64;
	
	// Game objects
	private Level level;
	private Lines lines = new Lines();
	private Player player = new Player();
	private ArrayList<Kakka> balls = new ArrayList<Kakka>();
	public ArrayList<Pommi> pommit = new ArrayList<Pommi>();	// TODO: CHANGE TO PRIVATE!
	private ArrayList<Luoti> luodit = new ArrayList<Luoti>();
	private ArrayList<TargetArea> maalit = new ArrayList<TargetArea>();
	
	// Level objects
	private int position = -1;		// current level tempo position
	
	// Tempo object
	float nextTempo = 0.0f; // time for next tempo
	boolean tempoSignal = false;
	
	private Random rdom = new Random();
}
