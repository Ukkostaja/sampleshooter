package com.sampleshooter;

import com.badlogic.gdx.math.Vector2;

public class Kakka {
	int vari;
	Vector2 suunta;
	Vector2 sijainti;
	
	Kakka(int vari){
		switch (vari) {
		case -1:
			suunta = new Vector2(2, -1);
			break;
			
		case 0:
			suunta = new Vector2(2, 0);
			break;
		case 1:
			suunta = new Vector2(2, 1);
			break;

		default:
			System.out.println("Virhe");
			break;
		}
		this.sijainti = new Vector2(220, 100);
	}
}
