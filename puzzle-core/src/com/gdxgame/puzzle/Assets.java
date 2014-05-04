package com.gdxgame.puzzle;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/** Game resource repository */
public class Assets {

	public static Random rand;
	public static Skin defultSkin;
	public static TextureRegionDrawable tileTexture;

	static Color visibleColor, hiddenColor_green, hiddColor_red;

	public static float board_cells_Width, board_cells_Heigth,board_Bg_Width, board_Bg_Heigth, w_unit, h_unit, screenWidth,
			screenHeight, hudHeigth,bottomTableHeigth, correctVolume, wrongVolume;

	public static Drawable musicOn, musicOff, soundFxOn, soundFxOff;
	
	public static Sound correct, wrong;
	public static Music music;

	public static void load() {
		rand = new Random();

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		w_unit = screenWidth / 9;
		h_unit = Gdx.graphics.getHeight() / 16;
		board_cells_Width = 8 * w_unit;
		board_cells_Heigth = 9 * h_unit;
		board_Bg_Width = board_cells_Width + w_unit/2;
		board_Bg_Heigth = board_cells_Heigth + h_unit/2;
		hudHeigth= bottomTableHeigth = 3.25f * h_unit;

		visibleColor = Color.GRAY;
		hiddenColor_green = Color.GREEN;
		hiddColor_red = Color.RED;

		defultSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		tileTexture = new TextureRegionDrawable(new TextureRegion(new Texture(
				Gdx.files.internal("tile.png"))));

		musicOn = new TextureRegionDrawable(new TextureRegion(new Texture(
				Gdx.files.internal("gui/music_on.png"))));
		musicOff = new TextureRegionDrawable(new TextureRegion(new Texture(
				Gdx.files.internal("gui/music_off.png"))));
		soundFxOn = new TextureRegionDrawable(new TextureRegion(new Texture(
				Gdx.files.internal("gui/sound_fx_on.png"))));
		soundFxOff = new TextureRegionDrawable(new TextureRegion(new Texture(
				Gdx.files.internal("gui/sound_fx_off.png"))));
		
		correct = Gdx.audio.newSound(Gdx.files.internal("sound/correct.wav"));
		wrong = Gdx.audio.newSound(Gdx.files.internal("sound/wrong.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("sound/loop.mp3"));
		correctVolume = 1;
		wrongVolume = 1;

	}
}
