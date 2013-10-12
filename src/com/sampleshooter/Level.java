package com.sampleshooter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Level {
	static ArrayList<String> level;
	
	
	public static void load()
	{
		level = new ArrayList<String>();
		
		// Pit‰‰ muuttaa suhteelliseksi assets directoriin androidilla. Ohje:
		// http://code.google.com/p/libgdx/wiki/FileHandling
		read("../sampleshooter/assets/level1.txt");
	
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

}
