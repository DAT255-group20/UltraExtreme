package ultraextreme.model.entity;

import junit.framework.TestCase;
import ultraextreme.model.util.Direction;
import ultraextreme.model.util.Position;

public class PlayerShipTest extends TestCase {

	PlayerShip playerShip;

	private void resetInstanceVariables(double x, double y, int width,
			int height) {
		playerShip = new PlayerShip(x, y, width, height);
	}

	/**
	 * Create a ship and check if it's possible to get the given values.
	 */
	public void testGetProperties() {
		resetInstanceVariables(10, 20, 30, 40);
		assertEquals(playerShip.getPosition().getX(), 10.0);
		assertEquals(playerShip.getPosition().getY(), 20.0);
		assertEquals(playerShip.getWidth(), 30);
		assertEquals(playerShip.getHeight(), 40);
		assertEquals(playerShip.getDirection(), Direction.UP);
	}

	/**
	 * Test if it's possible to move the ship.
	 */
	public void testMove() {
		resetInstanceVariables(10, 20, 30, 40);
		playerShip.move(0, 0);
		assertEquals(playerShip.getPosition(), new Position(10.0, 20.0));

		playerShip.move(10, 100);
		assertEquals(playerShip.getPosition(), new Position(20.0, 120.0));

		playerShip.move(-100, -1000);
		assertEquals(playerShip.getPosition(), new Position(-80.0, -880.0));
	}

	/**
	 * Test if it's possible to destroy the ship. FIXME: Not implemented yet.
	 */
	public void testIsDestroyed() {
		// FIXME: Since the ship can't get hurt yet this is hard to test.
		resetInstanceVariables(0, 0, 0, 0);
		assertEquals(playerShip.isDestroyed(), false);
	}
}
