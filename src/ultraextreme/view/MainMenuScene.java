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
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;

/**
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * 
 */
public class MainMenuScene extends MenuScene {

	public static final int MENU_START = 0;

	public static final int MENU_HIGH_SCORES = 1;

	public static final int MENU_EXIT = 2;

	public MainMenuScene(final Camera camera, final Font font,
			final VertexBufferObjectManager vertexBufferObjectManager) {
		super(camera);

		/*
		 * Add the background.
		 */
		final float width = camera.getWidth();
		final float height = camera.getHeight();
		final SpriteBackground background = new SpriteBackground(new Sprite(0,
				0, width, height, SpriteFactory.getInstance()
						.getMainMenuBackgroundTexture(),
				vertexBufferObjectManager));
		setBackground(background);

		/*
		 * Add the start button. /*
		 */
		final IMenuItem startButton = new SpriteMenuItem(MENU_START,
				SpriteFactory.getInstance().getMainMenuStartButtonTexture(),
				vertexBufferObjectManager);
		final float scale = width / 800;
		startButton.setWidth(scale * startButton.getWidth());
		startButton.setHeight(scale * startButton.getHeight());
		startButton.setX(scale * (width - startButton.getWidth()) / 2);
		startButton.setY(scale * 400);
		addMenuItem(startButton);

		/*
		 * Add the high score button. /*
		 */
		final IMenuItem highScoresButton = new SpriteMenuItem(MENU_HIGH_SCORES,
				SpriteFactory.getInstance()
						.getMainMenuHighScoresButtonTexture(),
				vertexBufferObjectManager);
		highScoresButton.setWidth(scale * highScoresButton.getWidth());
		highScoresButton.setHeight(scale * highScoresButton.getHeight());
		highScoresButton
				.setX(scale * (width - highScoresButton.getWidth()) / 2);
		highScoresButton.setY(scale * 550);
		addMenuItem(highScoresButton);

		/*
		 * Add the exit button. /*
		 */
		final IMenuItem exitButton = new SpriteMenuItem(MENU_EXIT,
				SpriteFactory.getInstance().getMainMenuExitButtonTexture(),
				vertexBufferObjectManager);
		exitButton.setWidth(scale * exitButton.getWidth());
		exitButton.setHeight(scale * exitButton.getHeight());
		exitButton.setX(scale * (width - exitButton.getWidth()) / 2);
		exitButton.setY(scale * 700);
		addMenuItem(exitButton);

		Log.d("DEBUG", "onCreateScene");
	}
}