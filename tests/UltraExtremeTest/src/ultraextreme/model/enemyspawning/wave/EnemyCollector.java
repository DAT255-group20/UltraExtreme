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

import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.enemy.AbstractEnemy;
import ultraextreme.model.enemy.IEnemy;

/**
 * This is only used in some Wave tests since a listener is needed.
 * 
 * @author Daniel Jonsson
 * 
 */
public class EnemyCollector implements IWaveListener {

	private boolean hasWaveEnded = false;

	private List<IEnemy> enemies = new ArrayList<IEnemy>();

	@Override
	public void enemySpawned(AbstractEnemy enemy) {
		enemies.add(enemy);
	}

	public List<IEnemy> getSpawnedEnemies() {
		return this.enemies;
	}

	public boolean hasWaveEnded() {
		return hasWaveEnded;
	}

	@Override
	public void waveEnded(AbstractWave wave) {
		this.hasWaveEnded = true;
	}

}
