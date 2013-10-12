package com.sampleshooter;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Luoti {
	Vector2 piste;
	Vector2 suunta;
	int animatemode= 0; //0 = ei animoi, 1 = looppaa kerran, 2= looppaa kokoajan.
	int frame=0;
	TextureRegion[][] spritet;
	
	public TextureRegion getSprite() {
		
		return spritet[0][0];
	}
	
	
	
	public void  update() {
		piste.add(suunta);
		if(animatemode !=0){
			
		}
		
	}
	
	Luoti(Vector2 lahtopiste, Vector2 suunta) {
		piste= lahtopiste;
		suunta=suunta;
	}

}
