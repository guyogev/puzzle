package com.gdxgame.puzzle;

import com.badlogic.gdx.Game;

public class Puzzle extends Game {

	PlayScreen playScreen;
	MenuScreen menuScreen;

	@Override
	public void create() {
		Assets.load();
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		menuScreen.dispose();
		playScreen.dispose();
		Assets.dispose();
	}
}
