package com.sampleshooter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
/*
public class Level {

	
	Level(){
		load();
		processSettings();
	}
	
	public static void load()
	{
		level = new ArrayList<String>();
		
		// Pit‰‰ muuttaa suhteelliseksi assets directoriin androidilla. Ohje:
		// http://code.google.com/p/libgdx/wiki/FileHandling
		read("../sampleshooter/assets/level1.txt");
	
	}
	
	private static void processSettings() {
		String sttempo = "tempo=";
		int talku = level.get(0).indexOf(sttempo);
		int tloppu = level.get(0).indexOf(".", talku);
		String temposubstring =level.get(0).substring(talku+sttempo.length(), tloppu);
		System.out.println(temposubstring);
		tempo = Integer.valueOf(temposubstring);
	}
	
	private static void read(String name){
		FileHandle f = Gdx.files.internal(name);
		BufferedReader bf = new  BufferedReader(f.reader());
		String temp;
		try {
			while( (temp = bf.readLine()) != null){
				level.add(temp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static ArrayList<String> level;
	static int tempo;
}*/

public class Level {
	public class TempoStep {
		public int tempo; // bpm
		public char note; // Note
		public int patter; // Pattern
		public int position; // Position
	}
	
	public Level(String levelFile) {
		
		read(levelFile);
	}
	
	/**
	 * Check if level has ended
	 * @return
	 */
	public boolean end() {
		return true;
	}
	
	/**
	 * Get's current tempo
	 * @return
	 */
	public int getTempo() {
		return 0;
	}
	
	/**
	 * Get's current delay between tempo steps
	 * @return
	 */
	public float getTempoDelay() {
		return 0.0F;
	}
	
	public int getTempoNumber() {
		
	}
	
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
				String line = bf.readLine().trim();
				
				// Ran out of lines
				if(line == null) {
					tempos.add(newStep);
					break;
				}
				
				// Empty line means the end of tempo step
				if(line.isEmpty()) {
					// Add this to the list
					tempos.add(newStep);
					
					// Create new tempo step
					newStep = new TempoStep();
				}
				
				// Skip comment
				if(line.charAt(0) == ';')
					continue;
				
				// Check for alpha == new note
				if(Character.isLetter(line.charAt(0))) { // new note
					newStep.note = line.charAt(0);
				}
				else if(Character.isDigit(line.charAt(0))) { // new tempo
					current_tempo = Integer.parseInt(line);
				}
				else if(line.charAt(0) == '#') { // new pattern
					
				}
				
				newStep.pattern = current_pattern;
				newStep.tempo = current_tempo;
			}
			
			bf.close();
		} catch(IOException e) {
			e.printStackTrace();
			tempos.clear();
		}
	}
	
	public void step() {
		current_position++;
	}
	
	private int current_position = 0;
	private ArrayList<TempoStep> tempos;
	
	private final int DEFAULT_PATTERN = 1;
	private final int DEFAULT_TEMPO = 60;
}
