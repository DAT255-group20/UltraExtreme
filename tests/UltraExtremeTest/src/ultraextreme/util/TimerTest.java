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

package ultraextreme.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TimerTest {

	private Timer timer;
	private Object o;

	@Before
	public void setUp() throws Exception {
		timer = null;
		o = new Object();
	}

	@Test
	public void testGetObject() {
		Object o1 = new Object();
		timer = new Timer("", 100, o1);
		assertTrue(o1 == timer.getObject());
	}

	@Test
	public void testGetPropertyName() {
		String s = "123";
		timer = new Timer(s, 100, o);
		assertTrue(s == timer.getPropertyName());
	}

	@Test
	public void testIsRunning() {
		timer = new Timer("", 100, o, 1);
		assertTrue(timer.isRunning());
		timer.update(99f);
		assertTrue(timer.isRunning());
		timer.update(2f);
		assertFalse(timer.isRunning());

		timer = new Timer("", 100, o, 3);
		assertTrue(timer.isRunning());
		timer.update(101f);
		assertTrue(timer.isRunning());
		timer.update(10f);
		assertTrue(timer.isRunning());
		timer.update(10f);
		assertTrue(timer.isRunning());
		timer.update(100f);
		assertTrue(timer.isRunning());
		timer.update(100f);
		assertFalse(timer.isRunning());
	}

	@Test
	public void testTimerStringFloatObject() {
		boolean ticked = false;

		timer = new Timer("hej123", 100f, o, 1);
		assertTrue(o == timer.getObject());
		assertTrue(timer.isRunning());
		assertEquals(timer.getPropertyName(), "hej123");

		ticked = timer.update(60f);
		assertFalse(ticked);
		assertTrue(timer.isRunning());

		ticked = timer.update(50f);
		assertTrue(ticked);
		assertFalse(timer.isRunning());
		assertEquals(timer.getPropertyName(), "hej123");
		assertTrue(o == timer.getObject());
	}

	@Test
	public void testTimerStringFloatObjectInt() {
		boolean ticked = false;

		timer = new Timer("hej123", 100f, o, 1);
		ticked = timer.update(101f);
		assertTrue(o == timer.getObject());
		assertTrue(ticked);
		assertFalse(timer.isRunning());
		assertEquals(timer.getPropertyName(), "hej123");

		timer = new Timer("hej321", 100f, o, 2);
		ticked = timer.update(101f);
		assertTrue(ticked);
		assertTrue(o == timer.getObject());
		assertTrue(timer.isRunning());
		assertEquals(timer.getPropertyName(), "hej321");

		ticked = timer.update(101f);
		assertTrue(ticked);
		assertFalse(timer.isRunning());
	}

	@Test
	public void testUpdate() {
		boolean ticked = false;

		timer = new Timer("hej123", 100f, o, 1);
		ticked = timer.update(90f);
		assertFalse(ticked);
		assertTrue(timer.isRunning());
		assertEquals(timer.getPropertyName(), "hej123");

		timer = new Timer("hej123", 150f, o, 2);
		ticked = timer.update(110f);
		assertFalse(ticked);
		assertTrue(timer.isRunning());
		ticked = timer.update(50f);
		assertTrue(ticked);
		assertTrue(timer.isRunning());
		ticked = timer.update(150f);
		assertTrue(ticked);
		assertFalse(timer.isRunning());
	}
}
