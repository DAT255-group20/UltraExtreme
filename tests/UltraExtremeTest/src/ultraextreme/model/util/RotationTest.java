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

package ultraextreme.model.util;

import javax.vecmath.Vector2d;

import junit.framework.TestCase;

/**
 * 
 * @author Viktor Anderling
 * 
 */
public class RotationTest extends TestCase {

	Rotation rotation;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetRotatedCoordinates() {
		rotation = new Rotation(0);
		Vector2d vec = rotation.getRotatedCoordinates(1, 1);
		assertTrue(vec.x == 1 && vec.y == 1);

		double epsilon = 0.0000000000001;

		rotation = new Rotation(Math.PI);
		vec = rotation.getRotatedCoordinates(1, 1);
		assertTrue(-1 - vec.x < epsilon && -1 - vec.y < epsilon);

		rotation = new Rotation(Math.PI / 2);
		vec = rotation.getRotatedCoordinates(1, 1);
		assertTrue(-1 - vec.x < epsilon && 1 - vec.y < epsilon);
	}
}
