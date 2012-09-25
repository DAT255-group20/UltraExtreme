package ultraextreme.model.enemy;

import ultraextreme.model.entity.BasicEnemyShip;
import ultraextreme.model.entity.EnemyShip;
import ultraextreme.model.item.AbstractWeapon;
import ultraextreme.model.item.BasicWeapon;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.util.Rotation;

/**
 * Class representing a very basic enemy that only flies downwards and has a
 * basic weapon.
 * 
 * @author zapray
 * 
 */
public class BasicEnemy extends AbstractEnemy {

	private static final int ySpeed = 50;
	private float cooldown = 0;

	private BasicEnemy(EnemyShip ship, AbstractWeapon weapon) {
		super(ship, weapon);
	}

	/**
	 * Constructor for an enemy with predetermined ship and weapon. Specific to
	 * this type of enemy
	 * 
	 * @param x
	 *            coordinate for the enemyShip
	 * @param y
	 *            coordinate for the enemyShip
	 * @param bulletManager
	 *            A reference to the bullet manager.
	 */
	public BasicEnemy(double x, double y, BulletManager bulletManager) {
		this(x, y, new Rotation(0), bulletManager);
	}

	/**
	 * Constructor for an enemy with predetermined ship and weapon. Specific to
	 * this type of enemy
	 * 
	 * @param x
	 *            coordinate for the enemyShip
	 * @param y
	 *            coordinate for the enemyShip
	 * @param rotation
	 *            Rotation modifier of the enemy's flying path.
	 * @param bulletManager
	 *            A reference to the bullet manager.
	 */
	public BasicEnemy(double x, double y, Rotation rotation,
			BulletManager bulletManager) {
		this(new BasicEnemyShip(x, y, rotation), new BasicWeapon(bulletManager));
	}

	@Override
	public void update(float timePassed) {
		this.getShip().move(0, timePassed * ySpeed);
		cooldown += timePassed;

		// Shoot every 0.8seconds
		if (cooldown > 0.8) {
			cooldown = 0;
			this.shoot();
		}
		// TODO cooldown should be stored in weapon
	}
}
