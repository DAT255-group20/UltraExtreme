package ultraextreme.model.util;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class PositionTest extends TestCase {

	/**
	 * Test the default constructor
	 */
	@Test
	public void testPosition() {
		Position p = new Position();
		assertEquals(0.0, p.getX());
		assertEquals(0.0, p.getY());

	}

	/**
	 * Test the copy constructor
	 */
	@Test
	public void testPositionPosition() {
		for (int i = -500; i < 500; i++) {
			double x = 50 * i;
			double y = 100 * i - 10;
			Position p = new Position(x, y);
			Position p2 = new Position(p);
			assertEquals(x, p2.getX());
			assertEquals(y, p2.getY());
		}
	}

	/**
	 * Test the constructor where an x and an y is given
	 */
	@Test
	public void testPositionDoubleDouble() {
		for (int i = -500; i < 500; i++) {
			double x = 50 * i;
			double y = 100 * i - 10;
			Position p = new Position(x, y);
			assertEquals(x, p.getX());
			assertEquals(y, p.getY());
		}
	}

	@Test
	public void testSetX() {
		Position p = new Position();
		for (double i = -10000; i < 10000; i += 100) {
			p.setX(i);
			assertEquals(i, p.getX());
		}
	}

	@Test
	public void testSetY() {
		Position p = new Position();
		for (double i = -10000; i < 10000; i += 100) {
			p.setY(i);
			assertEquals(i, p.getY());
		}
	}

	@Test
	public void testSetPosition() {
		Position p = new Position();
		for (double i = -10000; i < 10000; i += 100) {
			double x = i;
			double y = i + 100;
			p.setPosition(new Position(x, y));
			assertEquals(x, p.getX());
			assertEquals(y, p.getY());
		}
	}

	@Test
	public void testEqualsObject() {
		for (double i = -1000; i < 1000; i++) {
			double x = i;
			double y = i + 100;
			Position p = new Position(x, y);
			Position p2 = new Position(x, y);
			assertEquals(p, p2);
		}
	}

}
