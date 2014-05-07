package com.gdxgame.puzzle;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

/**
 * Singleton pattern.<br>
 * Game statistics display
 */
public class TopHud extends Table {
	private static TopHud topHud = new TopHud();
	private static TextButton tilesLeftDisp, striksDisp, scoreLable,topScoreLable,
			levelNumberLabel;
	private ImageButton menuButton;
	private static int score, tilesLeft, strikesLeft;

	public static TopHud getTopHud() {
		if (topHud == null)
			topHud = new TopHud();
		return topHud;
	}

	/** update fields & display */
	public void nextLevelSetup(int tilesLeft, int levelNumber) {
		setTilesLeft(tilesLeft);
		levelNumberLabel.setText("Level " + levelNumber);
	}

	/** update statistics according to player input */
	public static void updateHud(boolean correct) {
		if (correct) {
			setScore(Assets.colorLimit * Assets.colorLimit * strikesLeft
					* Level.getLevelNumber());
			setTilesLeft(tilesLeft - 1);
		} else {
			setStrikesLeft(strikesLeft - 1);
		}
	}

	/** initialize score, strikesLeft & update display */
	public void resetHud() {
		setScore(-score);
		setStrikesLeft(5);
	}

	/** set strikes left to max(1,strikesLeft) */
	public void resetStrikesLeft(int i) {
		setStrikesLeft(Math.max(1, strikesLeft));
	}

	public int getTilesLeft() {
		return tilesLeft;
	}

	public int getStrikesLeft() {
		return strikesLeft;
	}

	private TopHud() {
		super();
		align(Align.top);
		score = 0;
		strikesLeft = 5;

		//
		topScoreLable = new TextButton("Best "+Assets.highScore + score, Assets.defaultSkin);
		topScoreLable.setTouchable(Touchable.disabled);
		add(topScoreLable).width(4 * Assets.w_unit).padRight(Assets.w_unit / 2);
		//menuButton
		menuButton = new ImageButton(Assets.menuIcon);
		menuButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});
		add(menuButton).expandX().right().colspan(9).row();

		// scoreLable
		scoreLable = new TextButton("Score " + score, Assets.defaultSkin);
		scoreLable.setTouchable(Touchable.disabled);
		add(scoreLable).width(4 * Assets.w_unit).padRight(Assets.w_unit / 2);
		// levelNumberLabel
		levelNumberLabel = new TextButton("Level ", Assets.defaultSkin);
		levelNumberLabel.setTouchable(Touchable.disabled);
		add(levelNumberLabel).padLeft(Assets.w_unit / 2)
				.width(4 * Assets.w_unit).row();
		// tilesLeftDisp
		tilesLeftDisp = new TextButton("" + tilesLeft, Assets.defaultSkin);
		tilesLeftDisp.setTouchable(Touchable.disabled);
		tilesLeftDisp.setColor(Assets.hiddenColor_correct);
		add(tilesLeftDisp).padTop(Assets.w_unit / 4).width(2 * Assets.w_unit);
		// striksDisp
		striksDisp = new TextButton("" + strikesLeft, Assets.defaultSkin);
		striksDisp.setTouchable(Touchable.disabled);
		striksDisp.setColor(Assets.hiddColor_wrong);
		add(striksDisp).padTop(Assets.w_unit / 4).width(2 * Assets.w_unit);
	}

	/** Set tilesLeft & update display */
	private static void setTilesLeft(int tilesLeft) {
		TopHud.tilesLeft = tilesLeft;
		tilesLeftDisp.setText("" + tilesLeft);
	}

	/** Set strikesLeft & update display */
	private static void setStrikesLeft(int strikes) {
		strikesLeft = strikes;
		striksDisp.setText("" + strikesLeft);
	}

	/** Set score & update display */
	private static void setScore(int i) {
		score += i;
		scoreLable.setText("Score " + score);
		Assets.highScore = Math.max(score, Assets.highScore);
		topScoreLable.setText("Best " + Assets.highScore);
	}

	public boolean menuButtonPressed() {
		return menuButton.isChecked();
	}

	public void resetMenuButton() {
		menuButton.setChecked(false);
	}

}
