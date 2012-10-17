package ultraextreme.model.item;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.entity.BasicBullet;
import ultraextreme.model.entity.IBullet;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * @author Viktor Anderling
 */
public class SpreadWeaponTest extends TestCase {

	BulletManager bulletManager;
	SpreadWeapon spreadWeapon;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.resetInstanceVariables();
	}

	private void resetInstanceVariables() {
		bulletManager = new BulletManager();
		spreadWeapon = new SpreadWeapon(bulletManager);
	}

	@Test
	public void testFire() {
		float epsilon = 0.001f;
		assertTrue(bulletManager.getBullets().size() == 0);
		float cooldown = SpreadWeapon.getInitCooldown();

		spreadWeapon.fire(new Position(), PlayerID.PLAYER1, new Rotation(0),
				cooldown * (1 + epsilon));
		assertTrue(bulletManager.getBullets().size() == 4);

		spreadWeapon.fire(new Position(), PlayerID.PLAYER1, new Rotation(0),
				cooldown * (1 + epsilon));
		assertTrue(bulletManager.getBullets().size() == 8);

		spreadWeapon.fire(new Position(), PlayerID.PLAYER1, new Rotation(0),
				cooldown * (1 + epsilon));
		assertTrue(bulletManager.getBullets().size() == 12);
	}

	/**
	 * Test so the properties of the bullet that the weapon fires are correct.
	 */
	public void testBulletProperties() {
		float cooldown = SpreadWeapon.getInitCooldown();
		spreadWeapon.fire(new Position(10, 5), PlayerID.PLAYER1,
				new Rotation(0), cooldown + cooldown / 1000);
		IBullet bullet = bulletManager.getBullets().get(0);
		assertTrue(bullet instanceof BasicBullet);
		assertEquals(bullet.getPlayerId(), PlayerID.PLAYER1);

		List<AbstractBullet> bulletList = bulletManager.getBullets();

		// Make sure the bullets move a bit.
		for (AbstractBullet b : bulletList) {
			b.doMovement(0.1f);
		}

		// Check so that the bullets are not along the same line.
		double epsilon = 0.00001;
		for (AbstractBullet b1 : bulletList) {
			for (AbstractBullet b2 : bulletList) {
				if (b1 != b2) {
					assertFalse(Math.abs(b1.getPositionClone().getX()
							- b2.getPositionClone().getX()) < epsilon);
				}
			}
		}
	}

	@Test
	public void testShallowClone() {
		fail("Not yet tested");
	}
}
