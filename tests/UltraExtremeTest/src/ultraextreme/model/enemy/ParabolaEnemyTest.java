package ultraextreme.model.enemy;

import junit.framework.TestCase;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.item.WeaponFactory;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Position;

public class ParabolaEnemyTest extends TestCase {
	private BulletManager manager;

	public void setUp() {
		manager = new BulletManager();
		WeaponFactory.initialize(manager);
	}

	/**
	 * Test for a ParabolaEnemy moving in a Positive quadratic function
	 */
	public void testUpdate1() {

		Position startPos = new Position(200, 0);
		Position midPos = new Position(100, 0);
		Position endPos = new Position(0, 150);
		ParabolaEnemy enemy = new ParabolaEnemy(startPos, midPos, endPos,
				ObjectName.BASIC_WEAPON);

		boolean yWasNegative = false;
		boolean yWasPositive = false;

		for (int i = 0; i < 1000; i++) {
			// Test
			enemy.update(i * 0.1f);
			double x = enemy.getShip().getPositionClone().getX();
			double y = enemy.getShip().getPositionClone().getY();

			if (x < 200 && x > 100) {
				yWasNegative = (y <= 0);
			} else if (x < 100) {
				yWasPositive = (y >= 0);
			}
		}
		assertTrue(yWasNegative);
		assertTrue(yWasPositive);
		assertFalse(manager.getBullets().isEmpty());
	}

	/**
	 * Test for a ParabolaEnemy moving in a negative quadratic function
	 */
	public void testUpdate2() {

		Position startPos = new Position(-20, -20);
		Position midPos = new Position(0, 200);
		Position endPos = new Position(200, 0);
		ParabolaEnemy enemy = new ParabolaEnemy(startPos, midPos, endPos,
				ObjectName.SPINNING_WEAPON);
		boolean yWasNegative = false;
		boolean yWasPositive = false;

		for (int i = 0; i < 1000; i++) {
			enemy.update(i * 0.1f);
			double x = enemy.getShip().getPositionClone().getX();
			double y = enemy.getShip().getPositionClone().getY();
			if ((x > 200) && (y < 0)) {
				yWasNegative = true;
			} else if (x > 0 && y > 0) {
				yWasPositive = true;
			}
		}
		assertTrue(yWasNegative);
		assertTrue(yWasPositive);
		assertFalse(manager.getBullets().isEmpty());
	}

	public void testException() {
		Position startPos = new Position(0, 0);
		Position endPos = new Position(0, 0);
		Position midPos = new Position(1, 1);
		boolean exceptionThrown = false;
		try {
			new ParabolaEnemy(startPos, midPos, endPos,
					ObjectName.SPINNING_WEAPON);

		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
}
