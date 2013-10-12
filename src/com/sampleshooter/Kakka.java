package com.sampleshooter;

import com.badlogic.gdx.math.Vector2;

public class Kakka {
	int vari;
	Vector2 suunta;
	Vector2 sijainti;
	
	Kakka(int vari, Vector2 sijainti){
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
		this.sijainti = sijainti;
		Sound.koeaani.play();
	}
}
