package ultraextreme.model;

import junit.framework.TestCase;
import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.entity.BasicBullet;
import ultraextreme.model.item.BasicWeapon;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.util.Direction;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class BasicWeaponTest extends TestCase {

	BulletManager bulletManager;
	BasicWeapon basicWeapon;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.resetInstanceVariables();
	}

	private void resetInstanceVariables() {
		bulletManager = new BulletManager();
		basicWeapon = new BasicWeapon(bulletManager);
	}

	/**
	 * Test to fire some shots with the weapon.
	 */
	public void testFireShot() {
		assertTrue(bulletManager.getBullets().size() == 0);

		basicWeapon.fireShot(new Position(), PlayerID.PLAYER1, Direction.UP);
		assertTrue(bulletManager.getBullets().size() == 1);

		basicWeapon.fireShot(new Position(), PlayerID.PLAYER1, Direction.UP);
		assertTrue(bulletManager.getBullets().size() == 2);

		basicWeapon.fireShot(new Position(), PlayerID.PLAYER1, Direction.UP);
		assertTrue(bulletManager.getBullets().size() == 3);
	}

	/**
	 * Test so the properties of the bullet that the weapon fires are correct.
	 */
	public void testBulletProperties() {
		basicWeapon.fireShot(new Position(10, 5), PlayerID.PLAYER1,
				Direction.UP);
		AbstractBullet bullet = bulletManager.getBullets().get(0);
		assertTrue(bullet instanceof BasicBullet);
		assertEquals(bullet.getWidth(), 5);
		assertEquals(bullet.getHeight(), 5);
		assertEquals(bullet.getDirection(), Direction.UP);
		assertEquals(bullet.getPlayerId(), PlayerID.PLAYER1);
	}
}
