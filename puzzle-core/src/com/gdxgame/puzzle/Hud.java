package com.gdxgame.puzzle;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/** Game statistics display */
public class Hud extends Table {

	private static TextButton tilesLeftDisp, striksDisp, scoreLable,
			levelNumberLabel;
	private static int score, tilesLeft, strikesLeft = 5;

	public Hud() {
		super();
		score = 0;
		scoreLable = new TextButton("Score\n" + score, Assets.defultSkin);
		scoreLable.setTouchable(Touchable.disabled);
		add(scoreLable).left().width(4 * Assets.w_unit);

		levelNumberLabel = new TextButton("Level\n", Assets.defultSkin);
		levelNumberLabel.setTouchable(Touchable.disabled);
		add(levelNumberLabel).left().width(4 * Assets.w_unit)
				.padLeft(2 * Assets.w_unit).row();

		tilesLeftDisp = new TextButton("" + tilesLeft, Assets.defultSkin);
		tilesLeftDisp.setTouchable(Touchable.disabled);
		tilesLeftDisp.setColor(Assets.hiddenColor_green);
		add(tilesLeftDisp).left().padTop(Assets.w_unit / 4)
				.padLeft(Assets.w_unit).width(2 * Assets.w_unit);

		striksDisp = new TextButton("" + strikesLeft, Assets.defultSkin);
		striksDisp.setTouchable(Touchable.disabled);
		striksDisp.setColor(Assets.hiddColor_red);
		add(striksDisp).left().padTop(Assets.w_unit / 4)
				.padLeft(3 * Assets.w_unit).width(2 * Assets.w_unit);

	}

	public static void setTilesLeft(int tilesLeft) {
		Hud.tilesLeft = tilesLeft;
		tilesLeftDisp.setText("" + tilesLeft);
	}

	/** update statistics according to player input */
	public static void updateHud(boolean correct) {
		if (correct) {
			score += 100 * Level.getLevelNumber();
			tilesLeft--;
			scoreLable.setText("Score\n" + score);
			tilesLeftDisp.setText("" + tilesLeft);
		} else {
			strikesLeft--;
			striksDisp.setText("" + strikesLeft);
		}
	}

	public static int getTilesLeft() {
		return tilesLeft;
	}

	public static void nextLevelSetup(int coloredTiles, int levelNumber) {
		setTilesLeft(Level.getColoredTiles());
		levelNumberLabel.setText("Level\n" + levelNumber);

	}

}
