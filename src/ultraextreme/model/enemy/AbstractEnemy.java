package ultraextreme.model.enemy;

import ultraextreme.model.AbstractWeapon;
import ultraextreme.model.entity.EnemyShip;
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

	protected AbstractEnemy(EnemyShip ship, AbstractWeapon weapon) {
		this.ship = ship;
		this.weapon = weapon;
	}

	/**
	 * Fires the weapon assigned to this enemy
	 */
	public void Shoot() {
		weapon.fireShot(ship.getPosition(), PlayerID.ENEMY, Direction.DOWN);
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