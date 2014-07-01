package com.gdxgame.puzzle;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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

	static Random rand;
	static Preferences prefs = Gdx.app.getPreferences("Game Preferences");
	static Skin defaultSkin;

	static Color boardBgColor, TileVisibleColor, tileHiddenColor_correct,
			tileHiddColor_wrong;
	static ArrayList<Color> hiddenColors;

	static float board_cells_Width, board_cells_Heigth, board_Bg_Width,
			board_Bg_Heigth, w_unit, h_unit, screenWidth, screenHeight,
			hudHeigth, bottomTableHeigth, fxVolume;

	static Drawable musicOn, musicOff, soundFxOn, soundFxOff, menuIcon;

	static Sound correct, wrong;
	static Music music;

	static int colorLimit, highScore;
	static boolean fxSoundOn, musicSoundOn;

	private static Texture t1, t2, t3, t4, t5;

	static void load() {

		rand = new Random();
		loadPrefs();

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		w_unit = screenWidth / 9;
		h_unit = Gdx.graphics.getHeight() / 16;
		board_cells_Width = 7 * w_unit;
		board_cells_Heigth = 9 * h_unit;
		board_Bg_Width = board_cells_Width + w_unit / 2;
		board_Bg_Heigth = board_cells_Heigth + h_unit / 2;
		hudHeigth = bottomTableHeigth = 3.25f * h_unit;

		boardBgColor = new Color(.302f, .302f, .302f, .8f);
		TileVisibleColor = new Color(1, 1, 1, 1);
		tileHiddenColor_correct = new Color(0.384f, 1, 0, 1);
		tileHiddColor_wrong = new Color(1, .271f, 0, 1);
		hiddenColors = new ArrayList<Color>();
		hiddenColors.add(new Color(1, 0.969f, 0, 1));
		hiddenColors.add(new Color(0.451f, 0.686f, 0.902f, 1));
		hiddenColors.add(new Color(1, 0.467f, 0, 1));

		defaultSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

		t1 = new Texture(Gdx.files.internal("gui/music_on.png"));
		musicOn = new TextureRegionDrawable(new TextureRegion(t1));
		t2 = new Texture(Gdx.files.internal("gui/music_off.png"));
		musicOff = new TextureRegionDrawable(new TextureRegion(t2));
		t3 = new Texture(Gdx.files.internal("gui/sound_fx_on.png"));
		soundFxOn = new TextureRegionDrawable(new TextureRegion(t3));
		t4 = new Texture(Gdx.files.internal("gui/sound_fx_off.png"));
		soundFxOff = new TextureRegionDrawable(new TextureRegion(t4));
		t5 = new Texture(Gdx.files.internal("gui/menu.png"));
		menuIcon = new TextureRegionDrawable(new TextureRegion(t5));

		correct = Gdx.audio.newSound(Gdx.files.internal("sound/correct.wav"));
		wrong = Gdx.audio.newSound(Gdx.files.internal("sound/wrong.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("sound/loop.mp3"));

	}

	/** Load preferences from file */
	private static void loadPrefs() {
		musicSoundOn = prefs.getBoolean("musicSoundOn", true);
		fxSoundOn = prefs.getBoolean("fxSoundOn", true);
		fxVolume = prefs.getFloat("fxVolume", 1);
		colorLimit = prefs.getInteger("colorLimit", 1);
		highScore = prefs.getInteger("highScore", 0);
	}

	/** Save preferences from file */
	private static void savePref() {
		prefs.putBoolean("musicSoundOn", musicSoundOn);
		prefs.putBoolean("fxSoundOn", fxSoundOn);
		prefs.putFloat("fxVolume", fxVolume);
		prefs.putInteger("colorLimit", colorLimit);
		prefs.putInteger("highScore", highScore);
		prefs.flush();
	}

	/** Free memory */
	public static void dispose() {
		savePref();
/*		t1.dispose();
		t2.dispose();
		t3.dispose();
		t4.dispose();
		t5.dispose();
*/		correct.dispose();
		wrong.dispose();
		music.dispose();
	}

}
