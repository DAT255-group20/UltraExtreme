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
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * A pickupable weapon or bomb. What kind of weapon this pickup represents is
 * saved as its ObjectName
 * 
 * @author Johan Gronvall
 * 
 */
public class WeaponPickup extends AbstractEntity {
	private static final int width = 20;

	public WeaponPickup(double x, double y, ObjectName weaponType) {
		super(x, y, width, width, new Rotation(0), weaponType);
	}

	public WeaponPickup(Position position, ObjectName name) {
		super(position, width, width, name);
	}
}