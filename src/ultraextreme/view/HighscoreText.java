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
import org.andengine.util.color.Color;

import ultraextreme.model.util.Position;

public class HighscoreText extends Text {

	private final int index;

	public HighscoreText(Position textPos, Font font,
			VertexBufferObjectManager vbo, int index) {
		super((float) textPos.getX(), (float) textPos.getY(), font,
				"                       ", vbo);
		this.setColor(Color.BLACK);
		this.index = index;
		this.setHighscore(Highscore.EMPTY_HIGHSCORE);
	}

	public final void setHighscore(Highscore highscore) {
		this.setText("" + index + ".  |  " + highscore.getName() + "  |  "
				+ highscore.getScore());
	}
}
