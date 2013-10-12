package com.sampleshooter;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Screen {
	public abstract void render();
	public abstract void init(Game game);
	public abstract void removed();
	
	
	public SpriteBatch spriteBatch;
	
	public void draw (TextureRegion region, int x, int y) {
		int width = region.getRegionWidth();
		if (width < 0) width = -width;
		spriteBatch.draw(region, x, y, width, region.getRegionHeight());
	}
}
