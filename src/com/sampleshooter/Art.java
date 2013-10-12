package com.sampleshooter;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Art {
	public static TextureRegion[][] player;
	public static TextureRegion[][] ball;
	public static TextureRegion[][] bomb;
	
	public static void load()
	{
		// Pit‰‰ muuttaa suhteelliseksi assets directoriin androidilla. Ohje:
		// http://code.google.com/p/libgdx/wiki/FileHandling
		player = split("../sampleshooter/assets/player.png",32,32);
		ball = split("../sampleshooter/assets/ball.png",16,16);
		bomb = split("../sampleshooter/assets/bomb.png",16,16);
	}
	
	private static TextureRegion[][] split (String name, int width, int height) {
		return split(name, width, height, false);
	}
	
	private static TextureRegion[][] split (String name, int width, int height, boolean flipX) {
		Texture texture = new Texture(Gdx.files.internal(name));

		
		int xSlices = texture.getWidth() / width;
		int ySlices = texture.getHeight() / height;
		System.out.println(xSlices+" "+ySlices);
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
