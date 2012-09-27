package ultraextreme.model;

import ultraextreme.model.entity.PlayerShip;
import ultraextreme.model.item.AbstractWeapon;
import ultraextreme.model.item.BasicWeapon;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.item.ItemBar;
import ultraextreme.model.item.SpinningSpreadWeapon;
import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Dimension;
import ultraextreme.model.util.Rotation;
import ultraextreme.model.util.PlayerID;

/**
 * The player. The player has a ship and an item bar containing the ship's items
 * (such as weapons).
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Viktor Anderling
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
		Dimension levelDimension = Constants.getInstance().getLevelDimension();
		this.ship = new PlayerShip(levelDimension.getX() * 0.5, levelDimension.getY() * 0.8);
		this.playerId = playerId;
		this.itemBar = new ItemBar(playerId, bulletManager, new Rotation(
				Math.PI), 5);
		this.itemBar.addItem(new BasicWeapon(bulletManager));
		this.itemBar.addItem(new SpinningSpreadWeapon(bulletManager));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(ModelInput input, float timeElapsed) {
		ship.move(input.dX, input.dY);
		if (input.fireWeapons) {
			itemBar.fireWeapons(ship.getPosition(), timeElapsed);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PlayerShip getShip() {
		return ship;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PlayerID getPlayerId() {
		return playerId;
	}

	@Override
	public void giveWeapon(AbstractWeapon weapon) {
		itemBar.addItem(weapon);
	}
}