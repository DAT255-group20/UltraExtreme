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

import java.util.Collections;
import java.util.List;

import javax.vecmath.Vector2d;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import ultraextreme.model.util.Dimension;
import ultraextreme.model.util.Position;
import ultraextreme.view.SpriteFactory.HighScoresTexture;

/**
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 *
 */
public class HighscoreScene extends MenuScene {

	public static final int GOTO_MENU = 0;

	private static final int NR_OF_HIGHSCORES = 10;

	// TODO(plankton) Make these centered on the screen
	private static final Position HIGHSCORE_HEADER_POS = new Position(200, 400);
	private static final Position HIGHSCORE_LIST_POS = new Position(180, 420);
	private static final int HIGHSCORE_DISPERSION = 50;

	private HighscoreText[] highscores = new HighscoreText[NR_OF_HIGHSCORES];

	public HighscoreScene(Camera camera, Font font,
			VertexBufferObjectManager vbo) {
		super(camera);

		/*
		 * Add the background.
		 */
		final float screenWidth = camera.getWidth();
		final float screenHeight = camera.getHeight();
		final SpriteBackground background = new SpriteBackground(new Sprite(0,
				0, screenWidth, screenHeight,
				SpriteFactory
						.getHighScoresTexture(HighScoresTexture.BACKGROUND),
				vbo));
		setBackground(background);

		// FIXME: These lines can be found in all menu scenes I think ...
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
		 * Add a return-to-main-menu button.
		 */
		addMenuItem(createButton(GOTO_MENU, 1000,
				HighScoresTexture.RETURN_BUTTON, scaling, screenWidth, vbo));

		// FIXME Resources.GOTO_MENU not needed anymore
		
		// FIXME Resources.CLEAR_LIST not needed anymore

		Text highscoreHeader = new Text((float) HIGHSCORE_HEADER_POS.getX(),
				(float) HIGHSCORE_HEADER_POS.getY(), font, "Name  |  Score",
				vbo);
		highscoreHeader.setColor(Color.BLACK);
		attachChild(highscoreHeader);
		for (int i = 0; i < highscores.length; i++) {
			highscores[i] = new HighscoreText(new Position(
					HIGHSCORE_LIST_POS.getX(), HIGHSCORE_LIST_POS.getY() + i
							* HIGHSCORE_DISPERSION), font, vbo, i + 1);
			attachChild(highscores[i]);
		}
	}

	// FIXME: Copied method from OptionsScene and sligtly modified. Maybe we
	// could use a menu abstract class or something.
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
			final HighScoresTexture texture, final Vector2d scaling,
			final float screenWidth,
			final VertexBufferObjectManager vertexBufferObjectManager) {
		final IMenuItem button = new SpriteMenuItem(destination,
				SpriteFactory.getHighScoresTexture(texture),
				vertexBufferObjectManager);
		button.setWidth((float) scaling.x * button.getWidth());
		button.setHeight((float) scaling.y * button.getHeight());
		button.setX((screenWidth - button.getWidth()) / 2);
		button.setY((float) scaling.y * y);
		return button;
	}

	/**
	 * Displays the highscores on the screen.
	 * 
	 * @param highscores
	 *            The list of highscores.
	 */
	public void displayHighscore(List<Highscore> highscores) {
		Collections.sort(highscores);

		for (int i = 0; i < this.highscores.length; i++) {
			if (i < highscores.size()) {
				this.highscores[i].setHighscore(highscores.get(i));
			} else {
				this.highscores[i].setHighscore(Highscore.EMPTY_HIGHSCORE);
			}
		}
	}
}
