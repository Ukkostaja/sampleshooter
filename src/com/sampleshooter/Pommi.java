package com.sampleshooter;

import com.badlogic.gdx.math.Vector2;

public class Pommi {
	Vector2 sijainti;
	Vector2 nopeus =new Vector2(2,0);
	int tyyppi;
	
	
	Pommi(int x, int y,int tyyppi){
		sijainti = new Vector2(x*1.0f, y*1.0F);
		this.tyyppi = tyyppi;
		
	}
	
	void muutaSuunta(Vector2 uusiSuunta) {
		nopeus = uusiSuunta;
	}
	
	void update() {
		sijainti.add(nopeus);
	}
	
	
}
