package ultraextreme.model;

import ultraextreme.model.entity.PlayerShip;
import ultraextreme.model.item.AbstractWeapon;
import ultraextreme.model.item.ItemBar;
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

	// test
	public ItemBar getItemBar();
	
	/**
	 * adds a weapon (or bomb) to this player's ItemBar
	 * 
	 * @param weapon
	 */
	public void giveWeapon(AbstractWeapon weapon);
}