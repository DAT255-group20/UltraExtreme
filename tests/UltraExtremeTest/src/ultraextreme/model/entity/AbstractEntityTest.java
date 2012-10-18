/* ============================================================
 * Copyright 2012 Bjorn Persson Mattsson, Johan Gronvall, Daniel Jonsson,
 * Viktor Anderling
 *
 * This file is part of UltraExtreme.
 *
 * UltraExtreme is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * UltraExtreme is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with UltraExtreme. If not, see <http://www.gnu.org/licenses/>.
 * ============================================================ */

package ultraextreme.model.entity;

import javax.vecmath.Vector2d;

import junit.framework.TestCase;

import org.junit.Test;

import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Dimension;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * 
 * @author Viktor Anderling
 * @author Daniel Jonsson
 * @author Bjorn Persson Mattsson
 * 
 */
public abstract class AbstractEntityTest extends TestCase {

	private static final double INIT_X = 10;
	private static final double INIT_Y = 20;
	private static final int INIT_WIDTH = 30;
	private static final int INIT_HEIGHT = 40;
	private static final double INIT_ROT_ANGLE = 0;

	protected abstract AbstractEntity getNewAbstractEntity(double x, double y,
			int width, int height, Rotation direction);
	
	private AbstractEntity getNewAbstractEntity(double x, double y,
			int width, int height) {
		return getNewAbstractEntity(x, y, width, height, new Rotation(0));
	}

	private AbstractEntity newEntity() {
		return getNewAbstractEntity(INIT_X, INIT_Y, INIT_WIDTH, INIT_HEIGHT,
				new Rotation(INIT_ROT_ANGLE));
	}

	@Test
	public void testCollidesWith() {
		AbstractEntity e1 = getNewAbstractEntity(10, 10, 10, 10,
				new Rotation(0));
		AbstractEntity e2 = getNewAbstractEntity(30, 10, 10, 10,
				new Rotation(0));

		assertFalse(e1.collidesWith(e2));
		assertFalse(e2.collidesWith(e1));

		e1 = getNewAbstractEntity(10, 10, 10, 10, new Rotation(0));
		e2 = getNewAbstractEntity(15, 15, 10, 10, new Rotation(0));

		assertTrue(e1.collidesWith(e2));
		assertTrue(e2.collidesWith(e1));

		e1 = getNewAbstractEntity(20, 20, 10, 10, new Rotation(0));
		e2 = getNewAbstractEntity(30, 20, 10, 10, new Rotation(0));

		assertTrue(e1.collidesWith(e2));
		assertTrue(e2.collidesWith(e1));

		e1 = getNewAbstractEntity(20, 20, 10, 10, new Rotation(0));
		e2 = getNewAbstractEntity(31, 20, 10, 10, new Rotation(0));

		assertFalse(e1.collidesWith(e2));
		assertFalse(e2.collidesWith(e1));
	}

	@Test
	public void testGetCenteredPosition() {
		AbstractEntity entity = getNewAbstractEntity(20, 20, 10, 10,
				new Rotation(0));
		Position centPos = entity.getCenteredPositionClone();
		assertTrue(centPos.getX() == 25);
		assertTrue(centPos.getY() == 25);

		entity = getNewAbstractEntity(15, 30, 6, 18, new Rotation(0));
		centPos = entity.getCenteredPositionClone();
		assertFalse(centPos.getX() == 25);
		assertFalse(centPos.getY() == 25);
		assertTrue(centPos.getX() == 18);
		assertTrue(centPos.getY() == 39);
	}

	@Test
	public void testGetDirection() {
		AbstractEntity entity = newEntity();
		assertEquals(entity.getRotation(), new Rotation(0));
	}

	@Test
	public void testGetHeight() {
		AbstractEntity entity = newEntity();
		assertEquals(entity.getHeight(), INIT_HEIGHT);
	}

	@Test
	public void testGetPosition() {
		AbstractEntity entity = newEntity();
		assertEquals(entity.getPositionClone().getX(), INIT_X);
		assertEquals(entity.getPositionClone().getY(), INIT_Y);
	}

	@Test
	public void testGetWidth() {
		AbstractEntity entity = newEntity();
		assertEquals(entity.getWidth(), INIT_WIDTH);
	}

	@Test
	public void testIsOutOfScreen() {
		final Dimension screen = Constants.getLevelDimension();
		// Run through a lot of margins
		for (int marginal = 1000; marginal > -300; --marginal) {
			/*
			 * Test outside top
			 */
			int width = 20;
			int height = 10;
			int x = (int) (screen.getX() / 2);
			int y = -marginal + height - 1;
			AbstractEntity e = getNewAbstractEntity(x, y, width, height,
					new Rotation(0));
			// Check it's outside
			assertTrue(e.isOutOfScreen(marginal));
			e = getNewAbstractEntity(x, y + 1, width, height, new Rotation(0));
			// Check so it's now inside
			assertFalse(e.isOutOfScreen(marginal));

			/*
			 * Test outside bottom
			 */
			y = (int) screen.getY() + marginal + 1;
			e = getNewAbstractEntity(x, y, width, height, new Rotation(0));
			// Check it's outside
			assertTrue(e.isOutOfScreen(marginal));
			e = getNewAbstractEntity(x, y - 1, width, height, new Rotation(0));
			// Check so it's now inside
			assertFalse(e.isOutOfScreen(marginal));

			/*
			 * Test outside left
			 */
			x = -marginal + width - 1;
			y = (int) (screen.getX() / 2);
			e = getNewAbstractEntity(x, y, width, height, new Rotation(0));
			// Check it's outside
			assertTrue(e.isOutOfScreen(marginal));
			e = getNewAbstractEntity(x + 1, y, width, height, new Rotation(0));
			// Check so it's now inside
			assertFalse(e.isOutOfScreen(marginal));

			/*
			 * Test outside right
			 */
			x = (int) screen.getX() + marginal + 1;
			e = getNewAbstractEntity(x, y, width, height, new Rotation(0));
			// Check it's outside
			assertTrue(e.isOutOfScreen(marginal));
			e = getNewAbstractEntity(x - 1, y, width, height, new Rotation(0));
			// Check so it's now inside
			assertFalse(e.isOutOfScreen(marginal));
		}
	}

	@Test
	public void testMove() {
		AbstractEntity entity = newEntity();
		entity.move(0, 0);
		assertEquals(entity.getPositionClone(), new Position(INIT_X, INIT_Y));

		entity.move(10, 100);
		assertEquals(entity.getPositionClone(), new Position(INIT_X + 10,
				INIT_Y + 100));

		entity.move(-100, -1000);
		assertEquals(entity.getPositionClone(), new Position(INIT_X - 90.0,
				INIT_Y - 900.0));
	}

	@Test
	public void testSetPosition() {
		AbstractEntity entity = newEntity();
		entity.setPosition(new Position(40, 50));
		assertEquals(entity.getPositionClone().getX(), 40.0);
		assertEquals(entity.getPositionClone().getY(), 50.0);
		entity.setPosition(new Position(-40, -50));
		assertEquals(entity.getPositionClone().getX(), -40.0);
		assertEquals(entity.getPositionClone().getY(), -50.0);
	}

	@Test
	public void testGetNormalizedDirection() {
		AbstractEntity e = getNewAbstractEntity(0, 0, 10, 10);
		
		e.move(10, 10);
		Vector2d direction = e.getNormalizedDirection();
		assertEquals(Math.sqrt(1.0/2.0), direction.x, 0.0000001);
		assertEquals(Math.sqrt(1.0/2.0), direction.y, 0.0000001);
		
		e.move(-10, -10);
		direction = e.getNormalizedDirection();
		assertEquals(-Math.sqrt(1.0/2.0), direction.x, 0.0000001);
		assertEquals(-Math.sqrt(1.0/2.0), direction.y, 0.0000001);
		
		e.move(1, 0);
		direction = e.getNormalizedDirection();
		assertEquals(1.0, direction.x);
		assertEquals(0.0, direction.y);
	}

	@Test
	public void testGetRotation() {
		for (double i = 0; i < 2 * Math.PI; i += Math.PI / 10) {
			Rotation r = new Rotation(i);
			AbstractEntity e = getNewAbstractEntity(10, 10, 10, 10, r);
			assertEquals(r.getAngle(), e.getRotation().getAngle());
		}
	}

	@Test
	public abstract void testGetObjectName();
}
