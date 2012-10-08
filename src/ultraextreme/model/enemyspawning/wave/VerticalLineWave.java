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

import ultraextreme.model.enemy.BasicEnemy;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * This is a enemy wave where the enemies spawn in a vertical line. You set a
 * number of enemies and where they should spawn, then is a new enemy spawned
 * every 2 second until the max number of enemies is reach.
 * 
 * @author Daniel Jonsson
 * 
 */
public class VerticalLineWave extends AbstractWave {

	private float timer;

	private int enemyCounter;

	private final int numberOfEnemies;

	private final Rotation rotation;

	private final Position spawningPositon;

	/**
	 * Create a new vertical enemy line.
	 * 
	 * @param numberOfEnemies
	 *            Number of enemies in the line.
	 * @param rotation
	 *            How much you want to rotate the line.
	 * @param x
	 *            X position where the enemies should spawn.
	 * @param y
	 *            Y position where the enemies should spawn.
	 * @param bulletManager
	 *            Reference to a bullet manager so the enemies can be created.
	 */
	public VerticalLineWave(final int numberOfEnemies, final double rotation,
			final int x, final int y, final BulletManager bulletManager) {
		super(bulletManager);
		timer = 2;
		enemyCounter = 0;
		this.numberOfEnemies = numberOfEnemies;
		this.rotation = new Rotation(rotation);
		this.spawningPositon = new Position(x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(final float timeElapsed) {
		timer += timeElapsed;
		if (timer >= 2) {
			fireNewEnemySpawned(new BasicEnemy(spawningPositon.getX(),
					spawningPositon.getY(), rotation, this.bulletManager));
			timer -= 2;
			enemyCounter++;
			if (enemyCounter >= numberOfEnemies) {
				this.fireWaveEnded();
			}
		}
	}
}
