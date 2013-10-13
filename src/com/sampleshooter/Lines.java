package com.sampleshooter;

public class Lines {
	public Lines() {
		int nuottialku = Game.GAME_WIDTH / 6;
		
		for(int i = 0; i < 5; i++)
			notelines[i] = nuottialku + i*spacing;
	}
	
	public int[] getLines() {
		return notelines;
	}
	
	private int[] notelines = new int[5];
	private final int spacing = 64;
}
