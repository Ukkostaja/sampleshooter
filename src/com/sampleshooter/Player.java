package com.sampleshooter;

public class Player {
	int ship_positio =2;
	int ship_moving =0;
	
	public void setPositio(int moving){
		this.ship_positio+= moving;
		if(ship_positio < 0) ship_positio=0;
		if(ship_positio >4) ship_positio =4;
	}
	
}
