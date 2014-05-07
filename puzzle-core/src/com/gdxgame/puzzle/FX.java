package com.gdxgame.puzzle;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/** Animated FX using Actions*/
public class FX {
	static float defaultDelta = .3f;

	/** disable actor input & fade out with default timing */
	public static void hideActor(Actor a) {
		hideActor(a, defaultDelta);
	}

	/** enable actor input & fade in with default timing */
	public static void showActor(Actor a) {
		showActor(a, defaultDelta);
	}

	/** disable actor input & fade out effect in delta seconds */
	public static void hideActor(Actor a, float delta) {
		a.setTouchable(Touchable.disabled);
		a.addAction(Actions.fadeOut(delta));
	}

	/** enable actor input & fade in effect delta seconds */
	public static void showActor(Actor a, float delta) {
		a.setTouchable(Touchable.enabled);
		a.addAction(Actions.fadeIn(delta));
	}

}
