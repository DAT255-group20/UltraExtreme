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

import ultraextreme.model.entity.BasicBullet;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Johan Gronvall
 * @author Viktor Anderling
 * 
 */
public class SpinningWeapon extends AbstractWeapon {

	private static final int BULLET_WIDTH = 20;
	private static final int BULLET_HEIGHT = 40;
	private static final int BULLET_DAMAGE = 5;
	private static final float INIT_COOLDOWN = 1 / 6f;
	private static final double ANGLE_STEP = Math.PI / 12;
	private float cooldown;
	private double currentAngle;

	public SpinningWeapon(final BulletManager bulletManager) {
		super(bulletManager, ObjectName.SPINNING_WEAPON);
		cooldown = INIT_COOLDOWN;
		currentAngle = 0;
	}

	@Override
	public void fire(final Position shipPosition, final PlayerID playerId,
			final Rotation rotation, final float timeElapsed) {
		cooldown = cooldown - timeElapsed;
		if (cooldown < 0) {
			cooldown = cooldown + INIT_COOLDOWN;
			this.getBulletManager().addBullet(
					new BasicBullet(shipPosition.getX(), shipPosition.getY(),
							BULLET_WIDTH, BULLET_HEIGHT, playerId,
							new Rotation(rotation.getAngle() + currentAngle),
							BULLET_DAMAGE));
			currentAngle = currentAngle + ANGLE_STEP;
		}
	}

	public static float getInitCooldown() {
		return INIT_COOLDOWN;
	}

	@Override
	public AbstractWeapon getNewInstance() {
		return new SpinningWeapon(this.getBulletManager());
	}

}
