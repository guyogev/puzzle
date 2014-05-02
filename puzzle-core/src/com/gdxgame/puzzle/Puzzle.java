package com.gdxgame.puzzle;

import com.badlogic.gdx.Game;

public class Puzzle extends Game {

	@Override
	public void create() {
		Assets.load();
		setScreen(new MenuScreen(this));
		
	}
	
	public void render() {
		super.render(); 
	}
}
