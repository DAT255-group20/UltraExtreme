package ultraextreme.model.enemy;

import junit.framework.TestCase;
import ultraextreme.model.item.BulletManager;

/**
 * 
 * @author Johan Gronvall
 * 
 */
public class AbstractEnemyTest extends TestCase {
	BulletManager bulletManager;
	AbstractEnemy enemy;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		resetInstanceVariables();
	}

	private void resetInstanceVariables() {
		bulletManager = new BulletManager();
		enemy = new BasicEnemy(5, 5, bulletManager);
	}

	/**
	 * Tests the shoot() Method
	 */
	public void testShoot() {
		enemy.shoot();
		assertTrue(!bulletManager.getBullets().isEmpty());
	}

	/**
	 * Tests the isDead() method
	 */
	public void testIsDead() {
		assertTrue(enemy.getShip().isDestroyed() == enemy.isDead());
		enemy.getShip().damage(10000);
		assertTrue(enemy.isDead());
	}

}
