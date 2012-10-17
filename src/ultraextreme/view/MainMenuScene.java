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

import javax.vecmath.Vector2d;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.util.Dimension;

/**
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * 
 */
public class MainMenuScene extends MenuScene {

	// Button IDs
	public static final int MENU_START = 0;
	public static final int MENU_HIGHSCORE = 1;
	public static final int MENU_EXIT = 2;

	public MainMenuScene(final Camera camera, final Font font,
			final VertexBufferObjectManager vertexBufferObjectManager) {
		super(camera);

		/*
		 * Add the background.
		 */
		final float screenWidth = camera.getWidth();
		final float screenHeight = camera.getHeight();
		final SpriteBackground background = new SpriteBackground(new Sprite(0,
				0, screenWidth, screenHeight,
				SpriteFactory.getMainMenuBackgroundTexture(),
				vertexBufferObjectManager));
		setBackground(background);

		/*
		 * Scaling
		 */
		final Dimension screenDimension = new Dimension(screenWidth,
				screenHeight);
		Vector2d scaling = screenDimension
				.getQuotient(new Dimension(800, 1280));

		/*
		 * Add the start button. /*
		 */
		final IMenuItem startButton = new SpriteMenuItem(MENU_START,
				SpriteFactory.getMainMenuStartButtonTexture(),
				vertexBufferObjectManager);
		startButton.setWidth((float) scaling.x * startButton.getWidth());
		startButton.setHeight((float) scaling.y * startButton.getHeight());
		startButton.setX((float) scaling.x
				* (screenWidth - startButton.getWidth()) / 2);
		startButton.setY((float) scaling.y * 450);
		addMenuItem(startButton);

		/*
		 * Add the high score button. /*
		 */
		final IMenuItem highScoresButton = new SpriteMenuItem(MENU_HIGHSCORE,
				SpriteFactory.getMainMenuHighScoresButtonTexture(),
				vertexBufferObjectManager);
		highScoresButton.setWidth((float) scaling.x
				* highScoresButton.getWidth());
		highScoresButton.setHeight((float) scaling.y
				* highScoresButton.getHeight());
		highScoresButton.setX((float) scaling.x
				* (screenWidth - highScoresButton.getWidth()) / 2);
		highScoresButton.setY((float) scaling.y * 600);
		addMenuItem(highScoresButton);

		/*
		 * Add the exit button. /*
		 */
		final IMenuItem exitButton = new SpriteMenuItem(MENU_EXIT,
				SpriteFactory.getMainMenuExitButtonTexture(),
				vertexBufferObjectManager);
		exitButton.setWidth((float) scaling.x * exitButton.getWidth());
		exitButton.setHeight((float) scaling.y * exitButton.getHeight());
		exitButton.setX((float) scaling.x
				* (screenWidth - exitButton.getWidth()) / 2);
		exitButton.setY((float) scaling.y * 750);
		addMenuItem(exitButton);
	}
}