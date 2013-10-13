package com.sampleshooter;

import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.sampleshooter.Art;
import com.badlogic.gdx.Gdx;

public class GameScreen extends Screen {
	int home_line = Game.GAME_WIDTH* 3 / 4;
	public final Vector2 homepoint = new Vector2(home_line,Game.GAME_HEIGHT/2);
	int lineheight = Game.GAME_HEIGHT / (Player.maxpositions+2);
	int[] lanes = new int[Player.maxpositions+2];

	@Override
	public void render()
	{


		spriteBatch.begin();

		spriteBatch.end();
	}


	public void startOfDraw(){
		spriteBatch.begin();
	}

	public void endOfDraw() {
		spriteBatch.end();
	}

	public void drawPelaaja(Player pelaaja){
		

		draw(Art.player[0][0], this.home_line, lanes[pelaaja.ship_positio]);


	}

	public void drawPommit(ArrayList<Pommi> pommit) {
		for(int i = 0;i<pommit.size();i++) if (pommit.get(i).death <= 0){
			Pommi pommi = pommit.get(i);
			
			float seizurePosX = 0.0f;
			if (pommi.rushCounter > 0) {
				seizurePosX = (float)Math.random() * 16.0f - 8.0f;
			}
			
			switch (pommi.tyyppi) {
			case 0:	
				draw(Art.note_full[0][0],(int)pommi.sijainti.x + (int)seizurePosX,(int)pommi.sijainti.y);
				break;
			case 1:
				draw(Art.note_perus[0][0],(int)pommi.sijainti.x - 64 + (int)seizurePosX,(int)pommi.sijainti.y);
			default:
				draw(Art.ball[0][0],(int)pommi.sijainti.x + (int)seizurePosX,(int)pommi.sijainti.y);
				break;
			}
			;
		}
	}

	public void drawLines(int[] viivat) {
		for(int i = 0; i< viivat.length;i++){
			draw(Art.line[0][0],viivat[i],Game.GAME_HEIGHT / 7);
		}
	}

	public void drawKakat(ArrayList<Kakka> kakat){
		for(int i = 0;i<kakat.size();i++) {
			draw(Art.kuula[0][0],(int)kakat.get(i).sijainti.x,(int)kakat.get(i).sijainti.y);
		}
	}
	
	public void drawLuodit(ArrayList<Luoti> luodit){
		for(int i = 0;i<luodit.size();i++) if (luodit.get(i).death <= 0){
			draw(Art.kuula[0][0],(int)luodit.get(i).piste.x,(int)luodit.get(i).piste.y + 16);
		}
	}	

	@Override
	public void init(Game game) {
		// TODO Auto-generated method stub
		Matrix4 projection = new Matrix4();
		projection.setToOrtho(0, Game.GAME_WIDTH, Game.GAME_HEIGHT, 0, -1, 1);

		spriteBatch = new SpriteBatch(100);
		spriteBatch.setProjectionMatrix(projection);
		for(int i=1;i<Player.maxpositions+2;i++) {
			lanes[i] = lineheight*i;
		}
	}

	@Override
	public void removed() {
		// TODO Auto-generated method stub

	}
}
