package ultraextreme.model.entity;

import ultraextreme.model.util.Rotation;
import ultraextreme.model.util.Position;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class PlayerShipTest extends AbstractEntityTest {

	PlayerShip playerShip;

	private void resetInstanceVariables(double x, double y, int width,
			int height) {
		playerShip = new PlayerShip(x, y, width, height);
	}

	/**
	 * Test if it's possible to move the ship.
	 */
	@Override
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

	@Override
	public void testGetDirection() {
		resetInstanceVariables(0, 0, 0, 0);
		assertEquals(playerShip.getRotation(), new Rotation(0));
	}

	@Override
	public void testGetSpeedMod() {
		// FIXME
	}

	@Override
	protected AbstractEntity getNewAbstractEntity(double x, double y,
			int width, int height, Rotation direction) {
		return new PlayerShip(x, y, width, height);
	}
}
