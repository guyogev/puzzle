package com.gdxgame.puzzle;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

/*
 * flowing MVC pattern, screens are the game View.
 * screen will extract data from the game modal and display it.
 */
public abstract class AbstractScreen implements Screen {
	// screen hold pointer to the Game object
	Puzzle game;
	public static Stage stage;

	@Override
	public abstract void render(float delta);

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public abstract void dispose();

}
