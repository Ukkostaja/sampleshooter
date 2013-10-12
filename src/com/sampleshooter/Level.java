package com.sampleshooter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Level {
	static ArrayList<String> level;
	static int tempo;
	
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

}
