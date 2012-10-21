package ultraextreme.model;

import ultraextreme.model.util.Difficulty;

/**
 * A class capable of containing a number of settings for the game.
 * 
 * @author Viktor Anderling
 * @author Bjorn Persson Mattsson
 *
 */
public class Settings {

	private static Difficulty difficulty = Difficulty.NORMAL;
	
	/**
	 * Sets the difficulty for this Settings.
	 * 
	 * @param difficulty
	 * 				The chosen difficulty.
	 */
	public static void setDifficulty(Difficulty difficulty) {
		Settings.difficulty = difficulty;
	}
	
	/**
	 * Gets the difficulty for this Settings.
	 * 
	 * @return The current difficulty.
	 */
	public static Difficulty getDifficulty() {
		return difficulty;
	}
}
