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

import javax.vecmath.Vector2d;

/**
 * Defines an immutable positive dimension with an x-value and an y-value with
 * decimals.
 * 
 * @author Viktor Anderling
 * 
 */
public class Dimension {

	private double xValue;
	private double yValue;

	/**
	 * @precon Both x and y must be positive doubles.
	 * 
	 * @param x
	 *            A positive double.
	 * @param y
	 *            A positive double.
	 */
	public Dimension(final double x, final double y) {
		if (x <= 0 || y <= 0) {
			throw new IllegalArgumentException("Non-positive input value");
		} else {
			this.xValue = x;
			this.yValue = y;
		}
	}

	/**
	 * @return The length of the dimension along the x-axis
	 */
	public double getX() {
		return xValue;
	}

	/**
	 * @return The length of the dimension along the y-axis
	 */
	public double getY() {
		return yValue;
	}

	/**
	 * Calculates the quotient between this and the other dimension
	 * 
	 * @param otherDimension
	 * @return A vector with an x-quotient and an y-quotient.
	 */
	public Vector2d getQuotient(final Dimension otherDimension) {
		return new Vector2d(this.getX() / otherDimension.getX(), this.getY()
				/ otherDimension.getY());
	}

	/**
	 * Scales the given position in the given dimension to a position of this
	 * dimension.
	 * 
	 * @param otherDimension
	 *            The dimension of the other position.
	 * @param otherPosition
	 *            The other position that will be scaled.
	 * @return A position scaled from the other position.
	 */
	public Position scalePosition(final Dimension otherDimension,
			final Position otherPosition) {
		if (this.equals(otherDimension)) {
			return otherPosition;
		} else {
			final Vector2d quotientVector = this.getQuotient(otherDimension);
			return new Position(quotientVector.x * otherPosition.getX(),
					quotientVector.y * otherPosition.getY());
		}
	}

	@Override
	public int hashCode() {
		// Generated by Eclipse
		final int prime = 181;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(xValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(yValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// Generated by Eclipse
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Dimension other = (Dimension) obj;
		if (Double.doubleToLongBits(xValue) != Double
				.doubleToLongBits(other.xValue)) {
			return false;
		}
		if (Double.doubleToLongBits(yValue) != Double
				.doubleToLongBits(other.yValue)) {
			return false;
		}
		return true;
	}
}