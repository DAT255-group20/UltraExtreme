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

package ultraextreme.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class ModelInputTest {

	@Test
	public void testModelInput() {
		double dX1 = 34.324;
		double dX2 = 62.135;
		double dY1 = 8974.12;
		double dY2 = 213.54;
		boolean fireWeapons1 = true;
		boolean fireWeapons2 = false;
		boolean dropBomb1 = false;
		boolean dropBomb2 = true;

		ModelInput input = new ModelInput(dX1, dY1, fireWeapons1, dropBomb1);

		assertTrue(input.dX == dX1);
		assertTrue(input.dY == dY1);
		assertTrue(input.fireWeapons == fireWeapons1);
		assertTrue(input.dropBomb == dropBomb1);

		input = new ModelInput(dX2, dY2, fireWeapons2, dropBomb2);

		assertFalse(input.dX == dX1);
		assertFalse(input.dY == dY1);
		assertFalse(input.fireWeapons == fireWeapons1);
		assertFalse(input.dropBomb == dropBomb1);

		assertTrue(input.dX == dX2);
		assertTrue(input.dY == dY2);
		assertTrue(input.fireWeapons == fireWeapons2);
		assertTrue(input.dropBomb == dropBomb2);
	}
}