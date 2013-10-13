package com.sampleshooter;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class TargetArea {
	Vector2 sijainti;
	Rectangle hitbox;
	
	public TargetArea(Vector2 sijainti) {
		this.sijainti = sijainti;
		this.hitbox = new Rectangle(sijainti.x, sijainti.y, Art.target[0][0].getRegionWidth(), Art.target[0][0].getRegionHeight());
			
		
	}
			
	
	private boolean inHitbox(Vector2 piste) {
		return com.badlogic.gdx.math.Intersector.isPointInTriangle(piste, sijainti,
				(new Vector2(sijainti)).add(new Vector2(0, 64)),(new Vector2(sijainti)).add(new Vector2(64, 0)));
	}
	
	public boolean check(ArrayList<Kakka> kuulat){
		for(Kakka kk : kuulat) {
			if(this.inHitbox(kk.sijainti)) {
				kk.die();
				return true;
			}
		}
		return false;
	}
		
	
	
	public TargetArea(int x, int y){
		this.sijainti = new Vector2(x,y);
	}
}
