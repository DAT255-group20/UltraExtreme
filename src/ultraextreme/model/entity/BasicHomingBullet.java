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

import javax.vecmath.Vector2d;

import ultraextreme.model.util.Constants;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * A bullet that constantly changes its direction towards its target, until it
 * runs out of fuel and then continues straight forward.
 * 
 * @author Viktor Anderling
 * 
 */
public class BasicHomingBullet extends AbstractHomingBullet {

	private final Vector2d normDirectionVector;

	private static final float speed = 300f;
	/**
	 * Distance left before stopping to track the enemy.
	 */
	private double bulletFuel;

	public BasicHomingBullet(final double x, final double y, final int width,
			final int height, final PlayerID playerId,
			AbstractDestroyableEntity target) {
		super(x, y, width, height, playerId, new Rotation(0),
				ObjectName.BASIC_HOMING_BULLET);
		this.setTarget(target);
		normDirectionVector = new Vector2d();
		updateDirection();
		bulletFuel = Constants.getLevelDimension().getY() * 0.8; // Will
																	// track
																	// 80%
																	// of
																	// the
																	// levels
																	// length.

	}

	private void updateDirection() {
		final Position targetPosition = target.getPositionClone();
		final Position thisPosition = this.getPositionClone();
		normDirectionVector.normalize(new Vector2d(targetPosition.getX()
				- thisPosition.getX(), targetPosition.getY()
				- thisPosition.getY()));
	}

	@Override
	public void doMovement(float timePassed) {
		if (!((AbstractDestroyableEntity) target).isDestroyed()
				|| bulletFuel < 0) {
			updateDirection();
		}
		final double xMovement = normDirectionVector.x * timePassed * speed;
		final double yMovement = normDirectionVector.y * timePassed * speed;
		bulletFuel = bulletFuel
				- Math.sqrt(xMovement * xMovement + yMovement * yMovement);

		this.move(xMovement, yMovement);
	}

	@Override
	public Vector2d getNormalizedDirection() {
		return normDirectionVector;

	}

}
