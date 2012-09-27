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
 * @author zapray
 * @author Viktor Anderling
 * 
 */
public class BasicEnemy extends AbstractEnemy {

	private static final int ySpeed = 50;

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

		this(new EnemyShip(x, y, 40, 40, new Rotation(0), 50, ObjectName.BASIC_ENEMYSHIP), new BasicWeapon(
				bulletManager));
		
	}

	@Override
	public void update(float timeElapsed) {
		this.getShip().move(0, timeElapsed * ySpeed);
		this.shoot(timeElapsed);
	}
}
