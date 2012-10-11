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

package ultraextreme.model.item;

import static org.junit.Assert.fail;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Viktor Anderling
 */
public class SpinningSpreadWeaponTest extends TestCase {
	BulletManager bulletManager;
	SpinningSpreadWeapon spinningWeapon;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.resetInstanceVariables();
	}

	private void resetInstanceVariables() {
		bulletManager = new BulletManager();
		spinningWeapon = new SpinningSpreadWeapon(bulletManager);
	}

	@Test
	public void testFire() {
		fail("Not yet implemented");
	}

	@Test
	public void testSpinningSpreadWeapon() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetInitCooldown() {
		fail("Not yet implemented");
	}
}