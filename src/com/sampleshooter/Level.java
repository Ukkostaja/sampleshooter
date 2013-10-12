package com.sampleshooter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import com.sampleshooter.Patterns;

/**
 * Level reader and controller
 * @author Petri Partanen
 */
public class Level {
	public class Note {
		public char note;
		public int position;
	}
	
	public class PatternShoot {
		public boolean left;
		public boolean center;
		public boolean right;
	}
	
	public class TempoStep {
		public int pattern;	// pattern number
		public int tempo;	// bpm
		Vector<Note> notes = new Vector<Note>();
	}
	
	public Level(String path, String levelFile) {
		read(path + levelFile);
		patterns = new Patterns(path + "patterns.txt");
	}
	
	/**
	 * Check if level has ended
	 * @return
	 */
	public boolean end() {
		if(current_position >= tempos.size())
			return true;
		
		return false;
	}
	
	/**
	 * Get current notes
	 * @return
	 */
	public Vector<Note> getNotes() {
		return tempos.get(current_position).notes;
	}
	
	public PatternShoot getPattern() {
		String str = patterns.getPattern();
		
		PatternShoot shoot = new PatternShoot();
		
		shoot.left = (str.charAt(0) != '0');
		shoot.center = (str.charAt(1) != '0');
		shoot.right = (str.charAt(2) != '0');
		
		return shoot;
	}
	
	/**
	 * Get's current tempo
	 * @return
	 */
	public int getTempo() {
		return tempos.get(current_position).tempo;
	}
	
	/**
	 * Get's current delay between tempo steps
	 * @return
	 */
	public float getTempoDelay() {
		return 60.0f / getTempo() * 1000.0f; 
	}
	
	/**
	 * This parses the level textfile
	 * @param name	Level filepath
	 */
	private void read(String name) {
		tempos.clear();
		
		FileHandle f = Gdx.files.internal(name);
		BufferedReader bf = new BufferedReader(f.reader());
		
		// Try to read the level
		try {
			// Create initial first tempo
			TempoStep newStep = new TempoStep();
			
			// Set initial tempo
			int current_pattern = DEFAULT_PATTERN;
			int current_tempo = DEFAULT_TEMPO;
			
			while(true) {
				// Read line
				String line = bf.readLine();
				
				// Ran out of lines
				if(line == null) {
					tempos.add(newStep);
					break;
				}
				
				line = line.trim();
				
				// Empty line means the end of tempo step
				// Also . ends the step
				if(line.isEmpty() || line.charAt(0) == '.') {
					// Add this to the list
					tempos.add(newStep);
					
					// Create new tempo step
					newStep = new TempoStep();
					
					continue;
				}
				
				// Skip comment line
				if(line.charAt(0) == ';')
					continue;
				
				// Check for alpha == new note
				if(Character.isLetter(line.charAt(0))) { // new note
					char note = line.charAt(0);
					line = line.substring(1).trim(); // erase note char
					int position = Integer.parseInt(line);
					
					Note newnote = new Note();
					newnote.note = note;
					newnote.position = position;
					newStep.notes.add(newnote);
				}
				else if(Character.isDigit(line.charAt(0))) { // new tempo
					current_tempo = Integer.parseInt(line);
				}
				else if(line.charAt(0) == '#') { // new pattern
					current_pattern = Integer.parseInt(line.substring(1));
				}
				
				// Set the basic info on this tempo
				newStep.pattern = current_pattern;
				newStep.tempo = current_tempo;
			}
			
			bf.close();
		} catch(IOException e) {
			e.printStackTrace();
			tempos.clear();
		}
	}
	
	/**
	 * Step the level by one
	 */
	public void step() {
		current_position++;
		patterns.step();
	}
	
	private int current_position = 0;
	private Patterns patterns;
	private ArrayList<TempoStep> tempos = new ArrayList<TempoStep>();
	
	private final int DEFAULT_PATTERN = 1;
	private final int DEFAULT_TEMPO = 60;
}
