package com.gdxgame.puzzle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

/** Color changing animated Button */
public class Tile extends Button {
	protected Color hiddenColor, visibleColor;

	public Tile() {
		super(Assets.defaultSkin);
		hiddenColor = visibleColor = Assets.visibleColor;
		setColor(visibleColor);
		final Tile t = this;
		addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				PlayScreen.checkTile(t);
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

			}
		});
	}
}
