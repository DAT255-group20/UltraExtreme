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

package ultraextreme.model.enemyspawning.wave;

import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * This is a enemy wave where the enemies spawn in a horizontal line. You set a
 * number of enemies that should spawn in a horizontal line, how many lines
 * there should be and where they should spawn. When run this class will spawn a
 * new horizontal line of enemies every 2 seconds until the specified number of
 * lines is met.
 * 
 * @author Daniel Jonsson
 * 
 */
public class RectangleWave extends AbstractWave {

	private float timer;

	private int lineCounter;

	private final int maxLines;

	private final int enemiesInLines;

	private final Rotation rotation;

	private final Position spawningPosition;

	private AbstractEnemyProvider enemyProvider;

	/**
	 * Create a new vertical enemy line.
	 * 
	 * @param enemiesInLines
	 *            How many enemies a horizontal line should consist of.
	 * @param lines
	 *            Number of horizontal lines.
	 * @param rotation
	 *            How much you want to rotate the line.
	 * @param x
	 *            X position where the enemies should spawn.
	 * @param y
	 *            Y position where the enemies should spawn.
	 * @param enemyProvider
	 *            The class that will create the RectangleWave its enemies.
	 */
	public RectangleWave(final int enemiesInLines, final int lines,
			final double rotation, final int x, final int y,
			final AbstractEnemyProvider enemyProvider) {
		timer = 2;
		lineCounter = 0;
		this.enemiesInLines = enemiesInLines;
		this.maxLines = lines;
		this.rotation = new Rotation(rotation);
		this.spawningPosition = new Position(x, y);
		this.enemyProvider = enemyProvider;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(final float timeElapsed) {
		timer += timeElapsed;
		if (timer >= 2) {
			spawnLine();
			timer -= 2;
			lineCounter++;
			if (lineCounter >= maxLines) {
				this.fireWaveEnded();
			}
		}
	}

	/**
	 * Spawn a new horizontal line.
	 */
	private void spawnLine() {
		for (int i = 0; i < enemiesInLines; i++) {
			Position p = new Position(spawningPosition.getX() + i * 75,
					spawningPosition.getY());
			fireNewEnemySpawned(enemyProvider.getEnemy(p, rotation));
		}
	}

}
