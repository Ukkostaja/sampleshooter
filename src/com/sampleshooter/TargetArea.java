package com.sampleshooter;


import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class TargetArea {
	Vector2 sijainti;
	Rectangle hitbox;
	
	public TargetArea(Vector2 sijainti) {
		this.sijainti = sijainti;
		this.hitbox = new Rectangle(sijainti.x, sijainti.y, Art.target[0][0].getRegionWidth(), Art.target[0][0].getRegionHeight());
			
		
	}
			
	
	public boolean check(ArrayList<Kakka> kuulat){
		for(Kakka kk : kuulat) {
			
		}
		
		return false;
	}
		
	
	
	public TargetArea(int x, int y){
		this.sijainti = new Vector2(x,y);
	}
}
