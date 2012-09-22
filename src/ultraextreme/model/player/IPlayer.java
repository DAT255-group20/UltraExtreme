package ultraextreme.model.player;

import ultraextreme.model.ModelInput;
import ultraextreme.model.entity.PlayerShip;
import ultraextreme.model.util.PlayerID;

/**
 * An interface for a player.
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * 
 */
public interface IPlayer {

	/**
	 * Read the player's input data and update the player's ship.
	 * 
	 * @param input
	 *            Input data such as keystrokes.
	 * @param delta
	 *            Time since last update.
	 */
	public void update(ModelInput input, float delta);

	/**
	 * Return a reference to the player's ship.
	 */
	public PlayerShip getShip();

	/**
	 * Return the ID of the player.
	 * 
	 * @return The player's ID.
	 */
	public PlayerID getPlayerId();
}