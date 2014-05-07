package com.gdxgame.puzzle;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

/**Main game screen*/
public class PlayScreen extends AbstractScreen {

	private static Level level;
	private static Board board;
	private static TopHud topHud;
	private static BottomHud bottomHud;
	// Arranges hud & buttons
	private Table screenLayout;
	// Holds only the board object.
	private Stage boardStage;
	private float FXdelta = .3f;
	private MenuWindow menuWindow;
	private InputMultiplexer inputMultiplexer;

	@Override
	public void show() {
		Gdx.input.setInputProcessor(inputMultiplexer);
		Assets.music.setLooping(true);
		if (Assets.musicSoundOn)
			Assets.music.play();
		level = Level.init();
		restartGame();
	}

	@Override
	public void hide() {
		Assets.music.stop();
	}

	public PlayScreen(Puzzle puzzle) {
		game = puzzle;
		stage = new Stage();
		boardStage = new Stage(stage.getViewport(), stage.getSpriteBatch());
		initInputProcessing();
		initScreenLayout();
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
		inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(stage);
		inputMultiplexer.addProcessor(boardStage);
		// Gdx.input.setInputProcessor(inputMultiplexer);
	}

	/** create stage layout & fill with UI objects */
	private void initScreenLayout() {
		screenLayout = new Table();
		screenLayout.setFillParent(true);
		screenLayout.align(Align.top);

		topHud = TopHud.getTopHud();
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
		level = level.next();
		board = Board.getBoard(level.getBoardHeight(), level.getBoardWidth(),
				level.getColoredTiles());
		boardStage.addActor(board.getBgTable());
		boardStage.addActor(board.getCellsTable());
		topHud.nextLevelSetup(level.getColoredTiles(), Level.getLevelNumber());
		bottomHud.showPatternButton();
	}

	/** Main game loop */
	private void gameLoop() {
		// show menu
		if (topHud.menuButtonPressed()) {
			topHud.resetMenuButton();
			if (!menuWindow.isTouchable()){
				FX.showActor(menuWindow, FXdelta);
				FX.hideActor(board.getCellsTable());
			}
			else{
				FX.hideActor(menuWindow, FXdelta);
				FX.showActor(board.getCellsTable());
			}
		}
		// level finished
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
		// game over
		if (topHud.getStrikesLeft() <= 0 && !menuWindow.isTouchable()){
			FX.showActor(menuWindow, FXdelta);
			FX.hideActor(board.getCellsTable());
		}
		if (menuWindow.isTouchable()) {
			int choice = menuWindow.getchoice();
			switch (choice) {
			case 1:
				menuWindow.resetChoice();
				restartLevel();
				FX.showActor(board.getCellsTable());
				break;
			case 2:
				menuWindow.resetChoice();
				restartGame();
				FX.showActor(board.getCellsTable());
				break;
			case 3:
				menuWindow.resetChoice();
				backTMainMenu();
				FX.showActor(board.getCellsTable());
				break;
			default:
				break;
			}
		}

	}

	private void backTMainMenu() {
		game.setScreen(game.menuScreen);
		// dispose();
	}

	private void restartGame() {
		level.reset();
		topHud.resetHud();
		nextLevelSetup();

	}

	private void restartLevel() {
		board.resetBoard();
		bottomHud.showPatternButton();
		topHud.resetStrikesLeft(1);
		topHud.nextLevelSetup(level.getColoredTiles(), Level.getLevelNumber());
	}

	public static void checkTile(Tile t) {
		// correct
		if (t.hiddenColor.equals(bottomHud.getChosenColor())) {
			TopHud.updateHud(true);
			t.setTouchable(Touchable.disabled);
			t.addAction(Actions.color(Assets.hiddenColor_correct, .3f));
			if (Assets.fxSoundOn)
				Assets.correct.play(Assets.fxVolume);
		}
		// chose wrong color
		else if (t.hiddenColor != t.visibleColor) {
			TopHud.updateHud(false);
			t.addAction(sequence(Actions.touchable(Touchable.disabled),
					Actions.color(Assets.hiddColor_wrong, .7f),
					Actions.color(t.visibleColor, .3f),
					Actions.touchable(Touchable.enabled)));
			if (Assets.fxSoundOn)
				Assets.wrong.play(Assets.fxVolume);
		}
		// wrong
		else {
			TopHud.updateHud(false);
			t.addAction(Actions.color(Assets.hiddColor_wrong, .3f));
			if (Assets.fxSoundOn)
				Assets.wrong.play(Assets.fxVolume);
		}

	}
}
