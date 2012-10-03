package ultraextreme.model;

import java.beans.PropertyChangeListener;

import ultraextreme.model.entity.PlayerShip;
import ultraextreme.model.item.ItemBar;
import ultraextreme.model.util.PlayerID;

/**
 * An interface for a player.
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Johan Gronvall
 * @author Viktor Anderling
 * 
 */
public interface IPlayer extends PropertyChangeListener {
	
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
	 * @return The number of lives the player has left.
	 */
	public int getLives();
}