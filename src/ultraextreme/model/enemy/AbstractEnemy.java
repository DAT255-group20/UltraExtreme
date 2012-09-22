package ultraextreme.model.enemy;

import ultraextreme.model.entity.EnemyShip;
import ultraextreme.model.item.AbstractWeapon;
import ultraextreme.model.util.Direction;
import ultraextreme.model.util.PlayerID;

/**
 * Represents an enemy brain/controller.
 * 
 * @author Johan Gronvall
 * 
 */
public abstract class AbstractEnemy implements IEnemy {

	// The entity representing the enemyShip
	private EnemyShip ship;
	private AbstractWeapon weapon;
	private Direction enemyDirection;

	protected AbstractEnemy(EnemyShip ship, AbstractWeapon weapon) {
		this.ship = ship;
		this.weapon = weapon;
		this.enemyDirection = ship.getDirection();
	}

	/**
	 * Fires the weapon assigned to this enemy
	 */
	public void shoot() {
		weapon.fireShot(ship.getPosition(), PlayerID.ENEMY, this.enemyDirection);
	}

	/**
	 * returns true if this enemy has been destroyed
	 * 
	 * @return true if this enemy has been destroyed
	 */
	public boolean isDead() {
		return ship.isDestroyed();
	}

	@Override
	public EnemyShip getShip() {
		return ship;
	}

}