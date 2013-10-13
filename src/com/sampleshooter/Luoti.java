package com.sampleshooter;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Luoti {
	
	Vector2 piste;
	Vector2 suunta;
	int animatemode= 0; //0 = ei animoi, 1 = looppaa kerran, 2= looppaa kokoajan.
	int frame=0;
	TextureRegion[][] spritet;
	int death = 0;

	public TextureRegion getSprite() {

		return spritet[0][0];
	}



	public void  update() {
		piste.add(suunta);
		if(animatemode !=0){
			
		}
		
		// death effects - delete object or what?
		if (death > 0) {
			
		}		

	}

	Luoti(Vector2 lahtopiste, Vector2 suunta) {
		piste= lahtopiste;
		this.suunta=suunta;
		death = 0;
	}
	
	void collide() {
	
		// effects go here
		die();
		
	}
	
	void die() {
		
		death = 1;
		
	}
	
	
	
}