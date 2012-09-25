package ultraextreme.model.enemy;

import ultraextreme.model.entity.EnemyShip;
import ultraextreme.model.item.AbstractWeapon;
import ultraextreme.model.item.BasicWeapon;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.util.Direction;
import ultraextreme.model.util.ObjectName;

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

		this(new EnemyShip(x, y, 10, 10, Direction.DOWN, 50, ObjectName.BASIC_ENEMYSHIP), new BasicWeapon(
				bulletManager));
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
