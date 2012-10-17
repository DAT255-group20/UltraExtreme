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

import java.util.Random;

import ultraextreme.model.entity.EnemyShip;
import ultraextreme.model.item.AbstractWeapon;
import ultraextreme.model.util.Constants;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Rotation;

/**
 * Represents an enemy brain/controller.
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Johan Gronvall
 * @author Viktor Anderling
 * 
 */
public abstract class AbstractEnemy implements IEnemy {

	// The entity representing the enemyShip
	private final EnemyShip ship;
	private final AbstractWeapon weapon;
	private final Rotation enemyRotation;
	private final static Random RANDOM_GENERATOR = new Random();

	protected AbstractEnemy(final EnemyShip ship, final AbstractWeapon weapon) {
		this.ship = ship;
		this.weapon = weapon;
		this.enemyRotation = ship.getRotation();
	}

	/**
	 * Fires the weapon assigned to this enemy
	 */
	public void shoot(final float timeElapsed) {
		weapon.fire(ship.getPositionClone(), PlayerID.ENEMY,
				this.enemyRotation, timeElapsed);
	}

	@Override
	public boolean isDead() {
		return ship.isDestroyed();
	}

	@Override
	public EnemyShip getShip() {
		return ship;
	}

	@Override
	public AbstractWeapon getWeapon() {
		return weapon;
	}

	/**
	 * May randomly return true if the enemy is dead based on the dropchance of
	 * weapons.
	 **/
	@Override
	public boolean shouldSpawnPickup() {

		return (RANDOM_GENERATOR.nextInt(99) < Constants.getWeaponDropChance() + 1 && isDead());
	}

	/**
	 * Update method called once per loop, updating the enemy's behavior
	 * 
	 * @param timePassed
	 *            since last update in (seconds?)
	 */
	public abstract void update(float timePassed);
}