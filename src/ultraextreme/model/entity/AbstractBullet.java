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

import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Rotation;

/**
 * An abstract class representing a generic Bullet.
 * 
 * @author Bjorn Persson Mattsson
 * @author Viktor Anderling
 * 
 */
public abstract class AbstractBullet extends AbstractEntity implements IBullet {

	// What player shot this bullet
	private final PlayerID playerId;

	private boolean markedForRemoval = false;

	/**
	 * 
	 * @param playerId
	 *            The owner of the bullet.
	 * @param direction
	 *            The direction the bullet is shot at.
	 */
	public AbstractBullet(final double x, final double y, final int width,
			final int height, PlayerID playerId, Rotation rotation,
			final ObjectName bulletType) {
		super(x, y, width, height, rotation, bulletType);
		this.playerId = playerId;
	}

	/**
	 * Executes the bullets movement algorithm.
	 * 
	 * @param timePassed
	 *            Time passed since last update.
	 */
	public abstract void doMovement(float timePassed);

	@Override
	public PlayerID getPlayerId() {
		return playerId;
	}

	@Override
	public void markForRemoval() {
		markedForRemoval = true;
	}

	@Override
	public boolean isMarkedForRemoval() {
		return markedForRemoval;
	}

	@Override
	public int getDamage() {
		return 10;
	}
}