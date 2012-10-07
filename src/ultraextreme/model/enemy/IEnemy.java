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

package ultraextreme.model.enemy;

import ultraextreme.model.entity.EnemyShip;
import ultraextreme.model.item.AbstractWeapon;

/**
 * Interface for self-thinking enemies
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Johan Gronvall
 * 
 */
public interface IEnemy {

	/**
	 * @return The ship of this enemy.
	 */
	EnemyShip getShip();

	/**
	 * @return true if this enemy has been destroyed
	 */
	boolean isDead();

	/**
	 * true if and only if this enemy should be spawning a pickup
	 * 
	 * @return true if and only if this enemy should be spawning a pickup
	 */
	boolean shouldSpawnPickup();

	/**
	 * @return The weapon of this enemy.
	 */
	AbstractWeapon getWeapon();

	/**
	 * @return The score value that is earned by killing this enemy.
	 */
	int getScoreValue();
}