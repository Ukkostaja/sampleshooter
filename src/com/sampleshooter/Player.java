package com.sampleshooter;

public class Player {
	int ship_positio =3; //1-7 aloituspositio  (maxpositions /2 aika hyvä arvo)
	static final int maxpositions=6; //pelaajalla mahdollisia positioita tämä -1
	int ship_moving_to =0;

	public void setPositio(int moving){
		this.ship_positio+= moving;
		if(ship_positio < 1) ship_positio=1;
		if(ship_positio >=maxpositions) ship_positio =maxpositions;
	}

}