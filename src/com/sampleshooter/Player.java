package com.sampleshooter;

public class Player {
	int ship_positio =4; //1-7
	final int maxpositions=8;
	int ship_moving_to =0;

	public void setPositio(int moving){
		this.ship_positio+= moving;
		if(ship_positio < 1) ship_positio=1;
		if(ship_positio >=maxpositions) ship_positio =maxpositions;
	}

}