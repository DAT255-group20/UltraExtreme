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

package ultraextreme.util;

import java.util.HashMap;
import java.util.Map;

public final class Resources {

	private Map<ResourceName, String> resourceMap = new HashMap<ResourceName, String>();

	public enum ResourceName {
		MENU_START_GAME_TEXT, SCORE, LIVES, GOTO_MENU, MENU_HIGHSCORE_TEXT, CLEAR_HIGHSCORE, DEFAULT_HIGHSCORE_NAME, HIGHSCORE_INPUT_TEXT, HIGHSCORE_INPUT_TITLE;
	}

	private static Resources instance;

	private Resources() {
	}

	public static Resources getInstance() {
		if (instance == null) {
			instance = new Resources();
		}
		return instance;
	}

	public String getResource(ResourceName resName) {
		return resourceMap.get(resName);
	}

	public void setResource(ResourceName resName, String resource) {
		resourceMap.put(resName, resource);
	}
}