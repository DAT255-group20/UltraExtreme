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

/**
 * A class that wants to know when an ItemBar has been updated/changed needs to
 * implement this and add itself as a listener to the ItemBar.
 * 
 * @author Daniel Jonsson
 * 
 */
public interface ItemBarUpdateListener {

	/**
	 * An item bar has been updated.
	 * 
	 * @param itemBar
	 *            The item bar that has been updated.
	 */
	public void itemBarUpdated(ItemBar itemBar);
}
