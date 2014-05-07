package com.gdxgame.puzzle;

public class ColoredTile extends Tile {
	public ColoredTile() {
		super();
		hiddenColor = Assets.hiddenColors.get(Assets.rand
				.nextInt(Assets.colorLimit));
	}
}
