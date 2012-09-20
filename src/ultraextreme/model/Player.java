package ultraextreme.model;

import ultraextreme.model.entity.PlayerShip;
import ultraextreme.model.util.PlayerID;

/**
 * The player. The player has a ship and an item bar containing the ship's items
 * (such as weapons).
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * 
 */
public class Player implements IPlayer {

	/**
	 * Reference to the player's ship.
	 */
	private PlayerShip ship;

	/**
	 * The player's ID. This is used in the view to differentiate player ships
	 * from each other.
	 */
	private PlayerID playerId;

	/**
	 * Item bar containing the items that the player's ship is currently
	 * holding.
	 */
	private ItemBar itemBar;

	/**
	 * Create a new player.
	 * 
	 * @param playerId
	 *            Assign an ID to the player.
	 * @param bulletManager
	 *            A manager that the player ship's guns should add the bullets
	 *            to.
	 */
	public Player(PlayerID playerId, BulletManager bulletManager) {
		this.ship = new PlayerShip();
		this.playerId = playerId;
		this.itemBar = new ItemBar(ship.getPosition(), playerId, bulletManager);
		// Add a basic weapon for testing purpose
		this.itemBar.addItem(new BasicWeapon(bulletManager));
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(ModelInput input, float delta) {
		ship.move(input.dX, input.dY);
		if (input.fireWeapons) {
			itemBar.fireWeapons();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public PlayerShip getShip() {
		return ship;
	}

	/**
	 * {@inheritDoc}
	 */
	public PlayerID getPlayerId() {
		return playerId;
	}
}