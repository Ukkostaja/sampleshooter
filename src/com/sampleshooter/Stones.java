package com.sampleshooter;

import com.badlogic.gdx.math.Vector2;

public class Stones {
	int vari;
	Vector2 suunta;
	Vector2 sijainti;
	int death = 0;
	

	Stones(int vari, Vector2 sijainti){
		switch (vari) {
		case -1:
			suunta = new Vector2(2, -2);
			break;

		case 0:
			suunta = new Vector2(2, 0);
			break;
		case 1:
			suunta = new Vector2(2, 2);
			break;

		default:
			System.out.println("Virhe");
			break;
		}
		this.sijainti = new Vector2(sijainti);
		
	}

	void muutaSuunta(Vector2 uusiSuunta) {
		suunta = uusiSuunta;
	}

	public void update() {
		this.sijainti.add(suunta);

	}
	public void die(){
		this.death = 1;
	}
}