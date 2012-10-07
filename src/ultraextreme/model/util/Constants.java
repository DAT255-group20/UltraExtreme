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

/**
 * This class defines constants in the model such as fixed dimensions etc.
 * 
 * @author Viktor Anderling
 * 
 */
public final class Constants {

	public static final String EVENT_ENEMY_KILLED = "enemyKilled";
	public static final String EVENT_ENEMY_DAMAGED ="enemyDamaged";
	public static final String EVENT_NEW_ENTITY = "newEntity";
	public static final String EVENT_REMOVED_ENTITY = "removedEntity";

	private Constants() {
	}

	/**
	 * @return The relative dimension of a arbitrary level.
	 */
	public static Dimension getLevelDimension() {
		return new Dimension(900, 1600);
	}

	/**
	 * returns the dropChance of a weaponPickup upon enemy death as a percentage
	 * 
	 * @return
	 */
	public static int getWeaponDropChance() {
		return 10;
	}

	/**
	 * @return A positive double that impacts how fast the enemy ships will move
	 */
	public static double getEnemySpeedModifier() {
		return 1;
	}

	/**
	 * @return A positive double that impacts how fast the player ships will
	 *         move
	 */
	public static double getPlayerSpeedModifier() {
		return 1;
	}

	/**
	 * @return A positive double that impacts how fast the upgrade items will
	 *         move
	 */
	public static double getPickupSpeedModifier() {
		return 1;
	}

	/**
	 * @return A positive double that impacts how fast the bullets will move
	 */
	public static double getBulletSpeedModifier() {
		return 300;
	}

	/**
	 * @return The time in seconds of which the will stay invincible after
	 *         receiving damage.
	 */
	public static double getShipInvincibilityTime() {
		return 3;
	}

	/**
	 * @return The number of lives the ship will start with.
	 */
	public static int getInitShipLives() {
		return 3;
	}
}
