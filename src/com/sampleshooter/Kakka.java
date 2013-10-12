package com.sampleshooter;

import com.badlogic.gdx.math.Vector2;

public class Kakka {
	int vari;
	Vector2 suunta;
	
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
			break;
		}
	}
}
