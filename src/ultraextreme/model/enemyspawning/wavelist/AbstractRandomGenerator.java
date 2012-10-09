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

package ultraextreme.model.enemyspawning.wavelist;

/**
 * This can be given to the RandomWaveList's constructor, so you can customize
 * the random numbers it will use. This makes the list more testable.
 * 
 * @author Daniel Jonsson
 * 
 */
public abstract class AbstractRandomGenerator {

	/**
	 * Return a float between 0.0 and 1.0.
	 * 
	 * @return A random float between 0.0 and 1.0.
	 */
	public abstract float nextFloat();
}
