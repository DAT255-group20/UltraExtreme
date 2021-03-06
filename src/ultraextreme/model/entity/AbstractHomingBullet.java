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
 * An abstract class representing a generic HomingBullet.
 * 
 * @author Bjorn Persson Mattsson
 * @author Viktor Anderling
 * 
 */
public abstract class AbstractHomingBullet extends AbstractBullet {

	protected AbstractEntity target;

	public AbstractHomingBullet(final double x, final double y,
			final int width, final int height, PlayerID playerId,
			Rotation direction, final ObjectName bulletType, int bulletDamage) {
		super(x, y, width, height, playerId, direction, bulletType,
				bulletDamage);
	}

	/**
	 * Sets the target for the homing bullet.
	 * 
	 * @param target
	 *            Target
	 */
	public void setTarget(AbstractEntity target) {
		this.target = target;
	}
}