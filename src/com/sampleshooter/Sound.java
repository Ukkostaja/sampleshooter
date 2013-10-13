package com.sampleshooter;

import javax.print.attribute.standard.Chromaticity;

import com.badlogic.gdx.Gdx;

public class Sound {
	final static int nroChimes = 12; //jatkuu muista huolimatta. tiedoston viimeinen indexi +1
	final static int nroChords = 12; //pys�ytt�� toisensa tiedoston viimeinen indexi +1
	
	private static int playingChord =0;
	public static com.badlogic.gdx.audio.Sound koeaani;
	public static com.badlogic.gdx.audio.Sound pulse;
	public static com.badlogic.gdx.audio.Sound[] chimes;
	public static com.badlogic.gdx.audio.Sound[] chords;
	public static com.badlogic.gdx.audio.Sound intro;
	public static com.badlogic.gdx.audio.Sound strafe;
	public static com.badlogic.gdx.audio.Sound shoot;
	
	public static void load() 
	{
		strafe = load("../sampleshooter/assets/Drum_strafe.wav");
		shoot = load("../sampleshooter/assets/Drum_shoot.wav");
		intro = load("../sampleshooter/assets/title.wav");
		chimes = new com.badlogic.gdx.audio.Sound[nroChimes];
		chords = new com.badlogic.gdx.audio.Sound[nroChords];
		pulse = load("../sampleshooter/assets/Pulse.wav");
		koeaani= load("../sampleshooter/assets/hroot_plunk.wav"); 
		for(int i=1 ;i < nroChimes;i++) {
			chimes[nroChimes-i] = load("../sampleshooter/assets/Chime_"+i+".wav");
		}
		for(int i=1;i < nroChords;i++) {
			chords[nroChords-i] = load("../sampleshooter/assets/Chord_"+i+".wav");
		}
	}
	
	public static void playChord(int nro){
		if(playingChord != 0){
			chords[playingChord].stop();
		}
		chords[nro].play();
		
		playingChord = nro;
	}
	
	public static void playChime(int nro){
		chimes[nro].play();
	
	}
	
	private static com.badlogic.gdx.audio.Sound load (String name) {
		return Gdx.audio.newSound(Gdx.files.internal(name));
	}
}
