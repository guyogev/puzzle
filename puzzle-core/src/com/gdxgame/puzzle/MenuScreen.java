package com.gdxgame.puzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuScreen extends AbstractScreen {
	
	Table layout;
	
	public MenuScreen(Puzzle puzzle) {
		game = puzzle;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		layout = new Table();
		layout.setFillParent(true);
		stage.addActor(layout);
		
		TextButton startButtom = new TextButton("Start",Assets.defultSkin);
		startButtom.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new PlayScreen(game));
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});
		
		layout.add(startButtom);
		
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.1f, .1f, .1f, .5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
