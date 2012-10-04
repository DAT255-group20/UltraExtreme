package ultraextreme.model.enemy;

import java.util.Random;

import ultraextreme.model.entity.EnemyShip;
import ultraextreme.model.item.AbstractWeapon;
import ultraextreme.model.util.Constants;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Rotation;

/**
 * Represents an enemy brain/controller.
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Johan Gronvall
 * @author Viktor Anderling
 * 
 */
public abstract class AbstractEnemy implements IEnemy {

	// The entity representing the enemyShip
	private final EnemyShip ship;
	private final AbstractWeapon weapon;
	private final Rotation enemyRotation;
	private final static Random randomGenerator = new Random();

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

	@Override
	public AbstractWeapon getWeapon() {
		return weapon;
	}

	/**
	 * May randomly return true if the enemy is dead based on the dropchance of
	 * weapons.
	 **/
	@Override
	public boolean shouldSpawnPickup() {

		return (randomGenerator.nextInt(99) < Constants.getWeaponDropChance() + 1 && isDead());
	}

	/**
	 * Update method called once per loop, updating the enemy's behavior
	 * 
	 * @param timePassed
	 *            since last update in (seconds?)
	 */
	public abstract void update(float timePassed);
}