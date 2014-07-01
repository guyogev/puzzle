package com.gdxgame.puzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
		game.playScreen = new PlayScreen(game);

		String text = "How to play:\n"
				+ "this is a match memory game.\n"
				+ "Hit 'show pattern' when ready. A pattern will appear for a short time, memories colored tiles possision\n."
				+ "Recreate the pattern. Use bottom button to toggle colors.\n\n"
				+ "Select difficulty:";
		Label instructions = new Label(text, Assets.defaultSkin);	
		instructions.setWrap(true);
		
		TextButton easy = new TextButton("easy (one color)",Assets.defaultSkin);
		easy.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Assets.colorLimit = 1;
				game.setScreen(game.playScreen);
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});

		TextButton medium = new TextButton("medium (two colors)", Assets.defaultSkin);
		medium.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Assets.colorLimit = 2;
				game.setScreen(game.playScreen);
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});

		TextButton hard = new TextButton("hard (three colors)", Assets.defaultSkin);
		hard.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Assets.colorLimit = 3;
				game.setScreen(game.playScreen);
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});
		
		layout.add(instructions).left().width(8 * Assets.w_unit).row();
		layout.add(easy).width(6 * Assets.w_unit).height(Assets.h_unit)
				.pad(Assets.h_unit / 4).row();
		layout.add(medium).width(6 * Assets.w_unit).height(Assets.h_unit)
				.pad(Assets.h_unit / 4).row();
		layout.add(hard).width(6 * Assets.w_unit).height(Assets.h_unit)
				.pad(Assets.h_unit / 4).row();

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.702f, .702f, .702f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}