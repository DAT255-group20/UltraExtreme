package ultraextreme.model;

import ultraextreme.model.entity.PlayerShip;
import ultraextreme.model.util.PlayerID;

/**
 * The player.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class Player implements IPlayer {

	PlayerShip ship;
	
	/**
	 * The player's ID. This is used in the view to differentiate player ships
	 * from each other.
	 */
	PlayerID playerId;
	
	/**
	 * Item bar containing the items that the player's ship are currently
	 * holding.
	 */
	ItemBar itemBar;
	
	/**
	 * Create a new player.
	 * @param playerId Assign an ID to the player.
	 * @param bulletManager A manager that the player ship's guns should add
	 * the bullets to.
	 */
	public Player(PlayerID playerId, BulletProductionQueue bulletManager) {
		this.ship = new PlayerShip();
		this.playerId = playerId;
		this.itemBar = new ItemBar(playerId, bulletManager);
	}
	
	/**
	 * Read the player's input data and update the player's ship.
	 * @param input Input data such as keystrokes.
	 * @param delta Time since last update.
	 */
	public void update(ModelInput input, float delta) {
		ship.move(input.dX, input.dY);
		if (input.fireWeapons) {
			itemBar.fireWeapons();
		}
	}
	
	public PlayerShip getShip() {
		return ship;
	}
	
	/**
	 * Return the ID of the player.
	 * @return The player's ID.
	 */
	public PlayerID getPlayerId() {
		return playerId;
	}
}