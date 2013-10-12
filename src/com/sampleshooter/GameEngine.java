package com.sampleshooter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;

public class GameEngine extends Thread {
	float ship_positio; //0-5
	int ship_moving; // -1,0 tai 1
	Random rdom = new Random();
	
	ArrayList<Kakka> kakat = new ArrayList<Kakka>();
	ArrayList<Pommi> pommit = new ArrayList<Pommi>();
			
	
	private GameScreen pelitila;
	private Input input;
	
	static final float ticktime = 100F; //millisekuntti m‰‰r‰ johon tasap‰istet‰‰n 
	
	public GameEngine(GameScreen pelitila) {
		this.pelitila = pelitila;
		ship_positio =2 ;
		ship_moving =0;
		
	}
	
	
	//t‰t‰ ei ehk‰ tarvita
	public void setInput(Input input) {
		this.input = input;
	}
	
	
	//Tarkistetaan onko ajoitus oikein ja toimitaan sen mukaan.
	public void confirmInput(int rawinput) {
		this.setPositio(rawinput);
		if(rawinput ==0){
			kakat.add(new Kakka(rdom.nextInt(3)-1));
		}
		//this.ship_moving=rawinput;
	}
	
	private void setPositio(int moving){
		ship_positio+= moving;
		if(ship_positio < 0) ship_positio=0;
		if(ship_positio >4) ship_positio =4;
	}

	public void tick() throws InterruptedException {
		float dtime = Gdx.graphics.getDeltaTime()*1000;
		//System.out.println(dtime);
		
		//Lasketaan kaikki tapahtuneet muutokset
		Kakka kakka;
		for(int i = 0; i< kakat.size(); i++){
			kakka = kakat.get(i);
			kakka.sijainti.x += kakka.suunta.x;
			kakka.sijainti.y += kakka.suunta.y;
		}
		
		//k‰sket‰‰n peliruutua piirt‰‰ kaikki relevANTIT asiat
		
		pelitila.startOfDraw();
		
		pelitila.drawPelaaja(ship_positio,ship_moving);
		pelitila.drawKakat(kakat);
		
		pelitila.endOfDraw();
		
		long sleeptime = (long) (ticktime-dtime);
		if( sleeptime > 0) {
			//this.sleep(sleeptime);
		}
		
		
	}
}
