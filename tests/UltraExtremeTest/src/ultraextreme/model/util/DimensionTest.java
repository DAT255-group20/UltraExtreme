package ultraextreme.model.util;

import javax.vecmath.Vector2d;

import junit.framework.TestCase;

/**
 * 
 * @author Viktor Anderling
 *
 */
public class DimensionTest extends TestCase {

	private Dimension dimension;
	private Dimension otherDimension;
	private static double epsilon = 0.000001;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dimension = null;
		otherDimension = null;
	}

	public void testGetX() {
		dimension = new Dimension(10, 20);
		assertTrue(Math.abs(dimension.getX() - 10) < epsilon);
		dimension = new Dimension(120, 300);
		assertTrue(Math.abs(dimension.getX() - 120) < epsilon);
	}

	public void testGetY() {
		dimension = new Dimension(10, 20);
		assertTrue(Math.abs(dimension.getY() - 20) < epsilon);
		dimension = new Dimension(120, 300);
		assertTrue(Math.abs(dimension.getY() - 300) < epsilon);
	}

	public void testEquals() {
		dimension = new Dimension(10, 20);
		otherDimension = new Dimension(10, 30);
		assertFalse(dimension.equals(otherDimension));
		otherDimension = new Dimension(20, 20);
		assertFalse(dimension.equals(otherDimension));
		otherDimension = new Dimension(10, 20);
		assertEquals(dimension, otherDimension);
		Vector2d vec = new Vector2d(10, 20);
		assertFalse(dimension.equals(vec));
	}

	public void testGetQuotient() {
		dimension = new Dimension(20, 50);
		otherDimension = new Dimension(10, 200);
		Vector2d qVec = dimension.getQuotient(otherDimension);
		assertTrue(Math.abs(qVec.x - 2) < epsilon
				&& Math.abs(qVec.y - 0.25) < epsilon);
		qVec = otherDimension.getQuotient(dimension);
		assertTrue(Math.abs(qVec.x - 0.5) < epsilon
				&& Math.abs(qVec.y - 4) < epsilon);
	}

	public void testScalePosition() {
		Position oldPos = new Position(5, 20);
		dimension = new Dimension(100, 200);
		otherDimension = new Dimension(400, 600);
		Vector2d qVec = dimension.getQuotient(otherDimension);
		Position newPos = dimension.scalePosition(otherDimension, oldPos);
		assertTrue(Math.abs(qVec.x * 5 - newPos.getX()) < epsilon
				&& Math.abs(qVec.y * 20 - newPos.getY()) < epsilon);
	}
}
