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
import org.andengine.helperclasses.InputText;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.color.Color;

import ultraextreme.model.IUltraExtremeModel;
import ultraextreme.util.Resources;
import ultraextreme.util.Resources.ResourceName;

/**
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class GameOverScene extends MenuScene {

	public static final int GOTO_MENU = 0;

	private IUltraExtremeModel gameModel;
	private InputText nameInput;

	public GameOverScene(IUltraExtremeModel gameModel, final Camera camera,
			final Font font,
			final VertexBufferObjectManager vertexBufferObjectManager,
			BaseGameActivity activity) {
		super(camera);
		this.gameModel = gameModel;
		setBackground(new Background(0.9f, 0.1f, 0.1f));
		final IMenuItem gotoMenuButton = new TextMenuItem(GOTO_MENU, font,
				Resources.getInstance().getResource(ResourceName.GOTO_MENU),
				vertexBufferObjectManager);
		gotoMenuButton.setPosition(100, 100);
		gotoMenuButton.setColor(Color.BLACK);
		addMenuItem(gotoMenuButton);

		// TODO(plankton) Extract strings
		nameInput = new InputText(100, 300, "Highscore name",
				"Enter your name for the highscore list",
				SpriteFactory.getTextInputBackground(), font, 0, 0,
				vertexBufferObjectManager, activity);
		nameInput.setText(Resources.getInstance().getResource(
				ResourceName.DEFAULT_HIGHSCORE_NAME));
		nameInput.getChildByIndex(0).setColor(Color.BLACK); // Change text color
		this.attachChild(nameInput);
		this.registerTouchArea(nameInput);
	}

	/**
	 * @return the nameInput
	 */
	public String getName() {
		return nameInput.getText();
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return gameModel.getPlayer().getScore();
	}
}