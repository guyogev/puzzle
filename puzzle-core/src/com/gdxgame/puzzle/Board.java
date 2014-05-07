package com.gdxgame.puzzle;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Singleton Pattern.
 * 
 * Tiles centers on screen with background
 * */
public class Board{
	private static Board board;
	private static Table bgTable, cellsTable;
	private static ArrayList<Tile> tileBatch;

	/** board background getter*/
	public Table getBgTable() {
		return bgTable;
	}

	/** board tiles getter*/
	public Table getCellsTable() {
		return cellsTable;
	}

	/** Temporarily shows Tiles */
	public void showTiles() {
		float freezeTime = 1.5f;
		for (Tile t : tileBatch) {
			t.addAction(sequence(Actions.touchable(Touchable.disabled),
					delay(freezeTime), Actions.touchable(Touchable.enabled)));
			if (t.hiddenColor != Assets.hiddColor_wrong) {
				t.addAction(sequence(
						Actions.color(t.hiddenColor, freezeTime / 5),
						delay(3 * freezeTime / 5),
						Actions.color(t.visibleColor, freezeTime / 5)));
			}
		}
	}

	/** Create new Board */
	public static Board getBoard(int h, int w, int c) {
		if (null == board)
			board = new Board();
		float tileW = Assets.board_cells_Width / w;
		float tileH = Assets.board_cells_Heigth / h;
		float bgW = Assets.board_Bg_Width;
		float bgH = Assets.board_Bg_Heigth;
	
		// board background
		bgTable = new Table();
		bgTable.setFillParent(true);
		Button bgButtun = new Button(Assets.defaultSkin);
		bgButtun.setDisabled(true);
		bgButtun.setColor(.9f, .8f, .7f, .5f);
		bgTable.add(bgButtun).width(bgW).height(bgH);
	
		// board cells
		cellsTable = new Table();
		cellsTable.setFillParent(true);
		tileBatch = createTileBatch(h * w - c, c);
		Tile t;
		for (int i = 1; i <= h; i++) {
			for (int j = 1; j <= w; j++) {
				t = tileBatch.remove(0);
				cellsTable.add(t).pad(1).width(tileW).height(tileH);
				tileBatch.add(t);
			}
			cellsTable.row();
		}
	
		return board;
	
	}

	public void resetBoard() {
		for (Tile t : tileBatch){
			t.addAction(Actions.color(t.visibleColor,.3f));
			t.setTouchable(Touchable.enabled);
		}
	}


	private Board() {
	}


	/** Returns ArrayList of red+green Tiles with random order */
	private static ArrayList<Tile> createTileBatch(int unColored, int colored) {
		ArrayList<Tile> batch = new ArrayList<Tile>();
		for (int i = 0; i < unColored; i++)
			batch.add(new Tile());
		for (int i = 0; i < colored; i++)
			batch.add(new ColoredTile());
		Collections.shuffle(batch);
		return batch;
	}

}
