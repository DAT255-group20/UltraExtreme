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

package ultraextreme.model.enemyspawning;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.enemy.IEnemy;

/**
 * Used in the EnemySpawnerTest to collect the spawned enemies.
 * 
 * @author Daniel Jonsson
 * 
 */
public class EnemyCollector implements PropertyChangeListener {

	private List<IEnemy> enemies;

	public EnemyCollector() {
		enemies = new ArrayList<IEnemy>();
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals(EnemySpawner.NEW_ENEMY)) {
			enemies.add((IEnemy) event.getNewValue());
		}
	}

	public List<IEnemy> getSpawnedEnemies() {
		return enemies;
	}

}
