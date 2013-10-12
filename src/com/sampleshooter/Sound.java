package com.sampleshooter;

import com.badlogic.gdx.Gdx;

public class Sound {
	public static com.badlogic.gdx.audio.Sound koeaani;
	
	public static void load() 
	{
		koeaani= load("../sampleshooter/assets/hroot_plunk.wav"); 
	}
	
	private static com.badlogic.gdx.audio.Sound load (String name) {
		return Gdx.audio.newSound(Gdx.files.internal(name));
	}
}
