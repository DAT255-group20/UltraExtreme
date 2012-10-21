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

package ultraextreme.controller;

/**
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class ControllerEvent {

	private final AbstractController source;
	private final ControllerEventType eventType;

	public enum ControllerEventType {
		SWITCH_TO_GAME, SWITCH_TO_MENU, SWITCH_TO_HIGHSCORE, SWITCH_TO_GAMEOVER, EXIT_GAME;
	}

	public ControllerEvent(final AbstractController source,
			final ControllerEventType eventType) {
		this.source = source;
		this.eventType = eventType;
	}

	public AbstractController getSource() {
		return source;
	}

	public ControllerEventType getEventType() {
		return eventType;
	}
}
