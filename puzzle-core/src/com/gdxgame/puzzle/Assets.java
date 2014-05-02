package com.gdxgame.puzzle;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/** Game resource repository */
public class Assets {

	public static Random rand;
	public static Skin defultSkin;
	public static TextureRegionDrawable tileTexture;

	static Color visibleColor, hiddenColor_green, hiddColor_red;

	public static int boardSize, w_unit;

	public static Sound correct, wrong;
	public static Music music;

	public static void load() {
		rand = new Random();

		w_unit = Gdx.graphics.getWidth() / 10;
		boardSize = 9 * w_unit;

		visibleColor = Color.GRAY;
		hiddenColor_green = Color.GREEN;
		hiddColor_red = Color.RED;

		defultSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		tileTexture = new TextureRegionDrawable(new TextureRegion(new Texture(
				Gdx.files.internal("tile.png"))));

		correct = Gdx.audio.newSound(Gdx.files.internal("sound/correct.wav"));
		wrong = Gdx.audio.newSound(Gdx.files.internal("sound/wrong.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("sound/loop.mp3"));

	}
}
