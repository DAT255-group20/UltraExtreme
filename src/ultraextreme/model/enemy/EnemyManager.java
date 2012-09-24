package ultraextreme.model.enemy;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.enemyspawning.EnemySpawner;

public class EnemyManager implements PropertyChangeListener {

	public static final String NEW_ENEMY = "n";

	private List<IEnemy> enemies;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public EnemyManager() {
		enemies = new ArrayList<IEnemy>();
	}

	public List<IEnemy> getEnemies() {
		return enemies;
	}

	public void addEnemy(IEnemy enemy) {
		enemies.add(enemy);
		pcs.firePropertyChange(EnemyManager.NEW_ENEMY, null, enemy);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		// This is executed when an enemy spawner wants to add a new enemy.
		if (event.getPropertyName().equals(EnemySpawner.NEW_ENEMY)) {
			addEnemy((IEnemy) event.getNewValue());
		}
	}
}
