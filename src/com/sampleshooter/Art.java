package com.sampleshooter;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Application.ApplicationType;

import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Art {
	
	

		
	static String path = "../sampleshooter/assets/";
	public static TextureRegion[][] player;
	public static TextureRegion[][] ball;
	public static TextureRegion[][] bomb;
	public static TextureRegion[][] line;
	public static TextureRegion[][] note_full;
	public static ArrayList<TextureRegion[][]> target = new ArrayList<TextureRegion[][]>();
	public static TextureRegion intro;
	public static TextureRegion[][] luoti;
	public static TextureRegion[][] note_perus;
	public static TextureRegion[][] kuula;
	public static TextureRegion credits;

	public static void load()
	{
		if(Gdx.app.getType() == ApplicationType.Android) {
			path = "";
		}
		// Pit‰‰ muuttaa suhteelliseksi assets directoriin androidilla. Ohje:
		// http://code.google.com/p/libgdx/wiki/FileHandling
		player = split(path+"player.png",64,64);
		ball = split(path+"ball.png",16,16);
		bomb = split(path+"bomb.png",16,16);
		line = split(path+"nuottiviiva.png",4,512);
		note_full = split(path +"Nuotti_koko.png",64,64);
		note_perus = split(path+"Nuotti_perus.png",128,64);
		
		luoti = split(path+"luoti.png",64,32);
		intro = load(path+"StartupScreen_crop.png",1024,512);
		credits = load(path+"Credits.png",1024,512);
		kuula = split(path+"kuula.png",32,32);
		for(int i =1; i < 4;i++){
			target.add(split(path+"YmpyraSulkeutuu_"+i+".png",64,64));
		}
		

	}
	
	public static TextureRegion load (String name, int width, int height) {
		Texture texture = new Texture(Gdx.files.internal(name));
		TextureRegion region = new TextureRegion(texture, 0, 0, width, height);
		region.flip(false, true);
		return region;
	}

	private static TextureRegion[][] split (String name, int width, int height) {
		return split(name, width, height, false);
	}

	private static TextureRegion[][] split (String name, int width, int height, boolean flipX) {
		Texture texture = new Texture(Gdx.files.internal(name));


		int xSlices = texture.getWidth() / width;
		int ySlices = texture.getHeight() / height;
		//System.out.println(xSlices+" "+ySlices);
		TextureRegion[][] res = new TextureRegion[xSlices][ySlices];
		for (int x = 0; x < xSlices; x++) {
			for (int y = 0; y < ySlices; y++) {
				res[x][y] = new TextureRegion(texture, x * width, y * height, width, height);
				res[x][y].flip(flipX, true);
			}
		}
		return res;
	}

}
