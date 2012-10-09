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

import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.IPlayer;
import ultraextreme.model.IPlayerListener;
import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Position;
import ultraextreme.util.Resources;
import ultraextreme.util.Resources.ResourceName;

/**
 * A text displaying the players current lives.
 * 
 * @author Viktor Anderling
 * 
 */
public class LivesText extends Text implements IPlayerListener {

	public LivesText(Position textPos, Font font, VertexBufferObjectManager vbo) {
		super((float) textPos.getX(), (float) textPos.getY(), font, Resources
				.getInstance().getResource(ResourceName.LIVES)
				+ " "
				+ Constants.getInitShipLives(), vbo);
	}

	@Override
	public void playerUpdate(IPlayer player) {
		setText(Resources.getInstance().getResource(ResourceName.LIVES) + " "
				+ player.getLives());
	}
}