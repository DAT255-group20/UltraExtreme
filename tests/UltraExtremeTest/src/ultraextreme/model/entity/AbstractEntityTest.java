package ultraextreme.model.entity;

import junit.framework.TestCase;
import ultraextreme.model.util.Direction;
import ultraextreme.model.util.Position;

public abstract class AbstractEntityTest extends TestCase {

	protected abstract AbstractEntity getNewAbstractEntity(double x, double y,
			int width, int height, Direction direction);

	public void testMove() {
		AbstractEntity entity = newEntity();
		entity.move(0, 0);
		assertEquals(entity.getPosition(), new Position(10.0, 20.0));

		entity.move(10, 100);
		assertEquals(entity.getPosition(), new Position(20.0, 120.0));

		entity.move(-100, -1000);
		assertEquals(entity.getPosition(), new Position(-80.0, -880.0));
	}

	public void testSetPosition() {
		AbstractEntity entity = newEntity();
		entity.setPosition(new Position(40, 50));
		assertEquals(entity.getPosition().getX(), 40.0);
		assertEquals(entity.getPosition().getY(), 50.0);
		entity.setPosition(new Position(-40, -50));
		assertEquals(entity.getPosition().getX(), -40.0);
		assertEquals(entity.getPosition().getY(), -50.0);
	}

	public void testGetPosition() {
		AbstractEntity entity = newEntity();
		assertEquals(entity.getPosition().getX(), 10.0);
		assertEquals(entity.getPosition().getY(), 20.0);
	}

	public void testGetWidth() {
		AbstractEntity entity = newEntity();
		assertEquals(entity.getWidth(), 30);
	}

	public void testGetHeight() {
		AbstractEntity entity = newEntity();
		assertEquals(entity.getHeight(), 40);
	}

	public void testGetDirection() {
		AbstractEntity entity = newEntity();
		assertEquals(entity.getDirection(), Direction.UP);
	}

	public void testCollidesWith() {
		// TODO Write collidesWith test
	}

	public void testIsOutOfScreen() {
		// TODO Write isOutOfScreen test
	}

	public abstract void testGetSpeedModifier();

	private AbstractEntity newEntity() {
		return getNewAbstractEntity(10, 20, 30, 40, Direction.UP);
	}
}
