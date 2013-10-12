package com.sampleshooter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;

public class GameEngine extends Thread {
	float ship_positio; //0-5
	int ship_moving; // -1,0 tai 1
	Random rdom = new Random();
	Level level = new Level();
	String levelkakat;
	long eteneminen = 0;
	int loop;
	Game game;
	
	ArrayList<Kakka> kakat = new ArrayList<Kakka>();
	ArrayList<Pommi> pommit = new ArrayList<Pommi>();
			
	private Screen screen;
	private GameScreen pelitila;
	
	private final Input input = new Input();
	static final float ticktime = 100F; //millisekuntti m‰‰r‰ johon tasap‰istet‰‰n 
	
	public GameEngine(Game game) {
		this.game = game;
		ship_positio =2 ;
		ship_moving =0;
		levelkakat = level.level.get(1);
		
		Art.load();
		Sound.load();
		Level.load();
		Gdx.input.setInputProcessor(input);
		pelitila = new GameScreen();
		setScreen(pelitila);
		
		this.input.setEngine(this);
		
	}
	/**
	 * Changes the active screen which renders.
	 * @param newScreen	new renderable screen
	 */
	private void setScreen(Screen newScreen)
	{
		if(screen != null)
			screen.removed();
		
		screen = newScreen;
		
		if(screen != null)
			screen.init(game);
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

	public void pasko(char merkki) {
		Kakka uusi;
		switch (merkki) {
		case '+':
			uusi = new Kakka(1);
			break;
		case '-':
			uusi = new Kakka(-1);
			break;
		default:
			uusi = new Kakka(0); 
			break;
		}
		kakat.add(uusi);
		
	}
	
	public void tick() throws InterruptedException {
		float dtime = Gdx.graphics.getDeltaTime()*1000;
		//System.out.println(dtime);
		loop++;
		if(loop++>=level.tempo){
			pasko(levelkakat.charAt((int)eteneminen));
			loop=0;eteneminen++;
		}
		if(eteneminen >= levelkakat.length()){
			this.GracefulExit();
		}
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


	private void GracefulExit() {
		System.exit(0);
		
	}
}
