package com.sampleshooter;

import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.sampleshooter.Art;
import com.badlogic.gdx.Gdx;

public class GameScreen extends Screen {
	
	int loop = 0;
	
	
	@Override
	public void render()
	{
	

		spriteBatch.begin();
		
		spriteBatch.end();
	}

	
	public void startOfDraw(){
		spriteBatch.begin();
	}
	
	public void endOfDraw() {
		spriteBatch.end();
	}
	
	public void drawPelaaja(float positio,int moving){
		draw(Art.player[0][0],200,20+(int) (40*positio));
		

	}
	
	public void drawKakat(ArrayList<Kakka> kakat){
		for(int i = 0;i<kakat.size();i++){
			draw(Art.ball[0][0],(int)kakat.get(i).sijainti.x,(int)kakat.get(i).sijainti.y);
		}
	}
	
	@Override
	public void init(Game game) {
		// TODO Auto-generated method stub
		Matrix4 projection = new Matrix4();
		projection.setToOrtho(0, 320, 240, 0, -1, 1);

		spriteBatch = new SpriteBatch(100);
		spriteBatch.setProjectionMatrix(projection);
	}

	@Override
	public void removed() {
		// TODO Auto-generated method stub
		
	}
}
