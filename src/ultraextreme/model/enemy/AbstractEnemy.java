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
 * @author Johan Gronvall
 * @author Viktor Anderling
 * 
 */
public abstract class AbstractEnemy implements IEnemy {

	// The entity representing the enemyShip
	private EnemyShip ship;
	private AbstractWeapon weapon;
	private Rotation enemyDirection;
	private static Random randomGenerator = new Random();
	
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
	
	@Override
	public AbstractWeapon getWeapon() {
		return weapon;
	}
	
	/**
	May randomly return true if the enemy is dead based on the dropchance of weapons. 
	**/
	@Override
	public boolean ShouldSpawnPickup() {
		
		return (randomGenerator.nextInt(99) < Constants.getInstance().getWeaponDropChance()+1 && isDead());
	}


}