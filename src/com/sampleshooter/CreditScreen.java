package com.sampleshooter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

public class CreditScreen extends Screen {
	CreditScreen() {
		spriteBatch = new SpriteBatch();
		
		Matrix4 projection = new Matrix4();
		projection.setToOrtho(0, Game.GAME_WIDTH, Game.GAME_HEIGHT, 0, 1, -1);

		spriteBatch = new SpriteBatch(1);
		spriteBatch.setProjectionMatrix(projection);
	}
	
	@Override
	public void render()
	{
		spriteBatch.begin();
		spriteBatch.draw(Art.credits, 100, 90);
		spriteBatch.end();
	}
	
	@Override
	public void init(Game game) {
		Sound.intro.play();
		
	}

	@Override
	public void removed() {
		Sound.intro.stop();
	}
}
