package ultraextreme.model.enemy;

import junit.framework.TestCase;

import org.junit.Test;

import ultraextreme.model.item.BulletManager;
/**
 * 
 * @author Johan Gronvall
 *
 */
public class AbstractEnemyTest extends TestCase{
	BulletManager bulletManager;
	BasicEnemy enemy;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		resetInstanceVariables();
	}
	@Test
	private void resetInstanceVariables() {
		bulletManager = new BulletManager();
		enemy = new BasicEnemy(5,5, bulletManager);
	}
	
	/**
	 * Tests the shoot() Method
	 */
	@Test
	public void testShoot() {
		enemy.Shoot();
		assertTrue(!bulletManager.getBullets().isEmpty());
	}
	
	/**
	 * Tests the isDead() method
	 */
	@Test
	public void testIsDead() {
		assertTrue(enemy.getShip().isDestroyed() == enemy.isDead());
		enemy.getShip().damage(10000);
		assertTrue(enemy.isDead());
	}
	
	
	
}
