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

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import ultraextreme.model.util.Position;
import ultraextreme.util.Resources;
import ultraextreme.util.Resources.ResourceName;

public class HighscoreScene extends MenuScene {

	public static final int GOTO_MENU = 0;
	public static final int CLEAR_LIST = 1;

	// TODO(plankton) Make these centered on the screen
	private static final Position GOTOMENU_BTN_POS = new Position(100, 100);
	private static final Position CLEARLIST_BTN_POS = new Position(100, 750);
	private static final Position HIGHSCORE_HEADER_POS = new Position(135, 160);
	private static final Position HIGHSCORE_LIST_POS = new Position(80, 220);
	private static final int HIGHSCORE_DISPERSION = 50;

	private HighscoreText[] highscores = new HighscoreText[10];

	public HighscoreScene(Camera camera, Font font,
			VertexBufferObjectManager vbo) {
		super(camera);
		setBackground(new Background(0.1f, 0.9f, 0.1f));

		final IMenuItem gotoMenuButton = new TextMenuItem(GOTO_MENU, font,
				Resources.getInstance().getResource(ResourceName.GOTO_MENU),
				vbo);
		gotoMenuButton.setPosition((float) GOTOMENU_BTN_POS.getX(),
				(float) GOTOMENU_BTN_POS.getY());
		gotoMenuButton.setColor(Color.BLUE);
		addMenuItem(gotoMenuButton);

		final IMenuItem clearListButton = new TextMenuItem(CLEAR_LIST, font,
				Resources.getInstance().getResource(
						ResourceName.CLEAR_HIGHSCORE), vbo);
		clearListButton.setPosition((float) CLEARLIST_BTN_POS.getX(),
				(float) CLEARLIST_BTN_POS.getY());
		clearListButton.setColor(Color.BLUE);
		addMenuItem(clearListButton);

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
