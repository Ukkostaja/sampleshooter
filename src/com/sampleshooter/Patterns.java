package com.sampleshooter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Class used for shooting patterns
 * @author Petri Partanen
 *
 */
public class Patterns {
	public class Pattern {
		Vector<String> patterns = new Vector<String>();
	}
	
	/**
	 * Initializes patterns from a file
	 * @param name	path of the patterns file
	 */
	public Patterns(String name) {
		FileHandle f = Gdx.files.internal(name);
		BufferedReader bf = new BufferedReader(f.reader());
		
		int current_num = 0;
		
		try {
			while(true) {
				String line = bf.readLine();
				
				if(line == null)
					break;
				
				line = line.trim();
				
				// skip empty line and comment
				if(line.isEmpty() || line.charAt(0) == ';')
					continue;
				
				// Check if defining a new pattern
				if(line.charAt(0) == '#') {
					current_num = Integer.parseInt(line.substring(1));
					continue;
				}
				
				// Add pattern for this number if none exists already
				if(!pattern.containsKey(current_num))
					pattern.put(current_num, new Pattern());
				
				// Add new line pattern
				Pattern pat = pattern.get(current_num);
				pat.patterns.add(line);
				
				// Set pattern back
				pattern.put(current_num, pat);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public String fetch() {
		Pattern pat = pattern.get(current);
		
		// Reset counter to the beginning
		if(step >= pat.patterns.size()) {
			step = 0;
		}
		
		String patterns = pat.patterns.get(step);
		
		step++;
		
		return patterns;
	}
	
	/**
	 * Set a pattern
	 * @param num
	 */
	public void set(int num) {
		// TODO: Check if it exists
		current = num;
		step = 0;
	}
	
	private int current;
	private int step;
	private Map<Integer, Pattern> pattern = new HashMap<Integer, Pattern>();
}
