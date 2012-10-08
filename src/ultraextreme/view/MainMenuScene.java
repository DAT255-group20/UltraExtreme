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

package ultraextreme.view;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import ultraextreme.util.Resources;
import ultraextreme.util.Resources.ResourceName;
import android.util.Log;

/**
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class MainMenuScene extends MenuScene {

	public static final int MENU_START = 0;

	public MainMenuScene(final Camera camera, final Font font,
			final VertexBufferObjectManager vertexBufferObjectManager) {
		super(camera);
		setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		final IMenuItem startButton = new TextMenuItem(MENU_START, font,
				Resources.getInstance().getResource(ResourceName.START_GAME),
				vertexBufferObjectManager);
		startButton.setPosition(100, 100);
		startButton.setColor(Color.BLACK);
		addMenuItem(startButton);
		Log.d("DEBUG", "onCreateScene");
	}
}