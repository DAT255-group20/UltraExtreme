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

package ultraextreme.model;

/**
 * A container class for all input information that goes into the model.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class ModelInput {

	/** The player movement change on the x axis. */
	public final double dX;

	/** The player movement change on the y axis. */
	public final double dY;

	/** True if and only if the player is firing his weapons. */
	public final boolean fireWeapons;

	/** True if and only if the player is dropping a bomb. */
	public final boolean dropBomb;

	/**
	 * Creates a new immutable ModelInput.
	 * 
	 * @param dX
	 *            The player movement change on the x axis
	 * @param dY
	 *            The player movement change on the y axis
	 * @param fireWeapons
	 *            True if and only if the player is firing his weapons
	 * @param dropBomb
	 *            True if and only if the player is dropping a bomb
	 */
	public ModelInput(final double dX, final double dY,
			final boolean fireWeapons, final boolean dropBomb) {
		this.dX = dX;
		this.dY = dY;
		this.fireWeapons = fireWeapons;
		this.dropBomb = dropBomb;
	}
}