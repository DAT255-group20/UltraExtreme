package ultraextreme.model.enemy;

import ultraextreme.model.entity.EnemyShip;
import ultraextreme.model.item.AbstractWeapon;
import ultraextreme.model.item.BasicWeapon;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Rotation;

/**
 * Class representing a very basic enemy that only flies downwards and has a
 * basic weapon.
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Johan Gronvall
 * @author Viktor Anderling
 * 
 */
public class BasicEnemy extends AbstractEnemy {

	private static final int Y_SPEED = 50;

	private BasicEnemy(final EnemyShip ship, final AbstractWeapon weapon) {
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
	public BasicEnemy(final double x, final double y,
			final BulletManager bulletManager) {
		this(new EnemyShip(x, y, 40, 40, new Rotation(0), 50,
				ObjectName.BASIC_ENEMYSHIP), new BasicWeapon(bulletManager));
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
		this(new EnemyShip(x, y, 40, 40, rotation, 50,
				ObjectName.BASIC_ENEMYSHIP), new BasicWeapon(bulletManager));
	}

	@Override
	public void update(float timeElapsed) {
		this.getShip().move(0, timeElapsed * Y_SPEED);
		this.shoot(timeElapsed);
	}
}
