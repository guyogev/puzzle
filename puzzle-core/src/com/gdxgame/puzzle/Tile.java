package com.gdxgame.puzzle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

/**Color changing animated Button */
public abstract class Tile extends Button {
	protected Color hiddenColor, visibleColor;

	public Tile() {
		super(Assets.defultSkin);
		visibleColor = Assets.visibleColor;
		setColor(visibleColor);
		addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				addAction(Actions.color(hiddenColor, .3f));
				setTouchable(Touchable.disabled);
				if (hiddenColor == Assets.hiddenColor_green) {
					Hud.updateHud(true);
					Assets.correct.play();
				}
				else{
					Hud.updateHud(false);
					Assets.wrong.play();
				}
				
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

			}
		});
	}
}
