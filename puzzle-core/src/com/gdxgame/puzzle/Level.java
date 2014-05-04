package com.gdxgame.puzzle;

/**
 * Singleton pattern.
 * 
 * Level raw data
 */
public class Level {
	private static Level level;
	private static int levelNumber, boardHeight, boardWidth, coloredTiles;

	private Level() {
		reset();
	}

	void reset() {
		levelNumber = 0;
		boardHeight = 2;
		boardWidth = 2;
		coloredTiles = 0;
	}

	public static int getLevelNumber() {
		return levelNumber;
	}

	public int getBoardHeight() {
		return boardHeight;
	}

	public int getBoardWidth() {
		return boardWidth;
	}

	public int getColoredTiles() {
		return coloredTiles;
	}

	public static Level next() {
		if (level == null)
			level = new Level();
 		levelNumber++;
		if ((double)coloredTiles / (boardHeight * boardWidth) < 0.5)
			coloredTiles++;
		else {
			if (boardHeight != boardWidth)
				boardHeight = boardWidth = Math.max(boardHeight, boardWidth);
			else if (Assets.rand.nextDouble() >= 0.5)
				boardHeight++;
			else
				boardWidth++;
			coloredTiles = Math.max(1, boardHeight*boardWidth/8);
		}
		return level;
	}

}
