package ultraextreme.model;

import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.util.PlayerID;

/**
 * An inventory containing weapons and bombs.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class ItemBar {

	int maximumNumberOfItems;
	int cursorPosition;
	List<Weapon> items;
	Bomb bomb;
	PlayerID playerId;
	BulletProductionQueue bulletManager;
	
	public ItemBar(PlayerID playerId, BulletProductionQueue bulletManager) {
		this(playerId, bulletManager, 1);
	}
	
	public ItemBar(PlayerID playerId, BulletProductionQueue bulletManager,
			int maximumNumberOfItems) {
		this.playerId = playerId;
		this.bulletManager = bulletManager;
		this.items = new ArrayList<Weapon>();
		this.maximumNumberOfItems = maximumNumberOfItems;
		this.cursorPosition = 0;
	}
	
	/**
	 * Add an item to the item bar.
	 * @param item The item that should be added.
	 */
	public void addItem(Weapon item) {
		items.set(cursorPosition, item);
		cursorPosition++;
		cursorPosition = cursorPosition % (maximumNumberOfItems - 1);
	}
	
	public void fireWeapons() {
		for (Weapon weapon : items) {
			weapon.fireShot(playerId);
		}
	}
}
