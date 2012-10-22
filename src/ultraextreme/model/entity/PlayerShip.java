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

import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Dimension;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * The player's ship.
 * 
 * @author Bjorn Persson Mattsson
 * @author Viktor Anderling
 * 
 */
public class PlayerShip extends AbstractDestroyableEntity {

	private static final int HITBOX_SIZE = 10;
	/**
	 * If the ship is hit this update.
	 */
	private boolean justHit;

	/**
	 * @return Whether or not the ship is destroyed.
	 */
	private boolean destroyed;

	public PlayerShip() {
		this(0, 0);
	}

	public PlayerShip(final double x, double y) {
		this(x, y, HITBOX_SIZE, HITBOX_SIZE);
	}

	public PlayerShip(final double x, final double y, final int width,
			final int height) {
		super(x, y, width, height, new Rotation(0), ObjectName.PLAYERSHIP);
	}

	/**
	 * Checks so that the ship wont move out of the level along the x-axis.
	 * 
	 * @param deltaX
	 *            The given movement along the x-axis.
	 * 
	 * @return True if movement with the given distance wont take the ship out
	 *         of the level, else false.
	 */
	public boolean canMoveX(double deltaX) {
		final Dimension dimension = Constants.getLevelDimension();
		final Position position = this.getPositionClone();
		final double newX = deltaX + position.getX();
		return newX + getWidth() / 2 < dimension.getX()
				&& newX - getWidth() / 2 > 0;
	}

	/**
	 * Checks so that the ship wont move out of the level along the y-axis.
	 * 
	 * @param deltaX
	 *            The given movement along the y-axis.
	 * 
	 * @return True if movement with the given distance wont take the ship out
	 *         of the level, else false.
	 */
	public boolean canMoveY(double deltaY) {
		final Dimension dimension = Constants.getLevelDimension();
		final Position position = this.getPositionClone();
		double newY = deltaY + position.getY();
		return newY + getHeight() / 2 < dimension.getY()
				&& newY - getHeight() / 2 > 0;
	}

	@Override
	public boolean isDestroyed() {
		return destroyed;
	}

	/**
	 * Returns true if the ship got hit this update, else false.
	 * 
	 * @return True if the ship got hit this update, else false.
	 */
	public boolean justGotHit() {
		return justHit;
	}

	@Override
	public void move(double x, double y) {
		justHit = false;
		super.move(x, y);
	}

	@Override
	public void receiveDamage(int damage) {
		justHit = true;
	}

	public void setDestroyed() {
		destroyed = true;
	}

	/**
	 * Resets values for the ship. Position will not be changed.
	 */
	public void reset() {
		justHit = false;
		destroyed = false;
	}
}
