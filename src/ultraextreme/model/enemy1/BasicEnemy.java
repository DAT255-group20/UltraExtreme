package ultraextreme.model.enemy1;

import ultraextreme.model.BasicWeapon;
import ultraextreme.model.AbstractWeapon;
import ultraextreme.model.entity.BasicEnemyShip;
import ultraextreme.model.entity.EnemyShip;
import ultraextreme.model.util.PlayerID;

/**
 * Class representing a very basic enemy that only flies downwards and has a
 * basic weapon.
 * 
 * @author zapray
 * 
 */
public class BasicEnemy extends AbstractEnemy {

	private static final int ySpeed = -50;
	private float cooldown = 0;

	protected BasicEnemy(EnemyShip ship, AbstractWeapon weapon) {
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
	 */
	public BasicEnemy(double x, double y) {
		// TODO change to a proper
		// FIXME: BasicWeapon need a reference to the bullet manager.
		this(new BasicEnemyShip(x, y), new BasicWeapon(null));
	}

	@Override
	public void update(float timePassed) {
		this.getShip().move(0, timePassed * ySpeed);
		cooldown += timePassed;

		// Shoot every 0.8seconds
		if (cooldown > 0.8) {
			cooldown = 0;
			this.Shoot();
		}
		// TODO cooldown should be stored in weapon
	}
}
