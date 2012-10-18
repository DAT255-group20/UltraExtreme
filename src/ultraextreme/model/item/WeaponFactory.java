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

import java.util.HashMap;
import java.util.Map;

import ultraextreme.model.util.ObjectName;

/**
 * A factory class for weapons in charge of creating weapons
 * 
 * @author Johan Gronvall
 * 
 */
public final class WeaponFactory {

	private Map<ObjectName, AbstractWeapon> weaponMap;

	private static WeaponFactory instance;

	private WeaponFactory(BulletManager manager) {
		weaponMap = new HashMap<ObjectName, AbstractWeapon>();
		weaponMap.put(ObjectName.BASIC_WEAPON, new BasicWeapon(manager));
		weaponMap.put(ObjectName.SPINNING_WEAPON, new SpinningWeapon(manager));
		weaponMap.put(ObjectName.BASIC_SPREAD_WEAPON, new SpreadWeapon(manager));
		weaponMap.put(ObjectName.BOMB, new Bomb(manager));
	}

	/**
	 * WeaponFactory must have been initialized before it can be used;
	 * 
	 * @param bulletManager
	 *            A reference to a bullet manager.
	 */
	public static void initialize(BulletManager bulletManager) {
		instance = new WeaponFactory(bulletManager);
	}

	/**
	 * Returns a new instance of the specified weapon
	 * 
	 * @param objectName
	 *            the name of the desired weapon
	 * @return a new instance of the specified weapon
	 */
	public static AbstractWeapon getNewWeapon(ObjectName objectName) {
		return instance.weaponMap.get(objectName).getNewInstance();
	}
}