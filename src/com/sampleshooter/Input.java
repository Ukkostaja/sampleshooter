package com.sampleshooter;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class Input implements InputProcessor{

	
	GameEngine gEngine;
	
	Input(GameEngine gEngine){
	this.gEngine = gEngine;

	}
	
	Input() {
		
	}
		
	
	public void setEngine(GameEngine gEngine){
		this.gEngine = gEngine;
	}

		@Override
		public boolean keyDown(int keycode) {
			//System.out.println(keycode);
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			if(character == 'a') {
				System.out.println(gEngine.level.tempo);
			}
			if(character == 'p') {
				int handhalf = Game.GAME_HEIGHT /2;
				System.out.println("zerg "+ handhalf +" rush!!!");
				gEngine.pommit.add(new Pommi(50, handhalf));
			}
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
			if (screenY<=Game.GAME_HEIGHT/3) gEngine.confirmInput(-1);
			if(screenY>Game.GAME_HEIGHT/3 && screenY<2*Game.GAME_HEIGHT/3) gEngine.confirmInput(0);
			if(screenY>=2*Game.GAME_HEIGHT/3) gEngine.confirmInput(1);
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}

