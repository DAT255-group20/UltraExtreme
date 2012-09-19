package ultraextreme.model;

import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.util.Direction;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;

/**
 * An inventory containing weapons and bombs.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class ItemBar {

	private int maximumNumberOfItems;
	private int cursorPosition;
	private List<AbstractWeapon> items;
	private Bomb bomb;
	private PlayerID playerId;
	private BulletProductionQueue bulletManager;
	private Position shipPosition;

	public ItemBar(Position shipPosition, PlayerID playerId, BulletProductionQueue bulletManager) {
		this(playerId, bulletManager, 1);
	}

	public ItemBar(PlayerID playerId, BulletProductionQueue bulletManager,
			int maximumNumberOfItems) {
		this.playerId = playerId;
		this.bulletManager = bulletManager;
		this.items = new ArrayList<AbstractWeapon>();
		this.maximumNumberOfItems = maximumNumberOfItems;
		this.cursorPosition = 0;
	}

	/**
	 * Add an item to the item bar.
	 * 
	 * @param item
	 *            The item that should be added.
	 */
	public void addItem(AbstractWeapon item) {
		items.set(cursorPosition, item);
		cursorPosition++;
		cursorPosition = cursorPosition % (maximumNumberOfItems - 1);
	}

	/**
	 * Fires all the weapons in the ItemBar
	 */
	public void fireWeapons() {
		for (AbstractWeapon weapon : items) {
			weapon.fireShot(shipPosition, playerId, Direction.UP);
		}
	}
}
