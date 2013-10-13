package com.sampleshooter;

import com.badlogic.gdx.math.Vector2;

public class Pommi {
	Vector2 sijainti;
	Vector2 nopeus =new Vector2(32,0); // 2,0
	static final int rushPosX = 440;
	
	int tyyppi;
	int death;
	int moveTick = 0;
	int moveSpeed = 24; // smaller number is faster
	int rushCounter = -1;


	Pommi(int x, int y,int tyyppi){
		
		// dirty override! >:D
		x = 72;
		
		sijainti = new Vector2(x*1.0f, y*1.0F);
		this.tyyppi = tyyppi;
		death = 0;

	}

	void muutaSuunta(Vector2 uusiSuunta) {
		nopeus = uusiSuunta;
	}

	void update() {
		
		moveTick++;
		
		if (moveTick >= moveSpeed) {
		
			moveTick -= moveSpeed;
			sijainti.add(nopeus);
			
		}
		
		if (sijainti.x >= rushPosX && rushCounter == -1) {
			
			rushCounter = 110;
		}
		else if (rushCounter > 0) {
			
			moveTick = 0; // freeze movement
			
			rushCounter--;
			if (rushCounter <= 0) {
				
				moveSpeed = 1;
				
			}
			
		}
		// die at end of the screen?
		
		// death effects - delete object or what?
		if (death > 0) {
			
		}
		
	}
	
	void collide() {
		
		// effects go here
		
		playHitSound();
		
		die();
		
	}
	
	void die() {
		
		death = 1;
		
	}
	
	void playHitSound() {
		
		// play hit sound based on the position
		
		int altitude = ((int)this.sijainti.x - 50) / 32;
		altitude -= 2;
		
		if (altitude < 0) {
			altitude = 0;
		}
		
		if (altitude > 10) {
			altitude = 10;
		}
		
		// System.out.println("Tone: " + altitude);
		
		
	}


}