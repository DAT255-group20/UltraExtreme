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

import org.junit.Test;

import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class PlayerShipTest extends AbstractEntityTest {

	private PlayerShip playerShip;

	@Override
	protected AbstractEntity getNewAbstractEntity(double x, double y,
			int width, int height, Rotation direction) {
		return new PlayerShip(x, y, width, height);
	}

	private void resetInstanceVariables(double x, double y, int width,
			int height) {
		playerShip = new PlayerShip(x, y, width, height);
	}

	@Override
	public void testGetDirection() {
		resetInstanceVariables(0, 0, 0, 0);
		assertEquals(playerShip.getRotation(), new Rotation(0));
	}

	/**
	 * Test if it's possible to destroy the ship. FIXME: Not implemented yet.
	 */
	public void testIsDestroyed() {
		// FIXME: Since the ship can't get hurt yet this is hard to test.
		fail("See above comment");
		resetInstanceVariables(0, 0, 0, 0);
		assertEquals(playerShip.isDestroyed(), false);
	}

	/**
	 * Test if it's possible to move the ship.
	 */
	@Override
	public void testMove() {
		resetInstanceVariables(10, 20, 30, 40);
		playerShip.move(0, 0);
		assertEquals(playerShip.getPositionClone(), new Position(10.0, 20.0));

		playerShip.move(10, 100);
		assertEquals(playerShip.getPositionClone(), new Position(20.0, 120.0));

		playerShip.move(-100, -1000);
		assertEquals(playerShip.getPositionClone(), new Position(-80.0, -880.0));
	}
	
	@Override
	public void testGetRotation() {
		resetInstanceVariables(10, 20, 30, 40);
		assertEquals(0, playerShip.getRotation().getAngle());
	}
	
	public void testCanMove()
	{
		fail("Not yet tested");
	}
	
	public void testJustGotHit()
	{
		fail("Not yet tested");
	}
	
	public void testReceiveDamage()
	{
		fail("Not yet tested");
	}
	
	public void testSetDestroyed()
	{
		fail("Not yet tested");
	}

	@Override
	@Test
	public void testGetObjectName() {
		resetInstanceVariables(0, 0, 0, 0);
		assertEquals(ObjectName.PLAYERSHIP, playerShip.getObjectName());
	}
}
