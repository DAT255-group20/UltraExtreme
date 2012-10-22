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

import java.util.EnumMap;
import java.util.Map;

import javax.vecmath.Vector2d;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.util.Difficulty;
import ultraextreme.model.util.Dimension;
import ultraextreme.view.SpriteFactory.OptionsTexture;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class OptionsScene extends MenuScene {

	// Button IDs
	public static final int CHANGE_DIFFICULTY = 0;
	public static final int RESET_HIGH_SCORES = 1;
	public static final int RETURN_TO_MAIN_MENU = 2;

	/**
	 * Map with the different kinds of difficulty buttons
	 */
	private Map<Difficulty, IMenuItem> difficultyButtons;

	/**
	 * Which difficulty the button was last set to. This is used when updating
	 * the difficulty button to easily deatch the old one.
	 */
	private Difficulty lastDifficulty;

	/**
	 * 
	 * @param camera
	 * @param vertexBufferObjectManager
	 * @param difficulty
	 *            Which difficulty level it should be set to from the beginning.
	 */
	public OptionsScene(final Camera camera,
			final VertexBufferObjectManager vertexBufferObjectManager,
			Difficulty difficulty) {
		super(camera);

		/*
		 * Add the background.
		 */
		final float screenWidth = camera.getWidth();
		final float screenHeight = camera.getHeight();
		final SpriteBackground background = new SpriteBackground(new Sprite(0,
				0, screenWidth, screenHeight,
				SpriteFactory.getOptionsTexture(OptionsTexture.BACKGROUND),
				vertexBufferObjectManager));
		setBackground(background);

		/*
		 * Scaling
		 */
		final Dimension screenDimension = new Dimension(screenWidth,
				screenHeight);
		// The following resolution is what the background and buttons were made
		// for
		Vector2d scaling = screenDimension
				.getQuotient(new Dimension(800, 1280));

		/*
		 * Store all difficulty buttons in a map and add the current difficulty
		 * button to the scene.
		 */
		difficultyButtons = new EnumMap<Difficulty, IMenuItem>(Difficulty.class);
		difficultyButtons.put(
				Difficulty.NORMAL,
				createButton(CHANGE_DIFFICULTY, 350,
						OptionsTexture.NORMAL_DIFFICULTY, scaling, screenWidth,
						vertexBufferObjectManager));
		difficultyButtons.put(
				Difficulty.HARD,
				createButton(CHANGE_DIFFICULTY, 350,
						OptionsTexture.HARD_DIFFICULTY, scaling, screenWidth,
						vertexBufferObjectManager));
		difficultyButtons.put(
				Difficulty.EXTREME,
				createButton(CHANGE_DIFFICULTY, 350,
						OptionsTexture.EXTREME_DIFFICULTY, scaling,
						screenWidth, vertexBufferObjectManager));
		difficultyButtons.put(
				Difficulty.ULTRAEXTREME,
				createButton(CHANGE_DIFFICULTY, 350,
						OptionsTexture.ULTRAEXTREME_DIFFICULTY, scaling,
						screenWidth, vertexBufferObjectManager));
		addMenuItem(difficultyButtons.get(difficulty));
		lastDifficulty = difficulty;

		/*
		 * Add the reset button.
		 */
		addMenuItem(createButton(RESET_HIGH_SCORES, 650,
				OptionsTexture.RESET_BUTTON, scaling, screenWidth,
				vertexBufferObjectManager));

		/*
		 * Add the return button.
		 */
		addMenuItem(createButton(RETURN_TO_MAIN_MENU, 950,
				OptionsTexture.RETURN_BUTTON, scaling, screenWidth,
				vertexBufferObjectManager));
	}

	/**
	 * Create a menu button.
	 * 
	 * @param destination
	 * @param y
	 * @param texture
	 * @param scaling
	 * @param screenWidth
	 * @param vertexBufferObjectManager
	 */
	private IMenuItem createButton(final int destination, final int y,
			final OptionsTexture texture, final Vector2d scaling,
			final float screenWidth,
			final VertexBufferObjectManager vertexBufferObjectManager) {
		final IMenuItem button = new SpriteMenuItem(destination,
				SpriteFactory.getOptionsTexture(texture),
				vertexBufferObjectManager);
		button.setWidth((float) scaling.x * button.getWidth());
		button.setHeight((float) scaling.y * button.getHeight());
		button.setX((screenWidth - button.getWidth()) / 2);
		button.setY((float) scaling.y * y);
		return button;
	}

	/**
	 * Switch to the next difficulty level.
	 */
	public void updateDifficultyButton(Difficulty difficulty) {
		detachChild(difficultyButtons.get(lastDifficulty));
		addMenuItem(difficultyButtons.get(difficulty));
		lastDifficulty = difficulty;
	}
}