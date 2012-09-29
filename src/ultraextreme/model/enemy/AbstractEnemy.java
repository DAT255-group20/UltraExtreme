package ultraextreme.model.enemy;

import ultraextreme.model.entity.EnemyShip;
import ultraextreme.model.item.AbstractWeapon;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Rotation;

/**
 * Represents an enemy brain/controller.
 * 
 * @author Johan Gronvall
 * @author Viktor Anderling
 * 
 */
public abstract class AbstractEnemy implements IEnemy {

	// The entity representing the enemyShip
	private final EnemyShip ship;
	private final AbstractWeapon weapon;
	private final Rotation enemyRotation;

	protected AbstractEnemy(final EnemyShip ship, final AbstractWeapon weapon) {
		this.ship = ship;
		this.weapon = weapon;
		this.enemyRotation = ship.getRotation();
	}

	/**
	 * Fires the weapon assigned to this enemy
	 */
	public void shoot(final float timeElapsed) {
		weapon.fire(ship.getPosition(), PlayerID.ENEMY, this.enemyRotation,
				timeElapsed);
	}

	@Override
	public boolean isDead() {
		return ship.isDestroyed();
	}

	@Override
	public EnemyShip getShip() {
		return ship;
	}

}