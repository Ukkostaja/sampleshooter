package com.sampleshooter;

import com.badlogic.gdx.math.Vector2;

public class Pommi {
	Vector2 sijainti;
	Vector2 nopeus =new Vector2(2,0);

	Pommi(int x, int y){
		sijainti = new Vector2(x*1.0f, y*1.0F);


	}

	void muutaSuunta(Vector2 uusiSuunta) {
		nopeus = uusiSuunta;
	}

	void update() {
		sijainti.add(nopeus);
	}


}