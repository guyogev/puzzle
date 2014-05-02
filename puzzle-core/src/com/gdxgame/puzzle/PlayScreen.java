package com.gdxgame.puzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class PlayScreen extends AbstractScreen {
	Level level;
	Board board;
	Hud hud;
	// Arranges hud & buttons
	private Table screenLayout;
	// Holds only the board object.
	private Stage boardStage;
	private TextButton showButton, nextLevelButton;

	public PlayScreen(Puzzle puzzle) {
		game = puzzle;
		stage = new Stage();
		boardStage = new Stage();
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(stage);
		inputMultiplexer.addProcessor(boardStage);
		Gdx.input.setInputProcessor(inputMultiplexer);

		screenLayout = new Table();
		screenLayout.setFillParent(true);

		Assets.music.setLooping(true);
		// Assets.music.play();

		hud = new Hud();
		screenLayout.add(hud).expand().top().left().row();

		buttonsSetup();

		nextLevelSetup();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.1f, .1f, .1f, .5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameLoop();
		stage.act(Gdx.graphics.getDeltaTime());
		boardStage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		boardStage.draw();

	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	/** reset the board with new level */
	private void nextLevelSetup() {
		boardStage.clear();
		level = Level.getLevel();
		board = Board.getBoard(Level.getBoardHeight(), Level.getBoardWidth(),
				Level.getColoredTiles());
		boardStage.addActor(board.getBgTable());
		boardStage.addActor(board.getCellsTable());
		Hud.nextLevelSetup(Level.getColoredTiles(), Level.getLevelNumber());
		showButton(showButton);
		hideButton(nextLevelButton);

	}
	
	/** create UI buttons*/
	private void buttonsSetup() {
		showButton = new TextButton("Show", Assets.defultSkin);
		showButton.setSize(2 * Assets.w_unit, Assets.w_unit);
		showButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				hideButton(showButton);
				board.showTiles();
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});

		nextLevelButton = new TextButton("Next Level", Assets.defultSkin);
		nextLevelButton.setSize(2 * Assets.w_unit, Assets.w_unit);
		nextLevelButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				hideButton(nextLevelButton);
				nextLevelSetup();
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});
		screenLayout.add(showButton).left().padLeft(Assets.w_unit / 4)
				.width(3 * Assets.w_unit).padBottom(1.5f * Assets.w_unit)
				.height(1.5f * Assets.w_unit);
		screenLayout.add(nextLevelButton).left().padRight(Assets.w_unit / 4)
				.padBottom(1.5f * Assets.w_unit).width(3 * Assets.w_unit)
				.height(1.5f * Assets.w_unit);
		stage.addActor(screenLayout);

	}
	
	/** Button fade out effect*/
	private void hideButton(Button b) {
		b.setTouchable(Touchable.disabled);
		b.addAction(Actions.fadeOut(.3f));
	}

	/** Button fade in effect*/
	private void showButton(Button b) {
		b.setTouchable(Touchable.enabled);
		b.addAction(Actions.fadeIn(.3f));
	}

	/** Main game loop*/
	private void gameLoop() {
		if (Hud.getTilesLeft() == 0) {
			showButton(nextLevelButton);
		}
	}

}
