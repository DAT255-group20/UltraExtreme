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

package ultraextreme.model.enemy;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.enemyspawning.EnemySpawner;
import ultraextreme.model.util.Constants;

/**
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Johan Gronvall
 * 
 */
public class EnemyManager implements PropertyChangeListener {

	private final List<AbstractEnemy> enemies;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public EnemyManager() {
		enemies = new ArrayList<AbstractEnemy>();
	}

	public List<AbstractEnemy> getEnemies() {
		return enemies;
	}

	public void addEnemy(final AbstractEnemy enemy) {
		enemies.add(enemy);
		pcs.firePropertyChange(Constants.EVENT_NEW_ENTITY, null, enemy);

	}

	public void clearDeadEnemies() {
		for (int i = 0; i < enemies.size(); i++) {
			boolean remove = false;
			final AbstractEnemy e = enemies.get(i);
			if (e.isDead()) {
				pcs.firePropertyChange(Constants.EVENT_ENEMY_KILLED, null, e);
				remove = true;
			} else if (e.getShip().isOutOfScreen(300)) {
				remove = true;
			}
			if (remove) {
				removeEnemy(i);
				i--;
			}
		}
	}

	private void removeEnemy(int index) {
		pcs.firePropertyChange(Constants.EVENT_REMOVED_ENTITY, null,
				enemies.get(index));
		enemies.remove(index);
	}

	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(
			final PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}

	@Override
	public void propertyChange(final PropertyChangeEvent event) {
		// This is executed when an enemy spawner wants to add a new enemy.
		if (event.getPropertyName().equals(EnemySpawner.NEW_ENEMY)) {
			addEnemy((AbstractEnemy) event.getNewValue());
		}
	}

	public void clearAllEnemies() {
		for (int i = 0; i < enemies.size(); i++) {
			removeEnemy(i);
			i--;
		}
		enemies.clear();
	}
}
