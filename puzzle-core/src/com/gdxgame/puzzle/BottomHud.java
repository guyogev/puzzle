package com.gdxgame.puzzle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

/**
 * Singleton pattern. <br>
 * bottom input buttons.
 */
public class BottomHud extends Table {

	private static BottomHud bottomHud = new BottomHud();

	private enum HudChoice {
		undecided, showPattern, unlockNextLevel,
	}

	private HudChoice buttonPressedCode;
	private TextButton showPatternButton, nextLevelButton;
	private Button colorButton;
	private int colorIndex;

	private BottomHud() {
		super();// .debug();
		align(Align.top);
		buttonPressedCode = HudChoice.undecided;
		colorIndex = 0;
		// showPatternButton
		showPatternButton = new TextButton("Show Pattern", Assets.defaultSkin);
		showPatternButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				FX.hideActor(showPatternButton);
				buttonPressedCode = HudChoice.showPattern;
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});
		// nextLevelButton
		nextLevelButton = new TextButton("Next Level", Assets.defaultSkin);
		nextLevelButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				FX.hideActor(nextLevelButton);
				buttonPressedCode = HudChoice.unlockNextLevel;
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});
		// colorButton
		colorButton = new Button(Assets.defaultSkin);
		colorButton.setColor(Assets.hiddenColors.get(colorIndex));
		colorButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				colorIndex = (colorIndex + 1) % Assets.colorLimit;
				colorButton.setColor(Assets.hiddenColors.get(colorIndex));
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});

		// Arrange layout
		FX.hideActor(nextLevelButton, 0);
		add(showPatternButton).width(3 * Assets.w_unit)
				.height(1.5f * Assets.h_unit).padRight(Assets.w_unit / 2);

		add(colorButton).width(1.5f * Assets.w_unit).height(
				1.5f * Assets.h_unit);

		add(nextLevelButton).width(3 * Assets.w_unit)
				.height(1.5f * Assets.h_unit).padLeft(Assets.w_unit / 2);

	}

	public static BottomHud getBottomHud() {
		if (bottomHud == null)
			bottomHud = new BottomHud();
		return bottomHud;
	}

	/**
	 * 0 == no button was pressed<br>
	 * 1 == showPatternButton pressed.<br>
	 * 2 == nextLevelButton pressed.<br>
	 * */
	public int getchoice() {
		switch (buttonPressedCode) {
		case showPattern:
			return 1;
		case unlockNextLevel:
			return 2;
		default:
			return 0;
		}
	}

	/** set */
	public void resetButtonPressed() {
		buttonPressedCode = HudChoice.undecided;
	}

	/** shows Show Pattern button, hides Next Level button */
	public void showPatternButton() {
		FX.showActor(showPatternButton);
		FX.hideActor(nextLevelButton);
	}

	/** shows Next Level button, hides Show Pattern button */
	public void showNextLevelButton() {
		FX.showActor(nextLevelButton);
		FX.hideActor(showPatternButton);
	}

	public boolean nextLevelButtonIsVisible() {
		return nextLevelButton.isTouchable();
	}

	public boolean showPatternButtonIsVisible() {
		return nextLevelButton.isTouchable();
	}

	public Color getChosenColor() {
		return colorButton.getColor();
	}
}
