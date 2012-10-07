package ultraextreme.model;

import java.beans.PropertyChangeListener;

/**
 * An interface for a GameModel that only has get methods.
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Johan Gronvall
 * 
 */
public interface IUltraExtremeModel {

	/**
	 * @return The human player in the game.
	 */
	IPlayer getPlayer();

	/**
	 * Adds a listener to the model.
	 * 
	 * @param listener
	 * 				The provided listener.
	 */
	void addPropertyChangeListener(PropertyChangeListener listener);
	
	/**
	 * Registers the provided listener to the player.
	 * 
	 * @param listener
	 *            Listener
	 */
	void registerPlayerListener(IPlayerListener listener);

	/**
	 * Removes the provided listener from the player.
	 * 
	 * @param listener
	 *            Listener
	 */
	void unregisterPlayerListener(IPlayerListener listener);
	
	/**
	 * @return true if and only if the game is over.
	 */
	boolean isGameOver();
}