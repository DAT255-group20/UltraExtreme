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
	private EnemyShip ship;
	private AbstractWeapon weapon;
	private Rotation enemyDirection;

	protected AbstractEnemy(EnemyShip ship, AbstractWeapon weapon) {
		this.ship = ship;
		this.weapon = weapon;
		this.enemyDirection = ship.getRotation();
	}

	/**
	 * Fires the weapon assigned to this enemy
	 */
	public void shoot(float timeElapsed) {
		weapon.fire(ship.getPosition(), PlayerID.ENEMY, this.enemyDirection,
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