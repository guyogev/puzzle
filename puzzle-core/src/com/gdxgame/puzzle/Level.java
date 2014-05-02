package com.gdxgame.puzzle;

/**
 * Singleton pattern.
 * 
 * Level raw data
 */
public class Level {
	private static Level level = new Level();
	private static int levelNumber, boardHeight, boardWidth, coloredTiles;

	private Level() {
		levelNumber = 0;
		boardHeight = 2;
		boardWidth = 2;
		coloredTiles = 0;
	}

	/**Creates nect level*/
	public static Level getLevel() {
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

	public static int getLevelNumber() {
		return levelNumber;
	}

	public static int getBoardHeight() {
		return boardHeight;
	}

	public static int getBoardWidth() {
		return boardWidth;
	}

	public static int getColoredTiles() {
		return coloredTiles;
	}

}
