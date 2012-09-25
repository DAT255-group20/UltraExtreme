package ultraextreme.model;

import junit.framework.TestCase;
import ultraextreme.model.entity.BasicBullet;
import ultraextreme.model.entity.IBullet;
import ultraextreme.model.item.BasicWeapon;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.util.Rotation;
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

		basicWeapon.fire(new Position(), PlayerID.PLAYER1, new Rotation(0), null);
		assertTrue(bulletManager.getBullets().size() == 1);

		basicWeapon.fire(new Position(), PlayerID.PLAYER1, new Rotation(0), null);
		assertTrue(bulletManager.getBullets().size() == 2);

		basicWeapon.fire(new Position(), PlayerID.PLAYER1, new Rotation(0), null);
		assertTrue(bulletManager.getBullets().size() == 3);
	}

	/**
	 * Test so the properties of the bullet that the weapon fires are correct.
	 */
	public void testBulletProperties() {
		basicWeapon.fire(new Position(10, 5), PlayerID.PLAYER1,
				new Rotation(0), null);
		IBullet bullet = bulletManager.getBullets().get(0);
		assertTrue(bullet instanceof BasicBullet);
		assertEquals(bullet.getWidth(), 5);
		assertEquals(bullet.getHeight(), 5);
		assertEquals(bullet.getRotation(), new Rotation(0));
		assertEquals(bullet.getPlayerId(), PlayerID.PLAYER1);
	}
}
