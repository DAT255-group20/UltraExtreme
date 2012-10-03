package ultraextreme.model;

/**
 * A listener for the Player class.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public interface IPlayerListener {

	/**
	 * Is called when an update occurs in the Player.
	 * 
	 * @param player
	 *            Player
	 */
	void playerUpdate(IPlayer player);
}