package com.gdxgame.puzzle;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**play screen menu & options*/
public class MenuWindow extends Window {

	private enum RestartChoice {
		undecided, level, game, mainMenu
	}

	private RestartChoice choice;
	private TextButton tryAgain, restart, mainMenu;
	private ImageButton music, soundFx;
	private Table layout;

	public MenuWindow() {
		super("Menu", Assets.defaultSkin);
		setSize(Assets.board_cells_Width, Assets.board_cells_Heigth);

		layout = new Table();// .debug();
		layout.setFillParent(true);
		addActor(layout);

		choice = RestartChoice.undecided;

		createButtons();

		layout.add(mainMenu).width(6 * Assets.w_unit).height(Assets.h_unit)
				.padBottom(Assets.h_unit / 4).colspan(6).row();
		layout.add(tryAgain).width(6 * Assets.w_unit).height(Assets.h_unit)
				.padBottom(Assets.h_unit / 4).colspan(6).row();
		layout.add(restart).width(6 * Assets.w_unit).height(Assets.h_unit)
				.padBottom(Assets.h_unit / 4).colspan(6).row();
		layout.add(music).left().colspan(2);
		layout.add(soundFx).left().colspan(2);

	}

	private void createButtons() {
		mainMenu = new TextButton("Back To Main Menu", Assets.defaultSkin);
		mainMenu.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				choice = RestartChoice.mainMenu;
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}

		});
		tryAgain = new TextButton("Try Level Again", Assets.defaultSkin);
		tryAgain.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				choice = RestartChoice.level;
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});

		restart = new TextButton("New Game", Assets.defaultSkin);
		restart.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				choice = RestartChoice.game;
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});

		music = new ImageButton(Assets.musicOn, Assets.musicOff,
				Assets.musicOff);
		if (!Assets.musicSoundOn)
			music.setChecked(true);
		music.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (music.isChecked()) {
					Assets.music.play();
					Assets.musicSoundOn = true;
				} else {
					Assets.music.stop();
					Assets.musicSoundOn = false;
				}
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});

		soundFx = new ImageButton(Assets.soundFxOn, Assets.soundFxOff,
				Assets.soundFxOff);
		if (!Assets.fxSoundOn)
			soundFx.setChecked(true);
		soundFx.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (!soundFx.isChecked()) {
					Assets.fxSoundOn = false;
				} else {
					Assets.fxSoundOn = true;
					Assets.correct.play(Assets.fxVolume);
				}

				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});
	}

	/**
	 * 0 == undecided <br>
	 * 1 == restart level <br>
	 * 2 == new game <br>
	 * 3 == back to main menu
	 * */
	public int getchoice() {
		switch (choice) {
		case level:
			return 1;
		case game:
			return 2;
		case mainMenu:
			return 3;
		default:
			return 0;
		}
	}

	public void resetChoice() {
		choice = RestartChoice.undecided;
		FX.hideActor(this);
	}

}
