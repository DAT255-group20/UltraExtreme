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

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.controller.ControllerEvent.ControllerEventType;
import ultraextreme.view.MainMenuScene;

/**
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class MainMenuController extends AbstractController implements
		IOnMenuItemClickListener {

	private final MainMenuScene scene;

	public MainMenuController(final Camera camera, final Font font,
			final VertexBufferObjectManager vertexBufferObjectManager) {
		super();
		scene = new MainMenuScene(camera, font, vertexBufferObjectManager);
		scene.setOnMenuItemClickListener(this);
	}

	@Override
	public boolean onMenuItemClicked(final MenuScene menuScene,
			final IMenuItem menuItem, float menuItemLocalX, float menuItemLocalY) {
		switch (menuItem.getID()) {
		case MainMenuScene.MENU_START:
			fireEvent(new ControllerEvent(this,
					ControllerEventType.SWITCH_TO_GAME));
			break;
			
		case MainMenuScene.MENU_HIGHSCORE:
			fireEvent(new ControllerEvent(this,
					ControllerEventType.SWITCH_TO_HIGHSCORE));
			break;

		default:
			break;
		}
		return true;
	}

	@Override
	public Scene getScene() {
		return scene;
	}

	@Override
	public void activateController() {
		// Auto-generated method stub

	}

	@Override
	public void deactivateController() {
		// Auto-generated method stub

	}
}