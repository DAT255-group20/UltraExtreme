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

package ultraextreme.model.enemyspawning.wave;

import ultraextreme.model.enemy.AbstractEnemy;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * This class is used by RandomWaveList and RectangleWave so there can be
 * rectangles with different enemies.
 * 
 * @author Daniel Jonsson
 * 
 */
public abstract class AbstractEnemyProvider {

	/**
	 * Build an enemy.
	 * 
	 * @param spawningPosition
	 *            The enemy ship's spawning position.
	 * @param rotation
	 *            Rotation of the enemy ship.
	 * @return AbstractEnemy
	 */
	public abstract AbstractEnemy getEnemy(Position spawningPosition,
			Rotation rotation);
}
