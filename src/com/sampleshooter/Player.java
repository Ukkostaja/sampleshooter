package com.sampleshooter;

public class Player {
	int ship_positio =3; //1-7 aloituspositio  (maxpositions /2 aika hyvä arvo)
	static final int maxpositions=5; //pelaajalla mahdollisia positioita tämä -1
	static final int minpositions=1;
	int ship_moving_to =0;
	
	public void setPositio(int moving){
		this.ship_positio+= moving;
		if(ship_positio < minpositions) ship_positio=minpositions;
		if(ship_positio >=maxpositions) ship_positio =maxpositions;
	}
	
	public void ammu() {
		
		
	}
	
	public int collide() {
		
		// take hit
		// play sound
		// lose life
		// if not lives, end game, return -1
		
		return 0;
	}
	
}
