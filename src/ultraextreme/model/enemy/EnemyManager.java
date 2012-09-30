package ultraextreme.model.enemy;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.enemyspawning.EnemySpawner;

public class EnemyManager implements PropertyChangeListener {

	public static final String NEW_ENEMY = "add";

	private final List<IEnemy> enemies;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public EnemyManager() {
		enemies = new ArrayList<IEnemy>();
	}

	public List<IEnemy> getEnemies() {
		return enemies;
	}

	public void addEnemy(final IEnemy enemy) {
		enemies.add(enemy);
		pcs.firePropertyChange(EnemyManager.NEW_ENEMY, null, enemy.getShip());
	}

	public void clearDeadEnemies() {
		for (int i = 0; i < enemies.size(); i++) {
			final IEnemy e = enemies.get(i);
			if (e.isDead() || e.getShip().isOutOfScreen(150) ) {
				pcs.firePropertyChange("remove", null, e);
				enemies.remove(i);
				i--;
			}
		}
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
			addEnemy((IEnemy) event.getNewValue());
		}
	}
}
