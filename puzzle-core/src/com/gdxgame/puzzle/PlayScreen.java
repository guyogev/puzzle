package com.gdxgame.puzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class PlayScreen extends AbstractScreen {

	private Level level;
	private Board board;
	private TopHud topHud;
	private BottomHud bottomHud;
	// Arranges hud & buttons
	private Table screenLayout;
	// Holds only the board object.
	private Stage boardStage;
	private float FXdelta = .3f;
	private MenuWindow menuWindow;

	public PlayScreen(Puzzle puzzle) {
		game = puzzle;
		stage = new Stage();
		boardStage = new Stage(stage.getViewport(), stage.getSpriteBatch());
		initInputProcessing();
		initScreenLayout();

		Assets.music.setLooping(true);
		Assets.music.play();

		// begin first level
		nextLevelSetup();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.1f, .1f, .1f, .5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameLoop();
		boardStage.act(Gdx.graphics.getDeltaTime());
		boardStage.draw();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		Table.drawDebug(stage);
	}

	@Override
	public void dispose() {
		boardStage.dispose();
		stage.dispose();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		boardStage.getViewport().update(width, height, true);
	}

	/** create & initialize input processors */
	private void initInputProcessing() {
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(stage);
		inputMultiplexer.addProcessor(boardStage);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	/** create stage layout & fill with UI objects */
	private void initScreenLayout() {
		screenLayout = new Table();
		screenLayout.setFillParent(true);
		screenLayout.align(Align.top);

		topHud = TopHud.getHud();
		screenLayout.add(topHud).height(Assets.hudHeigth)
				.width(Assets.screenWidth).top().row();

		menuWindow = new MenuWindow();
		screenLayout.add(menuWindow).height(Assets.board_Bg_Heigth)
				.width(Assets.screenWidth).row();
		FX.hideActor(menuWindow, 0);

		bottomHud = BottomHud.getBottomHud();
		screenLayout.add(bottomHud).height(Assets.bottomTableHeigth)
				.width(Assets.screenWidth).pad(Assets.h_unit / 4).row();

		stage.addActor(screenLayout);
	}

	/** set the board with new level */
	private void nextLevelSetup() {
		boardStage.clear();
		level = Level.next();
		board = Board.getBoard(level.getBoardHeight(), level.getBoardWidth(),
				level.getColoredTiles());
		boardStage.addActor(board.getBgTable());
		boardStage.addActor(board.getCellsTable());
		topHud.nextLevelSetup(level.getColoredTiles(), Level.getLevelNumber());
		bottomHud.showPatternButton();
	}

	/** Main game loop */
	private void gameLoop() {
		//level finished
		if (topHud.getTilesLeft() == 0 && !bottomHud.nextLevelButtonIsVisible())
			bottomHud.showNextLevelButton();
		switch (bottomHud.getchoice()) {
		case 1:
			board.showTiles();
			bottomHud.resetButtonPressed();
			break;
		case 2:
			nextLevelSetup();
			bottomHud.resetButtonPressed();
		default:
			break;
		}
		//game over
		if (topHud.getStrikesLeft() <= 0) {
			if (!menuWindow.isTouchable())
				FX.showActor(menuWindow, FXdelta);
			int choice = menuWindow.getchoice();
			switch (choice) {
			case 1:
				restartLevel();
				break;
			case 2:
				restartGame();
			case 3:
				backTMainMenu();
			default:
				break;
			}
		}
	}

	private void backTMainMenu() {
		game.setScreen(new MenuScreen(game));
		// dispose();
	}

	private void restartGame() {
		level.reset();
		topHud.resetHud();
		nextLevelSetup();
		menuWindow.resetChoice();
	}

	private void restartLevel() {
		board.reset();
		bottomHud.showPatternButton();
		topHud.resetStrikesLeft(1);
		topHud.nextLevelSetup(level.getColoredTiles(), Level.getLevelNumber());
		menuWindow.resetChoice();
	}

}
