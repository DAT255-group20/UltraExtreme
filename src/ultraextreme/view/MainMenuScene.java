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

import ultraextreme.model.util.Position;
import ultraextreme.util.Resources;
import ultraextreme.util.Resources.ResourceName;

/**
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class MainMenuScene extends MenuScene {

	// Button IDs
	public static final int MENU_START = 0;
	public static final int MENU_HIGHSCORE = 1;
	
	// TODO Make these centered on the screen
	private static final Position START_BTN_POS = new Position(100, 100);
	private static final Position HIGHSCORE_BTN_POS = new Position(100, 200);

	public MainMenuScene(final Camera camera, final Font font,
			final VertexBufferObjectManager vertexBufferObjectManager) {
		super(camera);
		setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		
		final IMenuItem startButton = new TextMenuItem(MENU_START, font,
				Resources.getInstance().getResource(ResourceName.MENU_START_GAME_TEXT),
				vertexBufferObjectManager);
		startButton.setPosition((float)START_BTN_POS.getX(), (float)START_BTN_POS.getY());
		startButton.setColor(Color.BLACK);
		addMenuItem(startButton);
		
		final IMenuItem highscoreButton = new TextMenuItem(MENU_HIGHSCORE, font,
				Resources.getInstance().getResource(ResourceName.MENU_HIGHSCORE_TEXT),
				vertexBufferObjectManager);
		highscoreButton.setPosition((float)HIGHSCORE_BTN_POS.getX(), (float)HIGHSCORE_BTN_POS.getY());
		highscoreButton.setColor(Color.BLACK);
		addMenuItem(highscoreButton);
	}
}