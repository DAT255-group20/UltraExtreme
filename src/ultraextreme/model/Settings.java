package ultraextreme.model;

import ultraextreme.model.util.Difficulty;

/**
 * A class capable of containing a number of settings for the game.
 * 
 * @author Viktor Anderling
 *
 */
public class Settings {

	private Difficulty difficulty;
	
	/**
	 * Creates a Settings with normal difficulty.
	 */
	private Settings() {
		this.difficulty = Difficulty.NORMAL;
	}
	
	/**
	 * Creates a Settings with the chosen difficulty.
	 * 
	 * @param difficulty
	 * 				The chosen difficulty.
	 */
	private Settings(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	/**
	 * Sets the difficulty for this Settings.
	 * 
	 * @param difficulty
	 * 				The chosen difficulty.
	 */
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	/**
	 * Gets the difficulty for this Settings.
	 * 
	 * @return The current difficulty.
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
}

