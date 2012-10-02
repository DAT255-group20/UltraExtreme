package ultraextreme.model;

import java.beans.PropertyChangeListener;

import ultraextreme.model.entity.PlayerShip;
import ultraextreme.model.item.AbstractWeapon;
import ultraextreme.model.item.ItemBar;
import ultraextreme.model.util.PlayerID;

/**
 * An interface for a player.
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Johan Gronvall
 * 
 */
public interface IPlayer extends PropertyChangeListener {

	/**
	 * Read the player's input data and update the player's ship.
	 * 
	 * @param input
	 *            Input data such as keystrokes.
	 * @param delta
	 *            Time since last update.
	 */
	void update(ModelInput input, float delta);

	/**
	 * Return a reference to the player's ship.
	 */
	PlayerShip getShip();

	/**
	 * Return the ID of the player.
	 * 
	 * @return The player's ID.
	 */
	PlayerID getPlayerId();

	/**
	 * @return The score of the player.
	 */
	int getScore();

	// TODO test
	public ItemBar getItemBar();

	/**
	 * adds a weapon (or bomb) to this player's ItemBar
	 * 
	 * @param weapon
	 */
	void giveWeapon(AbstractWeapon weapon);
}