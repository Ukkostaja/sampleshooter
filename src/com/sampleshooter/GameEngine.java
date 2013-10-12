package com.sampleshooter;

import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public class GameEngine extends Thread {
	float ship_positio; //0-5
	int ship_moving; // -1,0 tai 1
	
	ArrayList<Kakka> kakat = new ArrayList<Kakka>();
	ArrayList<Pommi> pommit = new ArrayList<Pommi>();
			
	
	private GameScreen pelitila;
	private Input input;
	
	static final float ticktime = 10F; //millisekuntti määrä johon tasapäistetään 
	
	public GameEngine(GameScreen pelitila) {
		this.pelitila = pelitila;
		ship_positio =2 ;
		ship_moving =0;
		
	}
	
	
	//tätä ei ehkä tarvita
	public void setInput(Input input) {
		this.input = input;
	}
	
	
	//Tarkistetaan onko ajoitus oikein ja toimitaan sen mukaan.
	public void confirmInput(int rawinput) {
		System.out.println("olen hereillä");
		this.ship_moving+=rawinput;
	}
	
	

	public void tick() throws InterruptedException {
		float dtime = Gdx.graphics.getDeltaTime()*1000;
		//System.out.println(dtime);
		
		//Lasketaan kaikki tapahtuneet muutokset
		
		
		//käsketään peliruutua piirtää kaikki relevANTIT asiat
		
		pelitila.startOfDraw();
		
		pelitila.drawPelaaja(ship_positio,ship_moving);
		
		
		pelitila.endOfDraw();
		
		long sleeptime = (long) (ticktime-dtime);
		if( sleeptime > 0) {
		//	this.sleep(sleeptime);
		}
		
		
	}
}
